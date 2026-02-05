import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ObjectManager implements KeyListener {

	List<Brick> bricks;
	List<Ball> balls;

	boolean win = false;
	BrickBreaker brickBreaker;
	Date timeAtStart;
	double winTime;
	public ObjectManager(BrickBreaker brickBreaker) {
		balls = new ArrayList<Ball>();
		bricks = new ArrayList<Brick>();
		for(int i = 0; i < 24; i++) {
			balls.add( new Ball(BrickBreaker.PANEL_WIDTH/2, BrickBreaker.PANEL_HEIGHT - 40, 4, 4) );
		}
		//ball = new Ball(BrickBreaker.PANEL_WIDTH/2, BrickBreaker.PANEL_HEIGHT - 40, 4, 4);
		
		this.brickBreaker = brickBreaker;
		//TODO: make unbreakable bricks gen in lines or random length and direction
		//TODO: run simulation at fast speed (no drawing) to see if possible to win
		for(int r = 0 ; r < 10; r++) {
			for(int c = 0; c < 20; c++ ) {
				bricks.add(new Brick(c*40,100+r*20, 40, 20, c + r*10));
			}
		}
		timeAtStart = new Date();
	}
	
	public void draw(Graphics g) {
		
		if(win) {
			g.setColor(Color.white);
			g.drawString("All done in " + (winTime/1000.0) + " seconds", BrickBreaker.PANEL_WIDTH/2 - 40, BrickBreaker.PANEL_HEIGHT/2);
		}else {
			for(Brick b : bricks) {
				b.draw(g);
			}
			for(Ball b : balls) {
				b.draw(g);
			}
		//	ball.draw(g);
			drawTimeElapsed(g);
		}
	}
	

	private void drawTimeElapsed(Graphics g) {
		Date currentTime = new Date();
		long difference = currentTime.getTime() - timeAtStart.getTime();
		g.setColor(Color.white);
		g.drawString("" + difference/1000.0, 20, BrickBreaker.PANEL_HEIGHT-20);
	}

	public void update() {
		for(Ball b : balls) {
			checkCollisions(b);
		}
		purgeDeadBricks();
		for(Ball b : balls) {
			b.update();
		}
		
		if(!win)
		checkWin();
	}

	private void checkWin() {
		if(bricks.size() == 0) {
			win = true;
			Date currentTime = new Date();
			long difference = currentTime.getTime() - timeAtStart.getTime();
			winTime = difference;
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

	private void checkCollisions(Ball ball) {

		for(int i = bricks.size()-1 ; i >= 0; i--) {
			Brick b = bricks.get(i);
			if(b.isAlive && b.collisionBox.intersects(ball.collisionBox)) {
				// From left
				if(b.breakable) {
				b.isAlive = false;
				}
				if(ball.collisionBox.intersectsLine(b.collisionBox.getMinX(), b.collisionBox.getMaxY(), 
						b.collisionBox.getMaxX(), b.collisionBox.getMaxY())) {
					//System.out.println("from bottom");
					
					//ball.y = (int)b.collisionBox.getMaxY();
					ball.yVel = -ball.yVel;
				}
				else if(ball.collisionBox.intersectsLine(b.collisionBox.getMinX(), b.collisionBox.getMinY(), 
						b.collisionBox.getMinX(), b.collisionBox.getMaxY())) {
					//System.out.println("from left");
				
					//ball.x = (int)b.collisionBox.getMinX();
					ball.xVel = -ball.xVel;
					
				}
				
				//From right
				else if(ball.collisionBox.intersectsLine(b.collisionBox.getMaxX(), b.collisionBox.getMinY(), 
						b.collisionBox.getMaxX(), b.collisionBox.getMaxY())) {
					//System.out.println("from right");
				
					//ball.x = (int)b.collisionBox.getMaxX();
					ball.xVel = -ball.xVel;
				}
				
				//From above
				else if(ball.collisionBox.intersectsLine(b.collisionBox.getMinX(), b.collisionBox.getMinY(), 
						b.collisionBox.getMaxX(), b.collisionBox.getMinY())) {
					//System.out.println("from top");
					
					//ball.y = (int)b.collisionBox.getMinY();
					ball.yVel = -ball.yVel;
				}
				
				break;
				//From below
				
				//bricks.remove(i);
				
				//ball.hit(horizontal);
				//break;
			}
		}
	//	purgeDeadBricks();
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			for(Ball ball : balls)
			ball.left();
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			for(Ball ball : balls)
			ball.right();
		}
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			for(Ball ball : balls)
			ball.up();
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			for(Ball ball : balls)
			ball.down();
		}
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			brickBreaker.restart();
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	
}
