package weapons;

import combat.DamageType;
import combat.Melee;

public class Fist extends Weapon {
	
	public Fist() {
		super(null, 15, 1);
		
		this.setAttack(new Melee("Punch in the Liver",DamageType.BLUGGEONING, 5));
	}
}
