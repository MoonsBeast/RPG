package graphics;

public class VisualComponent {
	protected int xPos = 0, yPos = 0, width = 0, height = 0;
	protected boolean isVisible = true;

	public VisualComponent() {}
	
	public VisualComponent(int xPos,int yPos,int width,int height) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.width = width;
		this.height = height;
	}

	public synchronized int getxPos() {
		return xPos;
	}

	public synchronized void setxPos(int xPos) {
		this.xPos = xPos;
	}

	public synchronized int getyPos() {
		return yPos;
	}

	public synchronized void setyPos(int yPos) {
		this.yPos = yPos;
	}

	public synchronized int getWidth() {
		return width;
	}

	public synchronized void setWidth(int width) {
		this.width = width;
	}

	public synchronized int getHeight() {
		return height;
	}

	public synchronized void setHeight(int height) {
		this.height = height;
	}
	
	public synchronized boolean isVisible() {
		return isVisible;
	}

	public synchronized void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}
}
