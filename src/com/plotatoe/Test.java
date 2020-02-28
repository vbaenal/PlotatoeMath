package com.plotatoe;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import com.plotatoe.math.Compute;

/*
 * author @Wicmage
 */

public class Test {

	public static void main(final String[] args) {
    	final String[] msg = { "x^2", "-5", "5", "0.1" }; 
		try {
			// Plot test
			BufferedImage result = Plotatoe.getPlot(msg.clone());
			File plot = new File("plot.png");
			ImageIO.write(result, "png", plot);
			
			// Projective test
			result = Plotatoe.getProjectivePlot(msg.clone());
			File projective = new File("projective.png");
			ImageIO.write(result, "png", projective);
			
			// Text test
			Compute computed = Plotatoe.results(msg.clone());
			System.out.println(computed.toString());
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}	
}
