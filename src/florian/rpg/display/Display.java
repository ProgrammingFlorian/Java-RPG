package florian.rpg.display;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Display {
	
	private JFrame frame;
	private Canvas canvas;
	
	private int width, height;
	private String title;
	
	public Display(int width, int height, String title) {
		this.title = title;
		this.width = width;
		this.height = height;
		
		create();
	}
	
	private void create() {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		frame = new JFrame(title);
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight()));
		canvas.setMaximumSize(new Dimension(frame.getWidth(), frame.getHeight()));
		canvas.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
		canvas.setFocusable(false);
		
		frame.add(canvas);
		frame.pack();
		frame.setVisible(true);
	}
	
	public Canvas getCanvas() {
		return canvas;
	}
	
	public JFrame getFrame() {
		return frame;
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
}