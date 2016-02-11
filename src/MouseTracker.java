import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Sarun Wongtanakarn
 *
 */
public class MouseTracker extends MouseAdapter {

	private Point firstCoor;
	private Point lastCoor;
	private CoordinateLabel coorLabel;
	private List<Rectangle> markings;
	private ImagePanel imagePanel;

	public MouseTracker(CoordinateLabel coorLabel, ImagePanel imagePanel) {
		this.coorLabel = coorLabel;
		this.imagePanel = imagePanel;
	}
	
	public void resetMarkings() {
		markings = new ArrayList<Rectangle>();
		imagePanel.draw(markings);
	}
	
	public List<Rectangle> getMarkings() {
		return markings;
	}
	
	private Point getCalibratedCoordination(MouseEvent e) {
		// Y axis is shifted by 20
		// TODO: Find why 20 !
		return new Point(e.getX(), e.getY() - 20);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		firstCoor = getCalibratedCoordination(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		lastCoor = getCalibratedCoordination(e);
		Rectangle marking = calculateMarking();
		if (marking != null) {
			markings.add(marking);
			imagePanel.draw(markings);
		}
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
		// TODO: Why 20?
		coorLabel.setCoordinate(getCalibratedCoordination(e));
		coorLabel.repaint();
	}

	// TODO: Need some unit tests here !
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
			System.out.println(String.format("%s %s %s %s", x, y, width, height));
			return new Rectangle(x, y, width, height);
		}

	}
}
