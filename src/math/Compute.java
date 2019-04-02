package math;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * @author Wicmage
 */

public class Compute {
	
	/*
	 * Buggy as hell
	 */

	public Map<Double[], Double> results = new HashMap<Double[],Double>();
	public String[] fData = new String[3];
	
	public Compute(String function, Map<String, double[]> varData) throws Exception {
		Map<String, double[]> varCopy = new HashMap<String, double[]>();
		varCopy.putAll(varData);
		String[] vars = varCopy.keySet().toArray(new String[1]);
		int nVars=vars.length;

		//TODO Can't compute single variables
		
		String interior = function.substring(2,function.length()-1);
		List<List<Integer>> par = Constants.locateParenthesis(interior);
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
			if(Constants.containsToken(parts[0]))
				parts[0]=parts[0].replace("PARENTHESIS"+j, "(" + parenthesis.get(j) + ")");
			else
				parts[0]=parts[0].replace("PARENTHESIS"+j, parenthesis.get(j));
			if(Constants.containsToken(parts[1]))
				parts[1]=parts[1].replace("PARENTHESIS"+j, "(" + parenthesis.get(j) + ")");
			else
				parts[1]=parts[1].replace("PARENTHESIS"+j, parenthesis.get(j));
		}
		
		fData[0]=Character.toString(function.charAt(0));
		fData[1]=parts[0];
		fData[2]=parts[1];
		
