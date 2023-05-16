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
import soundbridge.database.managers.ReviewManager;
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
import soundbridge.database.pojos.Review;
import soundbridge.database.pojos.Song;
import soundbridge.database.views.managers.AverageStarsManager;
import soundbridge.database.views.managers.Top20ViewManager;
import soundbridge.database.views.pojos.AverageStars;
import soundbridge.utils.WindowUtils;
import soundbridge.view.components.AutoCompleteTextField;

/**
 * Describes the engine which contains methods for accessing database which are
 * called by the view.
 */
public class Controller {

	private ClientManager clientManager = null;
	private EmployeeManager employeeManager = null;
	private AlbumManager albumManager = null;
	private SongManager songManager = null;
	private ClientPManager clientPManager = null;
	private ClientPPManager clientPPManager = null;
	private ArtistManager artistManager = null;
	private ArtGroupManager artGroupManager = null;
	private ReviewManager reviewManager = null;

	/**
	 * Checks if the logged user is an employee.
	 * 
	 * @param textFieldUserLogIn field containing username
	 * @param passwordFieldLogIn field containing password
	 * @return true if the instance of the employee exists on database, otherwise
	 *         false
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
	 */
	public boolean isEmployee(JTextField textFieldUserLogIn, JTextField passwordFieldLogIn)
			throws SQLException, Exception {
		String username = textFieldUserLogIn.getText();
		String passwd = passwordFieldLogIn.getText();

		if (null == employeeManager)
			employeeManager = new EmployeeManager();

		return employeeManager.askForEmployeeUsingIdAndPasswd(username, passwd);
	}

	/**
	 * Checks if the logged user is a client.
	 * 
	 * @param textFieldUserLogIn field containing username
	 * @param passwordFieldLogIn field containing password
	 * @return true if the instance of the client exists on database, otherwise
	 *         false
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
	 */
	public boolean isClient(JTextField textFieldUserLogIn, JTextField passwordFieldLogIn)
			throws SQLException, Exception {
		String username = textFieldUserLogIn.getText();
		String passwd = passwordFieldLogIn.getText();

		if (null == clientManager)
			clientManager = new ClientManager();

		return clientManager.askForClientUsingIdAndPasswd(username, passwd);
	}

	/**
	 * Returns a client with given username on database.
	 * 
	 * @param username given username of client
	 * @return client with given username
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
	 */
	public Client clientByUsername(String username) throws SQLException, Exception {
		if (null == clientManager)
			clientManager = new ClientManager();

		return clientManager.getClientByUsername(username);
	}

	/**
	 * Returns an employee with given username on database.
	 * 
	 * @param username given username of employee
	 * @return employee with given username
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
	 */
	public Employee employeeByUsername(String username) throws SQLException, Exception {
		if (null == employeeManager)
			employeeManager = new EmployeeManager();

		return employeeManager.getEmployeeByUsername(username);
	}

	/**
	 * Deletes the account of the logged client.
	 * 
	 * @param client logged client
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
	 */
	public void deleteAccount(Client client) throws SQLException, Exception {
		if (null == clientManager)
			clientManager = new ClientManager();

		clientManager.delete(client);
	}

	/**
	 * Adds artists' and groups' names on database to the search bar autocomplete
	 * possibilities.
	 * 
	 * @param text autocomplete field
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
	 */
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

	/**
	 * Updates a client's personal information on database.
	 * 
	 * @param client     logged client
	 * @param combo      combo box of genders
	 * @param textNation field containing nationality
	 * @param textBirth  field containing birth date
	 * @param textAddr   field containing address
	 * @param textPhone  field containing phone number
	 * @param textEmail  field containing email
	 * @param textBank   field containing bank number
	 * @throws ParseException if unexpected error while parsing
	 * @throws SQLException   if there is an error on database
	 * @throws Exception      if there is a generic error
	 */
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

