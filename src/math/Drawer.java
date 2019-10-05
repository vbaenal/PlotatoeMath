package math;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Drawer{
	
	public static void plot(MsgParser mp, Compute c, BufferedImage bi) {
		double start, end;
		start = mp.start;
		end = mp.end;
		
		int xOrigin=50;
		int yOrigin=450;
		Graphics2D g = bi.createGraphics();
		g.setColor(Color.WHITE);
		
		Point2D.Double from = new Point2D.Double(xOrigin, 0);
		Point2D.Double to = new Point2D.Double(xOrigin, 500);

		Line2D.Double line = new Line2D.Double(from, to);
		
		g.draw(line);

		Point2D.Double from2 = new Point2D.Double(0, yOrigin);
		Point2D.Double to2 = new Point2D.Double(500, yOrigin);
		Line2D.Double line2 = new Line2D.Double(from2, to2);
		g.draw(line2);

		g.drawString(String.valueOf(start), xOrigin+5, yOrigin+15);
		g.drawString(String.valueOf(end), 480, yOrigin+15);
		
		
		Double[] keys = new Double[c.results.size()];
		Double[] values = new Double[c.results.size()];
		int x=0;
		for(Double key : c.results.keySet()) {
			keys[x] = key;
			x++;
		}
		Arrays.sort(keys);
		for(int i=0;i<keys.length;i++)
			values[i]=c.results.get(keys[i]);
		
		List<Double> aux = Arrays.asList(values);
		double yMin = Collections.min(aux);
		double yMax = Collections.max(aux);
		
		for(int i=0;i<keys.length;i++) {
			int height = (int) (435-435*(values[i]-yMin)/(yMax-yMin));
			if(i==keys.length-1) {
				g.drawString(String.valueOf(values[i]),480, height+10);
			} else {
				double offset = ((keys[i]-start)/(end-keys[i]));
				int xPos= (int) ((xOrigin+500*offset)/(1+offset));
				g.drawString(String.valueOf(new DecimalFormat("#.##").format(values[i])),xPos+5, height+10);
				g.drawString(String.valueOf(new DecimalFormat("#.##").format(keys[i])),xPos+5,yOrigin+15);
				
				g.drawRect(xPos, height, 2, 2);
				

				int nextHeight = (int) (435-435*(values[i+1]-yMin)/(yMax-yMin));
				double nextOffset = ((keys[i+1]-start)/(end-keys[i+1]));
				int nextXPos= (int) ((xOrigin+500*nextOffset)/(1+nextOffset));
				g.drawLine(xPos, height, nextXPos, nextHeight);
			}
		}
	}
	
}
