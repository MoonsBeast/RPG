package graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Tooltip extends VisualComponent implements Drawable {
	
	ArrayList<String> texts;
	
	public Tooltip() {
		texts = new ArrayList<>();
	}

	public Tooltip(int xPos, int yPos, int width, int height) {
		super(xPos, yPos, width, height);
		texts = new ArrayList<>();
	}
	
	public synchronized ArrayList<String> getTexts() {
		return texts;
	}

	public synchronized void setTexts(ArrayList<String> texts) {
		this.texts = texts;
	}

	@Override
	public void draw(Graphics brush) {
		
		if(!this.isVisible) return;
		
		brush.setColor(Color.WHITE);
		brush.fillRect(xPos, yPos, width, height);
		
		brush.setColor(Color.BLACK);
		brush.drawRect(xPos, yPos, width, height);
		
		int lines = 0;
		for(String text : texts) {
			brush.drawString(text, xPos+10, yPos+height/10+brush.getFontMetrics().getHeight()*lines++);
		}
		
	}

}
