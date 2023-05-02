package soundbridge.view;

import java.awt.Color;

import javax.swing.JFrame;

import soundbridge.view.factory.PanelFactory;

public class MainFrame {

	public JFrame frame;

	public MainFrame() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setBackground(Color.black);

		frame.getContentPane().add(PanelFactory.getJPanel(PanelFactory.LOGIN, frame, null, null, null, null));
		frame.revalidate();
		frame.repaint();
	}

}
