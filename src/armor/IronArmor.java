package armor;

import combat.AttackAction;
import combat.DamageType;

public class IronArmor extends Armor{
	
	protected Armor decoratedArmor;
	
	public IronArmor(Armor decoratedArmor, ArmorPart armorPart) {
		super(2, armorPart);
		this.decoratedArmor = decoratedArmor;
	}
	
	public Armor getDecoratedArmor() {
		return decoratedArmor;
	}

	public void setDecoratedArmor(Armor decoratedArmor) {
		this.decoratedArmor = decoratedArmor;
	}
	
	public int calculateDefense(AttackAction attack) {
		
		int defense = 0;
		
		if( attack.getDamageType() != DamageType.LIGHTNING && 
			attack.getDamageType() != DamageType.FORCE && 
			attack.getDamageType() != DamageType.THUNDER &&
			attack.getDamageType() != DamageType.RADIANT &&
			attack.getDamageType() != DamageType.NECROTIC) {
			
			defense = this.getDefense();
		}
		
		return defense + decoratedArmor.calculateDefense(attack);
	}
	
	public boolean checkIfPartIsEquiped(ArmorPart armorPart) { 
		return this.getArmorPart() == armorPart ? true : decoratedArmor.checkIfPartIsEquiped(armorPart);
	}
}
