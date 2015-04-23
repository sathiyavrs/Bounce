import java.awt.Color;
import java.awt.Graphics;

public class ScorePlus extends Item {

	private StartingPoint applet;

	public ScorePlus(int x, StartingPoint sp) {
		super(x);
		applet = sp;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void uponCollision(Ball b) {
		// TODO Auto-generated method stub
		hasNotCollided = false;
		applet.setScore(applet.getScore() + 1000);
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.CYAN);
		if (hasNotCollided)
			super.paint(g);
	}
}
