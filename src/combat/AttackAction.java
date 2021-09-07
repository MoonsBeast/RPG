package combat;

public class AttackAction {
	private DamageType damageType;
	private int damageValue;
	
	public DamageType getDamageType() {
		return damageType;
	}
	
	public void setDamageType(DamageType damageType) {
		this.damageType = damageType;
	}
	
	public int getDamageValue() {
		return damageValue;
	}
	
	public void setDamageValue(int damageValue) {
		this.damageValue = damageValue;
	}
}
