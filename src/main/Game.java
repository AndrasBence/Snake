package main;

import graph.MyFrame;
import snake.Snake;

public class Game {

	private final int WIDTH,  HEIGHT;
	private final Speed speed;
	
	private Snake snake;
	private MyFrame frame;

	{
		speed = Speed.HIGH;
		
		WIDTH = 40;
		HEIGHT = WIDTH;
	}

	public Game() {
		snake = new Snake(WIDTH, HEIGHT);
		frame = new MyFrame(snake);
	}

	private void run() {

		play();
		System.out.println("GAME OVER - (A kígyó kipurcant)");

	}

	private void play() {

		System.out.println("isAlive = " + snake.isAlive());

		do {
//			frame.repaint();
			try {
				snake.oneForward();
				frame.setLabels();
//				System.out.println(snake.getHead());

				
				if (snake.isAlive()) {
					frame.repaint();
				}
//				System.out.println("isAlive = " + snake.isAlive());
				Thread.sleep(speed.getTimeValue());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		} while (snake.isAlive());
		
		frame.clean();
	}

	public static void main(String[] args) {

		new Game().run();

	}

}
