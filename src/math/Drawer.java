package math;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Drawer {

	public static void plot(MsgParser mp, Compute c, BufferedImage bi) {
		double start, end;
		start = mp.start;
		end = mp.end;

		int xOrigin = 50;
		int yOrigin = 450;
		Graphics2D g = bi.createGraphics();
		g.setColor(Color.WHITE);

		g.drawLine(xOrigin,0,xOrigin,500);
		g.drawLine(0, yOrigin, 500, yOrigin);

		g.drawString(String.valueOf(start), xOrigin + 5, yOrigin + 15);
		g.drawString(String.valueOf(end), 480, yOrigin + 15);

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
			int height = (int) (435 - 435 * (values[i] - yMin) / (yMax - yMin));
			if (i == keys.length - 1) {
				if (!Common.arrayContains(mp.options, "nolabel"))
					g.drawString(String.valueOf(values[i]), 480, height + 10);
			} else {
				double offset = ((keys[i] - start) / (end - keys[i]));
				int xPos = (int) ((xOrigin + 500 * offset) / (1 + offset));
				if (!Common.arrayContains(mp.options, "nolabel")) {
					g.drawString(String.valueOf(new DecimalFormat("#.##").format(values[i])), xPos + 5, height + 10);
					g.drawString(String.valueOf(new DecimalFormat("#.##").format(keys[i])), xPos + 5, yOrigin + 15);
				}

				g.drawRect(xPos, height, 2, 2);

				int nextHeight = (int) (435 - 435 * (values[i + 1] - yMin) / (yMax - yMin));
				int nextXPos = 500;
				if (i + 1 != keys.length - 1) {
					double nextOffset = ((keys[i + 1] - start) / (end - keys[i + 1]));
					nextXPos = (int) ((xOrigin + 500 * nextOffset) / (1 + nextOffset));
				}
				g.drawLine(xPos, height, nextXPos, nextHeight);
			}
		}
	}

}
