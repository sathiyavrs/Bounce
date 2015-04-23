import java.awt.Color;
import java.awt.Graphics;

public class GravDown extends Item {

	public GravDown(int x) {
		super(x);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		if (hasNotCollided) {
			g.setColor(Color.GRAY);
			super.paint(g);
		}
	}

	@Override
	public void uponCollision(Ball b) {
		// TODO Auto-generated method stub
		hasNotCollided = false;
		b.setGravity(b.getGravity() - 5);
	}
}
