package snake;

import java.util.ArrayList;
import java.util.List;

public class Snake {

	private List<Cord> body;
	private int dX, dY; // direction of movement
	private boolean isAlive;
	private final int WIDTH, HEIGHT;
	private Apple apple;
	private int appleCounter;
	private int stepCounter;

	private final int initLen = 3;

	{
		appleCounter = 0;
		stepCounter = 0;
		body = new ArrayList<>();
//		isAlive = true;
	}

	public Snake(int wIDTH, int hEIGHT) {
		this.WIDTH = wIDTH;
		this.HEIGHT = hEIGHT;
		this.apple = new Apple(WIDTH, HEIGHT);

		setDirection(-1, 0);
		initBody();
		setDirection(0, 0);
		setNewApple();
		isAlive = true;
	}

	public void initBody() {
		Cord head = new Cord(WIDTH / 2, HEIGHT / 2);
		body.add(head);

		for (int i = 1; i < initLen; i++) {
			body.add(new Cord(head.getX() + i * dX, head.getY() + i * dY));
		}

		System.out.println(body);

	}

	public void setAlive() {
		isAlive = true;
	}

//	One Step in the direction of dX, dY
//	in case of eat, the body will not delete the last Point of his

	public void oneForward() {

		if (dX + dY != 0) {
			Cord head = getHead();
			
			body.add(0, new Cord(head.getX() + dX, head.getY() + dY));
			
//			System.out.println(" head " + getHead());
			stepCounter++;
			
			if (foundApple()) {
				setNewApple();
				appleCounter++;
			} else {
				body.remove(body.size() - 1);
			}

			isAlive = !(isOnBoard() || isLoop());
		}
	}

	private boolean foundApple() {
		return getHead().equals(apple.getPoint());
	}

	public boolean isAlive() {
		return isAlive;
	}

	public int getAppleCounter() {
		return appleCounter;
	}
	
	public int getStepCounter() {
		return stepCounter;
	}

//	BoardValidator
	private boolean isOnBoard() {
		Cord head = getHead();
		return (head.getX() == 0 || head.getY() == 0 || head.getX() == WIDTH + 1 || head.getY() == HEIGHT + 1);
	}

//	LoopValidator
	private boolean isLoop() {
		Cord head = getHead();
		for (int i = 1; i < body.size(); i++) {
			if (head.equals(body.get(i)))
				return true;
		}
		return false;
	}

	private void setNewApple() {
		do {
			apple.setNew();
		} while (body.contains(apple.getPoint()));
	}

	public Cord getHead() {
		if (body.size() > 0) {
			return body.get(0);
		} else {
			return null;
		}
	}

	public List<Cord> getBody() {
		return body;
	}

	public Cord getAppleCord() {
		return apple.getPoint();
	}

	public int getWidth() {
		return WIDTH;
	}

	public int getHeight() {
		return HEIGHT;
	}

	public void setDirection(int dX, int dY) {
		this.dX = dX;
		this.dY = dY;
	}

	public Cord getDirection() {
		return new Cord(dX, dY);
	}

	@Override
	public String toString() {
		return "Snake [body=" + body + "]";
	}

}
