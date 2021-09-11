package spellbooks;

import java.util.ArrayList;
import java.util.Random;
import combat.AttackAction;
import combat.DamageType;

public class Spellbook {
	private ArrayList<AttackAction> spells;
	private int criticChance; // between 0 and 99
	
	public Spellbook(ArrayList<AttackAction> spells, int criticChance) {
		
		this.spells = new ArrayList<AttackAction>();
		this.criticChance = criticChance > 99 ? 99 : (criticChance < 0 ? 0 : criticChance);
		this.setSpells(spells);

	}
	
	public ArrayList<AttackAction> getSpells() {
		return spells;
	}

	public void setSpells(ArrayList<AttackAction> spells) {
		
		for(AttackAction spell : spells) {
			
			this.addSpell(spell);
		}
	}
	
	public void addSpell(AttackAction attack) {
		
		if(attack.isSpell()) {
			this.spells.add(attack);
		}
		
	}
	
	public AttackAction castSpell(String name) {
		
		Random Random = new Random();
		for(AttackAction spell : spells) {
			
			if(spell.getName().equalsIgnoreCase(name)) {
				
				AttackAction chosenSpell = spell;
				chosenSpell.setCritic(Random.nextInt(100) >= criticChance);
				return chosenSpell;
			}
			
		}
		
		return new AttackAction("Move Hands like an Idiot", DamageType.PSYCHIC, 0);
	}
	
	public AttackAction castRandomSpell() {
			
		 Random Random = new Random();
		 AttackAction chosenSpell = this.spells.get(Random.nextInt(this.spells.size()));
		 chosenSpell.setCritic(Random.nextInt(100) >= criticChance);
		 
		 return chosenSpell;
	}
}
