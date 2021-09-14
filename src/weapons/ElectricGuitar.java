package weapons;

import combat.DamageType;
import combat.Melee;

public class ElectricGuitar extends Weapon {

	public ElectricGuitar() {
		super(null, 5, 2);
		
		this.setAttack(new Melee("Play Maluma",DamageType.THUNDER, 5));
	}

}
