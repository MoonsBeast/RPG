package graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Narrator extends VisualComponent implements Drawable {
	
	protected int round = 1;
	
	protected BufferedImage bg;
	protected String mainText = "";
	protected ArrayList<String> aditionalTexts = new ArrayList<String>();
	
	public Narrator(int xPos, int yPos, int width, int height) {
		super(xPos, yPos, width, height);
		bg = ImageLoader.loadImage("res/textures/textBackground.png");
	}

	public synchronized String getMainText() {
		return mainText;
	}

	public synchronized void setMainText(String mainText) {
		this.mainText = mainText;
		aditionalTexts.clear();
	}
	
	public synchronized void addAditionalText(String aditionalText) {
		this.aditionalTexts.add(aditionalText);
	}
	
	public synchronized void nextRound() {
		round++;
	}

	public synchronized int getRound() {
		return round;
	}

	public synchronized void setRound(int round) {
		this.round = round;
	}

	@Override
	public void draw(Graphics brush) {
		
		if(this.isVisible) {
			//Overall textBox
			brush.setColor(Color.LIGHT_GRAY);
			brush.fillRect(xPos+5, yPos+5, width-10, height-10);
			brush.drawImage(bg, xPos+10, yPos+10, width-20, height-20, null);
			
			//Outlines
			brush.setColor(Color.BLACK);
			brush.drawRect(xPos+5, yPos+5, width-10, height-10);
			brush.drawRect(xPos+10, yPos+10, width-20, height-20);
			
			//text
			//info
			brush.setFont(new Font("SansSerif", 0, 20));
			brush.drawString(mainText, xPos+this.width/8, yPos+this.height/2);
			//aditional text
			int lines = 1;
			for(String text : aditionalTexts) {
				brush.drawString(text, xPos+this.width/8, yPos+this.height/2+brush.getFontMetrics().getHeight()*lines++);
			}
			//round count
			brush.drawString("Ronda: "+round, xPos+this.width/8, yPos+this.height/3);
		}
		
	}

}
