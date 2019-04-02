package math;

import java.util.ArrayList;
import java.util.List;

/*
 * @author Wicmage
 */

public class FunctionParser {

	private enum Type {VAR, NUMBER, FUNCTION};
	
	private Type type = Type.FUNCTION;
	private String variable;
	private double number;
	private String function;
	
	// Obtains the function and decomposes it to a tree form
	public FunctionParser(String f) {
		char[] t = Constants.TOKENS.toCharArray();
		
		// Locate parenthesis
		List<List<Integer>> parData = Constants.locateParenthesis(f);
		this.function=f;
		
		List<String> parenthesis=new ArrayList<String>();
		for(List<Integer> li : parData) {
			if(li.get(0)!=li.get(1)) {
				String p=function.substring(li.get(0)+1, li.get(1));
				parenthesis.add(p);
				function=function.replace("("+p+")", "PARENTHESIS"+parData.indexOf(li));
			}
		}
		
		boolean n=true;
		int i=0;
		for(;i<t.length;i++) {
			if(function.contains(Character.toString(t[i]))) {
				n=false;
				break;
			}
		}
		
		if(n) {
			try {
				this.type=Type.NUMBER;
				number = Double.parseDouble(function);
			} catch(NumberFormatException e) {
				this.type=Type.VAR;
				variable = function;
			}
		} else {
			String[] parts = function.split("\\"+Character.toString(t[i]),2);
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
			this.function = Character.toString(t[i]) + "(";
			this.function+=new FunctionParser(parts[0]).toString()+","
					+ new FunctionParser(parts[1]).toString()+")";
		}
	}
	
	public String toString() {
		switch(type) {
		case NUMBER:
			return Double.toString(number);
		case VAR:
			return variable;
		default:
			return function;
		}
	}
	
}
