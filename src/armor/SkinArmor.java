package armor;

import combat.AttackAction;

public class SkinArmor extends Armor {

	public SkinArmor(int defense) {
		super(defense, ArmorPart.SKIN);
	}
	
	public int calculateDefense(AttackAction attack) {
		
		return this.getDefense();
	}
	
	public boolean checkIfPartIsEquiped(ArmorPart armorPart) { 
		return false;
	}
}
