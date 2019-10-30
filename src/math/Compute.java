package math;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * @author Wicmage
 */

public class Compute {

	public Map<Double, Double> results = new HashMap<Double,Double>();
	public String[] fData = new String[3];
	
	private FunctionParser fp;
	private double[] varData = new double[3];
	
	public Compute(FunctionParser fp, MsgParser mp) {
		this.fp = fp;
		this.varData[0]=mp.start;
		this.varData[1]=mp.end;
		this.varData[2]=mp.step;
	}
	
	public void compute() throws Exception {
		String function = fp.toString();
		
		String interior = function.substring(2,function.length()-1);
		List<List<Integer>> par = Common.locateParenthesis(interior);
		List<String> parenthesis=new ArrayList<String>();
		for(List<Integer> li : par) {
			if(li.get(0)!=li.get(1)) {
				String p=interior.substring(li.get(0)+1, li.get(1));
				parenthesis.add(p);
				interior=interior.replace("("+p+")", "PARENTHESIS"+par.indexOf(li));
			}
		}
		String[] parts = interior.split("\\,",2);
		for(int j=0;j<parenthesis.size();j++) {
			if(Common.containsBinaryToken(parts[0]))
				parts[0]=parts[0].replace("PARENTHESIS"+j, "(" + parenthesis.get(j) + ")");
			else
				parts[0]=parts[0].replace("PARENTHESIS"+j, parenthesis.get(j));
			if(Common.containsBinaryToken(parts[1]))
				parts[1]=parts[1].replace("PARENTHESIS"+j, "(" + parenthesis.get(j) + ")");
			else
				parts[1]=parts[1].replace("PARENTHESIS"+j, parenthesis.get(j));
		}
		
		fData[0]=Character.toString(function.charAt(0));
		fData[1]=parts[0];
		fData[2]=parts[1];
		
		var1(varData);
	}
	
	public void var1(double[] varData) {
		while(varData[0]<=varData[1]) {
			double aux=varData[0];
			String d1=fData[1].replace("x", Double.toString(aux));
			String d2=fData[2].replace("x", Double.toString(aux));
			results.put(aux, operation(Character.toString(fData[0].charAt(0)),d1,d2));
			varData[0]+=varData[2];
		}
	}
	
	public String toString() {
		String res="";
		for(Double da : results.keySet()) {
			res+="[";
			res+=new DecimalFormat("#.##").format(da);
			res+="] = " + new DecimalFormat("#.##").format(results.get(da)) + "\n";
		}
		return res;
	}
	
	public static Double prepareArg(String arg) {
		if(arg.contains("(")) {
			String interior = arg.substring(2,arg.length()-1);
			List<List<Integer>> par = Common.locateParenthesis(interior);
			List<String> parenthesis=new ArrayList<String>();
			for(List<Integer> li : par) {
				if(li.get(0)!=li.get(1)) {
					String p=interior.substring(li.get(0)+1, li.get(1));
					parenthesis.add(p);
					interior=interior.replace("("+p+")", "PARENTHESIS"+par.indexOf(li));
				}
			}
			String[] parts = interior.split("\\,",2);
			for(int j=0;j<parenthesis.size();j++) {
				if(Common.containsBinaryToken(parts[0]))
					parts[0]=parts[0].replace("PARENTHESIS"+j, "(" + parenthesis.get(j) + ")");
				else
					parts[0]=parts[0].replace("PARENTHESIS"+j, parenthesis.get(j));
				if(Common.containsBinaryToken(parts[1]))
					parts[1]=parts[1].replace("PARENTHESIS"+j, "(" + parenthesis.get(j) + ")");
				else
					parts[1]=parts[1].replace("PARENTHESIS"+j, parenthesis.get(j));
			}
			arg=operation(Character.toString(arg.charAt(0)),parts[0],parts[1]).toString();
		}
		return Double.parseDouble(arg);
	}
	
	public static Double operation(String opType, String arg1, String arg2) {
		Double dArg1 = prepareArg(arg1);
		Double dArg2 = prepareArg(arg2);
		switch(opType) {
		case "+":
			return dArg1+dArg2;
		case "*":
			return dArg1*dArg2;
		case "/":
			if(dArg2!=0) 
				return dArg1/dArg2;
			else
				return Double.MAX_VALUE;
		case "^":
			return Math.pow(dArg1, dArg2);
		}
		return null;
	}
}