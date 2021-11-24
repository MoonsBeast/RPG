package weapons;

import combat.DamageType;
import combat.Melee;

public class Rapier extends Weapon {
	
	public Rapier() {
		super(null, 15, 1, WeaponList.RAPIER);
		
		this.setAttack(new Melee("Stab with Style",DamageType.PIERCING, 10));
	}
}
