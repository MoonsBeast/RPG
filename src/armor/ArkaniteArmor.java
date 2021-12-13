package armor;

import combat.AttackAction;

public class ArkaniteArmor extends Armor {
	
	protected Armor decoratedArmor;

	public ArkaniteArmor(Armor decoratedArmor, ArmorPart armorPart) {
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

		return this.getDefense() + decoratedArmor.calculateDefense(attack);
	}

	public boolean checkIfPartIsEquiped(ArmorPart armorPart) { 
		return this.getArmorPart() == armorPart ? true : decoratedArmor.checkIfPartIsEquiped(armorPart);
	}
}
