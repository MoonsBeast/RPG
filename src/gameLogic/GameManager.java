package gameLogic;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import characters.Race;
import characters.RolClass;
import characters.Character;
import graphics.CharacterSide;
import graphics.GUIManager;
import graphics.ImageLoader;

public class GameManager implements Runnable{
	
	protected GUIManager GManager;
	protected Thread thread;
	
	protected BufferStrategy bStrat;
	protected Graphics graph;
	
	//BufferedImage patata;
	CharacterSide allies, enemies;
	
	protected boolean run = false;
	protected int windowWidth = 500, windowHeight = 500;
	protected int panelWidth = 500, panelHeight = 500;
	
	public GameManager() {
		
		this.GManager = new GUIManager();
	}
	
	public GameManager(int width, int height) {
		
		this.windowWidth = width;
		this.windowHeight = height;
		init();
	}
	
	private void init() {
		
		this.GManager = new GUIManager(windowWidth,windowHeight);
		Dimension size = this.GManager.getDimensions();
		panelWidth = size.width;
		panelHeight = size.height;
		
		//patata = ImageLoader.loadImage("res/textures/flowers.png");
		ArrayList<Character> pepes = new ArrayList<Character>();
		for(int i = 0; i < 4; i++) {
			pepes.add(new Character(1, Race.HUMAN, RolClass.ROGUE));
		}
		allies = new CharacterSide(0, 0, panelWidth/2, panelHeight, pepes, false);
		enemies = new CharacterSide(panelWidth/2, 0, panelWidth/2, panelHeight, pepes, true);
		
		
	}
	
	private void tick() {
		
	}

	private void render() {
		
		bStrat = this.GManager.getCanvas().getBufferStrategy();
		if(bStrat == null) {
			GManager.getCanvas().createBufferStrategy(3);
			return;
		}
		graph = bStrat.getDrawGraphics();
		
		//Clear
		graph.clearRect(0, 0, windowWidth, windowHeight);
		
		//Draw
		
		allies.draw(graph);
		enemies.draw(graph);
		//graph.drawImage(patata, 50, 50, null);
		
		//End Draw
		
		bStrat.show();
		graph.dispose();
	}
	
	public void run() {
		
		init();
		
		while(run) {
			tick();
			render();
		}
		
		stop();
	}
	
	public synchronized void start() {
		
		if(run) return;
		
		this.run = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop() {
		
		if(!run) return;
		
		try {
			
			this.run = false;
			thread.join();
			
		} catch (InterruptedException e) {
			
			e.printStackTrace();
			
		}
	}
}
