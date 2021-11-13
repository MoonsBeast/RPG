package graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Narrator extends VisualComponent implements Drawable {
	
	BufferedImage bg;
	
	public Narrator(int xPos, int yPos, int width, int height) {
		super(xPos, yPos, width, height);
		bg = ImageLoader.loadImage("res/textures/textBackground.png");
	}

	@Override
	public void draw(Graphics brush) {
		
		if(this.isVisible) {
			brush.setColor(Color.LIGHT_GRAY);
			brush.fillRect(xPos+5, yPos+5, width-10, height-10);
			brush.drawImage(bg, xPos+10, yPos+10, width-20, height-20, null);
			
			brush.setColor(Color.BLACK);
			brush.drawRect(xPos+5, yPos+5, width-10, height-10);
			brush.drawRect(xPos+10, yPos+10, width-20, height-20);
		}
		
	}

}
