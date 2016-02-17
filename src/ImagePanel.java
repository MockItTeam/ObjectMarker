import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

/**
 * 
 * @author Sarun Wongtanakarn
 *
 */
public class ImagePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private BufferedImage image;
	
	private List<Rectangle> markings;

	public ImagePanel() {
		markings = new ArrayList<Rectangle>();
	}
	
	public void setImage(BufferedImage image) {
		this.image = image;
		repaint();
	}
	
	public void draw(List<Rectangle> markings) {
		this.markings = markings;
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (image != null) {			
			
			Graphics2D g2 = (Graphics2D) g;
			
			AffineTransform transformer = new AffineTransform();
            transformer.scale(ObjectMarkerUI.SCALE, ObjectMarkerUI.SCALE);
			g2.setTransform(transformer);
			g2.drawImage(image, 0, 0, null);
			
			transformer = new AffineTransform();
            transformer.scale(1, 1);
            g2.setTransform(transformer);
            
			g2.setStroke(new BasicStroke(2.0f));
			g2.setColor(Color.RED);
			for (Rectangle r: markings) {	
				int x = (int) (r.x * ObjectMarkerUI.SCALE);
				int y = (int) (r.y * ObjectMarkerUI.SCALE);
				int width = (int) (r.width * ObjectMarkerUI.SCALE);
				int height = (int) (r.height * ObjectMarkerUI.SCALE);
				g.drawRect(x, y, width, height);
			}
		}
	}

}