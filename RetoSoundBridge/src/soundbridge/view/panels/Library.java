package soundbridge.view.panels;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import soundbridge.controller.Controller;
import soundbridge.database.managers.PlaylistManager;

import soundbridge.database.pojos.ArtGroup;
import soundbridge.database.pojos.Artist;
import soundbridge.database.pojos.Client;
import soundbridge.database.pojos.ClientP;
import soundbridge.database.pojos.ClientPP;
import soundbridge.database.pojos.Playlist;

import soundbridge.utils.WindowUtils;
import soundbridge.view.components.AutoCompleteTextField;
import soundbridge.view.components.TextPrompt;
import soundbridge.view.factory.PanelFactory;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.GridLayout;

/**
 * Panel that contains a menu with the top 20 most listened songs and the
 * favorite songs of the client, also the possibility to go to the profile panel
 * and the search bar to search for any artist or group.
 */
public class Library extends JPanel {

	private static final long serialVersionUID = -2776809426213236020L;
	private Controller controller = null;
	private JPanel panelGridPlaylist;
	private ArrayList<Playlist> playlists = null;

	/**
	 * Initializes the panel.
	 * 
	 * @param frame  frame where the panel is added
	 * @param client logged client
	 */
	public Library(JFrame frame, Client client) {
		initialize(frame, client);
	}

