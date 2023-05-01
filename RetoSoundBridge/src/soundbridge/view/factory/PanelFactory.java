package soundbridge.view.factory;

import javax.swing.JFrame;
import javax.swing.JPanel;

import soundbridge.database.pojos.ArtGroup;
import soundbridge.database.pojos.Artist;
import soundbridge.database.pojos.Client;
import soundbridge.view.panels.ArtistProfile;
import soundbridge.view.panels.ChangeSubscription;
import soundbridge.view.panels.Employee;
import soundbridge.view.panels.EmployeeManagesClients;
import soundbridge.view.panels.GroupProfile;
import soundbridge.view.panels.Library;
import soundbridge.view.panels.Login;
import soundbridge.view.panels.Profile;
import soundbridge.view.panels.SignUp;
import soundbridge.view.panels.UpdateClient;



public class PanelFactory {

	public static final String LOGIN = "LOGIN";
	public static final String LIBRARY = "LIBRARY";
	public static final String PROFILE = "PROFILE";
	public static final String SIGNUP = "SIGNUP";
	public static final String CHANGE_SUBSCRIPTION = "CHANGE_SUBSCRIPTION";
	public static final String UPDATE_CLIENT = "UPDATE_CLIENT";
	public static final String EMPLOYEE = "EMPLOYEE";
	public static final String EMPLOYEEMANAGESCLIENTS = "EMPLOYEEMANAGESCLIENTS";
	public static final String ARTIST_PROFILE = "ARTIST_PROFILE";
	public static final String GROUP_PROFILE = "GROUP_PROFILE";

	public static JPanel getJPanel(String panelName, JFrame frame, Client client, Artist artist, ArtGroup artGroup) {
		switch (panelName) {
		case LOGIN:
			return new Login(frame);
		case LIBRARY:
			return new Library(frame, client);
		case PROFILE:
			return new Profile(frame, client);
		case SIGNUP:
			return new SignUp(frame);
		case CHANGE_SUBSCRIPTION:
			return new ChangeSubscription(frame, client);
		case UPDATE_CLIENT:
			return new UpdateClient(frame, client);
		case EMPLOYEE:
			return new Employee(frame);
		case EMPLOYEEMANAGESCLIENTS:
			return new EmployeeManagesClients(frame);
		case ARTIST_PROFILE:
			return new ArtistProfile(frame, client, artist);
		case GROUP_PROFILE:
			return new GroupProfile(frame, client, artGroup);
		default:
			return null;
		}
	}

}
