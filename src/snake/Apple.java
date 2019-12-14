package snake;

import java.util.Random;

public class Apple {

	private Cord p;
	private final int W, H;

	public Apple(int w, int h) {
		p = new Cord(0, 0);
		this.W = w;
		this.H = h;
	}

	public void setNew() {
		Random rnd = new Random();
		p.setX(rnd.nextInt(W) + 1);
		p.setY(rnd.nextInt(H) + 1);
	}

	public Cord getPoint() {
		return p;
	}

}
