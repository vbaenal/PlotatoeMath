package math;

/*
 * author @Wicmage
 */

public class Test {

	public static void main(String[] args) {
		String msg="f(x)=(x+2);x<-[0,10000,1]";
		System.out.println(msg);
		try {
			String sentMessage;
			MsgParser mp = new MsgParser(msg);
			System.out.println("MsgParser Done");
			FunctionParser fp = new FunctionParser(mp.equation);
			System.out.println("FunctionParser Done");
			Compute c = new Compute(fp.toString(),mp.varData);
			sentMessage="Function: " + mp.functionName + 
					mp.varData.keySet() + " = " + fp.toString() + "\n";
			for(String var : mp.varData.keySet()) {
				sentMessage+="\nVariable: " + var + ", First Point: " + 
					mp.varData.get(var)[0] + ", End Point: " +
					mp.varData.get(var)[1] + ", Delta: " + mp.varData.get(var)[2];
				
				sentMessage+="\n"+c.toString();
			}
			System.out.println(sentMessage);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
