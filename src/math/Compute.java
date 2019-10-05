package math;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * @author Wicmage
 */

public class Compute {

	public Map<Double[], Double> results = new HashMap<Double[],Double>();
	private Map<String, double[]> varCopy = new HashMap<String, double[]>();
	public String[] fData = new String[3];
	private FunctionParser fp;
	
	public Compute(FunctionParser fp, Map<String, double[]> varData) {
		this.fp = fp;
		this.varCopy.putAll(varData);
	}
	
	public void compute() throws Exception {
		String function = fp.toString();
		String[] vars = varCopy.keySet().toArray(new String[1]);
		int nVars=vars.length;
		
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
			if(Common.containsToken(parts[0]))
				parts[0]=parts[0].replace("PARENTHESIS"+j, "(" + parenthesis.get(j) + ")");
			else
				parts[0]=parts[0].replace("PARENTHESIS"+j, parenthesis.get(j));
			if(Common.containsToken(parts[1]))
				parts[1]=parts[1].replace("PARENTHESIS"+j, "(" + parenthesis.get(j) + ")");
			else
				parts[1]=parts[1].replace("PARENTHESIS"+j, parenthesis.get(j));
		}
		
		fData[0]=Character.toString(function.charAt(0));
		fData[1]=parts[0];
		fData[2]=parts[1];
		
		if(nVars==1)
			var1(varCopy,vars);
		else throw new Exception();
	}
	
	public void var1(Map<String, double[]> varCopy, String[] vars) {
		while(varCopy.get(vars[0])[0]<=varCopy.get(vars[0])[1]) {
			Double[] var = new Double[1];
			var[0]=varCopy.get(vars[0])[0];
			String d1=fData[1].replace(vars[0], Double.toString(var[0]));
			String d2=fData[2].replace(vars[0], Double.toString(var[0]));
			results.put(var, operation(fData[0].charAt(0),d1,d2));
			varCopy.get(vars[0])[0]+=varCopy.get(vars[0])[2];
		}
	}
	
	public String toString() {
		String res="";
		for(Double[] da : results.keySet()) {
			res+="[";
			for(int i=0;i<da.length;i++) {
				res+=da[i];
				if(i==0 && da.length==2)
					res+=",";
			}
			res+="] = " + results.get(da) + "\n";
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
				if(Common.containsToken(parts[0]))
					parts[0]=parts[0].replace("PARENTHESIS"+j, "(" + parenthesis.get(j) + ")");
				else
					parts[0]=parts[0].replace("PARENTHESIS"+j, parenthesis.get(j));
				if(Common.containsToken(parts[1]))
					parts[1]=parts[1].replace("PARENTHESIS"+j, "(" + parenthesis.get(j) + ")");
				else
					parts[1]=parts[1].replace("PARENTHESIS"+j, parenthesis.get(j));
			}
			arg=operation(arg.charAt(0),parts[0],parts[1]).toString();
		}
		return Double.parseDouble(arg);
	}
	
	public static Double operation(char opType, String arg1, String arg2) {
		Double dArg1 = prepareArg(arg1);
		Double dArg2 = prepareArg(arg2);
		switch(opType) {
		case '+':
			return dArg1+dArg2;
		case '-':
			return dArg1-dArg2;
		case '*':
			return dArg1*dArg2;
		case '/':
			return dArg1/dArg2;
		}
		return null;
	}
}
