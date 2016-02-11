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
		System.out.println("FIRST:" + firstCoor);
		System.out.println("LAST:" + lastCoor);
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
}
