package soundbridge.controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import soundbridge.database.managers.AlbumManager;
import soundbridge.database.managers.ArtGroupManager;
import soundbridge.database.managers.ArtistManager;
import soundbridge.database.managers.ClientManager;
import soundbridge.database.managers.ClientPManager;
import soundbridge.database.managers.ClientPPManager;
import soundbridge.database.managers.ContainManager;
import soundbridge.database.managers.EmployeeManager;
import soundbridge.database.managers.PlayManager;
import soundbridge.database.managers.PlaylistManager;
import soundbridge.database.managers.SongManager;
import soundbridge.database.pojos.Album;
import soundbridge.database.pojos.ArtGroup;
import soundbridge.database.pojos.Artist;
import soundbridge.database.pojos.Client;
import soundbridge.database.pojos.ClientP;
import soundbridge.database.pojos.ClientPP;
import soundbridge.database.pojos.Contain;
import soundbridge.database.pojos.Employee;
import soundbridge.database.pojos.Play;
import soundbridge.database.pojos.Playlist;
import soundbridge.database.pojos.Song;
import soundbridge.database.views.managers.AverageStarsManager;
import soundbridge.database.views.managers.Top20ViewManager;
import soundbridge.database.views.pojos.AverageStars;
import soundbridge.view.components.AutoCompleteTextField;

public class Controller {

	private ClientManager clientManager = null;
	private EmployeeManager employeeManager = null;
	private AlbumManager albumManager = null;
	private SongManager songManager = null;
	private ClientPManager clientPManager = null;
	private ClientPPManager clientPPManager = null;
	private ArtistManager artistManager = null;
	private ArtGroupManager artGroupManager = null;

	public boolean isEmployee(JTextField textFieldUserLogIn, JTextField passwordFieldLogIn)
			throws SQLException, Exception {
		String username = textFieldUserLogIn.getText();
		String passwd = passwordFieldLogIn.getText();

		if (null == employeeManager)
			employeeManager = new EmployeeManager();

		return employeeManager.askForEmployeeUsingIdAndPasswd(username, passwd);
	}

	public boolean isClient(JTextField textFieldUserLogIn, JTextField passwordFieldLogIn)
			throws SQLException, Exception {
		String username = textFieldUserLogIn.getText();
		String passwd = passwordFieldLogIn.getText();

		if (null == clientManager)
			clientManager = new ClientManager();

		return clientManager.askForClientUsingIdAndPasswd(username, passwd);
	}

	public Client clientByUsername(String username) throws SQLException, Exception {
		if (null == clientManager)
			clientManager = new ClientManager();

		return clientManager.getClientByUsername(username);
	}

	public Employee employeeByUsername(String username) throws SQLException, Exception {
		if (null == employeeManager)
			employeeManager = new EmployeeManager();

		return employeeManager.doSelectAllUsingUsername(username);
	}

	public void deleteAccount(Client client) throws SQLException, Exception {
		if (null == clientManager)
			clientManager = new ClientManager();

		clientManager.delete(client);
	}

	public void addPossibilitiesToSearchBar(AutoCompleteTextField text) throws SQLException, Exception {
		ArtistManager artistManager = new ArtistManager();
		ArtGroupManager artGroupManager = new ArtGroupManager();

		ArrayList<Artist> artists = null;
		ArrayList<ArtGroup> groups = null;

		artists = (ArrayList<Artist>) artistManager.doSelectAll();
		if (artists != null) {
			for (Artist artist : artists) {
				if (artist.getArtGroup() == null) {
					text.addPossibility(artist.getName());
				}
			}
		}

		groups = (ArrayList<ArtGroup>) artGroupManager.doSelectAll();
		if (groups != null) {
			for (ArtGroup group : groups) {
				text.addPossibility(group.getName());
			}
		}

	}

