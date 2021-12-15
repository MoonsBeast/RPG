package characters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import armor.*;
import spellbooks.*;
import weapons.*;

public class RandomCharacterFactory implements CharacterFactory {
	
	protected static final List<Race> Races = Collections.unmodifiableList(Arrays.asList(Race.values()));
	protected static final List<RolClass> Classes = Collections.unmodifiableList(Arrays.asList(RolClass.values()));
	protected static final int RacesSize = Races.size(), ClassesSize = Classes.size();
	protected static final Random random = new Random();
	protected String[] names = {
		"Eufrasio",
		"Geriberto",
		"Nazario",
		"Gerbasio",
		"Amancio",
		"Humberto",
		"Guideon",
		"Percibal",
		"Cayetano",
		"Citripio",
		"Raymundo",
		"Rodrigo",
		"Gwydion",
		"Ermenegildo",
		"Deolindo",
		"Demetrio",
		"Protasio",
		"Marino",
		"Cristiano",
		"Eutelerio",
		"Rodolfo",
		"Natalio",
		"Victorio",
	};
	
	@Override
	public Character createCharacter(int level) {
		
		Race race = Races.get(random.nextInt(RacesSize));
		RolClass rolClass = Classes.get(random.nextInt(ClassesSize));
		Character character = new Character("Sir " + names[random.nextInt(names.length)],level,race,rolClass);
		armorUp(character);
		weaponUp(character);
		
		return character;
	}
	
	/**
	 * Equips the prefixed set of armor to a character based on its role
	 * 
	 * @param character character to dress*/
	public static void armorUp(Character character) {
		
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
	
	/**
	 * Equips the prefixed set of weapon/s to a character based on its role
	 * 
	 * @param character character to dress*/
	public void weaponUp(Character character) {

		ArrayList<Spellbook> books = new ArrayList<>();
		books.add(new BookOfLight());
		books.add(new BookOfDarkness());
		books.add(new BookOfLightning());
		books.add(new BookOfMaths());
		
		if(character.getRolClass() == RolClass.ARTIFICER) {
			
			character.setRightWeapon(new Musket());
			character.setLeftWeapon(random.nextInt(100) >= 75 ? new Shield() : null);
			
		}else if(character.getRolClass() == RolClass.BARBARIAN) {
			
			character.setRightWeapon(new Warhammer());
			
		}else if(character.getRolClass() == RolClass.BARD) {
			
			character.setRightWeapon(new ElectricGuitar());
			character.setSpellbook(books.get(random.nextInt(books.size())));
			
		}else if(character.getRolClass() == RolClass.CLERIC) {
			
			character.setRightWeapon(new Sword());
			character.setLeftWeapon(new Shield());
			character.setSpellbook(new BookOfLight());
			
		}else if(character.getRolClass() == RolClass.DRUID) {

			character.setSpellbook(books.get(random.nextInt(books.size())));
			
		}else if(character.getRolClass() == RolClass.FIGHTER) {
			
			character.setRightWeapon(new Sword());
			character.setLeftWeapon(new Sword());
			
		}else if(character.getRolClass() == RolClass.MONK) {
			
			character.setRightWeapon(new Fist());
			character.setLeftWeapon(new Fist());
			
		}else if(character.getRolClass() == RolClass.PALADIN) {
			
			character.setRightWeapon(random.nextBoolean() ? new Sword() : new Spear());
			character.setLeftWeapon(new Shield());
			character.setSpellbook(new BookOfLight());
			
		}else if(character.getRolClass() == RolClass.RANGER) {
			
			character.setRightWeapon(random.nextBoolean() ? new Sword() : new Spear());
			character.setLeftWeapon(new Shield());
			character.setSpellbook(new BookOfLight());
			
		}else if(character.getRolClass() == RolClass.ROGUE) {
			
			character.setRightWeapon(random.nextBoolean() ? new Rapier() : new Dagger());
			character.setLeftWeapon(new Dagger());
			
		}else if(character.getRolClass() == RolClass.SORCERER) {

			character.setRightWeapon(new Dagger());
			character.setSpellbook(books.get(random.nextInt(books.size())));

			
		}else if(character.getRolClass() == RolClass.WARLOCK) {
			
			character.setRightWeapon(random.nextBoolean() ? new Rapier() : new Dagger());
			character.setSpellbook(books.get(random.nextInt(books.size())));
			
		}else if(character.getRolClass() == RolClass.WIZARD) {
			
			character.setRightWeapon(new Dagger());
			character.setSpellbook(books.get(random.nextInt(books.size())));
			
		}
		
	}

}
