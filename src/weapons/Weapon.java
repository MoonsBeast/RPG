package weapons;

import java.util.Random;

import combat.AttackAction;
import combat.Melee;

public class Weapon {
	private Melee attack;
	private int criticChance; // between 0 and 99
	private int numOfHadsRequired;
	
	public Weapon(Melee attack, int criticChance, int numOfHadsRequired) {
		
		this.attack = attack;
		setCriticChance(criticChance);
		setNumOfHadsRequired(numOfHadsRequired);
		
	}
	
	public void upgradeWeapon(int quantity) {
		this.attack.setDamageValue(this.attack.getDamageValue() + quantity);
	}
	
	public Melee doAttack() {
		
		Random Random = new Random();
		Melee chosenAttack = this.attack;
		chosenAttack.setCritic(Random.nextInt(100) <= criticChance);
		 
		return chosenAttack;
	}
	
	public AttackAction getAttack() {
		return attack;
	}

	public void setAttack(Melee attack) {
		this.attack = attack;
	}

	public int getCriticChance() {
		return criticChance;
	}

	public void setCriticChance(int criticChance) {
		this.criticChance = criticChance > 99 ? 99 : (criticChance < 0 ? 0 : criticChance);
	}

	public int getNumOfHadsRequired() {
		return numOfHadsRequired;
	}

	public void setNumOfHadsRequired(int numOfHadsRequired) {
		this.numOfHadsRequired = numOfHadsRequired > 2 ? 2 : (numOfHadsRequired < 1 ? 1 : numOfHadsRequired);
	}
}
