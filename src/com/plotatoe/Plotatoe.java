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
		Compute res = new Compute(fp, mp);
		try {
			res.compute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public static BufferedImage getPlotAsBufferedImage(String[] msg, int type) {
		BufferedImage result = null;
		try {
			Compute c = results(msg);
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

	public static BufferedImage getProjectivePlot(String[] msg) {
		return getPlotAsBufferedImage(msg, 1);
	}

	public static BufferedImage getPlot(String[] msg) {
		return getPlotAsBufferedImage(msg, 0);
	}
}
