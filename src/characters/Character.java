package characters;

import spellbooks.Spellbook;
import weapons.Weapon;

public class Character {
	
	private int life, mana;
	private Race race;
	private RolClass rolClass;
	private Weapon rightWeapon,leftWeapon;
	private Spellbook spellbook;

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public int getMana() {
		return mana;
	}

	public void setMana(int mana) {
		this.mana = mana;
	}

	public Spellbook getSpellbook() {
		return spellbook;
	}

	public void setSpellbook(Spellbook spellbook) {
		this.spellbook = spellbook;
	}

	public Weapon getRightWeapon() {
		return rightWeapon;
	}

	public void setRightWeapon(Weapon rightWeapon) {
		this.rightWeapon = rightWeapon;
	}

	public Weapon getLeftWeapon() {
		return leftWeapon;
	}

	public void setLeftWeapon(Weapon leftWeapon) {
		this.leftWeapon = leftWeapon;
	}

	public Race getRace() {
		return race;
	}

	public void setRace(Race race) {
		this.race = race;
	}

	public RolClass getRolClass() {
		return rolClass;
	}

	public void setRolClass(RolClass rolClass) {
		this.rolClass = rolClass;
	}
	
}
