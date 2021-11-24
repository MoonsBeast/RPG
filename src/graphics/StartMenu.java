package graphics;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import characters.Character;
import characters.FromInputCharacterFactory;
import characters.Race;
import characters.RolClass;
import spellbooks.BookList;
import weapons.WeaponList;

public class StartMenu extends JPanel implements ActionListener{
	
	protected ArrayList<JComboBox<String>> comboOptions;
	protected ArrayList<String> casters, melee, hybrids;
	protected Label topText;
	protected JTextArea bottomText;
	protected JButton acceptButton;
	
	public StartMenu(JFrame frame, JButton nextScreenButton) {
		super(new GridLayout(4,0));
		
		//config the external button
		acceptButton = nextScreenButton;
		acceptButton.setEnabled(false);
		
		//get the real dimensions to work with
		Dimension size = frame.getContentPane().getSize();
		
		this.setPreferredSize(size);
		this.setMaximumSize(size);
		this.setMinimumSize(size);
		
		//dinamically load the comobo boxes and check what kind of combat
		//each class have
		casters = new ArrayList<>();
		melee = new ArrayList<>();
		hybrids = new ArrayList<>();
		
		List<Race> races = Collections.unmodifiableList(Arrays.asList(Race.values()));
		List<RolClass> classes = Collections.unmodifiableList(Arrays.asList(RolClass.values()));
		List<WeaponList> weapons = Collections.unmodifiableList(Arrays.asList(WeaponList.values()));
		List<BookList> books = Collections.unmodifiableList(Arrays.asList(BookList.values()));
		
		JComboBox<String> raceCombo = new JComboBox<String>();
		JComboBox<String> rolCombo = new JComboBox<String>();
		JComboBox<String> rightWeaponCombo = new JComboBox<String>();
		JComboBox<String> leftWeaponCombo = new JComboBox<String>();
		JComboBox<String> bookCombo = new JComboBox<String>();
		
		races.forEach((race) -> raceCombo.addItem(race.toString()));
		classes.forEach((rol) -> { // Add classes and check combat type
			rolCombo.addItem(rol.toString());
			
			Character dummy = new Character("",1, Race.AVEN, rol);
			if(dummy.isCaster() && dummy.isMelee()) {
				hybrids.add(rol.toString());
			}else if(dummy.isCaster()) {
				casters.add(rol.toString());
			}else {
				melee.add(rol.toString());
			}
								});
		weapons.forEach((race) -> {
			rightWeaponCombo.addItem(race.toString());
			leftWeaponCombo.addItem(race.toString());
		});
		books.forEach((book) -> bookCombo.addItem(book.toString()));
		
		//Add listeners for functionality
		raceCombo.addActionListener(this);
		rolCombo.addActionListener(this);
		rightWeaponCombo.addActionListener(this);
		leftWeaponCombo.addActionListener(this);
		bookCombo.addActionListener(this);
		
		//adding options to the comboboxes
		comboOptions = new ArrayList<JComboBox<String>>();
		comboOptions.add(raceCombo);
		comboOptions.add(rolCombo);
		comboOptions.add(rightWeaponCombo);
		comboOptions.add(leftWeaponCombo);
		comboOptions.add(bookCombo);
		
		//text components and final configs
		topText = new Label("Escoge una raza:");
		
		bottomText = new JTextArea("Eres:");
		
		bottomText.setEditable(false);
		rolCombo.setEnabled(false);
		rightWeaponCombo.setEnabled(false);
		leftWeaponCombo.setEnabled(false);
		bookCombo.setEnabled(false);
		
		//adding everything to the frame
		add(topText);
		add(raceCombo);
		add(rolCombo);
		add(rightWeaponCombo);
		add(leftWeaponCombo);
		add(bookCombo);
		add(bottomText);
		add(acceptButton);
	}
	
	public Character getCharacterFromImputs() {
		FromInputCharacterFactory factory = new FromInputCharacterFactory((String)comboOptions.get(0).getSelectedItem(), (String)comboOptions.get(1).getSelectedItem(), (comboOptions.get(2).isEnabled()? (String)comboOptions.get(2).getSelectedItem() : null) , (comboOptions.get(3).isEnabled()? (String)comboOptions.get(3).getSelectedItem() : null), (comboOptions.get(4).isEnabled() ? (String)comboOptions.get(4).getSelectedItem() : null));
		return factory.createCharacter(1);
	}
	
	protected void changeDescription() {
		
		//Compose the string descrption of the character
		String result = "Eres: ";
		
		result += "Un/a " + (String)comboOptions.get(0).getSelectedItem();
		
		if(comboOptions.get(1).isEnabled()) {
			result += ", tu clase es " + (String)comboOptions.get(1).getSelectedItem();
		}
		
		boolean weaponsPretextIsPut = false;
		for(int i = 2; i < 5; i++) {
			
			//begin or continuation of the sentence
			if(!weaponsPretextIsPut && comboOptions.get(i).isEnabled()) {
				
				result += "\n y portas un/a ";
				weaponsPretextIsPut = true;
				
			}else if(comboOptions.get(i).isEnabled()){
				
				result += ", ";
				
			}
			
			//item name
			if(comboOptions.get(i).isEnabled()) {
				
				result += (String)comboOptions.get(i).getSelectedItem();
				
			}	
			
		}
		
		bottomText.setText(result);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == comboOptions.get(0)) {
			
			comboOptions.get(1).setEnabled(true);
			
			if(!comboOptions.get(1).isEnabled()) {
				topText.setText("Ahora, selecciona tu clase:");
			}

		}else if(e.getSource() == comboOptions.get(1)) {
			
			String option = (String)comboOptions.get(1).getSelectedItem();	
			boolean enableWeapon = false, enableBook = false;
			
			//Calculates what weapons can be used and enables that combos
			if(hybrids.contains(option)) {
				
				enableWeapon = true;
				enableBook = true;
				
			}else if(casters.contains(option)){
				
				enableBook = true;
				
			}else {
				
				enableWeapon = true;
				
			}
			
			//Enables what has to be enabled
			comboOptions.get(2).setEnabled(enableWeapon);
			comboOptions.get(3).setEnabled(enableWeapon);
			comboOptions.get(4).setEnabled(enableBook);
			acceptButton.setEnabled(true);

			topText.setText("Y por último, selecciona tus armas:");
			
		}
		
		changeDescription();
	}

}
