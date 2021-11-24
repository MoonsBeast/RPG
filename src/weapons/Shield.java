package weapons;

import combat.DamageType;
import combat.Melee;

public class Shield extends Weapon {

	public Shield() {
		super(null, 0, 1, WeaponList.SHIELD);
		
		this.setAttack(new Melee("Charge",DamageType.BLUGGEONING, 3));
	}
	
}
