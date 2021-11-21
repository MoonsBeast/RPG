package characters;

import java.util.ArrayList;
import java.util.Random;

import armor.ArkaniteArmor;
import armor.ArmorPart;
import armor.IronArmor;
import armor.LeatherArmor;
import spellbooks.*;
import weapons.*;

public class FromInputCharacterFactory implements CharacterFactory {
	
	protected String race, rolClas, weaponRight, weaponLeft, spellBook;	
	public FromInputCharacterFactory(String race, String rolClas, String weaponRight, String weaponLeft, String spellBook) {
		this.race = race;
		this.rolClas = rolClas;
		this.weaponRight = weaponRight;
		this.weaponLeft = weaponLeft;
		this.spellBook = spellBook;
	}

	protected Race stringToRace(String race) {
		
		for(Race testRace : Race.values()) {
			
			if(testRace.toString() == race) {
				return testRace;
			}
			
		}
		
		return null;
	}
	
	protected RolClass stringToRolClass(String rolClass) {
		
		for(RolClass testRol : RolClass.values()) {
			
			if(testRol.toString() == rolClass) {
				return testRol;
			}
			
		}
		
		return null;
	}
	
	protected Weapon stringToWeapon(String weapon) {
		
		WeaponList weaponEnum = WeaponList.BOW;
		for(WeaponList testWeapon : WeaponList.values()) {
			
			if(testWeapon.toString() == weapon) {
				weaponEnum = testWeapon;
				break;
			}
			
		}
		
		Weapon res = null;
		switch(weaponEnum){
			case BOW: res = new Bow(); break;
			case DAGGER: res = new Dagger(); break;
			case ELECTRICGUITAR: res = new ElectricGuitar(); break;
			case FIST: res = new Fist(); break;
			case MUSKET: res = new Musket(); break;
			case RAPIER: res = new Rapier(); break;
			case SHIELD: res = new Shield(); break;
			case SPEAR: res = new Spear(); break;
			case SWORD: res = new Sword(); break;
			case WARHAMMER: res = new Warhammer(); break;
		}
		
		return res;
	}
	
	protected Spellbook stringToBook(String book) {
		
		BookList bookEnum = BookList.MATHS;
		for(BookList testWeapon : BookList.values()) {
			
			if(testWeapon.toString() == book) {
				bookEnum = testWeapon;
				break;
			}
			
		}
		
		Spellbook res = null;
		switch(bookEnum){
			case DARKNESS: res = new BookOfDarkness(); break;
			case LIGHT: res = new BookOfLight(); break;
			case LIGHTNING: res = new BookOfLightning(); break;
			case MATHS: res = new BookOfMaths(); break;
		}
		
		return res;
	}
	
