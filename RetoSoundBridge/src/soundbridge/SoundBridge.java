package soundbridge;

import java.awt.EventQueue;

import soundbridge.view.MainFrame;

/**
 * Class that loads the application.
 */
public class SoundBridge {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
