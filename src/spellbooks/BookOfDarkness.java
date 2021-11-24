package spellbooks;

import java.util.ArrayList;

import combat.DamageType;
import combat.Spell;

public class BookOfDarkness extends Spellbook {

	public BookOfDarkness() {
		super(new ArrayList<Spell>(), 5, BookList.DARKNESS);
		
		ArrayList<Spell> spells = new ArrayList<Spell>();
		
		spells.add(new Spell("Shadow Strike",DamageType.NECROTIC, 2, 1));
		spells.add(new Spell("Pulse of Darkness",DamageType.NECROTIC, 4, 2));
		spells.add(new Spell("Dusk",DamageType.NECROTIC, 7, 3));
		spells.add(new Spell("Pitch Black",DamageType.NECROTIC, 10, 4));
		spells.add(new Spell("It Who Hides In Shadows",DamageType.NECROTIC, 40, 10));
			
		this.setSpells(spells);
	}

}
