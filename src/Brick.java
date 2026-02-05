import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Brick extends GameObject{

	Color color;
	Random random = new Random();
	int i;
	boolean breakable;
	public Brick(int x, int y, int width, int height, int i) {
		super(x, y, width, height);
	
		this.i=i;
		breakable = random.nextInt(10) != 0;
		if(breakable) {
		
			color = new Color(random.nextInt(155) + 100, random.nextInt(155) + 100, random.nextInt(155) + 100);
		}else {
			ObjectManager.numUnbreakable++;
			color = new Color(150,150,150);
		}
		
	}
	
	public void draw(Graphics g) {
		g.setColor(color);
		g.fillRect(x, y, width, height);
		g.setColor(Color.white);
		g.drawString("" + i, x, y+10);
	}
	


}
