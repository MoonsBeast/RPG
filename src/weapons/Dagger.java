package weapons;

import combat.DamageType;
import combat.Melee;

public class Dagger extends Weapon {

	public Dagger(Melee attack, int criticChance, int numOfHadsRequired) {
		super(null, 66, 1);
		
		this.setAttack(new Melee("Backstab",DamageType.PIERCING, 7));
	}

}
