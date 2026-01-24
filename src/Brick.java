import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Brick extends GameObject{

	Color color;
	Random random = new Random();
	public Brick(int x, int y, int width, int height) {
		super(x, y, width, height);
		color = new Color(random.nextInt(155) + 100, random.nextInt(155) + 100, random.nextInt(155) + 100);
		
	}
	
	public void draw(Graphics g) {
		g.setColor(color);
		g.fillRect(x, y, width, height);
	}
	


}
