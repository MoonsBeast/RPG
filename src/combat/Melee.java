package combat;

public class Melee extends AttackAction {
	
	public Melee(String name, DamageType damageType, int damageValue, boolean isCritic) {
		super(name, damageType, damageValue, isCritic);
	}

	public Melee(String name, DamageType damageType, int damageValue) {
		super(name, damageType, damageValue);
	}
	
	public Melee(AttackAction attack) {
		super(attack);
	}

}
