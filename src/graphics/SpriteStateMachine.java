package graphics;

public class SpriteStateMachine {
	
	protected SpriteState actualState;
	protected int innerClock = 0, maxCount, module, timePerSprite, actualStateCount, spriteLimit;
	
	public SpriteStateMachine(SpriteState initialState, int timePerSprite) {
		
		calculateSpriteLimit(initialState);
		
		this.actualState = initialState;
		this.timePerSprite = timePerSprite;
		this.module = timePerSprite;
		this.maxCount = timePerSprite * module;
		
	}
	
	private void calculateSpriteLimit(SpriteState state) {
		
		int res = Integer.MAX_VALUE;
		if(state == SpriteState.IDLE) {
			
			res = 5;
			
		}else if(state == SpriteState.ATTACK) {
			
			res = 6;
			
		}else if(state == SpriteState.DEAD) {
			
			res = 7;
			
		}
		
		this.spriteLimit = res;
	}
	
	public void setStatus(SpriteState newState) {
		
		calculateSpriteLimit(newState);
		innerClock = 0;
	}
	
	public String tick() {
		
		innerClock++;
		
		if(innerClock > maxCount) innerClock = 0;
		
		if(innerClock % module == 0) {
			actualStateCount++;
			
			if(actualStateCount == this.spriteLimit && actualState == SpriteState.DEAD) {
				actualStateCount = spriteLimit-1;
			}
			
			if(actualStateCount == this.spriteLimit && actualState == SpriteState.ATTACK) {
				actualState = SpriteState.IDLE;
				actualStateCount = 0;
			}
			
			if(actualStateCount == this.spriteLimit && actualState == SpriteState.IDLE) {
				actualStateCount = 0;
			}
			
			calculateSpriteLimit(actualState);
		}
		
		return actualState.toString()+actualStateCount;
	}
}
