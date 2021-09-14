package combat;

public class Spell extends AttackAction {
	
	private int manaCost;
	
	public Spell(String name, DamageType damageType, int damageValue, int manaCost, boolean isCritic) {
		super(name, damageType, damageValue, isCritic);
		this.manaCost = manaCost;
	}

	public Spell(String name, DamageType damageType, int damageValue, int manaCost) {
		super(name, damageType, damageValue);
		this.manaCost = manaCost;
	}

	public Spell(Spell attack) {
		super(attack);
	}

	public int getSpellLevel() {
		return manaCost;
	}

	public void setSpellLevel(int spellLevel) {
		this.manaCost = spellLevel;
	}

}
