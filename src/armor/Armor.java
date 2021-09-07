package armor;

import characters.Character;

public class Armor extends Character{
	
	private Character decoratedCharacter;
	private int defense;
	
	Armor(int defense){
		this.defense = defense;
	}
	
	Armor(Character character,int defense){
		this.setDecoratedCharacter(character);
		this.defense = defense;
	}

	public int getDefense() {
		return defense;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

	public Character getDecoratedCharacter() {
		return decoratedCharacter;
	}

	public void setDecoratedCharacter(Character decoratedCharacter) {
		this.decoratedCharacter = decoratedCharacter;
	}
}
