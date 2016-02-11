import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
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
			g.drawImage(image, 0, 0, null);
			Graphics2D g2 = (Graphics2D) g;
			g2.setStroke(new BasicStroke(2.0f));
			g2.setColor(Color.RED);
			for (Rectangle r: markings) {	
				g.drawRect(r.x, r.y, r.width, r.height);
			}
		}
	}

}