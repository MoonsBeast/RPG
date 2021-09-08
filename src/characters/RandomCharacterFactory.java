package characters;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RandomCharacterFactory implements CharacterFactory {
	
	private static final List<Race> Races = Collections.unmodifiableList(Arrays.asList(Race.values()));
	private static final List<RolClass> Classes = Collections.unmodifiableList(Arrays.asList(RolClass.values()));
	private static final int RacesSize = Races.size(), ClassesSize = Classes.size();
	private static final Random Random = new Random();
	
	@Override
	public Character createCharacter() {
		// TODO Auto-generated method stub
		return new Character(1,Races.get(Random.nextInt(RacesSize)),Classes.get(Random.nextInt(ClassesSize)));
	}

}
