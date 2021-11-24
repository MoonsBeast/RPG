package weapons;

import combat.DamageType;
import combat.Melee;

public class Musket extends Weapon {

	public Musket() {
		super(null, 5, 1, WeaponList.MUSKET);
		
		this.setAttack(new Melee("Shoot",DamageType.PIERCING, 15));
	}

}
