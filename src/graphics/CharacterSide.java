package graphics;

import characters.Character;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class CharacterSide extends VisualComponent implements Drawable{
	
	ArrayList<CharacterSpace> actors = new ArrayList<CharacterSpace>();
	
	public CharacterSide(int xPos,int yPos,int width,int height, ArrayList<Character> characters, boolean invertPos, SpriteSheet sSheet) {
		super(xPos,yPos,width,height);
		
		calculateActorPositionAndDimensions(characters,invertPos,sSheet);
	}
	/**
	 * Calculates and initializes the space for each character based on the total space of the component and the number of characters
	 * 
	 * @param characters characters to draw
	 * @param invertPos whether the sprites have to look to the right(false) or the left(true)
	 * @param sSheet sprite sheet for the characters*/
	protected void calculateActorPositionAndDimensions(ArrayList<Character> characters, boolean invertPos, SpriteSheet sSheet) {
		
		Random random = new Random();
		
		int margin = 10;
		int innerWidth = width - 2 * margin;
		int innerHeight = height - 2 * margin;
		int placesOnTopRow = (int) Math.ceil((float) characters.size()/2.0);
		int placesOnBottonRow = (int) Math.floor((float)characters.size()/2.0);
		boolean isTopRowBigger = placesOnTopRow > placesOnBottonRow;
		int spaceXForActors = innerWidth / (placesOnTopRow + (isTopRowBigger ? 0 : 1));
		int spaceYForActors = innerHeight/2;
		
		boolean switchRow = false;
		int count = 0;
		
		for(Character charOnScene: characters) {
			
			int xPosFinal, yPosFinal;
			
			if(invertPos) {
				
				xPosFinal = xPos + margin + (isTopRowBigger ? 0 : spaceXForActors/characters.size()) + (!switchRow ? spaceXForActors/2 : 0) + count * spaceXForActors;
				
			}else {
				
				xPosFinal = xPos + margin + (isTopRowBigger ? 0 : spaceXForActors/4) + (switchRow ? spaceXForActors/2 : 0) + count * spaceXForActors;

			}
			
			yPosFinal = yPos + margin + (switchRow ? spaceYForActors : 0);
			
			charOnScene.setName(charOnScene.getName());
			this.actors.add(new CharacterSpace(invertPos ? (xPosFinal + spaceXForActors): xPosFinal,yPosFinal,invertPos ? -spaceXForActors : spaceXForActors,spaceYForActors,charOnScene,sSheet, new SpriteStateMachine(SpriteState.IDLE, 700+random.nextInt(76))));
			
			count++;
			
			if(count == placesOnTopRow) {
				switchRow = true;
				count = 0;
			}
		}
	}
	
	/**
	 * Clears the previous characters, sets the new ones and reevaluates the space
	 * 
	 * @param characters characters to draw
	 * @param invertPos whether the sprites have to look to the right(false) or the left(true)
	 * @param sSheet sprite sheet for the characters*/
	public void setNewActors(ArrayList<Character> characters, boolean invertPos, SpriteSheet sSheet) {
		this.actors.clear();
		calculateActorPositionAndDimensions(characters,invertPos,sSheet);
	}
	
	/**
	 * @return a VisualComponent object of the first character*/
	public synchronized VisualComponent getCharacterSpaceDimensions() {
		return (VisualComponent)actors.get(0);
	}
	
	
	/**
	 * Returns the index of a character to text
	 * 
	 * @param character character to look for
	 * @return the int value of the index of the character or -1 if not found*/
	public synchronized int getCharacterPosition(Character character) {
		
		int count = 0;
		for(CharacterSpace testSubject : actors) {
			
			if(testSubject.getCharacter() == character) {
				return count;
			}
			
			count++;
		}
		return -1;
	}
	/**
	 * @return the int value of actor in action*/
	public synchronized int getActorSize() {
		return this.actors.size();
	}
	/**
	 * @return an array list of character on the object that are alive*/
	public synchronized ArrayList<Character> getAliveActors() {
		return new ArrayList<Character>(actors.stream().filter(actor -> actor.getCharacter().isAlive()).map(space -> space.getCharacter()).toList());
	}
	
	public synchronized SpriteStateMachine getSSMachineOnIndex(int index) {
		return this.actors.get(index).getSsm();
	}
	
	public synchronized boolean isVisibleIndex(int index) {
		return this.actors.get(index).isVisible();
	}
	
	public synchronized void setVisibleOnIndex(boolean visible, int index) {
		this.actors.get(index).setVisible(visible);
	}
	
	public synchronized Character getCharacterOnIndex(int index) {
		return this.actors.get(index).getCharacter();
	}

	public synchronized void setCharacterOnIndex(Character character,int index) {
		this.actors.get(index).setCharacter(character);
	}
	
	public synchronized void setStateOnIndex(SpriteState state,int index) {
		this.actors.get(index).setState(state);
	}
	
	public synchronized SpriteSheet getSpiteSheetOnIndex(int index) {
		return actors.get(index).getSpiteSheet();
	}

	public synchronized void setSpiteSheetOnIndex(SpriteSheet spiteSheet, int index) {
		this.actors.get(index).setSpiteSheet(spiteSheet);
	}
	
	/**
	 * Levels up every character and sets the state to IDLE*/
	public synchronized void levelUpEveryone() {
		this.actors.forEach((actor) -> {
			actor.getCharacter().levelUp();
			actor.setState(SpriteState.IDLE);
		});
	}
	
	public synchronized boolean checkIfAnyActorContainsPoint(int x, int y) {
		return this.actors.stream().anyMatch(actor -> actor.containsPoint(x, y));   
	}
	
	public synchronized Character getActorThatContainsPoint(int x, int y) {
		return this.actors.stream().filter(actor -> actor.containsPoint(x, y)).map(space -> space.getCharacter()).findFirst().get();   
	}
	
	public synchronized boolean isMouseOnUpperHalfOfActorPoint(int x, int y) {
		return this.actors.stream().filter(actor -> actor.containsPoint(x, y)).findFirst().get().isPointOnUpperHalf(x, y);   
	}
	
	@Override
	public void draw(Graphics brush) {
		
		this.actors.forEach((actor) -> actor.draw(brush));
	}

}
