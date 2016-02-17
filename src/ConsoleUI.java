import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;
import javax.swing.JTextArea;

public class ConsoleUI extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private JTextArea console;
	
	public ConsoleUI() {
		init();
	}
	
	private void init() {
		setPreferredSize(new Dimension(800, 200));
		pack();
		
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int height = gd.getDisplayMode().getHeight();
		
		setLocation(30, height - 200);
		console = new JTextArea();
		add(console);
		setVisible(true);
	}
	
	public void append(String text) {
		console.append(text);
	}
	
	public String dumpText() {
		return console.getText();
	}
}