		if(nVars==1)
			var1(varCopy,vars);
		else if(nVars==2)
			var2(varCopy,vars);
		else throw new Exception();
	}
	
	//TODO 2 variables compute is not complete
	public void var2(Map<String, double[]> varCopy, String[] vars) {
		while(varCopy.get(vars[0])[0] <= varCopy.get(vars[0])[1]) {
			while(varCopy.get(vars[1])[0] <= varCopy.get(vars[1])[1]) {
				
			}
		}
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
	
	//TODO this shows the thing but is not sorted
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
	
	//TODO this works but is not efficient
	public static Double operation(char opType, String arg1, String arg2) {
		double res;
		
		if(opType=='+') {
			if(arg1.contains("(")) {
				String interior = arg1.substring(2,arg1.length()-1);
				List<List<Integer>> par = Constants.locateParenthesis(interior);
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
					if(Constants.containsToken(parts[0]))
						parts[0]=parts[0].replace("PARENTHESIS"+j, "(" + parenthesis.get(j) + ")");
					else
						parts[0]=parts[0].replace("PARENTHESIS"+j, parenthesis.get(j));
					if(Constants.containsToken(parts[1]))
						parts[1]=parts[1].replace("PARENTHESIS"+j, "(" + parenthesis.get(j) + ")");
					else
						parts[1]=parts[1].replace("PARENTHESIS"+j, parenthesis.get(j));
				}
				arg1=operation(arg1.charAt(0),parts[0],parts[1]).toString();
			}
			if(arg2.contains("(")) {
				String interior = arg2.substring(2,arg2.length()-1);
				List<List<Integer>> par = Constants.locateParenthesis(interior);
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
					if(Constants.containsToken(parts[0]))
						parts[0]=parts[0].replace("PARENTHESIS"+j, "(" + parenthesis.get(j) + ")");
					else
						parts[0]=parts[0].replace("PARENTHESIS"+j, parenthesis.get(j));
					if(Constants.containsToken(parts[1]))
						parts[1]=parts[1].replace("PARENTHESIS"+j, "(" + parenthesis.get(j) + ")");
					else
						parts[1]=parts[1].replace("PARENTHESIS"+j, parenthesis.get(j));
				}
				arg2=operation(arg2.charAt(0),parts[0],parts[1]).toString();
			}
			res=Double.parseDouble(arg1)+Double.parseDouble(arg2);
		} else if(opType=='-') {
			if(arg1.contains("(")) {
				String interior = arg1.substring(2,arg1.length()-1);
				List<List<Integer>> par = Constants.locateParenthesis(interior);
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
					if(Constants.containsToken(parts[0]))
						parts[0]=parts[0].replace("PARENTHESIS"+j, "(" + parenthesis.get(j) + ")");
					else
						parts[0]=parts[0].replace("PARENTHESIS"+j, parenthesis.get(j));
					if(Constants.containsToken(parts[1]))
						parts[1]=parts[1].replace("PARENTHESIS"+j, "(" + parenthesis.get(j) + ")");
					else
						parts[1]=parts[1].replace("PARENTHESIS"+j, parenthesis.get(j));
				}
				arg1=operation(arg1.charAt(0),parts[0],parts[1]).toString();
			}
			if(arg2.contains("(")) {
				String interior = arg2.substring(2,arg2.length()-1);
				List<List<Integer>> par = Constants.locateParenthesis(interior);
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
					if(Constants.containsToken(parts[0]))
						parts[0]=parts[0].replace("PARENTHESIS"+j, "(" + parenthesis.get(j) + ")");
					else
						parts[0]=parts[0].replace("PARENTHESIS"+j, parenthesis.get(j));
					if(Constants.containsToken(parts[1]))
						parts[1]=parts[1].replace("PARENTHESIS"+j, "(" + parenthesis.get(j) + ")");
					else
						parts[1]=parts[1].replace("PARENTHESIS"+j, parenthesis.get(j));
				}
				arg2=operation(arg2.charAt(0),parts[0],parts[1]).toString();
			}
			res=Double.parseDouble(arg1)-Double.parseDouble(arg2);
		} else if(opType=='*') {
			if(arg1.contains("(")) {
				String interior = arg1.substring(2,arg1.length()-1);
				List<List<Integer>> par = Constants.locateParenthesis(interior);
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
					if(Constants.containsToken(parts[0]))
						parts[0]=parts[0].replace("PARENTHESIS"+j, "(" + parenthesis.get(j) + ")");
					else
						parts[0]=parts[0].replace("PARENTHESIS"+j, parenthesis.get(j));
					if(Constants.containsToken(parts[1]))
						parts[1]=parts[1].replace("PARENTHESIS"+j, "(" + parenthesis.get(j) + ")");
					else
						parts[1]=parts[1].replace("PARENTHESIS"+j, parenthesis.get(j));
				}
				arg1=operation(arg1.charAt(0),parts[0],parts[1]).toString();
			}
			if(arg2.contains("(")) {
				String interior = arg2.substring(2,arg2.length()-1);
				List<List<Integer>> par = Constants.locateParenthesis(interior);
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
					if(Constants.containsToken(parts[0]))
						parts[0]=parts[0].replace("PARENTHESIS"+j, "(" + parenthesis.get(j) + ")");
					else
						parts[0]=parts[0].replace("PARENTHESIS"+j, parenthesis.get(j));
					if(Constants.containsToken(parts[1]))
						parts[1]=parts[1].replace("PARENTHESIS"+j, "(" + parenthesis.get(j) + ")");
					else
						parts[1]=parts[1].replace("PARENTHESIS"+j, parenthesis.get(j));
				}
				arg2=operation(arg2.charAt(0),parts[0],parts[1]).toString();
			}
			res=Double.parseDouble(arg1)*Double.parseDouble(arg2);
		} else {
			if(arg1.contains("(")) {
				String interior = arg1.substring(2,arg1.length()-1);
				List<List<Integer>> par = Constants.locateParenthesis(interior);
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
					if(Constants.containsToken(parts[0]))
						parts[0]=parts[0].replace("PARENTHESIS"+j, "(" + parenthesis.get(j) + ")");
					else
						parts[0]=parts[0].replace("PARENTHESIS"+j, parenthesis.get(j));
					if(Constants.containsToken(parts[1]))
						parts[1]=parts[1].replace("PARENTHESIS"+j, "(" + parenthesis.get(j) + ")");
					else
						parts[1]=parts[1].replace("PARENTHESIS"+j, parenthesis.get(j));
				}
				arg1=operation(arg1.charAt(0),parts[0],parts[1]).toString();
			}
			if(arg2.contains("(")) {
				String interior = arg2.substring(2,arg2.length()-1);
				List<List<Integer>> par = Constants.locateParenthesis(interior);
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
					if(Constants.containsToken(parts[0]))
						parts[0]=parts[0].replace("PARENTHESIS"+j, "(" + parenthesis.get(j) + ")");
					else
						parts[0]=parts[0].replace("PARENTHESIS"+j, parenthesis.get(j));
					if(Constants.containsToken(parts[1]))
						parts[1]=parts[1].replace("PARENTHESIS"+j, "(" + parenthesis.get(j) + ")");
					else
						parts[1]=parts[1].replace("PARENTHESIS"+j, parenthesis.get(j));
				}
				arg2=operation(arg2.charAt(0),parts[0],parts[1]).toString();
			}
			res=Double.parseDouble(arg1)/Double.parseDouble(arg2);
		}
		
		return res;
	}
}