	/**
	 * Initializes the components of the panel.
	 * 
	 * @param frame  frame where the panel is added
	 * @param client logged client
	 */
	private void initialize(JFrame frame, Client client) {
		setBounds(0, 0, 1000, 672);
		setLayout(null);
		setBackground(Color.black);

		JPanel panelProfileIcon = new JPanel();
		panelProfileIcon.setBounds(903, 45, 50, 50);
		add(panelProfileIcon);
		panelProfileIcon.setLayout(new BorderLayout(0, 0));
		panelProfileIcon.setOpaque(false);
		panelProfileIcon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.getContentPane().removeAll();
				frame.getContentPane().add(PanelFactory.getJPanel(PanelFactory.PROFILE, frame, client, null, null, null,
						null, null, null));
				frame.revalidate();
				frame.repaint();
			}
		});
		panelProfileIcon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelProfileIcon.setToolTipText("Ir a mi perfil.");

		JLabel lblProfileIcon = new JLabel("");
		panelProfileIcon.add(lblProfileIcon, BorderLayout.CENTER);

		JLabel lblUsername = new JLabel("@" + client.getUsername());
		lblUsername.setBounds(875, 100, 100, 30);
		lblUsername.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblUsername.setForeground(Color.white);
		lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblUsername);

		AutoCompleteTextField searchBar = new AutoCompleteTextField();
		searchBar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
					String search = searchBar.getText();
					executeSearch(frame, client, search);
				}
			}
		});
		searchBar.setBounds(110, 52, 600, 30);
		searchBar.setOpaque(false);
		searchBar.setForeground(Color.white);
		searchBar.setCaretColor(Color.WHITE);
		searchBar.setBorder(new LineBorder(Color.WHITE, 0));
		searchBar.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		add(searchBar);

		TextPrompt placeholderSearch = new TextPrompt("Búsqueda de artistas", searchBar);
		placeholderSearch.changeAlpha(0.8f);
		placeholderSearch.changeStyle(Font.ITALIC);
		placeholderSearch.setHorizontalAlignment(SwingConstants.LEFT);

		JLabel lblPlaylists = new JLabel("Mis listas de reproducción:");
		lblPlaylists.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblPlaylists.setBounds(90, 120, 301, 27);
		lblPlaylists.setForeground(Color.white);
		add(lblPlaylists);

		JPanel panelTop20 = new JPanel();
		panelTop20.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.getContentPane().removeAll();
				frame.getContentPane().add(PanelFactory.getJPanel(PanelFactory.TOP20VIEW, frame, client, null, null,
						null, null, null, null));
				frame.revalidate();
				frame.repaint();
			}
		});
		panelTop20.setBounds(90, 170, 115, 115);
		panelTop20.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		add(panelTop20);
		panelTop20.setLayout(new BorderLayout(0, 0));
		panelTop20.setOpaque(false);

		JLabel lblTop20Img = new JLabel("");
		panelTop20.add(lblTop20Img, BorderLayout.CENTER);

		JLabel lblTop20 = new JLabel("Top20");
		lblTop20.setHorizontalAlignment(SwingConstants.CENTER);
		lblTop20.setForeground(Color.white);
		lblTop20.setBounds(90, 295, 115, 27);
		add(lblTop20);

		JPanel panelFavorites = new JPanel();
		panelFavorites.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				goToFavorites(frame, client);
			}

		});
		panelFavorites.setBounds(230, 170, 115, 115);
		add(panelFavorites);
		panelFavorites.setLayout(new BorderLayout(0, 0));
		panelFavorites.setOpaque(false);
		panelFavorites.setVisible(false);

		JLabel lblFavoritesImg = new JLabel("");
		panelFavorites.add(lblFavoritesImg, BorderLayout.CENTER);

		JLabel lblFavorites = new JLabel("");
		lblFavorites.setHorizontalAlignment(SwingConstants.CENTER);
		lblFavorites.setForeground(Color.white);
		lblFavorites.setBounds(230, 295, 115, 27);
		add(lblFavorites);
		
		JPanel panelAddPlaylist = new JPanel();
		panelAddPlaylist.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.getContentPane().removeAll();
				frame.getContentPane().add(PanelFactory.getJPanel(PanelFactory.CREATE_PLAYLIST, frame, client, null,
						null, null, null, null, null));
				frame.revalidate();
				frame.repaint();
			}
		});
		add(panelAddPlaylist);
		panelAddPlaylist.setBounds(785, 45, 50, 50);
		panelAddPlaylist.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelAddPlaylist.setToolTipText("Crear nueva lista de reproducción.");
		panelAddPlaylist.setOpaque(false);


		if (client instanceof ClientPP) {
			panelAddPlaylist.setVisible(true);
		}

		if (client instanceof ClientP) {
			panelAddPlaylist.setVisible(false);
		}

		WindowUtils.addImage(panelTop20, lblTop20Img, "img/icon/top_icon.png");
		WindowUtils.addImage(panelFavorites, lblFavoritesImg, "img/icon/fav_icon.png");
		doAddPossibilitiesToSearchBar(searchBar);
		showFavorites(client, panelFavorites, lblFavorites);

		panelGridPlaylist = new JPanel();
		panelGridPlaylist.setBounds(90, 386, 920, 115);
		add(panelGridPlaylist);
		panelGridPlaylist.setLayout(new GridLayout(1, 6, 0, 0));
		panelGridPlaylist.setOpaque(false);

		JPanel panelBackground = new JPanel();
		panelBackground.setBounds(0, 0, 1000, 672);
		add(panelBackground);
		panelBackground.setLayout(new BorderLayout(0, 0));

		JLabel lblBackground = new JLabel("");
		panelBackground.add(lblBackground, BorderLayout.CENTER);
		
		WindowUtils.addImage(panelBackground, lblBackground, "img/panel/library_bg.png");

		panelFavorites.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelFavorites.setToolTipText("Ir a canciones favoritas.");

		WindowUtils.addImage(panelProfileIcon, lblProfileIcon, "img/icon/profile.png");

		try {
			addImagesToAlbums(frame, client);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	/**
	 * Autocomplete the text field of the artists and groups.
	 * 
	 * @param text the written text
	 */
	private void doAddPossibilitiesToSearchBar(AutoCompleteTextField text) {
		if (null == controller)
			controller = new Controller();

		try {
			controller.addPossibilitiesToSearchBar(text);
		} catch (SQLException sqle) {
			WindowUtils.errorPane("No se ha podido añadir el autocompletado.", "Error en la base de datos");
		} catch (Exception e) {
			WindowUtils.errorPane("No se ha podido añadir el autocompletado.", "Error general");
		}
	}


	/**
	 * Set the playlist of favorites visible if the client is able to have it.
	 * 
	 * @param client     logged client
	 * @param favourites panel of the favorite songs playlist
	 * @param favLbl     label of the favorite songs playlist
	 */


	private void showFavorites(Client client, JPanel favorites, JLabel favLbl) {

		if (client instanceof ClientPP || client instanceof ClientP) {
			favorites.setVisible(true);
			favLbl.setText("Favoritos");
		}
	}

	/**
	 * Get the artist using what the client had written in the search bar.
	 * 
	 * @param search what the client had written in the search bar
	 * @return the artist that the client want
	 */
	private Artist doSearchedArtist(String search) {
		if (null == controller)
			controller = new Controller();

		Artist artist = null;
		try {
			artist = controller.searchedArtist(search);
		} catch (SQLException e) {
			WindowUtils.errorPane("No se ha podido realizar la búsqueda.", "Error en la base de datos");
		} catch (Exception e) {
			WindowUtils.errorPane("No se ha podido realizar la búsqueda.", "Error general");
		}

		return artist;
	}

	/**
	 * Get the group using what the client had written in the search bar.
	 * 
	 * @param search what the client had written in the search bar
	 * @return the group that the client want
	 */
	private ArtGroup doSearchedGroup(String search) {
		if (null == controller)
			controller = new Controller();

		ArtGroup group = null;
		try {
			group = controller.searchedGroup(search);
		} catch (SQLException e) {
			WindowUtils.errorPane("No se ha podido realizar la búsqueda.", "Error en la base de datos");
		} catch (Exception e) {
			WindowUtils.errorPane("No se ha podido realizar la búsqueda.", "Error general");
		}

		return group;
	}

	/**
	 * Using the information of "doSearchedArtist" and "doSearchedGroup" takes the
	 * client to the profile of the artist or group searched or show an error
	 * message.
	 * 
	 * @param frame  frame where the panel is added
	 * @param client logged client
	 * @param search what the client had written in the search bar
	 */
	private void executeSearch(JFrame frame, Client client, String search) {
		Artist searchedArtist = doSearchedArtist(search);
		ArtGroup searchedArtGroup = doSearchedGroup(search);

		if (null != searchedArtist && null == searchedArtGroup)
			goToArtistProfile(frame, client, searchedArtist);
		else if (null != searchedArtGroup)
			goToGroupProfile(frame, client, searchedArtGroup);
		else
			WindowUtils.messagePaneWithIcon("No hay resultados.", "Sin resultados", "img/icon/no_results.png");
	}

	/**
	 * Takes the client to the artist profile.
	 * 
	 * @param frame  frame where the panel is added
	 * @param client logged client
	 * @param artist artist whose album is displayed if it is not null
	 */
	private void goToArtistProfile(JFrame frame, Client client, Artist artist) {
		frame.getContentPane().removeAll();
		frame.getContentPane().add(PanelFactory.getJPanel(PanelFactory.ARTIST_PROFILE, frame, client, null, artist,
				null, null, null, null));
		frame.revalidate();
		frame.repaint();
	}

	/**
	 * Takes the client art group's profile.
	 * 
	 * @param frame    frame where the panel is added
	 * @param client   logged client
	 * @param artGroup art group whose album is displayed if it is not null
	 */
	private void goToGroupProfile(JFrame frame, Client client, ArtGroup artGroup) {
		frame.getContentPane().removeAll();
		frame.getContentPane().add(PanelFactory.getJPanel(PanelFactory.GROUP_PROFILE, frame, client, null, null,
				artGroup, null, null, null));
		frame.revalidate();
		frame.repaint();
	}


	/**
	 * Takes the client to favorite songs playlist.
	 * 
	 * @param frame  frame where the panel is added
	 * @param client logged client
	 */


	private void goToFavorites(JFrame frame, Client client) {

		frame.getContentPane().removeAll();
		frame.getContentPane().add(PanelFactory.getJPanel(PanelFactory.FAVORITE_SONGS, frame, client, null, null, null,
				null, null, null));
		frame.revalidate();
		frame.repaint();

	}

	/**
	 * Gets all the playlists of a client premium plus.
	 * 
	 * @param client logged client
	 * @return list with all the client's playlists
	 * @throws SQLException
	 * @throws Exception
	 */
	private ArrayList<Playlist> getPlaylistsOfClientPP(Client client) throws SQLException, Exception {
		PlaylistManager playMan = new PlaylistManager();

		return playMan.selectPlaylistOfCLientPP(client);

	}

	/**
	 * Creates a panel that is meant to be added to the grid.
	 * 
	 * @return
	 */
	private JPanel createPanel() {
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 115, 115);
		panel.setLayout(new BorderLayout(0, 0));
		panel.setOpaque(false);
		panel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		return panel;
	}

	/**
	 * Creates a label that is meant be added to the specified panel. An image will
	 * be printed on it.
	 * 
	 * @param panel panel where the label is placed
	 * @return the label that is placed in the panel
	 */
	private JLabel createLabel(JPanel panel) {
		JLabel label = new JLabel("");
		panel.add(label, BorderLayout.CENTER);
		return label;

	}

	/**
	 * Creates a panel to fit the grid dimensions, but it is not shown.
	 * 
	 * @return the created panel
	 */
	private JPanel createPanelToFitGrid() {
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 115, 115);
		panel.setVisible(false);

		return panel;
	}

	/**
	 * Creates a maximum of 6 panels that correspond to the playlist of the client.
	 * 
	 * @param frame  frame where the panel is added
	 * @param client logged client
	 * @throws SQLException
	 * @throws Exception
	 */
	private void addImagesToAlbums(JFrame frame, Client client) throws SQLException, Exception {

		if (client instanceof ClientPP)
			playlists = getPlaylistsOfClientPP(client);

		if (null != playlists) {
			for (int i = 0; i < 6; i++) {
				if (i < playlists.size()) {
					Playlist playlist = playlists.get(i);
					String image = "img/icon/playlist_icon.png";

					JPanel panelAlbum = createPanel();
					panelAlbum.setToolTipText(playlist.getName() + " ("
							+ (new SimpleDateFormat("yyyy")).format(playlist.getCreationDate()) + ")");
					panelGridPlaylist.add(panelAlbum);

					JLabel lblAlbum = createLabel(panelAlbum);

					WindowUtils.addImage(panelAlbum, lblAlbum, image);

					// ArrayList<Song> songsOfAlbum = getSongsOfAlbum(playlist, artist);
					// playlist.setSongs(songsOfAlbum);

					createPlaylistPanelListener(frame, panelAlbum, client, playlist);

				} else {
					JPanel panelToFitGrid = createPanelToFitGrid();
					panelGridPlaylist.add(panelToFitGrid);
				}
			}
		}
	}


	/**
	 * Creates a listener for the panel. When it is clicked it takes the client to
	 * the playlist panel.
	 * 
	 * @param frame    frame where the panel is added
	 * @param panel    panel to which the listener is being created
	 * @param client   logged client
	 * @param playlist playlist the choosen playlist
	 */

	private void createPlaylistPanelListener(JFrame frame, JPanel panel, Client client, Playlist playlist) {
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.getContentPane().removeAll();
				frame.getContentPane().add(PanelFactory.getJPanel(PanelFactory.PLAYLIST, frame, client, null, null,
						null, null, null, playlist));
				frame.revalidate();
				frame.repaint();
			}
		});
	}
}
