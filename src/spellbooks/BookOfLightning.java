package spellbooks;

import java.util.ArrayList;

import combat.DamageType;
import combat.Spell;

public class BookOfLightning extends Spellbook {

	public BookOfLightning() {
		super(new ArrayList<Spell>(), 5, BookList.LIGHTNING);
		
		ArrayList<Spell> spells = new ArrayList<Spell>();
		
		spells.add(new Spell("Static Charge",DamageType.LIGHTNING, 2, 1));
		spells.add(new Spell("5V Battery",DamageType.LIGHTNING, 5, 2));
		spells.add(new Spell("Car Battery",DamageType.LIGHTNING, 12, 5));
		spells.add(new Spell("Put the Fingers on the Plug",DamageType.LIGHTNING, 100, 10));
		spells.add(new Spell("High Tension",DamageType.LIGHTNING, 380000, 20));
			
		this.setSpells(spells);
	}

}