	public static void armorUp(Character character) {
		
		Random random = new Random();
		ArrayList<ArmorPart> parts = new ArrayList<>();
		
		if(character.getRolClass() == RolClass.ARTIFICER) {
			
			parts.add(ArmorPart.ARMS);
			parts.add(ArmorPart.CHEST);
			parts.add(ArmorPart.LEGS);
			
			while(!parts.isEmpty()) {
				
				int pos = random.nextInt(parts.size());
				ArmorPart actual = parts.get(pos);
				
				if(character.checkIfPartIsEquiped(actual)) {

					if(parts.size() == 1) {
						
						IronArmor newPart = new IronArmor(character.getArmor(), actual);
						character.setArmor(newPart);
						
					}
					
					LeatherArmor newPart = new LeatherArmor(character.getArmor(), actual);
					character.setArmor(newPart);
				}
				
				parts.remove(pos);
			}
			
		}else if(character.getRolClass() == RolClass.BARBARIAN) {
			
			parts.add(ArmorPart.ARMS);
			parts.add(ArmorPart.CHEST);
			parts.add(ArmorPart.LEGS);
			
			for(ArmorPart actual : parts){

				if(character.checkIfPartIsEquiped(actual)) {
					
					IronArmor newPart = new IronArmor(character.getArmor(), actual);
					character.setArmor(newPart);
					
				}
			}
			
		}else if(character.getRolClass() == RolClass.BARD) {
			
			parts.add(ArmorPart.ARMS);
			parts.add(ArmorPart.CHEST);
			parts.add(ArmorPart.LEGS);
			
			for(ArmorPart actual : parts){

				if(character.checkIfPartIsEquiped(actual)) {
					
					LeatherArmor newPart = new LeatherArmor(character.getArmor(), actual);
					character.setArmor(newPart);
					
				}
			}
			
		}else if(character.getRolClass() == RolClass.CLERIC) {
			
			parts.add(ArmorPart.HEAD);
			parts.add(ArmorPart.ARMS);
			parts.add(ArmorPart.CHEST);
			parts.add(ArmorPart.LEGS);
			
			for(ArmorPart actual : parts){

				if(character.checkIfPartIsEquiped(actual)) {
					
					LeatherArmor newPart = new LeatherArmor(character.getArmor(), actual);
					character.setArmor(newPart);
					
				}
			}
			
		}else if(character.getRolClass() == RolClass.DRUID) {
			
			parts.add(ArmorPart.ARMS);
			parts.add(ArmorPart.CHEST);
			parts.add(ArmorPart.LEGS);
			
			for(ArmorPart actual : parts){
				
				if(character.checkIfPartIsEquiped(actual)) {
					
					LeatherArmor newPart = new LeatherArmor(character.getArmor(), actual);
					character.setArmor(newPart);
					
				}
			}
			
		}else if(character.getRolClass() == RolClass.FIGHTER) {
			
			parts.add(ArmorPart.ARMS);
			parts.add(ArmorPart.CHEST);
			parts.add(ArmorPart.LEGS);
			
			while(!parts.isEmpty()) {
				
				int pos = random.nextInt(parts.size());
				ArmorPart actual = parts.get(pos);
				
				if(character.checkIfPartIsEquiped(actual)) {
					
					if(parts.size() <= 2) {
						
						IronArmor newPart = new IronArmor(character.getArmor(), actual);
						character.setArmor(newPart);
						
					}
					
					LeatherArmor newPart = new LeatherArmor(character.getArmor(), actual);
					character.setArmor(newPart);
					
				}
				
				parts.remove(pos);
			}
			
		}else if(character.getRolClass() == RolClass.MONK) {
			
			parts.add(ArmorPart.ARMS);
			parts.add(ArmorPart.CHEST);
			parts.add(ArmorPart.LEGS);
			
			for(ArmorPart actual : parts){

				if(character.checkIfPartIsEquiped(actual)) {
					
					LeatherArmor newPart = new LeatherArmor(character.getArmor(), actual);
					character.setArmor(newPart);
					
				}
			}
			
		}else if(character.getRolClass() == RolClass.PALADIN) {
			
			parts.add(ArmorPart.HEAD);
			parts.add(ArmorPart.ARMS);
			parts.add(ArmorPart.CHEST);
			parts.add(ArmorPart.LEGS);
			
			while(!parts.isEmpty()) {
				
				int pos = random.nextInt(parts.size());
				ArmorPart actual = parts.get(pos);
				
				if(character.checkIfPartIsEquiped(actual)) {
					
					if(parts.size() <= 2) {
						
						ArkaniteArmor newPart = new ArkaniteArmor(character.getArmor(), actual);
						character.setArmor(newPart);
						
					}
					
					IronArmor newPart = new IronArmor(character.getArmor(), actual);
					character.setArmor(newPart);
					
				}
				
				parts.remove(pos);
			}
			
		}else if(character.getRolClass() == RolClass.RANGER) {
			
			parts.add(ArmorPart.ARMS);
			parts.add(ArmorPart.CHEST);
			parts.add(ArmorPart.LEGS);
			
			for(ArmorPart actual : parts){

				if(character.checkIfPartIsEquiped(actual)) {
					
					LeatherArmor newPart = new LeatherArmor(character.getArmor(), actual);
					character.setArmor(newPart);
					
				}
			}
			
		}else if(character.getRolClass() == RolClass.ROGUE) {
			
			parts.add(ArmorPart.ARMS);
			parts.add(ArmorPart.CHEST);
			parts.add(ArmorPart.LEGS);
			
			for(ArmorPart actual : parts){
				
				if(character.checkIfPartIsEquiped(actual)) {
					
					LeatherArmor newPart = new LeatherArmor(character.getArmor(), actual);
					character.setArmor(newPart);
					
				}

			}
			
		}else if(character.getRolClass() == RolClass.SORCERER) {

			LeatherArmor newPart = new LeatherArmor(character.getArmor(), ArmorPart.CHEST);
			character.setArmor(newPart);

			
		}else if(character.getRolClass() == RolClass.WARLOCK) {
			
			LeatherArmor newChest = new LeatherArmor(character.getArmor(), ArmorPart.CHEST);
			LeatherArmor newArms = new LeatherArmor(newChest, ArmorPart.ARMS);
			character.setArmor(newArms);
			
		}else if(character.getRolClass() == RolClass.WIZARD) {
			
			LeatherArmor newPart = new LeatherArmor(character.getArmor(), ArmorPart.CHEST);
			character.setArmor(newPart);
			
		}
		
	}
	

	@Override
	public Character createCharacter(int level) {
		
		Character actor = new Character("Hero", level, stringToRace(this.race), stringToRolClass(this.rolClas), stringToWeapon(this.weaponRight), null /*stringToWeapon(this.weaponLeft)*/, stringToBook(this.spellBook));
		armorUp(actor);
		return actor;
	}

	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race;
	}

	public String getRolClas() {
		return rolClas;
	}

	public void setRolClas(String rolClas) {
		this.rolClas = rolClas;
	}

	public String getWeaponRight() {
		return weaponRight;
	}

	public void setWeaponRight(String weaponRight) {
		this.weaponRight = weaponRight;
	}

	public String getWeaponLeft() {
		return weaponLeft;
	}

	public void setWeaponLeft(String weaponLeft) {
		this.weaponLeft = weaponLeft;
	}

	public String getSpellBook() {
		return spellBook;
	}

	public void setSpellBook(String spellBook) {
		this.spellBook = spellBook;
	}
	
	
}
