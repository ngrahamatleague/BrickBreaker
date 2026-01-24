import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Ball extends GameObject{

	Random random = new Random();
	
	int xVel;
	int yVel;
	
	public Ball(int x, int y, int width, int height) {
		super(x, y, width, height);
		xVel = random.nextInt(8)-4;
		if(xVel == 0) {
			if(random.nextBoolean()) {
				xVel+=2;
			}else {
				xVel-=2;
			}
		}
		yVel = -8;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(x, y, width, height);
	}
	
	@Override
	public void update() {
		x += xVel;
		y += yVel;
		bounceOffEdges();
		super.update();
	}

	private void bounceOffEdges() {
		if(x<0 && xVel<0) {
			x = 0;
			xVel = -xVel;
		}
		if(y<0 && yVel<0) {
			y = 0;
			yVel = -yVel;
		}
		if(x>BrickBreaker.PANEL_WIDTH - width && xVel>0) {
			x = BrickBreaker.PANEL_WIDTH - width;
			xVel = -xVel;
		}
		if(y>BrickBreaker.PANEL_HEIGHT - height && yVel>0) {
			y = BrickBreaker.PANEL_HEIGHT - height;
			yVel = -yVel;
		}
		
	}

	public void hit() {
		yVel = -yVel;
		
	}

	public void left() {
		// TODO Auto-generated method stub
		xVel -= 1;
	}

	public void right() {
		// TODO Auto-generated method stub
		xVel += 1;
	}

	public void up() {
		yVel -= 1;
	}
	
	public void down() {
		yVel += 1;
	}

}
