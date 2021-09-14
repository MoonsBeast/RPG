package characters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import armor.ArkaniteArmor;
import armor.ArmorPart;
import armor.IronArmor;
import armor.LeatherArmor;

public class RandomCharacterFactory implements CharacterFactory {
	
	private static final List<Race> Races = Collections.unmodifiableList(Arrays.asList(Race.values()));
	private static final List<RolClass> Classes = Collections.unmodifiableList(Arrays.asList(RolClass.values()));
	private static final int RacesSize = Races.size(), ClassesSize = Classes.size();
	private static final Random Random = new Random();
	
	@Override
	public Character createCharacter(int level) {
		
		Race race = Races.get(Random.nextInt(RacesSize));
		RolClass rolClass = Classes.get(Random.nextInt(ClassesSize));
		Character character = new Character(level,race,rolClass);
		armorUp(character);
		weaponUp(character);
		
		return character;
	}
	
	private void armorUp(Character character) {
		
		ArrayList<ArmorPart> parts = new ArrayList<>();
		
		if(character.getRolClass() == RolClass.ARTIFICER) {
			
			parts.add(ArmorPart.ARMS);
			parts.add(ArmorPart.CHEST);
			parts.add(ArmorPart.LEGS);
			
			while(!parts.isEmpty()) {
				
				int pos = Random.nextInt(parts.size());
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
				
				int pos = Random.nextInt(parts.size());
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
				
				int pos = Random.nextInt(parts.size());
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
	
	private void weaponUp(Character character) {
		
	}

}
