package soundbridge.view.factory;

import javax.swing.JFrame;
import javax.swing.JPanel;

import soundbridge.database.pojos.Client;
import soundbridge.view.panels.Library;
import soundbridge.view.panels.Login;



public class PanelFactory {

	public static final String LOGIN = "LOGIN";
	public static final String LIBRARY = "LIBRARY";

	public static JPanel getJPanel(String panelName, JFrame frame, Client client) {
		switch (panelName) {
		case LOGIN:
			return new Login(frame);
		case LIBRARY:
			return new Library(frame, client);
		default:
			return null;
		}
	}

}
