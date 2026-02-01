import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class BrickBreaker extends JPanel implements ActionListener{

	public static final int PANEL_WIDTH = 800;
	public static final int PANEL_HEIGHT = 500;
	
	JFrame window;
	Timer frameTimer;
	Timer updateTimer;
	ObjectManager objMgr;
	
	public void setup(int x, int y) {
		frameTimer = new Timer(1000/60, this);
		updateTimer = new Timer(1000/120, this);
		window = new JFrame();
		window.add(this);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
		objMgr = new ObjectManager(this);
		window.addKeyListener(objMgr);
		window.pack();
		window.setLocation(x, y);
		window.setVisible(true);
	}

	public void startGame() {
		
		frameTimer.start();
		updateTimer.start();
	}
	
	public void restart() {
		window.removeKeyListener(objMgr);
		objMgr = new ObjectManager(this);
		window.addKeyListener(objMgr);
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		g.setColor(Color.black);
		g.fillRect(0, 0, PANEL_WIDTH, PANEL_HEIGHT);
		objMgr.draw(g);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == frameTimer) {
			repaint();
		}else if(e.getSource() == updateTimer) {
			update();
		}
	}

	private void update() {
		objMgr.update();
		
	}
}
