import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class MouseTracker extends MouseAdapter {

	private Point firstCoor;
	private Point lastCoor;
	private CoordinateLabel coorLabel;
	private List<Rectangle> markings;

	public MouseTracker(CoordinateLabel coorLabel) {
		this.coorLabel = coorLabel;
	}
	
	public void resetMarkings() {
		markings = new ArrayList<Rectangle>();
	}
	
	public List<Rectangle> getMarkings() {
		return markings;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		firstCoor = new Point(e.getX(), e.getY());
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		lastCoor = new Point(e.getX(), e.getY());
		markings.add(calculateMarking());
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		updateCoordinate(e);
	}

	@Override
	public void mouseDragged(MouseEvent e) {

		updateCoordinate(e);
	}

	private void updateCoordinate(MouseEvent e) {
		coorLabel.setCoordinate(e.getX(), e.getY());
		coorLabel.repaint();
	}

	private Rectangle calculateMarking() {
		int x, y, width, height;
		int x0, y0, x1, y1;

		x0 = (int) firstCoor.getX();
		y0 = (int) firstCoor.getY();

		x1 = (int) lastCoor.getX();
		y1 = (int) lastCoor.getY();

		// Drag from {left to right || right to left}
		if (x0 < x1) {
			x = x0;
			width = x1 - x0;
		} else if (x0 > x1) {
			x = x1;
			width = x0 - x1;
		} else {
			x = x0;
			width = 0;
		}

		// Drag from {top to bottom || bottom to top}
		if (y0 < y1) {
			y = y0;
			height = y1 - y0;
		} else if (y0 > y1) {
			y = y1;
			height = y0 - y1;
		} else {
			y = y0;
			height = 0;
		}
		
		if (width == 0 || height == 0) {
			System.out.println("Empty area.");
			return null;
		} else {
			// System.out.println(String.format("%s %s %s %s", x, y, width, height));
			return new Rectangle(x, y, width, height);
		}

	}
}
