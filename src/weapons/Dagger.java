package weapons;

import combat.DamageType;
import combat.Melee;

public class Dagger extends Weapon {

	public Dagger() {
		super(null, 15, 1, WeaponList.DAGGER);
		
		this.setAttack(new Melee("Backstab",DamageType.PIERCING, 7));
	}

}
