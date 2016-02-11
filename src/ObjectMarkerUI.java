import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

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

	private ConsoleUI console;
	private CoordinateLabel coorLabel;
	private BufferedImage image;
	private ImagePanel imagePanel;
	private MouseTracker mouseTracker;
	private File[] files;
	private File currentFile;
	private int nextIndex = 0;

	public ObjectMarkerUI() {
		super("Object Marker");
		initUI();
		initConsole();
		loadFolder("/Users/mapfap/Desktop/images");
		loadNextFile(); // initiate first load.
	}

	private void initUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		coorLabel = new CoordinateLabel();

		JLayeredPane layeredPane = getRootPane().getLayeredPane();
		layeredPane.add(coorLabel, JLayeredPane.DRAG_LAYER);

		mouseTracker = new MouseTracker(coorLabel);
		addMouseListener(mouseTracker);
		addMouseMotionListener(mouseTracker);

		setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		imagePanel = new ImagePanel();
		layeredPane.add(imagePanel);
		setVisible(true);
	}
	
	private void initConsole() {
		console = new ConsoleUI();
	}

	private void loadFolder(String folderPath) {
		File folder = new File(folderPath);
		files = folder.listFiles();
	}

	private void loadImage() {
		try {
			image = ImageIO.read(currentFile);
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		int width = image.getWidth();
		int height = image.getHeight();

		mouseTracker.resetMarkings();

		imagePanel.setImage(image);
		imagePanel.setBounds(0, 0, width, height);
		setPreferredSize(new Dimension(width, height));
		coorLabel.setBounds(0, 0, width, height);
		pack();
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			exportAllMarkings();
			loadNextFile();
		}
	}

	private void exportAllMarkings() {
		if (currentFile == null) {
			return;
		}
		
		List<Rectangle> markings = mouseTracker.getMarkings();
		System.out.print(currentFile.getName() + " ");
		console.append(currentFile.getName() + " ");
		int size = markings.size();
		if (size == 0) {
			// print nothing.
		} else {			
			System.out.print(size);
			console.append(size + "");
		}
		for (Rectangle m : markings) {
			System.out.print(String.format(" %d %d %d %d", (int) m.getX(), (int) m.getY(), (int) m.getWidth(),
					(int) m.getHeight()));
			
			console.append(String.format(" %d %d %d %d", (int) m.getX(), (int) m.getY(), (int) m.getWidth(),
					(int) m.getHeight()));
		}
		System.out.println();
		console.append("\n");
	}

	private void loadNextFile() {
		do {
			if (nextIndex >= files.length) {
				// System.out.println("No more files.");
				mouseTracker.resetMarkings();
				System.out.println("Finished all images.");
				currentFile = null;
				return;
			}
			currentFile = files[nextIndex];
			nextIndex += 1;
		} while (!currentFile.getName().toLowerCase().contains(".png"));

		loadImage();
	}

}
