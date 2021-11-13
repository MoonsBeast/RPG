package graphics;

import java.awt.image.BufferedImage;
import java.util.HashMap;

public class SpriteSheet {
	
	protected BufferedImage sheet;
	protected HashMap<String,BufferedImage> croppedParts;
	
	public SpriteSheet(BufferedImage sheet) {
		this.sheet = sheet;
		croppedParts = new HashMap<String, BufferedImage>();
	}
	
	public BufferedImage crop(int x, int y, int width, int heigth) {
		return sheet.getSubimage(x, y, width, heigth);
	}
	
	public BufferedImage cropAndSafe(String name, int x, int y, int width, int heigth) {
		BufferedImage crop = this.crop(x, y, width, heigth);
		croppedParts.put(name, crop);
		return crop;
	}
	
	public BufferedImage getSavedCrop(String name) throws Exception {
		
		if(!croppedParts.containsKey(name)) throw new Exception("No such name");
		
		return croppedParts.get(name);
	}
}
