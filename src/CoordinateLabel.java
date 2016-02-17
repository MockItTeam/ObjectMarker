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
	
	private int actualX;
	private int actualY;

	public CoordinateLabel() {
		setBackground(Color.blue);
	}
	
	public void setCoordinate(Point coor) {
		x = (int) coor.x;
		y = (int) coor.y;
		actualX = (int) (coor.x / ObjectMarkerUI.SCALE);
		actualY = (int) (coor.y / ObjectMarkerUI.SCALE);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		String s = actualX + ", " + actualY;
		g.setColor(Color.red);
		g.drawString(s, x, y);
	}
}
