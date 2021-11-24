package gameLogic;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import characters.RandomCharacterFactory;
import characters.RolClass;
import combat.AttackAction;
import combat.DamageType;
import characters.Character;
import characters.Race;
import graphics.CharacterSide;
import graphics.CharacterSpace;
import graphics.GUIManager;
import graphics.ImageLoader;
import graphics.Narrator;
import graphics.SpriteSheet;
import graphics.SpriteState;
import graphics.SpriteStateMachine;
import graphics.Tooltip;
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
	protected Tooltip tooltip;
	
	protected ArrayList<Integer> turnBag;
	
	protected boolean run = false, isProtagonistLoaded = false, isFoeOnSpotlight = false;
	protected boolean haveCasted = true, haveUsedRightHand = true, haveUsedLeftHand = true;
	protected int timePassed = 0,characterPosibleActions = 0;
	protected int windowWidth = 500, windowHeight = 500;
	protected int panelWidth = 500, panelHeight = 500;
	
	public GameManager(int width, int height) {
		
		this.windowWidth = width;
		this.windowHeight = height;
	}
	
	private void init() {
		
		//Initializes all necessary components
		tooltip = new Tooltip();
		GManager = new GUIManager(windowWidth,windowHeight);
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
		
		allies = new CharacterSide(0, panelHeight/2, panelWidth/2, panelHeight/2, generateRandomCharacter(4,1), false, alliesSheet);
		enemies = new CharacterSide(panelWidth/2, panelHeight/2, panelWidth/2, panelHeight/2, generateRandomCharacter(4,1), true, badGuysSheet);
		
		allies.setSpiteSheetOnIndex(protagonistSheet, allies.getActorSize()-1);
		
		VisualComponent dimensions = allies.getCharacterSpaceDimensions();
		//-10 and -20 are because of the margins other components have, otherwise it would not fit properly
		spotlight = new CharacterSpace(windowWidth/2-dimensions.getWidth()/2-10, windowHeight/2-dimensions.getHeight()-20, dimensions.getWidth(), dimensions.getHeight());

	}
	
	private ArrayList<Character> generateRandomCharacter(int amount, int level){
		RandomCharacterFactory factory = new RandomCharacterFactory();
		ArrayList<Character> res = new ArrayList<Character>();
		
		for(int i = 0; i < amount; i++) {
			res.add(factory.createCharacter(level));
		}
		
		return res;
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
				generateNextRound();
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
	
	private synchronized void generateNextRound() {
		
		narrator.setMainText("La ronda ha acabado. Ganan los " + (isFoeOnSpotlight ? "villanos" : "heroes") + ".");
		
		if(isFoeOnSpotlight) {
			narrator.addAditionalText("Has aguantado " + narrator.getRound() + (narrator.getRound() == 1 ? " ronda" : " rondas") +". Más suerte la próxima vez.");
		}else {
			narrator.addAditionalText("¡¡Enhorabuena por la victoria!! Pero esto es solo una de muchas batallas a librar...");
			narrator.addAditionalText("Sientes como tus fuerzas vuelven y crecen...");
			narrator.addAditionalText("Tus enemigos preparan una resistencia aun mayor...");
			narrator.nextRound();
			allies.levelUpEveryone();
			enemies.setNewActors(generateRandomCharacter(4, narrator.getRound()), true, badGuysSheet);
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
	
	private synchronized void checkAndDrawTooltip(Graphics brush) {
		
		boolean drawTooltip = false, drawDownwards = true;
		Character infoCharacter = new Character();
		int x = GManager.getMouseX(), y = GManager.getMouseY();
		
		if(allies.checkIfAnyActorContainsPoint(x, y)) {
			
			infoCharacter = allies.getActorThatContainsPoint(x, y);
			drawDownwards = allies.isPointOnUpperHalf(x, y);
			drawTooltip = true;
			
		}else if(enemies.checkIfAnyActorContainsPoint(x, y)) {
			
			infoCharacter = enemies.getActorThatContainsPoint(x, y);
			drawDownwards = enemies.isPointOnUpperHalf(x, y);
			drawTooltip = true;
			
		}
		
		if(drawTooltip) {
			
			ArrayList<String> text = new ArrayList<>();
			text.add("Nombre: " + infoCharacter.getName());
			text.add("Nivel: " + infoCharacter.getLevel());
			text.add("Raza: " + infoCharacter.getRace());
			text.add("Clase: " + infoCharacter.getRolClass());
			text.add("Vida: " + infoCharacter.getActualLife() + " / " + infoCharacter.getMaxLife());
			text.add("Mana: " + infoCharacter.getActualMana() + " / " + infoCharacter.getMaxMana());
			text.add("Arma Derecha: " + (infoCharacter.getRightWeapon() != null ? infoCharacter.getRightWeapon().getName() : "Ninguna"));
			text.add("Arma Izquierda: " + (infoCharacter.getLeftWeapon() != null ? infoCharacter.getLeftWeapon().getName() : "Ninguna"));
			text.add("Grimorio: " + (infoCharacter.getSpellbook() != null ? infoCharacter.getSpellbook().getName() : "Ninguno"));
			
			tooltip.setTexts(text);
			
			int height = brush.getFontMetrics().getHeight()*9+5;
			tooltip.setXPos(x);
			tooltip.setYPos(drawDownwards ? y : y-height);
			
			var counter = new Object(){ int count = 0; };
			text.forEach(word -> {if(word.length() > counter.count) counter.count = word.length();});
			tooltip.setWidth(8 * counter.count);
			tooltip.setHeight(height);

			tooltip.draw(brush);
		}
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
		
		checkAndDrawTooltip(graph);
		
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