	/**
	 * Updates the logged client's password on database.
	 * 
	 * @param client  logged client
	 * @param passwd1 field containing password
	 * @param passwd2 field containing password
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
	 */
	public void changePasswdClient(Client client, JPasswordField passwd1, JPasswordField passwd2)
			throws SQLException, Exception {
		if (null == clientManager)
			clientManager = new ClientManager();

		client.setPasswd(String.valueOf(passwd1.getPassword()));

		clientManager.update(client);
	}

	/**
	 * Updates the logged employee's password on database.
	 * 
	 * @param employee logged employee
	 * @param passwd1  field containing password
	 * @param passwd2  field containing password
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
	 */
	public void changePasswdEmployee(Employee employee, JPasswordField passwd1, JPasswordField passwd2)
			throws SQLException, Exception {
		if (null == employeeManager)
			employeeManager = new EmployeeManager();

		employee.setPasswd(String.valueOf(passwd1.getPassword()));

		employeeManager.update(employee);
	}

	/**
	 * Inserts a reproduction of a song on database.
	 * 
	 * @param play play to be inserted
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
	 */
	public void insertPlay(Play play) throws SQLException, Exception {
		PlayManager playManager = new PlayManager();
		playManager.insert(play);
	}

	/**
	 * Returns the average stars of a given album.
	 * 
	 * @param album given album
	 * @return average stars of the album
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
	 */
	public AverageStars getAverageStars(Album album) throws SQLException, Exception {
		AverageStarsManager averageStarsManager = new AverageStarsManager();
		return averageStarsManager.getAverageStarsByAlbum(album);
	}

	/**
	 * Returns all the albums of an artist.
	 * 
	 * @param artist artist owner of albums
	 * @return albums of the artist
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
	 */
	public ArrayList<Album> getAlbumsByArtist(Artist artist) throws SQLException, Exception {
		if (null == albumManager)
			albumManager = new AlbumManager();

		return (ArrayList<Album>) albumManager.albumsByArtist(artist);
	}

	/**
	 * Returns all the songs of an album that belongs to a certain artist.
	 * 
	 * @param album  album to which songs belong
	 * @param artist artist owner of the album
	 * @return array list of songs included on the album
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
	 */
	public ArrayList<Song> getSongsByAlbumAndArtist(Album album, Artist artist) throws SQLException, Exception {
		if (null == songManager)
			songManager = new SongManager();

		return songManager.getSongsByAlbumWithArtist(album, artist);
	}

	/**
	 * Returns all songs not included in any album that belong to a certain artist.
	 * 
	 * @param artist artist owner of the songs
	 * @return songs not included in any album of the artist
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
	 */
	public ArrayList<Song> getSinglesByArtist(Artist artist) throws SQLException, Exception {
		if (null == songManager)
			songManager = new SongManager();

		return (ArrayList<Song>) songManager.getSinglesByArtist(artist);
	}

	/**
	 * Returns all the albums of an art group.
	 * 
	 * @param artGroup art group owner of albums
	 * @return albums of the art group
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
	 */
	public ArrayList<Album> getAlbumsByGroup(ArtGroup artGroup) throws SQLException, Exception {
		if (null == albumManager)
			albumManager = new AlbumManager();

		return (ArrayList<Album>) albumManager.albumsByArtGroup(artGroup);
	}

	/**
	 * Returns all the songs of an album that belongs to a certain art group.
	 * 
	 * @param album    album to which songs belong
	 * @param artGroup art group owner of the album
	 * @return array list of songs included on the album
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
	 */
	public ArrayList<Song> getSongsByAlbumAndGroup(Album album, ArtGroup artGroup) throws SQLException, Exception {
		if (null == songManager)
			songManager = new SongManager();

		return songManager.getSongsByAlbumWithGroup(album, artGroup);
	}

	/**
	 * Returns all songs not included in any album that belong to a certain art
	 * group.
	 * 
	 * @param artGroup art group owner of the songs
	 * @return songs not included in any album of the art group
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
	 */
	public ArrayList<Song> getSinglesByGroup(ArtGroup artGroup) throws SQLException, Exception {
		if (null == songManager)
			songManager = new SongManager();

		return (ArrayList<Song>) songManager.getSinglesByGroup(artGroup);
	}