	public void updateClient(Client client, JComboBox<String> combo, JTextField textNation, JTextField textBirth,
			JTextArea textAddr, JTextField textPhone, JTextField textEmail, JTextField textBank)
			throws ParseException, SQLException, Exception {
		client.setGender(combo.getSelectedItem().toString());
		client.setNationality(textNation.getText());

		String birthDate = textBirth.getText();
		Date date = new SimpleDateFormat("dd/MM/yyyy").parse(birthDate);

		client.setBirthDate(date);
		client.setAddress(textAddr.getText());
		client.setTelephone(textPhone.getText());
		client.setEmail(textEmail.getText());

		if (null == clientManager)
			clientManager = new ClientManager();
		if (null == clientPManager)
			clientPManager = new ClientPManager();
		if (null == clientPPManager)
			clientPPManager = new ClientPPManager();

		clientManager.update(client);

		if (client instanceof ClientP) {
			((ClientP) client).setBankAccount(textBank.getText());
			clientPManager.update((ClientP) client);
		} else if (client instanceof ClientPP) {
			((ClientPP) client).setBankAccount(textBank.getText());
			clientPPManager.update((ClientPP) client);
		}
	}

	public void changePasswdClient(Client client, JPasswordField passwd1, JPasswordField passwd2)
			throws SQLException, Exception {
		if (null == clientManager)
			clientManager = new ClientManager();

		client.setPasswd(String.valueOf(passwd1.getPassword()));

		clientManager.update(client);
	}

	public void changePasswdEmployee(Employee employee, JPasswordField passwd1, JPasswordField passwd2)
			throws SQLException, Exception {
		if (null == employeeManager)
			employeeManager = new EmployeeManager();

		employee.setPasswd(String.valueOf(passwd1.getPassword()));

		employeeManager.update(employee);
	}

	public void insertPlay(Play play) throws SQLException, Exception {
		PlayManager playManager = new PlayManager();
		playManager.insert(play);
	}

	public AverageStars getAverageStars(Album album) throws SQLException, Exception {
		AverageStarsManager averageStarsManager = new AverageStarsManager();
		return averageStarsManager.getAverageStarsByAlbum(album);
	}

	public ArrayList<Album> getAlbumsByArtist(Artist artist) throws SQLException, Exception {
		if (null == albumManager)
			albumManager = new AlbumManager();

		return (ArrayList<Album>) albumManager.albumsByArtist(artist);
	}

	public ArrayList<Song> getSongsByAlbumAndArtist(Album album, Artist artist) throws SQLException, Exception {
		if (null == songManager)
			songManager = new SongManager();

		return songManager.getSongsByAlbumWithArtist(album, artist);
	}

	public ArrayList<Song> getSinglesByArtist(Artist artist) throws SQLException, Exception {
		if (null == songManager)
			songManager = new SongManager();

		return (ArrayList<Song>) songManager.getSinglesByArtist(artist);
	}

	public ArrayList<Album> getAlbumsByGroup(ArtGroup artGroup) throws SQLException, Exception {
		if (null == albumManager)
			albumManager = new AlbumManager();

		return (ArrayList<Album>) albumManager.albumsByArtGroup(artGroup);
	}

	public ArrayList<Song> getSongsByAlbumAndGroup(Album album, ArtGroup artGroup) throws SQLException, Exception {
		if (null == songManager)
			songManager = new SongManager();

		return songManager.getSongsByAlbumWithGroup(album, artGroup);
	}

	public ArrayList<Song> getSinglesByGroup(ArtGroup artGroup) throws SQLException, Exception {
		if (null == songManager)
			songManager = new SongManager();

		return (ArrayList<Song>) songManager.getSinglesByGroup(artGroup);
	}

	public void changeClientSubscription(Client client, String bankNumber, String newSubscription)
			throws SQLException, Exception {
		if (null == clientManager)
			clientManager = new ClientManager();

		clientManager.changeSubscription(client.getId(), bankNumber, newSubscription);
	}

	public ClientP getPremiumClient(Client client) throws SQLException, Exception {
		if (null == clientPManager)
			clientPManager = new ClientPManager();

		return clientPManager.getClientPById(client.getId());
	}

	public ClientPP getPremiumPlusClient(Client client) throws SQLException, Exception {
		if (null == clientPPManager)
			clientPPManager = new ClientPPManager();

		return clientPPManager.getClientPPById(client.getId());
	}

	public Artist searchedArtist(String search) throws SQLException, Exception {
		if (null == artistManager)
			artistManager = new ArtistManager();

		return artistManager.getArtistByName(search);
	}

	public ArtGroup searchedGroup(String search) throws SQLException, Exception {
		if (null == artGroupManager)
			artGroupManager = new ArtGroupManager();

		return artGroupManager.getArtGroupByName(search);
	}

