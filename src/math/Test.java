package math;

/*
 * author @Wicmage
 */

public class Test {

	public static void main(String[] args) {
		//TODO If you do (a+b)*(a-b) (or any other operations with a and b in this order) the program doesn't work
		String msg="x+2*(x+3);x<-[1,2,1]";
		System.out.println(msg);
		try {
			String sentMessage;
			MsgParser mp = new MsgParser(msg);
			System.out.println("MsgParser Done");
			FunctionParser fp = new FunctionParser(mp.function);
			System.out.println(fp);
			System.out.println("FunctionParser Done");
			Compute c = new Compute(fp,mp.varData);
			c.compute();
			sentMessage="Function: f"  + mp.varData.keySet() + " = " + fp.toString() + "\n";
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
		
		//TODO Plot the results
	}
}
