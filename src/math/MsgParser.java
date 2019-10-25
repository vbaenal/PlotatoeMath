package math;

import java.util.ArrayList;
import java.util.List;

/*
 * @author Wicmage
 */

public class MsgParser {
	
	public double start, end, step;
	public String function;
	public String[] options;
	
	public MsgParser(String[] msg) throws Exception {
		if(msg.length<4) throw new Exception();
		
		List<String> auxFData = new ArrayList<String>();
		List<String> auxOptions = new ArrayList<String>();
		for(int i=0;i<msg.length;i++) {
			if(msg[i].charAt(0)=='#')
				auxOptions.add(msg[i].substring(1));
			else
				auxFData.add(msg[i]);
		}
		
		String[] functionData = Common.toArray(auxFData);
		this.options = Common.toArray(auxOptions);
		
		if(functionData.length<4) throw new Exception();

		
		//0 is function, 1 start, 2 end, 3 step
		function = functionData[0].toLowerCase().replace(" ", "")+"*1";
		start=Double.parseDouble(functionData[1]);
		end=Double.parseDouble(functionData[2]);
		step=Double.parseDouble(functionData[3]);
		
		//Some constraints
		//There should be at least 1 step
		if(end-start<step) throw new Exception();
		//Step can't be negative or very low number
		if(step<0.00001d) throw new Exception();
	}
}
