package graphics;

public abstract class VisualComponent {
	protected int xPos = 0, yPos = 0, width = 0, height = 0;
	protected boolean isVisible = true;

	public VisualComponent() {}
	
	public VisualComponent(int xPos,int yPos,int width,int height) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.width = width;
		this.height = height;
	}

	public synchronized int getXPos() {
		return xPos;
	}

	public synchronized void setXPos(int xPos) {
		this.xPos = xPos;
	}

	public synchronized int getYPos() {
		return yPos;
	}

	public synchronized void setYPos(int yPos) {
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
	
	public synchronized boolean containsPoint(int x, int y) {
		int cx = this.width < 0 ? this.xPos+this.width : this.xPos;
		int cy = this.height < 0 ? this.yPos+this.height : this.yPos;
		
		return (x > cx && x < cx + Math.abs(this.width)) && (y > cy && y < cy + Math.abs(height));
	}
	
	public synchronized boolean isPointOnUpperHalf(int x, int y) {
		int cy = this.height < 0 ? this.yPos+this.height : this.yPos;
		
		return containsPoint(x,y) && (y > cy && y < cy + Math.abs(height)/2);
	}
}
