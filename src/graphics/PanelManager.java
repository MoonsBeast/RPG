package graphics;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import characters.Character;
import characters.FromInputCharacterFactory;

public class PanelManager extends JPanel implements ActionListener{
	
	protected boolean isCanvasInPlace = false;
	protected StartMenu menuPanel;
	protected Canvas gameCanvas;
	protected JButton acceptButton;
	protected JTextArea topText, bottomText;
	
	public PanelManager(JFrame frame) {
		super(new BorderLayout());
		
		acceptButton = new JButton("Comenzar aventura");
		acceptButton.addActionListener(this);
		menuPanel = new StartMenu(frame,acceptButton);
		gameCanvas = new Canvas();
		configureScreen(gameCanvas, frame);

		add(menuPanel);
	}
	
	private void configureScreen(Component screen, JFrame frame) {
		screen.setPreferredSize(frame.getContentPane().getSize());
		screen.setMaximumSize(frame.getContentPane().getSize());
		screen.setMinimumSize(frame.getContentPane().getSize());
	}
	
	public synchronized Character getCharacterFromImputs() {
		return this.menuPanel.getCharacterFromImputs();
	}
	
	public Canvas getCanvas() {
		return this.gameCanvas;
	}
	
	public synchronized boolean isCanvasInPlace() {
		return this.isCanvasInPlace;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == acceptButton) {
			
			remove(menuPanel);
			add(gameCanvas);
			isCanvasInPlace = true;

		}
		
		repaint();
		revalidate();
	}

}
