package characters;

import armor.Armor;
import armor.ArmorPart;
import spellbooks.Spellbook;
import weapons.Weapon;

public class Character {
	
	private int level, life, mana;
	private Race race;
	private RolClass rolClass;
	private Armor armor;
	private Weapon rightWeapon,leftWeapon;
	private Spellbook spellbook;
	
	public Character(int level, Race race, RolClass rolClass) {
		// TODO Auto-generated constructor stub
	}
	
	public Character(int level, Race race, RolClass rolClass, Weapon rightWeapon, Weapon leftWeapon, Spellbook spellbook) {
		// TODO Auto-generated constructor stub
	}
	
	private Armor calculateBaseArmor() {
		return new Armor(0,ArmorPart.Skin);
	}
	
	private int calculateLife() {
		return 1;
	}
	
	private int calculateMana() {
		return 1;
	}
	
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

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public Armor getArmor() {
		return armor;
	}

	public void setArmor(Armor armor) {
		this.armor = armor;
	}
	
}
