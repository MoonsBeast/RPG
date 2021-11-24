package spellbooks;

import java.util.ArrayList;

import combat.DamageType;
import combat.Spell;

public class BookOfLight extends Spellbook {

	public BookOfLight() {
		
		super(new ArrayList<Spell>(), 5, BookList.LIGHT);
		
		ArrayList<Spell> spells = new ArrayList<Spell>();
		
		spells.add(new Spell("Flash",DamageType.RADIANT, 2, 1));
		spells.add(new Spell("Ray of Hope",DamageType.RADIANT, 5, 2));
		spells.add(new Spell("Dawn",DamageType.RADIANT, 8, 3));
		spells.add(new Spell("Sun Burn",DamageType.RADIANT, 12, 4));
		spells.add(new Spell("God's Punishment",DamageType.RADIANT, 50, 10));
			
		this.setSpells(spells);
	}

}
