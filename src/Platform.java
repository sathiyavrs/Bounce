import java.awt.Color;
import java.awt.Graphics;

public class Platform {

	int dx;
	int x;
	int y;
	static int width;
	static int height;

	int ballY;
	int ballRad;
	double ballDy;
	int ballX;
	double ballDx;
	boolean outToLeft = false;

	int lastDistance;

	public Platform() {
		// TODO Auto-generated constructor stub
		dx = -1;
		x = 300;
		y = 300;
		width = 120;
		height = 40;
	}

	public Platform(int x, int y) {
		this.x = x;
		this.y = y;
		dx = -1;
		width = 120;
		height = 40;
	}

	public void update(StartingPoint sp, Ball b) {
		dx = sp.getSpeedOfPlatforms();
		x += dx;
		lastDistance = sp.getDistanceOfLast();
		if (x + width < 0) {
			outToLeft = true;
		}

		checkForCollision(b);
	}

	public int getDx() {
		return dx;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setDx(int dx) {
		this.dx = dx;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	private void checkForCollision(Ball b) {
		// TODO Auto-generated method stub
		ballY = b.getY();
		ballRad = b.getRadius();
		ballDy = b.getDy();
		ballX = b.getX();
		ballDx = b.getDx();

		if (((ballY + ballRad / 2 >= y) && (ballY + ballRad / 2 < y + height
				/ 2))
				&& ((ballX >= x) && (ballX <= x + width)) && (ballDy > 0)) {
			b.setDy(b.getGameDy());

			b.setY(y - ballRad / 2);
		}

		else if ((ballDy < 0)
				&& ((ballX >= x) && (ballX <= x + width))
				&& ((ballY - ballRad / 2 <= y + height) && (ballY - ballRad / 2 > y))) {
			b.setDy(-1 * ballDy);
			b.setY(y + height + (ballRad / 2));
		}

		else if ((ballDx <= 0)
				&& ((ballX - ballRad / 2 <= x + width) && (ballX - ballRad / 2 > x
						+ width / 2))
				&& ((ballY >= y) && (ballY <= y + height))) {
			b.setDx(-1 * ballDx);
			b.setX(x + width + (ballRad / 2));
			if (ballDx == 0) {
				b.setDx(-3);
			}

		} else if ((ballDx >= 0)
				&& ((ballX + ballRad / 2 >= x) && (ballX + ballRad / 2 < x
						+ width / 2))
				&& ((ballY >= y) && (ballY <= y + height))) {
			b.setDx(-1 * ballDx);
			b.setX(x - ballRad / 2);
			if (ballDx == 0) {
				b.setDx(3);
			}

		}

	}

	public void paint(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(x + 2, y + 2, width - 3, height - 3);
		g.setColor(Color.BLACK);
		g.drawRect(x, y, width, height);
		g.drawRect(x + 1, y + 1, width - 2, height - 2);
		g.drawLine(x, y + height / 2, x + width, y + height / 2);
	}
}
