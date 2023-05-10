package soundbridge.view.factory;

import javax.swing.JFrame;

import javax.swing.JPanel;

import soundbridge.database.pojos.Album;
import soundbridge.database.pojos.ArtGroup;
import soundbridge.database.pojos.Artist;
import soundbridge.database.pojos.Client;
import soundbridge.database.pojos.Employee;
import soundbridge.database.pojos.Song;
import soundbridge.view.panels.AlbumView;
import soundbridge.view.panels.ArtistProfile;
import soundbridge.view.panels.ChangeSubscription;
import soundbridge.view.panels.ClientsReviews;
import soundbridge.view.panels.EmployeeMenu;
import soundbridge.view.panels.EmployeeProfile;
import soundbridge.view.panels.EmployeeReviews;
import soundbridge.view.panels.FavouriteSongs;
import soundbridge.view.panels.GroupProfile;
import soundbridge.view.panels.Library;
import soundbridge.view.panels.Login;
import soundbridge.view.panels.ManageClients;

import soundbridge.view.panels.Profile;
import soundbridge.view.panels.SignUp;
import soundbridge.view.panels.SingleView;
import soundbridge.view.panels.Top20View;
import soundbridge.view.panels.UpdateClient;
import soundbridge.view.panels.WriteReview;

public class PanelFactory {

	public static final String LOGIN = "LOGIN";
	public static final String LIBRARY = "LIBRARY";
	public static final String PROFILE = "PROFILE";
	public static final String SIGNUP = "SIGNUP";
	public static final String CHANGE_SUBSCRIPTION = "CHANGE_SUBSCRIPTION";
	public static final String UPDATE_CLIENT = "UPDATE_CLIENT";
	public static final String EMPLOYEE_MENU = "EMPLOYEE_MENU";
	public static final String MANAGE_CLIENTS = "MANAGE_CLIENTS";
	public static final String ARTIST_PROFILE = "ARTIST_PROFILE";
	public static final String GROUP_PROFILE = "GROUP_PROFILE";
	public static final String ALBUM_VIEW = "ALBUM_VIEW";
	public static final String TOP20VIEW = "TOP20VIEW";
	public static final String SINGLE_VIEW = "SINGLE_VIEW";
	public static final String EMPLOYEE_PROFILE = "EMPLOYEE_PROFILE";
	public static final String EMPLOYEE_REVIEWS = "EMPLOYEE_REVIEWS";
	public static final String CLIENTS_REVIEWS = "CLIENTS_REVIEWS";
	public static final String WRITE_REVIEW = "WRITE_REVIEW";
	public static final String FAVOURITE_SONGS = "FAVOURITE_SONGS";
	
	public static JPanel getJPanel(String panelName, JFrame frame, Client client, Employee employee, Artist artist, ArtGroup artGroup,
			Album album, Song song) {
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
		case EMPLOYEE_MENU:
			return new EmployeeMenu(frame, employee);
		case MANAGE_CLIENTS:
			return new ManageClients(frame, employee);
		case ARTIST_PROFILE:
			return new ArtistProfile(frame, client, artist);
		case GROUP_PROFILE:
			return new GroupProfile(frame, client, artGroup);
		case ALBUM_VIEW:
			return new AlbumView(frame, client, album, artist, artGroup);
		case TOP20VIEW:
			return new Top20View(frame, client);
		case SINGLE_VIEW:
			return new SingleView(frame, client, song, artist, artGroup);
		case EMPLOYEE_PROFILE:
			return new EmployeeProfile(frame, employee);
		case EMPLOYEE_REVIEWS:
			return new EmployeeReviews(frame, employee);
		case CLIENTS_REVIEWS:
			return new ClientsReviews(frame, client, album, artist, artGroup);
		case WRITE_REVIEW:
			return new WriteReview(frame, client, album, artist, artGroup);
		case FAVOURITE_SONGS:
			return new FavouriteSongs(frame, client);
		default:
			return null;
		}
	}

}
