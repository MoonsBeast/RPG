package graphics;

import java.awt.BorderLayout;
import java.awt.Dimension;
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

import characters.Race;
import characters.RolClass;
import spellbooks.BookList;
import weapons.WeaponList;

public class StartMenu extends JPanel implements ActionListener{
	
	protected ArrayList<JComboBox<String>> comboOptions;
	protected JTextArea topText, bottomText;
	protected JButton acceptButton;
	
	public StartMenu(JFrame frame) {
		super(new BorderLayout());
		
		Dimension size = frame.getContentPane().getSize();
		
		this.setPreferredSize(size);
		this.setMaximumSize(size);
		this.setMinimumSize(size);
		
		List<Race> races = Collections.unmodifiableList(Arrays.asList(Race.values()));
		List<RolClass> classes = Collections.unmodifiableList(Arrays.asList(RolClass.values()));
		List<WeaponList> weapons = Collections.unmodifiableList(Arrays.asList(WeaponList.values()));
		List<BookList> books = Collections.unmodifiableList(Arrays.asList(BookList.values()));
		
		JComboBox<String> raceCombo = new JComboBox<String>();
		JComboBox<String> rolCombo = new JComboBox<String>();
		JComboBox<String> weaponCombo = new JComboBox<String>();
		JComboBox<String> bookCombo = new JComboBox<String>();
		
		races.forEach((race) -> raceCombo.addItem(race.toString()));
		classes.forEach((rol) -> rolCombo.addItem(rol.toString()));
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
		
		acceptButton = new JButton("Comenzar aventura");
		acceptButton.setEnabled(false);
		
		topText = new JTextArea("Escoge una raza:");
		topText.setEditable(false);
		
		bottomText = new JTextArea("Eres:");
		bottomText.setEditable(false);
		
		add(topText,BorderLayout.NORTH);
		add(bottomText,BorderLayout.SOUTH);
		//add(acceptButton,BorderLayout.SOUTH);
		add(raceCombo,BorderLayout.EAST);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
