import javax.swing.SwingUtilities;

/**
 * 
 * @author Sarun Wongtanakarn
 *
 */
public class Main {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new ObjectMarkerUI();
			}
		});
	}
}
