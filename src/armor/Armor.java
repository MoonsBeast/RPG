package armor;

import combat.AttackAction;

public class Armor{

	private int defense;
	private ArmorPart armorPart;
	
	/**
	 * @param defense defense value for the part
	 * @param armorPart part of the body that this piece is going to be attached
	 * */
	public Armor(int defense, ArmorPart armorPart){
		this.defense = defense;
		this.armorPart = armorPart;
	}

	public int getDefense() {
		return defense;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

	public ArmorPart getArmorPart() {
		return armorPart;
	}

	public void setArmorPart(ArmorPart armorPart) {
		this.armorPart = armorPart;
	}
	/**
	 * Calculates the total defense of this piece and all pieces it decorates
	 * 
	 * @param attack attack that triggers the defense calculation, this way resistances and weaknesses can be applied 
	 * */
	public int calculateDefense(AttackAction attack) { 
		return this.defense;
	}
	
	/**
	 * Check itself and decorated armors to see if any is equal to the part
	 * 
	 * @param armorPart the part you are looking for 
	 * */
	public boolean checkIfPartIsEquiped(ArmorPart armorPart) { 
		return this.armorPart == armorPart;
	}
}
