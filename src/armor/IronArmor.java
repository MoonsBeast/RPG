package armor;

import combat.AttackAction;

public class IronArmor extends Armor{
	
	private Armor decoratedArmor;
	
	IronArmor(Armor decoratedArmor, ArmorPart armorPart) {
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
}
