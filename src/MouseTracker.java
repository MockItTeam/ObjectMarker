import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseTracker extends MouseAdapter {
	
	
	private Point firstCoor;
	private Point lastCoor;
	private CoordinateLabel coorLabel;
	
	public MouseTracker(CoordinateLabel coorLabel) {
		this.coorLabel = coorLabel;
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		firstCoor = new Point(e.getX(), e.getY());
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		
		lastCoor = new Point(e.getX(), e.getY());
		calculateMarking();
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
	
	private void calculateMarking() {
		int x, y, width, height;
		int x0, y0, x1, y1;
		
		x0 = (int) firstCoor.getX();
		y0 = (int) firstCoor.getY();
		
		x1 = (int) lastCoor.getX();
		y1 = (int) lastCoor.getY();
		
		
		if (x0 == x1 || y0 == y1) {
			System.out.println("Empty Area");
			return;
		}
		
		// flip box.
		if (x0 < x1) {
			x = x0;
			width = x1 - x0;
			if (y0 < y1) {
				// Dragging from top-left to bottom-right
				y = y0;
				height = y1 - y0;
			} else { // y0 > y1
				// Dragging from bottom-left to top-right	
				y = y1;
				height = y0 - y1;
			}
		} else { // x0 > x1
			x = x1;
			width = x0 - x1;
			if (y0 < y1) {
				// Dragging from bottom-right to top-left
				y = y0;
				height = y1 - y0;
			} else { // y0 > y1
				// Dragging from top-right to bottom-left
				y = y1;
				height = y0 - y1;
			}
		}
		
		System.out.println(String.format("%s %s %s %s", x, y, width, height));
	}
}
