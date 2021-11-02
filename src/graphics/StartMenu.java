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

import characters.Character;
import characters.Race;
import characters.RolClass;
import spellbooks.BookList;
import weapons.WeaponList;

public class StartMenu extends JPanel implements ActionListener{
	
	protected ArrayList<JComboBox<String>> comboOptions;
	protected ArrayList<String> casters, melee, hybrids;
	Label topText, bottomText;
	protected JButton acceptButton;
	
	public StartMenu(JFrame frame, JButton nextScreenButton) {
		super(new GridLayout(4,0));
		
		acceptButton = nextScreenButton;
		acceptButton.setEnabled(false);
		
		Dimension size = frame.getContentPane().getSize();
		
		this.setPreferredSize(size);
		this.setMaximumSize(size);
		this.setMinimumSize(size);
		
		casters = new ArrayList<>();
		melee = new ArrayList<>();
		hybrids = new ArrayList<>();
		
		List<Race> races = Collections.unmodifiableList(Arrays.asList(Race.values()));
		List<RolClass> classes = Collections.unmodifiableList(Arrays.asList(RolClass.values()));
		List<WeaponList> weapons = Collections.unmodifiableList(Arrays.asList(WeaponList.values()));
		List<BookList> books = Collections.unmodifiableList(Arrays.asList(BookList.values()));
		
		JComboBox<String> raceCombo = new JComboBox<String>();
		JComboBox<String> rolCombo = new JComboBox<String>();
		JComboBox<String> weaponCombo = new JComboBox<String>();
		JComboBox<String> bookCombo = new JComboBox<String>();
		
		races.forEach((race) -> raceCombo.addItem(race.toString()));
		classes.forEach((rol) -> {
			rolCombo.addItem(rol.toString());
			Character dummy = new Character(1, Race.AVEN, rol);
			if(dummy.isCaster() && dummy.isMelee()) {
				hybrids.add(rol.toString());
			}else if(dummy.isCaster()) {
				casters.add(rol.toString());
			}else {
				melee.add(rol.toString());
			}
								});
		weapons.forEach((race) -> weaponCombo.addItem(race.toString()));
		books.forEach((book) -> bookCombo.addItem(book.toString()));
		
		raceCombo.addActionListener(this);
		rolCombo.addActionListener(this);
		weaponCombo.addActionListener(this);
		bookCombo.addActionListener(this);
		
		comboOptions = new ArrayList<JComboBox<String>>();
		comboOptions.add(raceCombo);
		comboOptions.add(rolCombo);
		comboOptions.add(weaponCombo);
		comboOptions.add(bookCombo);

		topText = new Label("Escoge una raza:");
		
		bottomText = new Label("Eres:");
		
		rolCombo.setEnabled(false);
		weaponCombo.setEnabled(false);
		bookCombo.setEnabled(false);
		
		add(topText);
		add(new Label(" "));
		add(raceCombo);
		add(rolCombo);
		add(weaponCombo);
		add(bookCombo);
		add(bottomText);
		add(acceptButton);
	}
	
	protected void changeDescription() {
		
		String result = "Eres: ";
		
		result += "Un/a " + (String)comboOptions.get(0).getSelectedItem();
		
		if(comboOptions.get(1).isEnabled()) {
			result += ", tu clase es " + (String)comboOptions.get(1).getSelectedItem();
		}
		
		if(comboOptions.get(2).isEnabled() && comboOptions.get(3).isEnabled()) {
			result += " y portas un/a " + (String)comboOptions.get(2).getSelectedItem() + " y " + (String)comboOptions.get(3).getSelectedItem();
		}else if(comboOptions.get(2).isEnabled() || comboOptions.get(3).isEnabled()){
			String weapon = (String)(comboOptions.get(2).isEnabled() ? comboOptions.get(2).getSelectedItem() : comboOptions.get(3).isEnabled() ?  "libro de " + (String)comboOptions.get(3).getSelectedItem() : "");
			result += " y portas un/a " + weapon;
		}
		
		bottomText.setText(result);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		changeDescription();
		
		if(e.getSource() == comboOptions.get(0)) {
			
			comboOptions.get(1).setEnabled(true);
			
			if(!comboOptions.get(1).isEnabled()) {
				topText.setText("Ahora, selecciona tu clase:");
			}

		}else if(e.getSource() == comboOptions.get(1)) {
			
			String option = (String)comboOptions.get(1).getSelectedItem();	
			boolean enableWeapon = false, enableBook = false;
			
			if(hybrids.contains(option)) {
				
				enableWeapon = true;
				enableBook = true;
				
			}else if(casters.contains(option)){
				
				enableBook = true;
				
			}else {
				
				enableWeapon = true;
				
			}
			
			comboOptions.get(2).setEnabled(enableWeapon);
			comboOptions.get(3).setEnabled(enableBook);
			acceptButton.setEnabled(true);

			topText.setText("Y por último, selecciona tus armas:");
			
		}
	}

}
