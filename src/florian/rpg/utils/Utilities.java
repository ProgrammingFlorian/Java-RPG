package florian.rpg.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class Utilities {

	public static BufferedImage loadImage(String path) {
		try {
			return ImageIO.read(Utilities.class.getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}
	
	public static void drawString(Graphics g, String text, int xPos, int yPos, boolean center, Color c, Font font) {
		g.setColor(c);
		g.setFont(font);
		int x = xPos;
		int y = yPos;
		if(center) {
			FontMetrics fm = g.getFontMetrics(font);
			x = xPos - fm.stringWidth(text) / 2;
			y = (yPos - fm.getHeight() / 2) + fm.getAscent();
		}
		g.drawString(text, x, y);
	}
	
	public static String loadFileAsString(String path) {
		try {
			StringBuilder sb = new StringBuilder();
			FileReader fr = new FileReader(new File(Utilities.class.getResource(path).getFile()));
			BufferedReader br = new BufferedReader(fr);
			String line;
			while((line = br.readLine()) != null) {
				sb.append(line + ",");
			}
			br.close();
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String[] loadFileAsArray(String path) {
		try {
			List<String> output = new ArrayList<String>();
			FileReader fr = new FileReader(new File(Utilities.class.getResource(path).getFile()));
			BufferedReader br = new BufferedReader(fr);
			String line;
			while((line = br.readLine()) != null) {
				output.add(line);
			}
			br.close();
			return output.toArray(new String[0]);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	
	public static void saveToFile(String path, String content) {
		try (PrintWriter out = new PrintWriter(path)) {
		    out.println(content);
		    out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}