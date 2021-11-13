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
import graphics.CharacterSpace;
import graphics.GUIManager;
import graphics.ImageLoader;
import graphics.Narrator;
import graphics.SpriteSheet;
import graphics.SpriteState;
import graphics.VisualComponent;

public class GameManager implements Runnable{
	
	protected GUIManager GManager;
	protected Thread thread;
	
	protected BufferStrategy bStrat;
	protected Graphics graph;
	protected SpriteSheet sSheet;
	
	BufferedImage bg;
	Narrator narrator;
	CharacterSpace spotlight;
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
	}
	
	private void init() {
		
		//Initializes all necessary components
		this.GManager = new GUIManager(windowWidth,windowHeight);
		Dimension size = this.GManager.getDimensions();
		panelWidth = size.width;
		panelHeight = size.height;
		
		sSheet = new SpriteSheet(ImageLoader.loadImage("res/textures/knight1v3.png"));
		
		//Idle sprites
		for(int i = 0; i < 5; i++) {
			sSheet.cropAndSafe(SpriteState.IDLE.toString()+i, 22+64*i, 25, 20, 25);
		}
		
		//Attack sprites
		for(int i = 0; i < 6; i++) {
			sSheet.cropAndSafe(SpriteState.ATTACK.toString()+i, 22+64*i, 265, 38, 45);
		}
		
		//Death sprites
		for(int i = 0; i < 7; i++) {
			sSheet.cropAndSafe(SpriteState.DEAD.toString()+i, 13+64*i, 405, 40, 30);
		}
		//sSheet.cropAndSafe("test", 22, 25, 20, 24);
		//sSheet.cropAndSafe("test2", 22+64, 25, 20, 24);
		//sSheet.cropAndSafe("test3", 22+64*2, 25, 20, 24);
		
		bg = ImageLoader.loadImage("res/textures/background.jpg");
		narrator = new Narrator(0,0,panelWidth,panelHeight/4);
		
		ArrayList<Character> pepes = new ArrayList<Character>();
		for(int i = 0; i < 4; i++) {
			pepes.add(new Character(String.valueOf(i),1, Race.HUMAN, RolClass.ROGUE));
		}
		allies = new CharacterSide(0, panelHeight/2, panelWidth/2, panelHeight/2, pepes, false, sSheet);
		enemies = new CharacterSide(panelWidth/2, panelHeight/2, panelWidth/2, panelHeight/2, pepes, true, sSheet);
		
		VisualComponent dimensions = allies.getCharacterSpaceDimensions();
		//-10 and -20 are because of the margins other components have, otherwise it would not fit properly
		spotlight = new CharacterSpace(windowWidth/2-dimensions.getWidth()/2-10, windowHeight/2-dimensions.getHeight()-20, dimensions.getWidth(), dimensions.getHeight());

	}
	
	private void tick() {
		
		//Calculus of the next game state
		
	}

	private void render() {
		
		//Draw everything	
		bStrat = this.GManager.getCanvas().getBufferStrategy();
		if(bStrat == null) {
			GManager.getCanvas().createBufferStrategy(3);
			return;
		}
		graph = bStrat.getDrawGraphics();
		
		//Clear
		graph.clearRect(0, 0, windowWidth, windowHeight);
		
		//Draw
		
		graph.drawImage(bg, 0, 0, windowWidth, windowHeight, null);
		
		narrator.draw(graph);
		spotlight.draw(graph);
		allies.draw(graph);
		enemies.draw(graph);

		//End Draw
		
		bStrat.show();
		graph.dispose();
	}
	
	public void run() {
		
		init();
		
		while(run) {
			
			if(!GManager.isCanvasInPlace()) continue;
			
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
