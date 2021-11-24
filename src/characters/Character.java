package characters;

import java.util.HashMap;

import armor.Armor;
import armor.ArmorPart;
import armor.SkinArmor;
import combat.*;
import spellbooks.Spellbook;
import weapons.Shield;
import weapons.Weapon;

public class Character {
	
	protected int level, maxLife, actualLife, maxMana, actualMana;
	protected String name; 
	protected boolean isAlive = true; 
	protected Race race;
	protected RolClass rolClass;
	protected Armor armor;
	protected Weapon rightWeapon,leftWeapon;
	protected Spellbook spellbook;
	
	protected static HashMap<Race,Integer> baseArmorTable, baseLifeTable;
	protected static HashMap<RolClass,Integer> baseManaTable;
	
	public Character() {
		this.name = "Nobody";
		this.level = 1;
		this.race = Race.AVEN;
		this.rolClass = RolClass.ARTIFICER;
		
		populateTables();
		this.armor = calculateBaseArmor();
		this.maxLife = calculateLife();
		this.maxMana = calculateMana();
		
		this.actualLife = maxLife;
		this.actualMana = maxMana;
	}
	
	public Character(String name, int level, Race race, RolClass rolClass) {
		this.name = name;
		this.level = level;
		this.race = race;
		this.rolClass = rolClass;
		
		populateTables();
		this.armor = calculateBaseArmor();
		this.maxLife = calculateLife();
		this.maxMana = calculateMana();
		
		this.actualLife = maxLife;
		this.actualMana = maxMana;
	}
	
	public Character(String name, int level, Race race, RolClass rolClass, Weapon rightWeapon, Weapon leftWeapon, Spellbook spellbook) {
		this.name = name;
		this.level = level;
		this.race = race;
		this.rolClass = rolClass;
		this.setRightWeapon(rightWeapon);
		this.setLeftWeapon(leftWeapon);
		this.spellbook = spellbook;
		
		populateTables();
		this.armor = calculateBaseArmor();
		this.maxLife = calculateLife();
		this.maxMana = calculateMana();
		
		this.actualLife = maxLife;
		this.actualMana = maxMana;
	}
	
	private void populateTables() {
		
		//Armor Table
		if(Character.baseArmorTable == null) {
			
			Character.baseArmorTable = new HashMap<>();
			
			Character.baseArmorTable.put(Race.ORC, 5);
			Character.baseArmorTable.put(Race.HUMAN, 4);
			Character.baseArmorTable.put(Race.ELF, 4);
			Character.baseArmorTable.put(Race.DWARF, 3);
			Character.baseArmorTable.put(Race.HALFLING, 2);
			Character.baseArmorTable.put(Race.DRAGONBORN, 4);
			Character.baseArmorTable.put(Race.GNOME, 2);
			Character.baseArmorTable.put(Race.DEMON, 7);
			Character.baseArmorTable.put(Race.MINOTAUR, 6);
			Character.baseArmorTable.put(Race.CENTAUR, 6);
			Character.baseArmorTable.put(Race.GOBLIN, 2);
			Character.baseArmorTable.put(Race.AVEN, 5);
			Character.baseArmorTable.put(Race.LEONIN, 5);
		}
		
		//Life Table
		if(Character.baseLifeTable == null) {
			
			Character.baseLifeTable = new HashMap<>();
			
			Character.baseLifeTable.put(Race.ORC, 7);
			Character.baseLifeTable.put(Race.HUMAN, 5);
			Character.baseLifeTable.put(Race.ELF, 5);
			Character.baseLifeTable.put(Race.DWARF, 4);
			Character.baseLifeTable.put(Race.HALFLING, 3);
			Character.baseLifeTable.put(Race.DRAGONBORN, 6);
			Character.baseLifeTable.put(Race.GNOME, 3);
			Character.baseLifeTable.put(Race.DEMON, 8);
			Character.baseLifeTable.put(Race.MINOTAUR, 8);
			Character.baseLifeTable.put(Race.CENTAUR, 8);
			Character.baseLifeTable.put(Race.GOBLIN, 2);
			Character.baseLifeTable.put(Race.AVEN, 4);
			Character.baseLifeTable.put(Race.LEONIN, 5);
		}
		
		//Life Table
		if(Character.baseManaTable == null) {
			
			Character.baseManaTable = new HashMap<>();
			
			Character.baseManaTable.put(RolClass.ARTIFICER, 2);
			Character.baseManaTable.put(RolClass.BARBARIAN, 1);
			Character.baseManaTable.put(RolClass.BARD, 35);
			Character.baseManaTable.put(RolClass.CLERIC, 30);
			Character.baseManaTable.put(RolClass.DRUID, 40);
			Character.baseManaTable.put(RolClass.FIGHTER, 1);
			Character.baseManaTable.put(RolClass.MONK, 5);
			Character.baseManaTable.put(RolClass.PALADIN, 30);
			Character.baseManaTable.put(RolClass.RANGER, 1);
			Character.baseManaTable.put(RolClass.ROGUE, 1);
			Character.baseManaTable.put(RolClass.SORCERER, 40);
			Character.baseManaTable.put(RolClass.WARLOCK, 40);
			Character.baseManaTable.put(RolClass.WIZARD, 50);
		}
		
	};
	