	/**
	 * Changes logged client's subscription.
	 * 
	 * @param client          logged client
	 * @param bankNumber      bank number if subscription is paid, otherwise null
	 * @param newSubscription name of new subscription
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
	 */
	public void changeClientSubscription(Client client, String bankNumber, String newSubscription)
			throws SQLException, Exception {
		if (null == clientManager)
			clientManager = new ClientManager();

		clientManager.changeSubscription(client.getId(), bankNumber, newSubscription);
	}

	/**
	 * Returns a premium client by logged client's id.
	 * 
	 * @param client logged client
	 * @return premium client
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
	 */
	public ClientP getPremiumClient(Client client) throws SQLException, Exception {
		if (null == clientPManager)
			clientPManager = new ClientPManager();

		return clientPManager.getClientPById(client.getId());
	}

	/**
	 * Returns a premium plus client by logged client's id.
	 * 
	 * @param client logged client
	 * @return premium plus client
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
	 */
	public ClientPP getPremiumPlusClient(Client client) throws SQLException, Exception {
		if (null == clientPPManager)
			clientPPManager = new ClientPPManager();

		return clientPPManager.getClientPPById(client.getId());
	}

	/**
	 * Returns an artist with given name if exists in database.
	 * 
	 * @param search searched name of artist
	 * @return artist with given name
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
	 */
	public Artist searchedArtist(String search) throws SQLException, Exception {
		if (null == artistManager)
			artistManager = new ArtistManager();

		return artistManager.getArtistByName(search);
	}

	/**
	 * Returns an art group with given name if exists in database.
	 * 
	 * @param search searched name of art group
	 * @return art group with given name
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
	 */
	public ArtGroup searchedGroup(String search) throws SQLException, Exception {
		if (null == artGroupManager)
			artGroupManager = new ArtGroupManager();

		return artGroupManager.getArtGroupByName(search);
	}

	/**
	 * Inserts a new basic client into database.
	 * 
	 * @param client basic client to be inserted
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
	 */
	public void insertClient(Client client) throws SQLException, Exception {
		ClientManager clientManager = new ClientManager();
		clientManager.insert(client);
	}

	/**
	 * Inserts a new premium client into database.
	 * 
	 * @param client premium client to be inserted
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
	 */
	public void insertClientP(ClientP clientp) throws SQLException, Exception {
		ClientPManager clientpManager = new ClientPManager();
		clientpManager.insertReal(clientp);
	}

	/**
	 * Inserts a new premium plus client into database.
	 * 
	 * @param client premium plus client to be inserted
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
	 */
	public void insertClientPP(ClientPP clientpp) throws SQLException, Exception {
		ClientPPManager clientppManager = new ClientPPManager();
		clientppManager.insertReal(clientpp);
	}

	/**
	 * Updates a client on database.
	 * 
	 * @param client client to be updated
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
	 */
	public void updateClient(Client client) throws SQLException, Exception {
		ClientManager clientManager = new ClientManager();
		clientManager.update(client);
	}

	/**
	 * Returns a client by username.
	 * 
	 * @param username given username
	 * @return client with given username
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
	 */
	public Client getClientByUsername(String username) throws SQLException, Exception {
		ClientManager clientManager = new ClientManager();
		return clientManager.getClientByUsername(username);
	}

	/**
	 * Returns all clients from database.
	 * 
	 * @return array list of all clients
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
	 */
	public ArrayList<Client> getAllClients() throws SQLException, Exception {
		ClientManager clientManager = new ClientManager();

		return (ArrayList<Client>) clientManager.doSelectAll();
	}

	/**
	 * Returns all songs of the Top 20.
	 * 
	 * @return array list of songs included in the Top 20
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
	 */
	public ArrayList<Song> getTop20Songs() throws SQLException, Exception {
		Top20ViewManager top20manager = new Top20ViewManager();

		return (ArrayList<Song>) top20manager.selectViewTop20AndSongs();
	}

