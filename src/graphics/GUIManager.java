package graphics;

import java.awt.*;
import java.util.Random;
import javax.swing.*;

public class GUIManager {
	
	protected JFrame window;
	protected Canvas canvas;
	
	private int defaultWidth = 500, defaultHeight = 500;
	private String[] windowNames = {
			"¡Supercalifragilisticoespialidoso!",
			"Desconfia del mayordomo",
			"Pienso luego soy",
			"¡¡¡MAMÁ, QUE NO SE PUEDE PAUSAR!!!",
			"De primero de columpios"
			};
	
	public GUIManager() {
		setup();
	}
	
	public GUIManager(int width, int height) {
		this.defaultWidth = width;
		this.defaultHeight = height;
		setup();
	}
	
	public void setup() {
		
		Dimension size = new Dimension(defaultWidth,defaultHeight);
		Random random = new Random();
		
		this.window = new JFrame(windowNames[random.nextInt(windowNames.length)]);      
		this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.window.setSize(size);
		this.window.setLocationRelativeTo(null);
		
		this.canvas = new Canvas();
		this.canvas.setPreferredSize(this.window.getContentPane().getSize());
		this.canvas.setMaximumSize(this.window.getContentPane().getSize());
		this.canvas.setMinimumSize(this.window.getContentPane().getSize());
		
		this.window.add(canvas);
		this.window.setVisible(true);

	}
	
	public Canvas getCanvas() {
		return this.canvas;
	}
	
	public void setCanvas(Canvas canvas) {
		this.canvas = canvas;
	}
	
}
