import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

public class StartingPoint extends Applet implements Runnable, KeyListener {

	private Graphics doubleG;
	private Image img;

	private Ball b;
	private ArrayList<Platform> plats;
	private ArrayList<Item> items;

	private int numberOfPlatforms = 6;
	private int numberOfItems = 2;
	private int distanceOfLast = 100;

	private int speedOfPlatforms = -2;
	private int speedOfItems = -4;
	private int incSpeed = 0;
	private int score = 0;

	private int xPlat;
	private Random rand;
	private int yPlat;
	private int xInc = 200;
	private int yInc = 200;
	private int temp;
	private int j;

	private boolean over500 = false;
	private boolean removed500 = false;

	private boolean over1000 = false;
	private boolean removed1000 = false;

	// private boolean over2000 = false;
	// private boolean removed2000 = false;

	public boolean gameOver = true;
	Font font;

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getSpeedOfPlatforms() {
		return speedOfPlatforms;
	}

	public int getSpeedOfItems() {
		return speedOfItems;
	}

	public void setSpeedOfPlatforms(int speedOfPlatforms) {
		this.speedOfPlatforms = speedOfPlatforms;
	}

	public void setSpeedOfItems(int speedOfItems) {
		this.speedOfItems = speedOfItems;
	}

	Thread thread;
	private static final long serialVersionUID = 1L;

	public int getDistanceOfLast() {
		return distanceOfLast;
	}

	public void setDistanceOfLast(int distanceOfLast) {
		this.distanceOfLast = distanceOfLast;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		addKeyListener(this);
		setSize(640, 500);
		isFocusable();
		rand = new Random();

	}

	@Override
	public void update(Graphics g) {

		if (img == null) {
			img = createImage(this.getWidth(), this.getHeight());
			doubleG = img.getGraphics();
		}

		doubleG.setColor(getBackground());
		doubleG.fillRect(0, 0, this.getWidth(), this.getHeight());

		doubleG.setColor(getForeground());
		paint(doubleG);

		g.drawImage(img, 0, 0, this);
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub
		thread = new Thread(this);
		b = new Ball();

		plats = new ArrayList<Platform>();
		Random rand = new Random();
		plats.add(new Platform(200, rand.nextInt(20) + 300));

		for (int i = 1; i < numberOfPlatforms; i++) {
			createPlatforms(plats, i);
		}

		items = new ArrayList<Item>();
		for (int i = 0; i < numberOfItems; i++) {
			if (i == 0)
				items.add(new GravUp(rand.nextInt(3200) + 20));
			else if (i == 1)
				items.add(new GravDown(rand.nextInt(3200) + 20));
			else
				items.add(new ScorePlus(rand.nextInt(3200) + 20, this));
		}

		thread.start();
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		if (!gameOver) {

			b.paint(g);

			for (int i = 0; i < numberOfItems; i++) {
				items.get(i).paint(g);
			}
			for (int i = 0; i < numberOfPlatforms; i++) {
				plats.get(i).paint(g);
			}

			Font font = new Font("Times New Roman", Font.BOLD, 32);
			g.setFont(font);

			g.drawString("" + score, 60 + 1, 60 + 1);
			g.setColor(Color.MAGENTA);
			g.drawString("" + score, 60, 60);
		} else {
			font = new Font("Times New Roman", Font.BOLD, 32);
			g.setFont(font);

			g.setColor(Color.BLACK);

			g.drawString("Press Enter to play", 100, 100);
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			// b2.update(this);
			score += 1000 / b.getX();
			incSpeed++;
			b.update(this);

			if (score > 500 && !over500) {
				// numberOfPlatforms--;
				xInc += 65;
				over500 = true;
			}

			if (score > 1000 && !over1000) {
				// numberOfPlatforms--;
				xInc += 65;
				over1000 = true;
			}

			for (int i = 0; i < numberOfItems; i++) {
				items.get(i).update(this, b);
			}
			for (int i = 0; i < numberOfPlatforms; i++) {
				plats.get(i).update(this, b);
			}

			for (int i = 0; i < numberOfPlatforms; i++) {
				if (plats.get(i).getX() + Platform.width < 0) {
					updatePlatforms(plats, i);

					if (over500 && !removed500) {
						plats.remove(i);
						removed500 = true;
						numberOfPlatforms--;
					}

					if (over1000 && !removed1000) {
						plats.remove(i);
						removed1000 = true;
						numberOfPlatforms--;
					}
				}
			}

			repaint();
			if (incSpeed > 60) {
				for (int i = 0; i < numberOfItems; i++) {
					items.get(i).setDx(items.get(i).getDx() - 2);
				}
				for (int i = 0; i < numberOfPlatforms; i++) {
					plats.get(i).setDx(plats.get(i).getDx() - 1);
				}
				incSpeed = 0;
			}
			try {
				Thread.sleep(17);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

		switch (e.getKeyCode()) {

		case KeyEvent.VK_LEFT:
			b.moveToLeft();
			break;

		case KeyEvent.VK_RIGHT:
			b.moveToRight();
			break;
		case KeyEvent.VK_ENTER:
			if (gameOver) {
				b = new Ball();

				plats.clear();
				plats.add(new Platform(200, rand.nextInt(20) + 300));

				for (int i = 1; i < numberOfPlatforms; i++) {
					createPlatforms(plats, i);
				}
				numberOfPlatforms = 6;

				items.clear();
				for (int i = 0; i < numberOfItems; i++) {
					if (i == 0)
						items.add(new GravUp(rand.nextInt(3200) + 20));
					else if (i == 1)
						items.add(new GravDown(rand.nextInt(3200) + 20));
					else
						items.add(new ScorePlus(rand.nextInt(3200) + 20, this));
				}
				score = 0;
				gameOver = false;
			}
			break;

		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		switch (e.getKeyCode()) {

		case KeyEvent.VK_LEFT:
			b.stop();
			break;

		case KeyEvent.VK_RIGHT:
			b.stop();
			break;
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public void createPlatforms(ArrayList<Platform> platforms, int i) {

		if (i == 0) {
			i = numberOfPlatforms - 1;
		}

		yPlat = platforms.get(i - 1).getY() + Platform.height - yInc;
		xPlat = platforms.get(i - 1).getX() + Platform.width;
		yPlat += rand.nextInt(yInc * 2);

		if (yPlat < 100) {
			yPlat = 100;
		}

		if (yPlat > 400) {
			yPlat = 400;
		}
		if (yPlat < 350)
			xPlat += rand.nextInt(120) + 10;
		else {
			try {
				temp = xInc;
			} catch (Exception e) {
				temp = xInc;
			}
			if (temp < 0) {
				temp *= -1;
			}

			if (temp == 0) {
				temp = 10;
			}
			xPlat += rand.nextInt(temp) + 10;
		}
		platforms.add(new Platform(xPlat, yPlat));
	}

	public void updatePlatforms(ArrayList<Platform> platforms, int i) {
		j = i;
		if (i == 0) {
			i = numberOfPlatforms;
		}

		yPlat = platforms.get(i - 1).getY() + Platform.height - yInc;
		xPlat = platforms.get(i - 1).getX() + Platform.width;
		yPlat += rand.nextInt(yInc * 2);

		if (yPlat < 100) {
			yPlat = 100;
		}

		if (yPlat > 450) {
			yPlat = 450;
		}
		if (yPlat < 350)
			xPlat += rand.nextInt(120) + 10;
		else {

			temp = xInc;

			xPlat += rand.nextInt(temp) + 10;
		}

		platforms.get(j).setX(xPlat);
		platforms.get(j).setY(yPlat);

	}
}