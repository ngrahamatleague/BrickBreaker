import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {
	int x, y, width, height;

	Rectangle collisionBox;
	
	boolean isAlive = true;

	public GameObject(int x, int y, int width, int height) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.collisionBox = new Rectangle(x, y, width, height);
	}
	
	public void update() {
		this.collisionBox.setBounds(x, y, width, height);
	}
	
	public abstract void draw(Graphics g);

}