	public void insertClient(Client client) throws SQLException, Exception {
		ClientManager clientManager = new ClientManager();
		clientManager.insert(client);
	}

	public void insertClientP(ClientP clientp) throws SQLException, Exception {
		ClientPManager clientpManager = new ClientPManager();
		clientpManager.insertReal(clientp);
	}

	public void insertClientPP(ClientPP clientpp) throws SQLException, Exception {
		ClientPPManager clientppManager = new ClientPPManager();
		clientppManager.insertReal(clientpp);
	}

	public void updateClient(Client client) throws SQLException, Exception {
		ClientManager clientManager = new ClientManager();
		clientManager.update(client);
	}

	public Client getClientByUsername(String username) throws SQLException, Exception {
		ClientManager clientManager = new ClientManager();
		return clientManager.getClientByUsername(username);
	}

	public ArrayList<Client> getAllClients() throws SQLException, Exception {
		ClientManager clientManager = new ClientManager();

		return (ArrayList<Client>) clientManager.doSelectAll();
	}

	public ArrayList<Song> getTop20Songs() throws SQLException, Exception {
		Top20ViewManager top20manager = new Top20ViewManager();

		return (ArrayList<Song>) top20manager.selectViewTop20AndSongs();
	}

	public Artist getArtistById(int id) throws SQLException, Exception {
		ArtistManager artman = new ArtistManager();
		return artman.selectArtistById(id);
	}

	public ArtGroup getGroupById(int id) throws SQLException, Exception {
		ArtGroupManager artGroup = new ArtGroupManager();
		return artGroup.selectGroupById(id);
	}

	public void insertSongPLayList(Contain contain) throws SQLException, Exception {
		ContainManager contMan = new ContainManager();
		contMan.insert(contain);
	}

	public ArrayList<Playlist> getPlaylistsOfClientPPById(Client client) throws SQLException, Exception {
		PlaylistManager playMan = new PlaylistManager();

		return (ArrayList<Playlist>) playMan.getPlaylistsOfClientPPById(client);
	}

	public ArrayList<Playlist> getPlaylistsOfClientPById(Client client) throws SQLException, Exception {
		PlaylistManager playMan = new PlaylistManager();

		return (ArrayList<Playlist>) playMan.getPlaylistsOfClientPById(client);
	}

