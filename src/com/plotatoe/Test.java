package com.plotatoe;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

/*
 * author @Wicmage
 */

public class Test {

	public static void main(final String[] args) {
    final String[] msg = { "(x-2)*(x+2)", "-2", "2", "0.3" }; 
		try {
			BufferedImage result = Plotatoe.getPlotAsBufferedImage(msg,1);
			final File f = new File("test.png");
			ImageIO.write(result, "png", f);
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}	
}
