package weapons;

import combat.DamageType;
import combat.Melee;

public class Sword extends Weapon {

	public Sword() {
		super(null, 5, 1);
		
		this.setAttack(new Melee("Sword Swing",DamageType.SLASHING, 8));
	}

}
