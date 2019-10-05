package math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * @author Wicmage
 */

public class Common {

	public final static String TOKENS="+\\-*/";
	
	public static List<List<Integer>> locateParenthesis(String arg) {
		String f=new String(arg);
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		List<Integer> parData = new ArrayList<Integer>(Arrays.asList(0,0));
		for(int i=0;f.contains("(") || f.contains(")");i++) {
			res.add(new ArrayList<Integer>(parData));
			int par=0;
			for(int j=0;j<f.length();j++) {
				if(f.charAt(j)=='(') {
					if(par==0)
						//First parenthesis
						res.get(i).set(0, j);
					par++;
				}
				else if(f.charAt(j)==')') {
					par--;
					if(par==0) {
						//Second parenthesis
						res.get(i).set(1, j);
						break;
					}
				}
			}
			String p=f.substring(res.get(i).get(0)+1, res.get(i).get(1));
			f=f.replace("("+p+")", "PARENTHESISH");
		}
		
		return res;
	}
	
	public static boolean containsToken(String arg) {
		for(int i=0;i<TOKENS.length();i++) {
			if(arg.contains(Character.toString(TOKENS.charAt(i))))
				return true;
		}
		return false;
	}
	
}