	/**
	 * Returns an artist by id.
	 * 
	 * @param id id of the artist
	 * @return artist with given id
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
	 */
	public Artist getArtistById(int id) throws SQLException, Exception {
		ArtistManager artman = new ArtistManager();
		return artman.selectArtistById(id);
	}

	/**
	 * Returns an art group by id.
	 * 
	 * @param id id of the art group
	 * @return art group with given id
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
	 */
	public ArtGroup getGroupById(int id) throws SQLException, Exception {
		ArtGroupManager artGroup = new ArtGroupManager();
		return artGroup.selectGroupById(id);
	}

	/**
	 * Inserts a song into a playlist.
	 * 
	 * @param contain contain to be inserted
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
	 */
	public void insertSongPLayList(Contain contain) throws SQLException, Exception {
		ContainManager contMan = new ContainManager();
		contMan.insert(contain);
	}

	/**
	 * Returns all playlists of a premium plus client.
	 * 
	 * @param client client owner of playlists
	 * @return playlists of client
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
	 */
	public ArrayList<Playlist> getPlaylistsOfClientPPById(Client client) throws SQLException, Exception {
		PlaylistManager playMan = new PlaylistManager();

		return (ArrayList<Playlist>) playMan.getPlaylistsOfClientPPById(client);
	}

	/**
	 * Returns all playlists of a premium client.
	 * 
	 * @param client client owner of playlists
	 * @return playlists of client
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
	 */
	public ArrayList<Playlist> getPlaylistsOfClientPById(Client client) throws SQLException, Exception {
		PlaylistManager playMan = new PlaylistManager();

		return (ArrayList<Playlist>) playMan.getPlaylistsOfClientPById(client);
	}

