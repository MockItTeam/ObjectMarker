import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

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
	public static final float SCALE = 0.1f;

	public ObjectMarkerUI() {
		super("Object Marker");
		initUI();
		initConsole();
		loadFolder("/Users/mapfap/Desktop/images");
		loadNextFile(); // initiate first load.
	}

	private void initUI() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		// setLocationRelativeTo(null);
		setLocation(30, 30);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		coorLabel = new CoordinateLabel();

		JLayeredPane layeredPane = getRootPane().getLayeredPane();
		layeredPane.add(coorLabel, JLayeredPane.DRAG_LAYER);
		
		setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		imagePanel = new ImagePanel();
		layeredPane.add(imagePanel);

		mouseTracker = new MouseTracker(coorLabel, imagePanel);
		addMouseListener(mouseTracker);
		addMouseMotionListener(mouseTracker);

		

		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent we) {
				String ObjButtons[] = { "Yes", "No" };
				int PromptResult = JOptionPane.showOptionDialog(null, "Are you sure you want to exit?",
						"Prompt", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null,
						ObjButtons, ObjButtons[1]);
				if (PromptResult == 0) {
					System.out.println(console.dumpText());
					System.exit(0);
				}
			}
		});

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
		System.out.println("Load Image: " + currentFile.getName());
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
		console.append(currentFile.getName() + " ");
		int size = markings.size();
		if (size == 0) {
			// print nothing.
		} else {
			console.append(size + "");
		}
		for (Rectangle m : markings) {
			console.append(String.format(" %d %d %d %d", m.x, m.y, m.width, m.height));
		}
		console.append("\n");
	}

	private void loadNextFile() {
		do {
			if (nextIndex >= files.length) {
				mouseTracker.resetMarkings();
				System.out.println("Finished all images.");
				currentFile = null;
				return;
			}
			currentFile = files[nextIndex];
			nextIndex += 1;
		} while (!currentFile.getName().toLowerCase().contains(".jpg"));

		loadImage();
	}

}
