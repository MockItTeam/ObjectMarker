import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JComponent;

/**
 * 
 * @author Sarun Wongtanakarn
 *
 */
public class CoordinateLabel extends JComponent {

	private static final long serialVersionUID = 1L;
	
	private int x;
	private int y;

	public CoordinateLabel() {
		setBackground(Color.blue);
	}
	
	public void setCoordinate(Point coor) {
		x = coor.x;
		y = coor.y;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		String s = x + ", " + y;
		g.setColor(Color.red);
		g.drawString(s, x, y);
	}
}
