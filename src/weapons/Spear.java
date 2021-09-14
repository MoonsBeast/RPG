package weapons;

import combat.DamageType;
import combat.Melee;

public class Spear extends Weapon {

	public Spear() {
		super(null, 33, 2);
		
		this.setAttack(new Melee("Spear Charge",DamageType.PIERCING, 6));
	}

}