package weapons;

import combat.DamageType;
import combat.Melee;

public class Warhammer extends Weapon {

	public Warhammer() {
		super(null, 5, 2, WeaponList.WARHAMMER);
		
		this.setAttack(new Melee("Hammer Time!",DamageType.BLUGGEONING, 12));
	}
	
}
