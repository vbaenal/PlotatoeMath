package math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * @author Wicmage
 */

public class Common {

	public final static String[] SINGLE_TOKENS = {"-","log","sin","tan"}; 
	public final static String[] BINARY_TOKENS = {"+","*","/","^"};
	
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
	
	public static boolean containsSingleToken(String arg) {
		for(int i=0;i<SINGLE_TOKENS.length;i++)
			if(arg.contains(SINGLE_TOKENS[i]))
				return true;
		return false;
	}	
	
	public static boolean containsBinaryToken(String arg) {
		for(int i=0;i<BINARY_TOKENS.length;i++)
			if(arg.contains(BINARY_TOKENS[i]))
				return true;
		return false;
	}
	
	public static String[] toArray(List<String> list) {
		String[] array = new String[list.size()];
		for(int i=0;i<list.size();i++)
			array[i] = list.get(i);
		return array;
	}
	
	public static <T> boolean arrayContains(T[] array, T elem) {
		for(int i=0;i<array.length;i++)
			if(elem.equals(array[i]))
				return true;
		return false;
	}
}
