package graphics;

import characters.Character;
import java.awt.Graphics;
import java.util.ArrayList;

public class CharacterSide extends VisualComponent implements Drawable{
	
	ArrayList<CharacterSpace> actors = new ArrayList<CharacterSpace>();
	
	public CharacterSide(int xPos,int yPos,int width,int height, ArrayList<Character> characters) {
		super(xPos,yPos,width,height);
		
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
			
			int xPosFinal = xPos + margin + (isTopRowBigger ? 0 : spaceXForActors/4) + (switchRow ? spaceXForActors/2 : 0) + count * spaceXForActors;
			int yPosFinal = yPos + margin + (switchRow ? spaceYForActors : 0);
			
			this.actors.add(new CharacterSpace(xPosFinal,yPosFinal,spaceXForActors,spaceYForActors,charOnScene));
			
			count++;
			
			if(count == placesOnTopRow) {
				switchRow = true;
				count = 0;
			}
		}
	}

	@Override
	public void draw(Graphics brush) {
		
		this.actors.forEach((actor) -> actor.draw(brush));
	}

}
