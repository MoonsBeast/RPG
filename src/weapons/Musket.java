package weapons;

import combat.DamageType;
import combat.Melee;

public class Musket extends Weapon {

	public Musket(Melee attack, int criticChance, int numOfHadsRequired) {
		super(null, 40, 1);
		
		this.setAttack(new Melee("Shoot",DamageType.PIERCING, 15));
	}

}
