package spellbooks;

import java.util.ArrayList;
import java.util.Random;
import combat.DamageType;
import combat.Spell;

public class Spellbook {
	private ArrayList<Spell> spells;
	private int criticChance; // between 0 and 99
	
	public Spellbook(ArrayList<Spell> spells, int criticChance) {
		
		this.spells = new ArrayList<Spell>();
		this.criticChance = criticChance > 99 ? 99 : (criticChance < 0 ? 0 : criticChance);
		this.setSpells(spells);

	}
	
	public ArrayList<Spell> getSpells() {
		return spells;
	}

	public void setSpells(ArrayList<Spell> spells) {
		
		this.spells = spells;
	}
	
	public void addSpell(Spell attack) {

		this.spells.add(attack);

	}
	
	public Spell castSpell(String name) {
		
		Random Random = new Random();
		for(Spell spell : spells) {
			
			if(spell.getName().equalsIgnoreCase(name)) {
				
				Spell chosenSpell = spell;
				chosenSpell.setCritic(Random.nextInt(100) >= criticChance);
				return chosenSpell;
			}
			
		}
		
		return new Spell("Move Hands like an Idiot", DamageType.PSYCHIC, 0, 0);
	}
	
	public Spell castRandomSpell() {
			
		 Random Random = new Random();
		 Spell chosenSpell = this.spells.get(Random.nextInt(this.spells.size()));
		 chosenSpell.setCritic(Random.nextInt(100) >= criticChance);
		 
		 return chosenSpell;
	}
}