	private Armor calculateBaseArmor() {
		return new SkinArmor(baseArmorTable.get(this.race));
	}
	
	private int calculateLife() {
		return baseLifeTable.get(this.race) * this.level;
	}
	
	private int calculateMana() {
		return baseManaTable.get(this.rolClass) * this.level;
	}
	
	public void levelUp() {
		this.level++;
		this.maxLife = calculateLife();
		this.maxMana = calculateMana();
		
		this.actualLife = maxLife;
		this.actualMana = maxMana;
		
		if(!this.isAlive) this.isAlive = true;
	}
	
	public void levelDown() {
		this.level--;
		this.maxLife = calculateLife();
		this.maxMana = calculateMana();
		
		this.actualLife = maxLife;
		this.actualMana = maxMana;
		
		if(!this.isAlive) this.isAlive = true;
	}
	
	public boolean checkIfPartIsEquiped(ArmorPart armorPart) {
		
		return this.armor.checkIfPartIsEquiped(armorPart);
	}
	
	public boolean canDoAttackWithRightHand() {
		
		return this.rightWeapon != null;
	}
	
	public boolean canDoAttackWithLeftHand() {
		
		return this.leftWeapon != null;
	}
	
	public boolean canCast() {
		
		return this.spellbook != null;
	}
	
	public boolean canDoMelee() {
		
		return canDoAttackWithRightHand() || canDoAttackWithLeftHand();
	}
	
	public Melee attackWithRight(){
		return this.rightWeapon.doAttack();
	}
	
	public Melee attackWithLeft(){
		return this.leftWeapon.doAttack();
	}
	
	public Spell attackWithMagic(){
		
		Spell result = this.spellbook.castRandomSpell(this.actualMana);
		this.actualMana -= result.getManaCost();
		return result;
	}
	
	public boolean isCaster() {
		
		return this.rolClass == RolClass.BARD || 
				this.rolClass == RolClass.WIZARD || 
				this.rolClass == RolClass.SORCERER || 
				this.rolClass == RolClass.CLERIC || 
				this.rolClass == RolClass.PALADIN ||
				this.rolClass == RolClass.DRUID || 
				this.rolClass == RolClass.WARLOCK;
	}
	
	public boolean isMelee() {
		
		return this.rolClass == RolClass.BARBARIAN || 
				this.rolClass == RolClass.ARTIFICER || 
				this.rolClass == RolClass.FIGHTER || 
				this.rolClass == RolClass.MONK || 
				this.rolClass == RolClass.CLERIC || 
				this.rolClass == RolClass.PALADIN ||
				this.rolClass == RolClass.DRUID ||
				this.rolClass == RolClass.RANGER ||
				this.rolClass == RolClass.ROGUE;
	}
	
	public void calculateAttackRecieved(AttackAction attack) {
		
		int shieldBonus = 0;
		if(this.rightWeapon instanceof Shield) {
			shieldBonus += this.rightWeapon.getAttack().getDamageValue();
		}
		
		if(this.leftWeapon instanceof Shield) {
			shieldBonus += this.leftWeapon.getAttack().getDamageValue();
		}
		
		int damage = shieldBonus + attack.getProcessedDamageValue() - armor.calculateDefense(attack);
		this.actualLife -= damage > 0 ? damage : 0; 
		
		if(actualLife <= 0) this.isAlive = false;
	}
	
	public int getMaxLife() {
		return maxLife;
	}

	public void setMaxLife(int life) {
		this.maxLife = life;
	}

	public int getMaxMana() {
		return maxMana;
	}

	public void setMaxMana(int mana) {
		this.maxMana = mana;
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
		
		if(rightWeapon != null && rightWeapon.getNumOfHadsRequired() == 2) {
			this.setLeftWeapon(null);
		}
		
		this.rightWeapon = rightWeapon;
	}

	public Weapon getLeftWeapon() {
		return leftWeapon;
	}

	public void setLeftWeapon(Weapon leftWeapon) {
		
		if(leftWeapon != null && leftWeapon.getNumOfHadsRequired() == 2) {
			this.setRightWeapon(null);
		}
		
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

	public int getActualLife() {
		return actualLife;
	}

	public void setActualLife(int actualLife) {
		this.actualLife = actualLife;
	}

	public int getActualMana() {
		return actualMana;
	}

	public void setActualMana(int actualMana) {
		this.actualMana = actualMana;
	}
	
	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
