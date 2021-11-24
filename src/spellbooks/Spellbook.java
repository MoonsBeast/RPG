package spellbooks;

import java.util.ArrayList;
import java.util.Random;
import combat.DamageType;
import combat.Spell;

public class Spellbook {
	protected ArrayList<Spell> spells;
	protected int criticChance; // between 0 and 99
	protected String name;
	
	public Spellbook(ArrayList<Spell> spells, int criticChance, BookList name) {
		
		this.spells = new ArrayList<Spell>();
		this.name = name.toString();
		this.criticChance = criticChance > 99 ? 99 : (criticChance < 0 ? 0 : criticChance);
		this.setSpells(spells);

	}
	
	private ArrayList<Spell> copySpellsOnNewArray(ArrayList<Spell> spells){
		//Copy the spells ito anothe array making diferent instances
		ArrayList<Spell> result = new ArrayList<Spell>();
		
		for(Spell spell : spells) {
			
			try {
				result.add((Spell) spell.clone());
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	public void addSpell(Spell attack) {

		this.spells.add(attack);

	}
	
	public Spell castSpell(String name,int manaAvailable) {
		
		Random Random = new Random();
		ArrayList<Spell> availableSpells = copySpellsOnNewArray(this.spells);
		for(Spell spell : availableSpells) {
			
			if(spell.getName().equalsIgnoreCase(name)) {
				
				if(!(spell.getManaCost() <= manaAvailable)) break;
				
				spell.setCritic(Random.nextInt(100) <= criticChance);
				return spell;
			}
			
		}
		
		return new Spell("Move Hands like an Idiot", DamageType.PSYCHIC, 0, 0);
	}
	
	public Spell castRandomSpell(int manaAvailable) {
			
		 Random Random = new Random();
		 boolean canBeCasted = false;
		 ArrayList<Spell> availableSpells = copySpellsOnNewArray(this.spells);
		 Spell chosenSpell = null;
		 
		 //Tries to cast, if it cant, removes that spell and tries another, if none are possible
		 // does a generic spell that does nothing
		 while(!canBeCasted) {
			 
			 if(availableSpells.size() == 0) {
				 chosenSpell = new Spell("Move Hands like an Idiot", DamageType.PSYCHIC, 0, 0);
				 break;
			 }
			 
			 int randPos = Random.nextInt(availableSpells.size());
			 chosenSpell = availableSpells.get(randPos);
			 
			 canBeCasted = chosenSpell.getManaCost() <= manaAvailable;
			 
			 if(!canBeCasted) availableSpells.remove(randPos);
		 }
		 
		 chosenSpell.setCritic(Random.nextInt(100) <= criticChance);
		 
		 return chosenSpell;
	}
	
	public ArrayList<Spell> getSpells() {
		return spells;
	}

	public void setSpells(ArrayList<Spell> spells) {
		
		this.spells = spells;
	}

	public synchronized int getCriticChance() {
		return criticChance;
	}

	public synchronized void setCriticChance(int criticChance) {
		this.criticChance = criticChance;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
