package combat;

public class AttackAction {
	private String name;
	private boolean isCritic;
	private DamageType damageType;
	private int damageValue;

	public AttackAction(String name, DamageType damageType, int damageValue, boolean isCritic) {
		this.name = name;
		this.damageType = damageType;
		this.damageValue = damageValue;
		this.isCritic = isCritic;
	}
	
	public AttackAction(String name, DamageType damageType, int damageValue) {
		this.name = name;
		this.damageType = damageType;
		this.damageValue = damageValue;
		this.isCritic = false;
	}
	
	public AttackAction(AttackAction attack) {
		this.name = attack.name;
		this.damageType = attack.damageType;
		this.damageValue = attack.damageValue;
		this.isCritic = attack.isCritic;
	}
	
	public DamageType getDamageType() {
		return damageType;
	}
	
	public void setDamageType(DamageType damageType) {
		this.damageType = damageType;
	}
	
	public int getProcessedDamageValue() {
		return isCritic ? damageValue*2 : damageValue;
	}
	
	public int getDamageValue() {
		return damageValue;
	}
	
	public void setDamageValue(int damageValue) {
		this.damageValue = damageValue;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isCritic() {
		return isCritic;
	}

	public void setCritic(boolean isCritic) {
		this.isCritic = isCritic;
	}
}
