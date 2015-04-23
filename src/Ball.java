import java.awt.Color;
import java.awt.Graphics;

public class Ball {

	private double gravity = 15;
	private double frictionLoss = 0.95;
	private double energyLoss = 1;
	private double dt = 0.17;
	private boolean landed = false;

	private int x = 320;
	private int y = 25;
	private double dx = 0;
	private double dy = 0;
	private double gameDy = -75;
	private int radius = 50;

	public double getGameDy() {
		return gameDy;
	}

	public void setGameDy(double gameDy) {
		this.gameDy = gameDy;
	}

	public double getGravity() {
		return gravity;
	}

	public double getFrictionLoss() {
		return frictionLoss;
	}

	public double getEnergyLoss() {
		return energyLoss;
	}

	public double getDt() {
		return dt;
	}

	public boolean isLanded() {
		return landed;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public double getDx() {
		return dx;
	}

	public double getDy() {
		return dy;
	}

	public int getRadius() {
		return radius;
	}

	public void setGravity(double gravity) {
		this.gravity = gravity;
	}

	public void setFrictionLoss(double frictionLoss) {
		this.frictionLoss = frictionLoss;
	}

	public void setEnergyLoss(double energyLoss) {
		this.energyLoss = energyLoss;
	}

	public void setDt(double dt) {
		this.dt = dt;
	}

	public void setLanded(boolean landed) {
		this.landed = landed;
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

	public void setDy(double dy) {
		this.dy = dy;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public Ball(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Ball() {

	}

	public void update(StartingPoint point) {

		if (x + dx > point.getWidth() - radius / 2)
			dx = 0;
		else if (x + dx < radius / 2)
			dx = 0;
		else {
			x += dx;
		}
		// Zero gravity

		// if (y + dy > this.getHeight() - radius / 2)
		// dy = -dy;
		// else if (y + dy < radius / 2)
		// dy = -dy;
		// else
		// y += dy;

		// Actual Gravity

		if (y <= point.getHeight() - radius / 2) {

			dy += gravity * dt;
			y += dy * dt + 0.5 * gravity * dt * dt;

		}

//		if (y >= point.getHeight() - radius / 2 - 1) {
//			dx *= frictionLoss;
//			dy = gameDy;
//			y = point.getHeight() - radius / 2 - 1;
//			// System.out.println("Its zero");
//			if (Math.abs(dx) < 0.8)
//				dx = 0;
//		}

		if (y - radius / 2 < 0) {
			dy = -dy;
			y = radius / 2;
		}
		
		if(y+radius/2 > point.getHeight()){
			point.gameOver = true;
		}
	}

	public void paint(Graphics g) {
		g.setColor(Color.RED);
		g.fillOval(x - (radius / 2), y - (radius / 2), radius, radius);

		g.setColor(Color.BLACK);
		radius -= 2;
		g.drawOval(x - (radius / 2), y - (radius / 2), radius, radius);
		radius += 2;
		g.drawOval(x - (radius / 2), y - (radius / 2), radius, radius);

	}

	public void moveToRight() {
		dx = 6;
	}

	public void moveToLeft() {
		dx = -6;
	}

	public void stop() {
		// TODO Auto-generated method stub
		dx = 0;
	}

}
