package gameLogic;

import characters.RandomCharacterFactory;
import combat.Melee;
import combat.Spell;
import characters.Character;

public class MainGameLogic {

	public static void main(String[] args) {
		
		RandomCharacterFactory factory = new RandomCharacterFactory();
		
		Character godofredo = factory.createCharacter(1);
		
		String stats = String.format("-Nivel = %d\n-Vida = %d\n-Mana = %d\n-Raza = %s\n-Clase = %s\n", 
									godofredo.getLevel(),godofredo.getMaxLife(),godofredo.getMaxMana(),godofredo.getRace(),godofredo.getRolClass());
		
		System.out.println(stats);
		
		Melee h1 = null, h2 = null;
		Spell magic  = null;
		
		if(godofredo.canDoAttackWithRightHand()) {
			h1 = godofredo.attackWithRight();
		}
		
		if(godofredo.canDoAttackWithLeftHand()) {
			h2 = godofredo.attackWithLeft();
		}
		
		if(godofredo.canCast()) {
			magic = godofredo.attackWithMagic();
		}
		
		String res = "", format = "<<<%s>>>\n-AtName = %s\n-Damage = %d\n-Base = %d\n-Critic? = %s\n\n";
		
		if(h1 != null) {
			res += String.format(format, "H1", h1.getName(), h1.getProcessedDamageValue(), h1.getDamageValue(), h1.isCritic());
		}
		
		if(h2 != null) {
			res += String.format(format, "H2", h2.getName(), h2.getProcessedDamageValue(), h2.getDamageValue(), h2.isCritic());
		}
		
		if(magic != null) {
			format += "-ManaLeft = %d/%d";
			res += String.format(format, "Magic", magic.getName(), magic.getProcessedDamageValue(), magic.getDamageValue(), magic.isCritic(), godofredo.getActualMana(),godofredo.getMaxMana());
		}
		
		System.out.println(res);
	}

}
