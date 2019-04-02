package math;

import java.util.HashMap;
import java.util.Map;

/*
 * @author Wicmage
 */

public class MsgParser {
	
	public Map<String, double[]> varData;
	public String functionName;
	public String equation;
	
	public MsgParser(String msg) throws Exception {
		// Message to lower case. That should prevent some conflicts.
		String message = msg.toLowerCase();
		message=message.replace(" ", "");
		String[] msgData = message.split(";");
		//Just two parameters should be given separated with semicolons
		if(msgData.length!=2) throw new Exception();
			
		//Position 0 has the function
		String function = msgData[0];
		//Position 1 has the intervals + delta (distance between points)
		String[] intervals = msgData[1].split("\\&");
			
		//We want to get the equation as well as variables
		String[] fData = function.split("=");
		this.functionName = fData[0].split("\\(")[0];
		String[] fVarsArray = fData[0].split("\\(")[1].replace(")", "").split(",");
		this.equation = fData[1];
		
		String[] iVarsArray = new String[intervals.length];
		
		for(int i=0;i<fVarsArray.length-1;i++) {
			for(int j=i+1;j<fVarsArray.length;j++) {
				if(fVarsArray[i]==fVarsArray[j]) throw new Exception();
			}
		}
		
		varData = new HashMap<String, double[]>();
		for(int i=0;i<intervals.length;i++) {
			iVarsArray[i]=intervals[i].split("<-")[0];
			//aux[0]=a;aux[1]=b;aux[2]=delta
			String[] aux = intervals[i].split("<-")[1].split(",");
			//delete brackets
			aux[0]=aux[0].replace("[", "");
			aux[2]=aux[2].replace("]", "");
			double[] doubleAux = new double[3];
			//Parse string to double
			for(int j=0;j<3;j++)
				doubleAux[j] = Double.parseDouble(aux[j]);
			//b-a>delta
			if(doubleAux[1]-doubleAux[0]<=doubleAux[2]) throw new Exception();
			//delta>0
			if(doubleAux[2]<=0d) throw new Exception();
			varData.put(iVarsArray[i],doubleAux);
			
			//variables declared in function must be the same as variables declared on intervals
			if(!iVarsArray[i].equals(fVarsArray[i])) throw new Exception();
		}
		for(int i=0;i<fVarsArray.length;i++) {
			//same as above
			if(!fVarsArray[i].equals(iVarsArray[i])) throw new Exception();
		}
	}
}
