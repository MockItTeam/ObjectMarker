import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

/**
 * 
 * @author Sarun Wongtanakarn
 *
 */
public class ImagePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private BufferedImage image;

	public ImagePanel() {
		
	}
	
	public void setImage(BufferedImage image) {
		this.image = image;
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (image != null) {			
			g.drawImage(image, 0, 0, null);
		}
	}

}