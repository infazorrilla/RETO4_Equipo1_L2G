package soundbridge.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import soundbridge.view.factory.PanelFactory;

/**
 * Class that contains the main frame where panels will be displayed.
 */
public class MainFrame {

	public JFrame frame;

	/**
	 * Initializes the class.
	 */
	public MainFrame() {
		initialize();
	}

	/**
	 * Initializes the components.
	 */
	private void initialize() {
		UIManager.put("ToolTip.background", Color.white);
		UIManager.put("ToolTip.font", new Font("Lucida Grande", Font.PLAIN, 13));
		UIManager.put("ToolTip.foreground", Color.black);
		UIManager.put("ToolTip.border", new LineBorder(Color.BLACK, 1));

		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setBackground(Color.black);

		ImageIcon icon = new ImageIcon("img/icon/sbbasic.png");
		Image img = icon.getImage();
		frame.setIconImage(img);

		frame.getContentPane()
				.add(PanelFactory.getJPanel(PanelFactory.LOGIN, frame, null, null, null, null, null, null, null));
		frame.revalidate();
		frame.repaint();
	}

}
