package soundbridge.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import soundbridge.view.factory.PanelFactory;

public class MainFrame {

	public JFrame frame;

	public MainFrame() {
		initialize();
	}

	private void initialize() {
		UIManager.put("ToolTip.background", Color.white);
		UIManager.put("ToolTip.font", new Font("Lucida Grande", Font.PLAIN, 13));
		UIManager.put("ToolTip.foreground", Color.black);
		UIManager.put("ToolTip.border",new LineBorder(Color.BLACK,1));
		
		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setBackground(Color.black);

		frame.getContentPane().add(PanelFactory.getJPanel(PanelFactory.LOGIN, frame, null, null, null, null, null, null));
		frame.revalidate();
		frame.repaint();
	}

}
