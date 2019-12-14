package graph;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import snake.Cord;
import snake.Snake;

@SuppressWarnings("serial")
public class MyFrame extends JFrame {

	private final int frWIDTH, frHEIGHT;
	private final int sideW;
	private final int rad;
	private final int infoPanelHeight;

	private Snake snake;

	private JLabel appleLabel;
	private JLabel stepLabel;
	private JPanel infoPanel;
	private GraphPanel graph;

	{
		sideW = 70;
		rad = 10;
		infoPanelHeight = 50;

	}

	public MyFrame(Snake snake) {
		this.snake = snake;
		this.frWIDTH = snake.getWidth() * rad + sideW + 10;
		this.frHEIGHT = snake.getHeight() * rad + sideW + infoPanelHeight + 10;
		initComponents();
		initFrame();

	}

//	csinalni egy okossagot amit visszaadja a mouseclick koordinatat...
//	coordinat a frame-en, coordinata a panel-en.

	private JPanel initComponents() {
		JPanel GUI = new JPanel();

		appleLabel = new JLabel("appleLabel");
		stepLabel = new JLabel("stepLabel");
		appleLabel.setForeground(Color.WHITE);
		stepLabel.setForeground(Color.WHITE);

		infoPanel = new JPanel();
		infoPanel.setPreferredSize(new Dimension(snake.getWidth() * rad + 5, infoPanelHeight));
		infoPanel.setLayout(new GridLayout(2, 2));

		GUI.setOpaque(false); // igy atlatszik a Frame hatter szine
		infoPanel.setOpaque(false); // lasd elozo

		graph = new GraphPanel(snake, rad);
		setLabels();

		infoPanel.add(appleLabel);
		infoPanel.add(stepLabel);

		GUI.add(infoPanel);
		GUI.add(graph);
		return GUI;

	}

	private void initFrame() {
		setTitle("Snake Game HAB V2");
		setSize(frWIDTH, frHEIGHT);
		setResizable(false);
//		pack();
		setBackground(Color.DARK_GRAY);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(initComponents());
		setLocationRelativeTo(null);

		addKeyListener(new keyListener());
		requestFocus();

		setVisible(true);

	}

	public void clean() {
		graph.cleanGraphPanel();
	}

	public void setLabels() {
		appleLabel.setText("Apples:  " + snake.getAppleCounter());
		stepLabel.setText("Steps:   " + snake.getStepCounter());
	}

	class keyListener implements KeyListener {

		@Override
		public void keyPressed(KeyEvent e) {
			
			Cord dir = snake.getDirection();
			
			if (dir.equals(new Cord(0,0))) {
				snake.setDirection(1, 0);
				System.out.println("Apple hunting STARTS");
				return;
			}

			int keyCode = e.getKeyCode();

			switch (keyCode) {
			case KeyEvent.VK_RIGHT:
				if (!dir.equals(new Cord(-1, 0))) {
					snake.setDirection(1, 0);
					;
				}
				break;
			case KeyEvent.VK_DOWN:
				if (!dir.equals(new Cord(0, -1))) {
					snake.setDirection(0, 1);
					;
				}
				break;
			case KeyEvent.VK_LEFT:
				if (!dir.equals(new Cord(1, 0))) {
					snake.setDirection(-1, 0);
					;
				}
				break;
			case KeyEvent.VK_UP:
				if (!dir.equals(new Cord(0, 1))) {
					snake.setDirection(0, -1);
					;
				}
				break;
			default:
				break;
			}

		}

		@Override
		public void keyReleased(KeyEvent e) {
		}

		@Override
		public void keyTyped(KeyEvent e) {
		}

	}

}
