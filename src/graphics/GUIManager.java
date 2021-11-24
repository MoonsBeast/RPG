package graphics;

import java.awt.*;
import java.util.Random;
import javax.swing.*;

import characters.Character;

public class GUIManager {
	
	protected JFrame window;
	protected PanelManager pManager;
	
	protected int defaultWidth = 500, defaultHeight = 500;
	private String[] windowNames = {
			"¡Supercalifragilisticoespialidoso!",
			"Desconfia del mayordomo",
			"Pienso luego soy",
			"¡¡¡MAMÁ, QUE NO SE PUEDE PAUSAR!!!",
			"De primero de columpios",
			"De pinta y colorea",
			"De primero de gateo",
			"Mas viejo que el poleo",
			"Patrocinado por Corea del medio",
			"Un juego para todas las edades",
			"Cola Cao >>> Nesquick",
			"Desarrollado encima de un cementerio indio",
			"Elije: Pastilla roja o pastilla azul",
			"Para matar una rata muy gorda",
			"Lee el archivo de las tormentas",
			"Quevedo estuvo aquí",
			"Juego no relacionado con IBM",
			"Indra es el boss final"
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
		
		//Creates the frame and adds the PanelManager
		this.window = new JFrame("Combat Idle: " + windowNames[random.nextInt(windowNames.length)]);      
		this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.window.setResizable(false);
		this.window.setSize(size);
		this.window.setLocationRelativeTo(null);
		
		pManager = new PanelManager(window);
		
		this.window.add(pManager);
		this.window.setVisible(true);

	}
	
	public synchronized Dimension getDimensions() {
		return this.window.getContentPane().getSize();
	}
	
	public synchronized Canvas getCanvas() {
		return pManager.getCanvas();
	}
	
	public synchronized boolean isCanvasInPlace() {
		return pManager.isCanvasInPlace();
	}
	
	public synchronized Character getCharacterFromImputs() {
		return this.pManager.getCharacterFromImputs();
	}

	public synchronized int getMouseX() {
		return pManager.getMouseX();
	}

	public synchronized void setMouseX(int mouseX) {
		this.pManager.setMouseX(mouseX);
	}

	public synchronized int getMouseY() {
		return pManager.getMouseY();
	}

	public synchronized void setMouseY(int mouseY) {
		this.pManager.setMouseY(mouseY);
	}
	
}
