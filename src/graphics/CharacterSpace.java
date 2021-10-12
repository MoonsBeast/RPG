package graphics;

import characters.Character;
import java.awt.Graphics;

public class CharacterSpace extends VisualComponent implements Drawable{
	
	protected Character character;
	
	public CharacterSpace(int xPos,int yPos,int width,int height, Character character) {
		super(xPos,yPos,width,height);
	}
	
	public Character getCharacter() {
		return character;
	}

	public void setCharacter(Character character) {
		this.character = character;
	}

	@Override
	public void draw(Graphics brush) {
		
		brush.drawRect(xPos, yPos, width, height);
		
	}
}
