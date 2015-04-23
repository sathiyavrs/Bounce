import java.awt.Graphics;
import java.util.Random;

public abstract class Item {
	boolean hasNotCollided = true;
	boolean actionPerformedOnce = false;

	private int x;
	private int y;
	private double dx;

	private int diameter;
	private int radius;

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public double getDx() {
		return dx;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setDx(double dx) {
		this.dx = dx;
	}

	public Item(int x) {
		// TODO Auto-generated constructor stub
		this.x = x;
		Random rand = new Random();
		this.y = rand.nextInt(200) + diameter + 10;
		dx = -2;
		diameter = 20;
		radius = 10;
	}

	public void update(StartingPoint sp, Ball b) {
		dx = sp.getSpeedOfItems();
		x += dx;
		if (x + radius < 0) {
			Random rand = new Random();
			y = rand.nextInt(200) + diameter + 10;
			x = sp.getWidth() + rand.nextInt(50) + 200;
			hasNotCollided = true;
			actionPerformedOnce = false;
		}

		checkForCollision(b);
	}

	private void checkForCollision(Ball b) {
		// TODO Auto-generated method stub
		int ballX = b.getX();
		int ballY = b.getY();
		int ballRad = b.getRadius();
		int xDif = ballX - x;
		int yDif = ballY - y;
		int distance = (int) Math.sqrt(xDif * xDif + yDif * yDif);

		if (distance < ballRad / 2 + radius) {
			if (!actionPerformedOnce) {
				uponCollision(b);
				actionPerformedOnce = true;
			}
		}
	}

	public abstract void uponCollision(Ball b);

	public void paint(Graphics g) {
		g.fillOval(x - radius, y - radius, diameter, diameter);
	}
}
