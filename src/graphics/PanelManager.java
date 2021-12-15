package graphics;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import characters.Character;
import characters.FromInputCharacterFactory;

public class PanelManager extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	protected boolean isCanvasInPlace = false;
	protected StartMenu menuPanel;
	protected Canvas gameCanvas;
	protected JButton acceptButton;
	protected JTextArea topText, bottomText;
	protected int mouseX = 0, mouseY = 0;
	
	public PanelManager(JFrame frame) {
		super(new BorderLayout());
		
		acceptButton = new JButton("Comenzar aventura");
		acceptButton.addActionListener(this);
		menuPanel = new StartMenu(frame,acceptButton);
		gameCanvas = new Canvas();
		configureScreen(gameCanvas, frame);
		gameCanvas.addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				
				mouseX = e.getX();
				mouseY = e.getY();
				
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {

				mouseX = e.getX();
				mouseY = e.getY();
				
			}
		});

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

	public synchronized int getMouseX() {
		return mouseX;
	}

	public synchronized void setMouseX(int mouseX) {
		this.mouseX = mouseX;
	}

	public synchronized int getMouseY() {
		return mouseY;
	}

	public synchronized void setMouseY(int mouseY) {
		this.mouseY = mouseY;
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
