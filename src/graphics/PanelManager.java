package graphics;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

import characters.Race;
import characters.RolClass;
import spellbooks.BookList;
import weapons.WeaponList;

public class PanelManager extends JPanel implements ActionListener{
	
	protected JPanel screen1;
	protected Canvas screen2;
	protected ArrayList<JComboBox<String>> comboOptions;
	
	public PanelManager(JFrame frame) {
		super(new BorderLayout());

		screen1 = new JPanel();
		screen2=new Canvas();
		configureScreen(screen1, frame);
		configureScreen(screen2, frame);
		
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
		
		comboOptions = new ArrayList<JComboBox<String>>();
		comboOptions.add(raceCombo);
		comboOptions.add(rolCombo);
		comboOptions.add(weaponCombo);
		comboOptions.add(bookCombo);
		
		screen1.add(raceCombo,BorderLayout.NORTH);
		screen1.add(rolCombo,BorderLayout.NORTH);
		
		add(screen1);
	}
	
	private void configureScreen(Component screen, JFrame frame) {
		screen.setPreferredSize(frame.getContentPane().getSize());
		screen.setMaximumSize(frame.getContentPane().getSize());
		screen.setMinimumSize(frame.getContentPane().getSize());
	}
	
	public Canvas getCanvas() {
		return this.screen2;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
