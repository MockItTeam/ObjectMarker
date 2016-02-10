import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;

/**
 * 
 * @author Sarun Wongtanakarn
 *
 */
public class ObjectMarkerUI extends JFrame implements KeyListener {

	private static final long serialVersionUID = 1L;

	private CoordinateLabel coorLabel;
	private BufferedImage image;
	private ImagePanel imagePanel;
	private File[] files;
	private int currentIndex = 0;
	
	public ObjectMarkerUI() {
		super("Object Marker");
		init();
		loadFolder("/Users/mapfap/Desktop/images");
		loadCurrentFile(); // initiate first load.
	}

	private void init() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
		coorLabel = new CoordinateLabel();

		JLayeredPane layeredPane = getRootPane().getLayeredPane();
		layeredPane.add(coorLabel, JLayeredPane.DRAG_LAYER);

		addMouseMotionListener(new MouseMotionAdapter() {
			
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
		});

		setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		imagePanel = new ImagePanel();
		layeredPane.add(imagePanel);
		setVisible(true);
	}
	

	private void loadFolder(String folderPath) {
		File folder = new File(folderPath);
		files = folder.listFiles();
	}

	private void loadImage(File file) {
		try {
			image = ImageIO.read(file);
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		int width = image.getWidth();
		int height = image.getHeight();

		imagePanel.setImage(image);
		imagePanel.setBounds(0, 0, width, height);
		setPreferredSize(new Dimension(width, height));
		coorLabel.setBounds(0, 0, width, height);
		pack();
		
		System.out.println(file.getName());
	}

	@Override
	public void keyTyped(KeyEvent e) { }

	@Override
	public void keyPressed(KeyEvent e) { }

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode()== KeyEvent.VK_ENTER) {
			loadCurrentFile();
		}
	}
	
	private void loadCurrentFile() {
		File file;
		
		do {
			if (currentIndex >= files.length) {
				System.out.println("No more files.");
				return;
			}
			file = files[currentIndex];
			currentIndex += 1;
		} while (! file.getName().toLowerCase().contains(".png"));
		
		loadImage(file);
	}

}