	/**
	 * Adds a song to favorite playlist.
	 * 
	 * @param client logged client
	 * @param songs  songs displayed on the table
	 * @param table  table containing songs
	 */
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
				WindowUtils.confirmationPane("La canción se ha añadido correctamente a favoritos.", "Canción añadida");
			} catch (Exception e) {
				WindowUtils.errorPane("No se han encontrado listas de reproducción.", "Error en la base de datos");
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
				WindowUtils.confirmationPane("Se ha añadido correctamente", "Canción añadida");
			} catch (Exception e) {
				WindowUtils.errorPane("No se han encontrado listas de reproducción.", "Error en la base de datos");
			}
		}
	}

	public void addToFavouritesSingleView(Client client, Song songg, JTable table) {

		if (client instanceof ClientPP) {
			Controller controller = new Controller();
			Contain contain = new Contain();
			ArrayList<Playlist> songsById;
			Playlist playlist = new Playlist();
			Playlist playlistt = new Playlist();
			try {
				songsById = controller.getPlaylistsOfClientPPById(client);
				playlist = songsById.get(0);
				playlistt.setId(playlist.getId());
				contain.setPlaylist(playlistt);
				contain.setSong(songg);
				controller.insertSongPLayList(contain);
				WindowUtils.confirmationPane("Se ha añadido correctamente", "Canción añadida");
			} catch (Exception e) {
				WindowUtils.errorPane("No se han encontrado listas de reproducción.", "Error en la base de datos");
			}
		}
		if (client instanceof ClientP) {
			Controller controller = new Controller();
			Contain contain = new Contain();
			ArrayList<Playlist> songsById;
			Playlist playlist = new Playlist();
			Playlist playlistt = new Playlist();
			try {
				songsById = controller.getPlaylistsOfClientPById(client);
				playlist = songsById.get(0);
				playlistt.setId(playlist.getId());
				contain.setPlaylist(playlistt);
				contain.setSong(songg);
				controller.insertSongPLayList(contain);
				WindowUtils.confirmationPane("Se ha añadido correctamente", "Canción añadida");
			} catch (Exception e) {
				WindowUtils.errorPane("No se han encontrado listas de reproducción.", "Error en la base de datos");
			}
		}
	}

	/**
	 * Returns all songs included into the favorite playlist of the logged premium
	 * plus client.
	 * 
	 * @param client logged client
	 * @return array list of songs in favorites
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
	 */
	public ArrayList<Song> selectFavouriteSongOfClientPP(Client client) throws SQLException, Exception {
		SongManager songMan = new SongManager();

		return (ArrayList<Song>) songMan.selectFavouriteSongOfClientPP(client);
	}

	/**
	 * Returns all songs included into the favorite playlist of the logged premium
	 * client.
	 * 
	 * @param client logged client
	 * @return array list of songs in favorites
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
	 */
	public ArrayList<Song> selectFavouriteSongOfClientP(Client client) throws SQLException, Exception {
		SongManager songMan = new SongManager();

		return (ArrayList<Song>) songMan.selectFavouriteSongOfClientP(client);
	}

	/**
	 * Checks if content of a field doesn't contain numeric values.
	 * 
	 * @param textField field to be validated
	 * @return true if field isn't empty and doesn't contain numeric values,
	 *         otherwise false
	 */
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

	/**
	 * Checks if content of a field isn't empty.
	 * 
	 * @param textField field to be validated
	 * @return true if field isn¡t empty, otherwise false
	 */
	public boolean isNotEmptyText(JTextField textField) {
		boolean ret = true;
		String str = textField.getText();

		if (str.isBlank()) {
			ret = false;
		}

		return ret;
	}

	/**
	 * Checks if content of a text area isn't empty.
	 * 
	 * @param textArea area to be validated
	 * @return true if field isn¡t empty, otherwise false
	 */
	public boolean isNotEmptyTextArea(JTextArea textArea) {
		boolean ret = true;
		String str = textArea.getText();

		if (str.isBlank()) {
			ret = false;
		}

		return ret;
	}

	/**
	 * Checks if content of a field contains just numeric values and length is
	 * correct.
	 * 
	 * @param textField field to be validated
	 * @param length    given length of string
	 * @return true if field isn't empty, has correct length and contains just
	 *         numbers, otherwise false
	 */
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

	/**
	 * Checks if content of a field has the correct length.
	 * 
	 * @param textField field to be validated
	 * @param length    given length of string
	 * @return true if field isn't empty and has correct length, otherwise false
	 */
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

	/**
	 * Checks if content of a field contains between 2 and 3 numbers.
	 * 
	 * @param textField field to be validated
	 * @return true if field isn't empty and contains 2 or 3 numbers, otherwise
	 *         false
	 */
	public boolean isLengthCorrectNumericSeconds(JTextField textField) {
		boolean ret = true;
		String str = textField.getText();

		if (str.isBlank()) {
			ret = false;
		} else {
			if ((str.length() < 2) || (!str.matches("^\\d{2,3}$"))) {
				ret = false;
			}
		}

		return ret;
	}

	/**
	 * Checks if content of a password field has correct length.
	 * 
	 * @param passwdField password field to be validated
	 * @param length      given length
	 * @return true if field isn't empty and has at least the specified length,
	 *         otherwise false
	 */
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

	/**
	 * Checks if content of a field follows the pattern of a personal id.
	 * 
	 * @param textField field to be validated
	 * @return true if field isn't empty and follows the pattern, otherwise false
	 */
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

	/**
	 * Checks if a content of a field corresponds to specified values.
	 * 
	 * @param textField field to be validated
	 * @return true if field isn't empty and contains one of the specified values,
	 *         otherwise false
	 */
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

	/**
	 * Checks if content of a field follows the specified date pattern (dd/MM/yyyy).
	 * 
	 * @param textField field to be validated
	 * @return true if field isn't empty and follows the pattern, otherwise false
	 */
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

	/**
	 * Checks if content of a field follows the specified phone number pattern.
	 * 
	 * @param textField field to be validated
	 * @return true if field isn't empty and follows the pattern, otherwise false
	 */
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

	/**
	 * Checks if content of a field follows an email pattern.
	 * 
	 * @param textField field to be validated
	 * @return true if field isn't empty and follows an email pattern, otherwise
	 *         false
	 */
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

	/**
	 * Returns all instances of artists and groups on database.
	 * 
	 * @return array list of object containing all artists and groups
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
	 */
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

	/**
	 * Inserts a new song into database.
	 * 
	 * @param song song to be inserted
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
	 */
	public void insertSong(Song song) throws SQLException, Exception {
		if (songManager == null)
			songManager = new SongManager();

		songManager.insert(song);
	}

	/**
	 * Returns a review of the album witten by logged client.
	 * 
	 * @param client logged client
	 * @param album  album to be reviewed
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
	 */
	public Review checkPreviousReview(Client client, Album album) throws SQLException, Exception {
		if (reviewManager == null)
			reviewManager = new ReviewManager();

		return reviewManager.getReviewByClientPPAndAlbum((ClientPP) client, album);
	}

	/**
	 * Updates a review already written on database.
	 * 
	 * @param previousReview review to be updated
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
	 */
	public void updateWrittenReview(Review previousReview) throws SQLException, Exception {
		if (reviewManager == null)
			reviewManager = new ReviewManager();

		reviewManager.update(previousReview);
	}

	/**
	 * Inserts a new review into database.
	 * 
	 * @param review review to be inserted
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
	 */
	public void insertNewReview(Review review) throws SQLException, Exception {
		if (reviewManager == null)
			reviewManager = new ReviewManager();

		reviewManager.insert(review);
	}

	/**
	 * Deletes the selected reviews from database.
	 * 
	 * @param selectedReviews reviews to be deleted
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
	 */
	public void deleteSelectedReviews(ArrayList<Review> selectedReviews) throws SQLException, Exception {
		if (reviewManager == null)
			reviewManager = new ReviewManager();

		if (selectedReviews != null) {
			for (Review review : selectedReviews) {
				reviewManager.delete(review);
			}
		}
	}

	/**
	 * Updates the selected reviews from database to be validated.
	 * 
	 * @param selectedReviews reviews to be updated
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
	 */
	public void validateSelectedReviews(ArrayList<Review> selectedReviews) throws SQLException, Exception {
		if (reviewManager == null)
			reviewManager = new ReviewManager();

		if (selectedReviews != null) {
			for (Review review : selectedReviews) {
				review.setValidated(true);
				reviewManager.update(review);
			}
		}
	}

	/**
	 * Returns all non validated reviews from database.
	 * 
	 * @return array list of non validated reviews
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
	 */
	public ArrayList<Review> nonValidatedReviews() throws SQLException, Exception {
		if (reviewManager == null)
			reviewManager = new ReviewManager();

		return reviewManager.nonValidatedReviewsWithAllInformation();
	}

	/**
	 * Returns all validated reviews from database.
	 * 
	 * @param album reviewed album
	 * @return array list of validated reviews
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
	 */
	public ArrayList<Review> validatedReviews(Album album) throws SQLException, Exception {
		if (reviewManager == null)
			reviewManager = new ReviewManager();

		return reviewManager.validatedReviewsWithAllInformation(album);
	}

	/**
	 * Gets all the playlists of a client premium plus.
	 * 
	 * @param client logged client
	 * @return list with all the client's playlists
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
	 */
	public ArrayList<Playlist> getPlaylistsOfClientPP(Client client) throws SQLException, Exception {
		PlaylistManager playMan = new PlaylistManager();

		return playMan.selectPlaylistOfCLientPP(client);

	}

	/**
	 * Gets all the playlists of a client premium plus.
	 * 
	 * @param client logged client
	 * @return list with all the client's playlists
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
	 */
	public ArrayList<Playlist> selectPlaylistsOfClientPPById(Client client) throws SQLException, Exception {
		PlaylistManager playMan = new PlaylistManager();

		return playMan.selectPlaylistOfCLientPP(client);

	}

}