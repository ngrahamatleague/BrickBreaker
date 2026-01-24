import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ObjectManager implements KeyListener {

	List<Brick> bricks;
	Ball ball;
	boolean win = false;
	public ObjectManager() {
		
		bricks = new ArrayList<Brick>();
		ball = new Ball(BrickBreaker.PANEL_WIDTH/2, BrickBreaker.PANEL_HEIGHT - 40, 20, 20);
		
		for(int r = 0 ; r < 10; r++) {
			for(int c = 0; c < 20; c++ ) {
				bricks.add(new Brick(c*40,10+r*20, 40, 20));
			}
		}
		
	}
	
	public void draw(Graphics g) {
		for(Brick b : bricks) {
			b.draw(g);
		}
		ball.draw(g);
		if(win) {
			g.setColor(Color.white);
			g.drawString("All done", BrickBreaker.PANEL_WIDTH/2 - 30, BrickBreaker.PANEL_HEIGHT/2);
		}
	}
	

	public void update() {
		ball.update();
		checkCollisions();
		purgeDeadBricks();
		checkWin();
	}

	private void checkWin() {
		if(bricks.size() == 0) {
			win = true;
		}
		
	}

	private void win() {
		// TODO Auto-generated method stub
		
	}

	private void purgeDeadBricks() {
		// TODO Auto-generated method stub
		Iterator<Brick> iter = bricks.iterator();
		while(iter.hasNext()) {
			if(!iter.next().isAlive) {
				iter.remove();
			}
		}
	}

	private void checkCollisions() {

		for(Brick b : bricks) {
			if(b.isAlive && b.collisionBox.intersects(ball.collisionBox)) {
				b.isAlive = false;
				ball.hit();
				break;
			}
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			ball.left();
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			ball.right();
		}
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			ball.up();
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			ball.down();
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	
}
