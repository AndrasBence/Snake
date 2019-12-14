package graph;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

import javax.swing.JPanel;

import snake.Cord;
import snake.Snake;

@SuppressWarnings("serial")
public class GraphPanel extends JPanel {

	private Snake snake;

	private Color headColor;
	private Color bodyColor;
	private Color appleColor;
	private Color backGndColor1, backGndColor2, color1;
	private final int x0, y0, laneW;

	private final int grWIDTH, grHEIGHT;
	private final int rad;

	{
		headColor = Color.ORANGE;
		bodyColor = Color.YELLOW;
		appleColor = Color.GREEN;
		backGndColor1 = new Color(86, 133, 82);
		backGndColor2 = new Color(80, 122, 77);
//		c1 = new Color(255, 0, 153);
		color1 = Color.BLACK;

		laneW = 10;
		x0 = laneW - 1;
		y0 = laneW - 1;

	}

	public GraphPanel(Snake snake, int rad) {
		this.snake = snake;
		this.rad = rad;
		grWIDTH = snake.getWidth() * rad + 2 * laneW;
		grHEIGHT = snake.getHeight() * rad + 2 * laneW;

		initPanel();
	}

	private void initPanel() {

		setPreferredSize(new Dimension(grWIDTH, grHEIGHT));
		setBackground(color1);

	}

	@Override
	public void paint(Graphics g) {
//		super.paint(g);

		g.setColor(backGndColor2);
//		g.fillRect(1, 1, grWIDTH - 2, grHEIGHT - 2);
		g.fillRect(laneW, laneW, grWIDTH - 2 * laneW - 1, grHEIGHT - 2 * laneW - 1);

		paintElements(g);

	}

	private void paintElements(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		Cord aCord;
		Point p;

//		 Apple paint
		g2d.setColor(appleColor);
		p = toGraphCord(snake.getAppleCord());
		g2d.fillRect(p.x, p.y, rad - 1, rad - 1);
	
		
//		Apples in the Corners - Just for fun
//		p = toGraphCord(new Cord(snake.getWidth(), snake.getHeight()));
//		g2d.fillOval(p.x, p.y, rad - 1, rad - 1);
//		p = toGraphCord(new Cord(1, 1));
//		g2d.fillOval(p.x, p.y, rad - 1, rad - 1);
//		p = toGraphCord(new Cord(snake.getWidth(), 1));
//		g2d.fillOval(p.x, p.y, rad - 1, rad - 1);
//		p = toGraphCord(new Cord(1, snake.getHeight()));
//		g2d.fillOval(p.x, p.y, rad - 1, rad - 1);

//		Snake paint
		ListIterator<Cord> bo = snake.getBody().listIterator();
		aCord = bo.next();
		p = toGraphCord(aCord);

		g2d.setColor(headColor);
//		g2d.setColor(bodyColor);
//		g2d.fillOval(p.x, p.y, rad - 1, rad - 1);
		g2d.fillRect(p.x, p.y, rad - 1, rad - 1);

		g2d.setColor(bodyColor);

		while (bo.hasNext()) {
			aCord = bo.next();
			p = toGraphCord(aCord);
			g2d.setColor(bodyColor);
//			g2d.fillOval(p.x, p.y, rad - 1, rad - 1);
			g2d.fillRect(p.x, p.y, rad - 1, rad - 1);
		}

	}

	public void cleanGraphPanel() {
		collapse(getGraphics());
	}

	private void collapse(Graphics g) {
		List<Cord> all = new ArrayList<>();
		for (int i = 1; i <= 40; i++) {
			for (int j = 1; j <= 40; j++) {
				all.add(new Cord(i, j));
			}
		}

		Collections.shuffle(all);

		Point p;
		g.setColor(Color.DARK_GRAY);

		for (Cord aCord : all) {
			try {
				p = toGraphCord(aCord);
				g.fillRect(p.x, p.y, rad, rad);
				Thread.sleep(1);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	private Point toGraphCord(Cord c) {
		int x = x0 + 1 + (c.getX() - 1) * rad;
		int y = y0 + 1 + (c.getY() - 1) * rad;
		return new Point(x, y);
	}

}
