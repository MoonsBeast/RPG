package weapons;

import java.util.Random;

import combat.AttackAction;
import combat.DamageType;

public class Weapon {
	private AttackAction attack;
	private int criticChance; // between 0 and 99
	
	public Weapon(AttackAction attack, int criticChance) {
		
		this.attack = !attack.isSpell() ? attack : new AttackAction("Swing like an Idiot", DamageType.BLUGGEONING, 1);
		this.criticChance = criticChance > 99 ? 99 : (criticChance < 0 ? 0 : criticChance);
		
	}
	
	public void upgradeWeapon(int quantity) {
		this.attack.setDamageValue(this.attack.getDamageValue() + quantity);
	}
	
	public AttackAction doAttack() {
		
		Random Random = new Random();
		AttackAction chosenAttack = this.attack;
		chosenAttack.setCritic(Random.nextInt(100) >= criticChance);
		 
		return chosenAttack;
		
	}
	
	public AttackAction getAttack() {
		return attack;
	}

	public void setAttack(AttackAction attack) {
		this.attack = attack;
	}

	public int getCriticChance() {
		return criticChance;
	}

	public void setCriticChance(int criticChance) {
		this.criticChance = criticChance;
	}
}
