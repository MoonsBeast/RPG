package spellbooks;

import java.util.ArrayList;

import combat.DamageType;
import combat.Spell;

public class BookOfMaths extends Spellbook {

	public BookOfMaths() {
		
		super(new ArrayList<Spell>(), 75);
		
		ArrayList<Spell> spells = new ArrayList<Spell>();
		
		spells.add(new Spell("Tedious Sum",DamageType.PSYCHIC, 2, 1));
		spells.add(new Spell("Multiple Multiplications",DamageType.PSYCHIC, 3, 2));
		spells.add(new Spell("Geometry 3D",DamageType.PSYCHIC, 5, 3));
		spells.add(new Spell("Derivation",DamageType.PSYCHIC, 7, 4));
		spells.add(new Spell("Integration",DamageType.PSYCHIC, 11, 5));
			
		this.setSpells(spells);
		
	}
	
	

}