	public void addToFavourites(Client client, ArrayList<Song> songs, JTable table) {

		if (client instanceof ClientPP) {
			Controller controller = new Controller();
			Contain contain = new Contain();
			ArrayList<Playlist> songsById;
			Playlist playlist = new Playlist();
			Playlist playlistt = new Playlist();
			Song song = new Song();
			try {
				songsById = controller.getPlaylistsOfClientPPById(client);
				playlist = songsById.get(0);
				playlistt.setId(playlist.getId());
				contain.setPlaylist(playlistt);
				song.setId(songs.get(table.getSelectedRow()).getId());
				contain.setSong(song);
				controller.insertSongPLayList(contain);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (client instanceof ClientP) {
			Controller controller = new Controller();
			Contain contain = new Contain();
			ArrayList<Playlist> songsById;
			Playlist playlist = new Playlist();
			Playlist playlistt = new Playlist();
			Song song = new Song();
			try {
				songsById = controller.getPlaylistsOfClientPById(client);
				playlist = songsById.get(0);
				playlistt.setId(playlist.getId());
				contain.setPlaylist(playlistt);
				song.setId(songs.get(table.getSelectedRow()).getId());
				contain.setSong(song);
				controller.insertSongPLayList(contain);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public ArrayList<Song> selectFavouriteSongOfClientPP(Client client) throws SQLException, Exception {
		SongManager songMan = new SongManager();

		return (ArrayList<Song>) songMan.selectFavouriteSongOfClientPP(client);
	}

	public ArrayList<Song> selectFavouriteSongOfClientP(Client client) throws SQLException, Exception {
		SongManager songMan = new SongManager();

		return (ArrayList<Song>) songMan.selectFavouriteSongOfClientP(client);
	}

	public boolean isLetterStringCorrect(JTextField textField) {
		boolean ret = true;
		String str = textField.getText();

		if (str.isBlank()) {
			ret = false;
		} else {
			if (!str.matches("^[\\D\\s]*$"))
				ret = false;
		}

		return ret;
	}

	public boolean isNotEmptyText(JTextField textField) {
		boolean ret = true;
		String str = textField.getText();

		if (str.isBlank()) {
			ret = false;
		}

		return ret;
	}

	public boolean isNotEmptyTextArea(JTextArea textArea) {
		boolean ret = true;
		String str = textArea.getText();

		if (str.isBlank()) {
			ret = false;
		}

		return ret;
	}

	public boolean isLengthCorrectNumeric(JTextField textField, int length) {
		boolean ret = true;
		String str = textField.getText();

		if (str.isBlank()) {
			ret = false;
		} else {
			if ((str.length() < length) || (!str.matches("^\\d{" + length + "}$"))) {
				ret = false;
			}
		}

		return ret;
	}

	public boolean isLengthCorrect(JTextField textField, int length) {
		boolean ret = true;
		String str = textField.getText();

		if (str.isBlank()) {
			ret = false;
		} else {
			if (str.length() < length) {
				ret = false;
			}
		}

		return ret;
	}
	
	public boolean isLengthCorrectNumericSeconds(JTextField textField, int minLength) {
		boolean ret = true;
		String str = textField.getText();

		if (str.isBlank()) {
			ret = false;
		} else {
			if ((str.length() < minLength) || (!str.matches("^\\d{2,3}$"))) {
				ret = false;
			}
		}

		return ret;
	}

	public boolean isLengthCorrectInPasswdField(JPasswordField passwdField, int length) {
		boolean ret = true;
		String str = String.valueOf(passwdField.getPassword());

		if (str.isBlank()) {
			ret = false;
		} else {
			if (str.length() < length) {
				ret = false;
			}
		}

		return ret;
	}

	public boolean isPersonalIdCorrect(JTextField textField) {
		boolean ret = true;
		String str = textField.getText();

		if (str.isBlank()) {
			ret = false;
		} else {
			if (str.length() != 9) {
				ret = false;
			} else {
				if (!str.matches("^\\d{8}[a-zA-Z]$"))
					ret = false;
			}
		}

		return ret;
	}

	public boolean isGenderCorrect(JTextField textField) {
		boolean ret = true;
		String str = textField.getText();

		if (str.isBlank()) {
			ret = false;
		} else {
			if (!str.equalsIgnoreCase("Hombre") && !str.equalsIgnoreCase("Mujer") && !str.equalsIgnoreCase("Otro"))
				ret = false;
		}

		return ret;

	}

	public boolean isDateCorrect(JTextField textField) {
		boolean ret = true;
		String str = textField.getText();

		if (str.isBlank()) {
			ret = false;
		} else {
			if (!str.matches("^\\d{2}/\\d{2}/\\d{4}$"))
				ret = false;
		}

		return ret;
	}

	public boolean isPhoneCorrect(JTextField textField) {
		boolean ret = true;
		String str = textField.getText();

		if (str.isBlank()) {
			ret = false;
		} else {
			if (!str.matches("^[+]\\d{11}$"))
				ret = false;
		}

		return ret;
	}

	public boolean isEmailCorrect(JTextField textField) {
		boolean ret = true;
		String str = textField.getText();

		if (str.isBlank()) {
			ret = false;
		} else {
			if (!str.matches("^(.+)@(.+)[.](.+)$"))
				ret = false;
		}

		return ret;
	}

	public ArrayList<Object> artistsAndGroups() throws SQLException, Exception {
		ArtistManager artistManager = new ArtistManager();
		ArtGroupManager artGroupManager = new ArtGroupManager();

		ArrayList<Artist> artists = null;
		ArrayList<ArtGroup> groups = null;
		ArrayList<Object> allArtists = new ArrayList<Object>();

		artists = (ArrayList<Artist>) artistManager.doSelectAll();
		if (artists != null) {
			for (Artist artist : artists) {
				if (artist.getArtGroup() == null) {
					allArtists.add(artist);
				}
			}
		}

		groups = (ArrayList<ArtGroup>) artGroupManager.doSelectAll();
		if (groups != null) {
			for (ArtGroup group : groups) {
				allArtists.add(group);
			}
		}
		
		return allArtists;
	}
	
	public void insertSong(Song song) throws SQLException, Exception {
		if (songManager == null)
			songManager = new SongManager();

		songManager.insert(song);
	}

}