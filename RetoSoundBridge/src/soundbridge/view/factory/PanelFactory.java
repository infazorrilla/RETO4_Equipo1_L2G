package soundbridge.view.factory;

import javax.swing.JFrame;
import javax.swing.JPanel;

import soundbridge.view.panels.Library;
import soundbridge.view.panels.Login;



public class PanelFactory {

	public static final String LOGIN = "LOGIN";
	public static final String LIBRARY = "LIBRARY";

	public static JPanel getJPanel(String panelName, JFrame frame) {
		switch (panelName) {
		case LOGIN:
			return new Login(frame);
		case LIBRARY:
			return new Library(frame);
		default:
			return null;
		}
	}

}
