package weapons;

import combat.DamageType;
import combat.Melee;

public class Shield extends Weapon {

	public Shield(Melee attack, int criticChance, int numOfHadsRequired) {
		super(null, 0, 1);
		
		this.setAttack(new Melee("Charge",DamageType.BLUGGEONING, 3));
	}
	
}
