package com.plotatoe.math;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Drawer {

	public static void plot(Compute c, BufferedImage bi) {
		double start, end;
		start = c.start;
		end = c.end;

		int xOrigin = 0;
		int yOrigin = 480;
		int w = 500;
		int h = 500;
		Graphics2D g = bi.createGraphics();
		g.setColor(new Color(0xF0F0F0));
		g.fillRect(0, 0, w, h);
		g.setColor(Color.LIGHT_GRAY);

		g.drawLine(xOrigin,0,xOrigin,h);
		g.drawLine(0, yOrigin, w, yOrigin);

		g.setColor(Color.BLACK);
		g.drawString(String.valueOf(start), xOrigin + 5, yOrigin + 15);
		g.drawString(String.valueOf(end), w-20, yOrigin + 15);

		Double[] keys = new Double[c.results.size()];
		Double[] values = new Double[c.results.size()];
		int x = 0;
		for (Double key : c.results.keySet()) {
			keys[x] = key;
			x++;
		}
		Arrays.sort(keys);
		for (int i = 0; i < keys.length; i++)
			values[i] = c.results.get(keys[i]);

		List<Double> aux = Arrays.asList(values);
		double yMin = Collections.min(aux);
		double yMax = Collections.max(aux);

		for (int i = 0; i < keys.length; i++) {
			int height = (int) (yOrigin - yOrigin * (values[i] - yMin) / (yMax - yMin));
			g.setColor(Color.BLACK);
			if (i == keys.length - 1) {
				if (!Common.arrayContains(c.options, "nolabel"))
					g.drawString(String.valueOf(values[i]), w-20, height + 10);
			} else {
				double offset = ((keys[i] - start) / (end - keys[i]));
				int xPos = (int) ((xOrigin + w * offset) / (1 + offset));
				if (!Common.arrayContains(c.options, "nolabel")) {
					g.drawString(String.valueOf(new DecimalFormat("#.##").format(values[i])), xPos + 5, height + 10);
					g.drawString(String.valueOf(new DecimalFormat("#.##").format(keys[i])), xPos + 5, yOrigin + 15);
				}
				g.setColor(Color.BLUE);
				g.drawRect(xPos, height, 2, 2);

				int nextHeight = (int) (yOrigin - yOrigin * (values[i + 1] - yMin) / (yMax - yMin));
				int nextXPos = w;
				if (i + 1 != keys.length - 1) {
					double nextOffset = ((keys[i + 1] - start) / (end - keys[i + 1]));
					nextXPos = (int) ((xOrigin + w * nextOffset) / (1 + nextOffset));
				}
				g.drawLine(xPos, height, nextXPos, nextHeight);
			}
		}
		

		int yZero = (int) (yOrigin - yOrigin*(-yMin) / (yMax - yMin));
		double offset = -start/end;
		int xZero = (int) ((xOrigin + w * offset) / (1 + offset));
		
		g.setColor(Color.GRAY);
		g.drawLine(xZero, 0, xZero, h);
		g.drawLine(0, yZero, w, yZero);
	}

	public static void projective(Compute c, BufferedImage result) {
		int pointsize = 3;
		int gridlines = 150;
		
		int width = 500, height = 500;
		int horizon = 50, zero = 450;
		int scale = zero-horizon;
		
		Graphics2D g = result.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(Color.black);
		g.fillRect(0, 0, width, height);
		g.setColor(Color.white);
		g.drawLine(0, horizon, width, horizon);
		
		//vertical lines
		for(int i = 0; i < gridlines; i++) {
			g.drawLine(width/2+i*scale, zero, width/2, horizon);
			g.drawLine(width/2-i*scale, zero, width/2, horizon);
		}
		g.drawLine(width/2, horizon, width/2, zero);
		
		//horizontal lines
		for(double i = 1; i < gridlines; i++) {
			g.drawLine(0, (int)(1/i*scale)+horizon, width, (int)(1/i*scale)+horizon);
		}
		
		Double[] keys = new Double[c.results.size()];
		Double[] values = new Double[c.results.size()];
		int j = 0;
		for (Double key : c.results.keySet()) {
			keys[j] = key;
			j++;
		}
		Arrays.sort(keys);
		for (int i = 0; i < keys.length; i++)
			values[i] = c.results.get(keys[i]);
		
		g.setColor(new Color(0xff6c27));
		for(int i = 0; i < keys.length; i++) {
			double x1 = keys[i];
			double z1 = values[i];
			if(z1 < -1) continue;
			//points
			g.fillOval((int)(x1/(z1+1)*scale+width/2-pointsize/2.0), (int)(1/(z1+1)*scale+horizon-pointsize/2.0), pointsize, pointsize);				
			
			//line segments
			if(i < keys.length-1) {
				double x2 = keys[i+1];
				double z2 = values[i+1];
				if(z2 < -1) continue;
				g.drawLine((int)(x1/(z1+1)*scale+width/2), (int)(1/(z1+1)*scale+horizon),(int)(x2/(z2+1)*scale+width/2), (int)(1/(z2+1)*scale+horizon));				
			}
		}
	}
}