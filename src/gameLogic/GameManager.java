package gameLogic;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import characters.Race;
import characters.RandomCharacterFactory;
import characters.RolClass;
import combat.AttackAction;
import combat.DamageType;
import characters.Character;
import graphics.CharacterSide;
import graphics.CharacterSpace;
import graphics.GUIManager;
import graphics.ImageLoader;
import graphics.Narrator;
import graphics.SpriteSheet;
import graphics.SpriteState;
import graphics.SpriteStateMachine;
import graphics.VisualComponent;

public class GameManager implements Runnable{
	
	protected GUIManager GManager;
	protected Thread thread;
	
	protected BufferStrategy bStrat;
	protected Graphics graph;
	protected SpriteSheet alliesSheet, protagonistSheet, badGuysSheet;
	
	protected BufferedImage bg;
	protected Narrator narrator;
	protected CharacterSpace spotlight;
	protected CharacterSide allies, enemies;
	
	protected ArrayList<Integer> turnBag;
	
	protected boolean run = false, isProtagonistLoaded = false, isFoeOnSpotlight = false;
	protected boolean haveCasted = true, haveUsedRightHand = true, haveUsedLeftHand = true;
	protected int timePassed = 0,characterPosibleActions = 0;
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
		bg = ImageLoader.loadImage("res/textures/background.jpg");
		narrator = new Narrator(0,0,panelWidth,panelHeight/4);
		turnBag = new ArrayList<>();
		
		//Sprite Sheets for everyone
		alliesSheet = new SpriteSheet(ImageLoader.loadImage("res/textures/knight2v3.png"));
		protagonistSheet = new SpriteSheet(ImageLoader.loadImage("res/textures/knight4v3.png"));
		badGuysSheet = new SpriteSheet(ImageLoader.loadImage("res/textures/knight3v3.png"));
		
		//Idle sprites
		for(int i = 0; i < 5; i++) {
			alliesSheet.cropAndSafe(SpriteState.IDLE.toString()+i, 22+64*i, 25, 20, 25);
			protagonistSheet.cropAndSafe(SpriteState.IDLE.toString()+i, 22+64*i, 25, 20, 25);
			badGuysSheet.cropAndSafe(SpriteState.IDLE.toString()+i, 22+64*i, 25, 20, 25);
		}
		
		//Attack sprites
		for(int i = 0; i < 6; i++) {
			alliesSheet.cropAndSafe(SpriteState.ATTACK.toString()+i, 22+64*i, 265, 38, 45);
			protagonistSheet.cropAndSafe(SpriteState.ATTACK.toString()+i, 22+64*i, 265, 38, 45);
			badGuysSheet.cropAndSafe(SpriteState.ATTACK.toString()+i, 22+64*i, 265, 38, 45);
		}
		
		//Death sprites
		for(int i = 0; i < 7; i++) {
			alliesSheet.cropAndSafe(SpriteState.DEAD.toString()+i, 13+64*i, 405, 40, 30);
			protagonistSheet.cropAndSafe(SpriteState.DEAD.toString()+i, 13+64*i, 405, 40, 30);
			badGuysSheet.cropAndSafe(SpriteState.DEAD.toString()+i, 13+64*i, 405, 40, 30);
		}
		
		//Character creation and their visual representation
		RandomCharacterFactory factory = new RandomCharacterFactory();
		ArrayList<Character> friends = new ArrayList<Character>(), 
							 foes = new ArrayList<Character>();
		for(int i = 0; i < 4; i++) {
			friends.add(factory.createCharacter(1));
			foes.add(factory.createCharacter(1));
		}
		allies = new CharacterSide(0, panelHeight/2, panelWidth/2, panelHeight/2, friends, false, alliesSheet);
		enemies = new CharacterSide(panelWidth/2, panelHeight/2, panelWidth/2, panelHeight/2, foes, true, badGuysSheet);
		
		allies.setSpiteSheetOnIndex(protagonistSheet, allies.getActorSize()-1);
		
