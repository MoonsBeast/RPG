package armor;

import combat.AttackAction;

public class Armor{

	private int defense;
	private ArmorPart armorPart;
	
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
	
	//Paso un AttackAction porque alguna armadura puede ser vulnerable o resistente a ciertos elementos
	public int calculateDefense(AttackAction attack) { 
		return this.defense;
	}
}
