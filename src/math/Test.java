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
		String[] msg= {"1/x", "-1", "2", "0.3"};
		try {
			String sentMessage;
			MsgParser mp = new MsgParser(msg);
			System.out.println("MsgParser Done");
			FunctionParser fp = new FunctionParser(mp.function);
			System.out.println("FunctionParser Done");
			System.out.println(fp.toString());
			Compute c = new Compute(fp,mp);
			c.compute();
			
			if(!Common.arrayContains(mp.options, "noplot")) {
				BufferedImage result = new BufferedImage(500,500,BufferedImage.TYPE_INT_RGB);
				Drawer.plot(mp, c, result);
				File f = new File("test.png");
				ImageIO.write(result,"png",f);
			} else {
				sentMessage="Function: f(x) = " + fp.toString() +
						"\n\tFirst Point: " + mp.start + 
						"\n\tEnd Point: " + mp.end + 
						"\n\tStep: " + mp.step;
				sentMessage+="\n"+c.toString();
				System.out.println(sentMessage);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}	
}
