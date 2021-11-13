package graphics;

import characters.Character;

import java.awt.Graphics;

public class CharacterSpace extends VisualComponent implements Drawable,Cloneable{
	
	protected Character character;
	protected SpriteSheet sSheet;
	protected SpriteStateMachine ssm;
	protected int frameCount = 0,idleState = 0;
	
	public CharacterSpace(int xPos,int yPos,int width,int height, Character character, SpriteSheet sSheet, SpriteStateMachine ssm) {
		super(xPos,yPos,width,height);
		this.character = character;
		this.sSheet = sSheet;
		this.ssm = ssm;
	}
	
	public CharacterSpace(int xPos,int yPos,int width,int height) {
		super(xPos,yPos,width,height);
		this.character = null;
		this.sSheet = null;
	}
	
	public Character getCharacter() {
		return character;
	}

	public void setCharacter(Character character) {
		this.character = character;
	}
	
	public void setState(SpriteState state) {
		this.ssm.setStatus(state);
	}

	@Override
	public void draw(Graphics brush) {
		
		if(this.isVisible) {
			
			try {
				brush.drawRect(xPos, yPos, width, height);
				
				if(sSheet != null) {
					brush.drawImage(sSheet.getSavedCrop(ssm.tick()), xPos, yPos, width, height, null);
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
//			if(frameCount % 700 == 0) {
//				idleState++;
//				if(idleState > 4) idleState = 0;
//			}
//			
//			frameCount++;
//			if(frameCount > 7000) frameCount = 0;
		}

	}
}
