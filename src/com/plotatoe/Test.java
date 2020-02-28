package com.plotatoe;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

/*
 * author @Wicmage
 */

public class Test {

	public static void main(final String[] args) {
		// TODO If you do (a+b)*(a-b) (or any other operations with a and b in this
		// order) the program doesn't work
		final String[] msg = { "1/x", "-2", "2", "0.01" };
		try {
			BufferedImage result = Plotatoe.getPlotAsBufferedImage(msg,1);
			final File f = new File("test.png");
			ImageIO.write(result, "png", f);
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}	
}
