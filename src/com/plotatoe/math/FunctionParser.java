package com.plotatoe.math;

import java.util.ArrayList;
import java.util.List;

/*
 * @author Wicmage
 */

public class FunctionParser {

	private String function;
	
	// Obtains the function and decomposes it to a tree form
	public FunctionParser(String f) {
		f=f.toLowerCase();
		f=f.replace("e", Constants.E);
		f=f.replace("pi", Constants.PI);
		f=f.replace("tau", Constants.TAU);
		f=f.replace("phi", Constants.PHI);
		
		// Locate parenthesis
		List<List<Integer>> parData = Common.locateParenthesis(f);
		this.function=f;
		
		List<String> parenthesis=new ArrayList<String>();
		for(List<Integer> li : parData) {
			if(li.get(0)!=li.get(1)) {
				String p=function.substring(li.get(0)+1, li.get(1));
				parenthesis.add(p);
				function=function.replace("("+p+")", "PARENTHESIS"+parData.indexOf(li));
			}
		}
		
		boolean hasBinaryToken=false;
		int i=0;
		for(;i<Common.BINARY_TOKENS.length;i++)
			if(function.contains(Common.BINARY_TOKENS[i])) {
				hasBinaryToken=true;
				break;
			}
		
		if(hasBinaryToken) {
			String[] parts = function.split("\\"+Common.BINARY_TOKENS[i],2);
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
			this.function = Common.BINARY_TOKENS[i] + "(";
			this.function+=new FunctionParser(parts[0]).toString() + "," + new FunctionParser(parts[1]).toString()+")";
		}
	}
	
	public String toString() {
		return function;
	}	
}