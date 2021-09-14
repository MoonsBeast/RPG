package weapons;

import combat.DamageType;
import combat.Melee;

public class Sword extends Weapon {

	public Sword() {
		super(null, 20, 1);
		
		this.setAttack(new Melee("Sword Swing",DamageType.SLASHING, 8));
	}

}
