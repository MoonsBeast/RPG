package characters;

public interface CharacterFactory {
	
	/**
	 * Creates a character of the desired level
	 * 
	 * @param level the character's level
	 * */
	public Character createCharacter(int level);
}
