package weapons;

import combat.DamageType;
import combat.Melee;

public class Bow extends Weapon {
	
	public Bow() {
		super(null, 5, 2);
		
		this.setAttack(new Melee("Sharpshot",DamageType.PIERCING, 5));
	}
}
