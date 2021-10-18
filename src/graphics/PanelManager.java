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
import javax.swing.JTextArea;
import javax.swing.border.Border;

import characters.Race;
import characters.RolClass;
import spellbooks.BookList;
import weapons.WeaponList;

public class PanelManager extends JPanel implements ActionListener{
	
	protected StartMenu menuPanel;
	protected Canvas gameCanvas;
	
	protected JTextArea topText, bottomText;
	
	public PanelManager(JFrame frame) {
		super(new BorderLayout());

		menuPanel = new StartMenu(frame);
		gameCanvas = new Canvas();
		configureScreen(gameCanvas, frame);

		add(menuPanel);
	}
	
	private void configureScreen(Component screen, JFrame frame) {
		screen.setPreferredSize(frame.getContentPane().getSize());
		screen.setMaximumSize(frame.getContentPane().getSize());
		screen.setMinimumSize(frame.getContentPane().getSize());
	}
	
	public Canvas getCanvas() {
		return this.gameCanvas;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}

}
