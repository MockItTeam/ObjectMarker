import java.awt.Color;
import java.awt.Graphics;

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
	
	public void setCoordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		String s = x + ", " + y;
		g.setColor(Color.red);
		g.drawString(s, x, y);
	}
}
