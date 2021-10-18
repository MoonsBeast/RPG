package graphics;

import java.awt.*;
import java.util.Random;
import javax.swing.*;

public class GUIManager {
	
	protected JFrame window;
	protected PanelManager pManager;
	
	private int defaultWidth = 500, defaultHeight = 500;
	private String[] windowNames = {
			"�Supercalifragilisticoespialidoso!",
			"Desconfia del mayordomo",
			"Pienso luego soy",
			"���MAM�, QUE NO SE PUEDE PAUSAR!!!",
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
		this.window.setResizable(false);
		this.window.setSize(size);
		this.window.setLocationRelativeTo(null);
		
		pManager = new PanelManager(window);
		
		this.window.add(pManager);
		this.window.setVisible(true);

	}
	
	public Dimension getDimensions() {
		return this.window.getContentPane().getSize();
	}
	
	public Canvas getCanvas() {
		return pManager.getCanvas();
	}
}