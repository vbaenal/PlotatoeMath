package com.plotatoe;
import java.awt.image.BufferedImage;

import com.plotatoe.math.Compute;
import com.plotatoe.math.Drawer;
import com.plotatoe.math.FunctionParser;
import com.plotatoe.math.MsgParser;


public class Plotatoe {
	
	public static Compute results(String[] msg) {
		MsgParser mp = null;
		FunctionParser fp = null;
		try {
			mp = new MsgParser(msg);
			fp = new FunctionParser(mp.function);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Compute(fp,mp);
	}

	public static BufferedImage getPlotAsBufferedImage(String[] msg, int type) {
		BufferedImage result = null;
		try {
			Compute c = results(msg);
			c.compute();
			result = new BufferedImage(500,500,BufferedImage.TYPE_INT_RGB);
			
			switch(type) {
			case 0:
				Drawer.plot(c, result);				
				break;
			case 1: Drawer.projective(c, result);
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