		VisualComponent dimensions = allies.getCharacterSpaceDimensions();
		//-10 and -20 are because of the margins other components have, otherwise it would not fit properly
		spotlight = new CharacterSpace(windowWidth/2-dimensions.getWidth()/2-10, windowHeight/2-dimensions.getHeight()-20, dimensions.getWidth(), dimensions.getHeight());

	}
	
	private void tick() {//Calculus of the next game state
		
		if(!isProtagonistLoaded) { //If the canvas is placed, the character selection is finished
			allies.setCharacterOnIndex(GManager.getCharacterFromImputs(), allies.getActorSize()-1);
			isProtagonistLoaded = true;
		}
		
		//counting time so animations can happen properly
		timePassed++;
		if(!(timePassed % (800*6) == 0)) {
			
			return;
					
		}
		
		//set the turn order
		if(turnBag.size() == 0) {
			
			for(int i = 0; i < (allies.getActorSize()+enemies.getActorSize()); i++) {
				
				//If you try to add an ally and is dead or you try to add a foe and is dead 
				CharacterSide target = i < allies.getActorSize() ? allies : enemies;
				int index = i < allies.getActorSize() ? i : i - allies.getActorSize();
				if(!target.getCharacterOnIndex(index).isAlive()) {
					continue;
				}
				
				turnBag.add(i);
			}
			
			Collections.shuffle(turnBag);
		}
		
		//setting the character to do attacks
		if(spotlight.getCharacter() == null) {
			
			int characterTurn = turnBag.get(0);
			
			Character tmpChar;
			SpriteSheet tmpSSheet;
			SpriteStateMachine tmpSSMachine;
			if(characterTurn < allies.getActorSize()) {
				
				tmpChar = allies.getCharacterOnIndex(characterTurn);
				tmpSSheet = allies.getSpiteSheetOnIndex(characterTurn);
				tmpSSMachine = allies.getSSMachineOnIndex(characterTurn);
				allies.setVisibleOnIndex(false, characterTurn);
				isFoeOnSpotlight = false;
				
			}else {
				
				tmpChar = enemies.getCharacterOnIndex(characterTurn - allies.getActorSize());
				tmpSSheet = enemies.getSpiteSheetOnIndex(characterTurn - allies.getActorSize());
				tmpSSMachine = enemies.getSSMachineOnIndex(characterTurn - allies.getActorSize());
				enemies.setVisibleOnIndex(false, characterTurn - allies.getActorSize());
				isFoeOnSpotlight = true;
				
			}
			
			if(tmpChar.canCast()) {
				characterPosibleActions++;
				haveCasted = false;
			}
			
			if(tmpChar.canDoAttackWithRightHand()) {
				characterPosibleActions++;
				haveUsedRightHand = false;
			}
			
			if(tmpChar.canDoAttackWithLeftHand()) {
				characterPosibleActions++;
				haveUsedLeftHand = false;
			}
			
			spotlight.setVisible(true);
			spotlight.setCharacter(tmpChar);
			spotlight.setSpiteSheet(tmpSSheet);
			spotlight.setSsm(tmpSSMachine);
		}
		
		if(!(haveUsedRightHand && haveUsedLeftHand && haveCasted)) { //if can attack somehow, attack
			
			Random random = new Random();
			CharacterSide targetSide = isFoeOnSpotlight ? allies : enemies;
			ArrayList<Character> posibleTargets = targetSide.getAliveActors();
			
			if(posibleTargets.size() == 0) {
				endTurn();
				return;
			}
			
			AttackAction attack = new AttackAction("Spit on her!!!", DamageType.ACID, 0);
			
			spotlight.setState(SpriteState.ATTACK);
			
			if(!haveUsedRightHand) {
				
				attack = spotlight.getCharacter().attackWithRight();
				haveUsedRightHand = true;
				
			}else if(!haveUsedLeftHand) {
				
				attack = spotlight.getCharacter().attackWithLeft();
				haveUsedLeftHand = true;
				
			}else if(!haveCasted) {
				
				attack = spotlight.getCharacter().attackWithMagic();
				haveCasted = true;
				
			}

			int objectPos = random.nextInt(posibleTargets.size());
			Character objective = posibleTargets.get(objectPos);
			int prevLife = objective.getActualLife();
			objective.calculateAttackRecieved(attack);
			
			narrator.setMainText(spotlight.getCharacter().getName()+" atacks " + objective.getName() + " with " + attack.getName() + " dealing " + (prevLife-objective.getActualLife()) + " damage.");
			
			if(!objective.isAlive()) { // if the target dies cant have a turn

				narrator.addAditionalText(objective.getName() + " has fainted.");
				
				
				int objectiveTurnNumber = targetSide.getCharacterPosition(objective) + (isFoeOnSpotlight ? 0 : allies.getActorSize());
				
				int pos = turnBag.indexOf(objectiveTurnNumber);
				if(pos != -1) {

					turnBag.remove(pos);
					
				}
			}
			
		}
		
		if(timePassed % (800*6*(characterPosibleActions+1)) == 0) {//character on spotlight have ended his things
			
			endTurn();
			
		}

	}
	
	private synchronized void endTurn() {
		spotlight.setVisible(false);
		spotlight.setCharacter(null);
		spotlight.setSpiteSheet(null);
		spotlight.setSsm(null);
		characterPosibleActions = 0;
		timePassed = 0;
		
		CharacterSide targetSide = isFoeOnSpotlight ? enemies : allies;
		targetSide.setVisibleOnIndex(true, turnBag.get(0) >= allies.getActorSize() ? turnBag.get(0) - allies.getActorSize() : turnBag.get(0));
		turnBag.remove(0);
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
