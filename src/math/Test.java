package math;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

/*
 * author @Wicmage
 */

public class Test {

	public static void main(String[] args) {
		//TODO If you do (a+b)*(a-b) (or any other operations with a and b in this order) the program doesn't work
		String msg="1/x;x<-[0.05,1,0.05]";
		System.out.println(msg);
		try {
			String sentMessage;
			MsgParser mp = new MsgParser(msg);
			System.out.println("MsgParser Done");
			FunctionParser fp = new FunctionParser(mp.function);
			System.out.println("FunctionParser Done");
			Compute c = new Compute(fp,mp.varData);
			c.compute();
			sentMessage="Function: f"  + mp.varData.keySet() + " = " + fp.toString() + "\n";
			for(String var : mp.varData.keySet()) {
				sentMessage+="\nVariable: " + var + ", First Point: " + 
					mp.start + ", End Point: " +
					mp.varData.get(var)[1] + ", Delta: " + mp.varData.get(var)[2];
				
				sentMessage+="\n"+c.toString();
			}
			System.out.println(sentMessage);
			
			BufferedImage result = new BufferedImage(500,500,BufferedImage.TYPE_INT_RGB);
			Drawer.plot(mp, c, result);
			File f = new File("test.png");
			ImageIO.write(result,"png",f);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
