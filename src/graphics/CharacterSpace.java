package graphics;

import characters.Character;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class CharacterSpace extends VisualComponent implements Drawable{
	
	protected Character character;
	protected SpriteSheet sSheet;
	protected SpriteStateMachine ssm;
	
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
	
	public synchronized Character getCharacter() {
		return character;
	}

	public synchronized void setCharacter(Character character) {
		this.character = character;
	}
	
	public synchronized void setState(SpriteState state) {
		this.ssm.setStatus(state);
	}
	
	public synchronized SpriteSheet getSpiteSheet() {
		return sSheet;
	}

	public synchronized void setSpiteSheet(SpriteSheet spiteSheet) {
		this.sSheet = spiteSheet;
	}
	
	public synchronized SpriteStateMachine getSsm() {
		return ssm;
	}

	public synchronized void setSsm(SpriteStateMachine ssm) {
		this.ssm = ssm;
	}

	@Override
	public void draw(Graphics brush) {
		
		if(!this.isVisible) return;
		
		if(this.character != null && !this.character.isAlive() && this.ssm.actualState != SpriteState.DEAD) {
			this.ssm.setStatus(SpriteState.DEAD);
		}
		
		try {
			//brush.drawRect(xPos, yPos, width, height);
			
			if(sSheet != null) {
				brush.drawImage(sSheet.getSavedCrop(ssm.tick()), xPos, yPos+10, width, height-10, null);
				
				//Life bar red Background
				brush.setColor(Color.RED);
				brush.fillRect(width < 0 ? xPos + width : xPos, yPos+height-height/15, Math.abs(width), height/15);
				
				//Life bar green foreground
				brush.setColor(Color.GREEN);
				int greenWidth = Math.abs(width)*character.getActualLife()/character.getMaxLife();
				brush.fillRect(width < 0 ? xPos + width : xPos, yPos+height-height/15, greenWidth <= 0 ? 0 : greenWidth, height/15);
				
				//Name background
				brush.setColor(Color.WHITE);
				brush.fillRect(width < 0 ? xPos + width : xPos, yPos, Math.abs(width), height/15);
				
				//Life text outline
				brush.setColor(Color.BLACK);
				brush.drawRect(width < 0 ? xPos + width : xPos, yPos+height-height/15, Math.abs(width), height/15);
				
				//Name outline
				brush.drawRect(width < 0 ? xPos + width : xPos, yPos, Math.abs(width), height/15);
				
				//Life and name
				brush.setFont(new Font("SansSerif", 0, height/15));
				brush.drawString(character.getActualLife()+" / "+character.getMaxLife(), (width < 0 ? xPos + width : xPos)+50, yPos+height-1);
				brush.drawString(character.getName(), (width < 0 ? xPos + width : xPos)+35, yPos+12);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
