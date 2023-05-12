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
import soundbridge.database.pojos.Album;
import soundbridge.database.pojos.ArtGroup;
import soundbridge.database.pojos.Artist;
import soundbridge.database.pojos.Client;
import soundbridge.database.pojos.ClientP;
import soundbridge.database.pojos.ClientPP;
import soundbridge.database.pojos.Playlist;
import soundbridge.database.pojos.Song;
import soundbridge.utils.WindowUtils;
import soundbridge.view.components.AutoCompleteTextField;
import soundbridge.view.components.TextPrompt;
import soundbridge.view.factory.PanelFactory;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.GridLayout;

public class Library extends JPanel {

	private static final long serialVersionUID = -2776809426213236020L;
	private Controller controller = null;
	private JPanel panelGridPlaylist;
	private ArrayList<Playlist> playlists = null;
	public Library(JFrame frame, Client client) {
		initialize(frame, client);
	}

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

		JPanel panelFavourites = new JPanel();
		panelFavourites.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				goToFvourites(frame, client);
			}

		});
		panelFavourites.setBounds(230, 170, 115, 115);
		add(panelFavourites);
		panelFavourites.setLayout(new BorderLayout(0, 0));
		panelFavourites.setOpaque(false);
		panelFavourites.setVisible(false);

		JLabel lblFavouritesImg = new JLabel("");
		panelFavourites.add(lblFavouritesImg, BorderLayout.CENTER);

		JLabel lblFavourites = new JLabel("");
		lblFavourites.setHorizontalAlignment(SwingConstants.CENTER);
		lblFavourites.setForeground(Color.white);
		lblFavourites.setBounds(230, 295, 115, 27);
		add(lblFavourites);

		JPanel panelImagePlus = new JPanel();
		panelImagePlus.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.getContentPane().removeAll();
				frame.getContentPane().add(PanelFactory.getJPanel(PanelFactory.CREATE_PLAYLIST, frame, client, null,
						null, null, null, null, null));
				frame.revalidate();
				frame.repaint();
			}
		});
		add(panelImagePlus);
		panelImagePlus.setBounds(823, 166, 50, 40);
		panelImagePlus.setLayout(new BorderLayout(0, 0));

		JLabel lblPlusIcon = new JLabel("       +");
		lblPlusIcon.setForeground(new Color(255, 255, 255));
		panelImagePlus.add(lblPlusIcon, BorderLayout.CENTER);

		WindowUtils.addImage(panelTop20, lblTop20Img, "img/icon/top_icon.png");
		WindowUtils.addImage(panelFavourites, lblFavouritesImg, "img/icon/fav_icon.png");
		doAddPossibilitiesToSearchBar(searchBar);
		showFavourites(client, panelFavourites, lblFavourites);

		JPanel panelBackground = new JPanel();
		panelBackground.setBounds(0, 0, 1000, 672);
		add(panelBackground);
		panelBackground.setLayout(null);

		JLabel lblBackground = new JLabel("");
		lblBackground.setBounds(-73, 0, 1000, 672);
		panelBackground.add(lblBackground);

		panelFavourites.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelFavourites.setToolTipText("Ir a canciones favoritas.");

		WindowUtils.addImage(panelProfileIcon, lblProfileIcon, "img/icon/profile.png");
		WindowUtils.addImage(panelBackground, lblBackground, "img/panel/library_bg.jpeg");


		
		panelGridPlaylist = new JPanel();
		panelGridPlaylist.setBounds(90, 386, 920, 115);
		add(panelGridPlaylist);
		panelGridPlaylist.setLayout(new GridLayout(1, 5, 69, 0));
		panelGridPlaylist.setOpaque(false);
		
		try {
			addImagesToAlbums(frame,client);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

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

	private void showFavourites(Client client, JPanel favourites, JLabel favLbl) {
		if (client instanceof ClientPP || client instanceof ClientP) {
			favourites.setVisible(true);
			favLbl.setText("Favoritos");
		}
	}

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

	private void goToArtistProfile(JFrame frame, Client client, Artist artist) {
		frame.getContentPane().removeAll();
		frame.getContentPane().add(PanelFactory.getJPanel(PanelFactory.ARTIST_PROFILE, frame, client, null, artist,
				null, null, null, null));
		frame.revalidate();
		frame.repaint();
	}

	private void goToGroupProfile(JFrame frame, Client client, ArtGroup artGroup) {
		frame.getContentPane().removeAll();
		frame.getContentPane().add(PanelFactory.getJPanel(PanelFactory.GROUP_PROFILE, frame, client, null, null,
				artGroup, null, null, null));
		frame.revalidate();
		frame.repaint();
	}

	private void goToFvourites(JFrame frame, Client client) {
		frame.getContentPane().removeAll();
		frame.getContentPane().add(PanelFactory.getJPanel(PanelFactory.FAVOURITE_SONGS, frame, client, null, null, null,
				null, null, null));
		frame.revalidate();
		frame.repaint();

	}
	private ArrayList<Playlist> getPlaylistsOfClientP(Client client) throws SQLException, Exception {
		PlaylistManager playMan = new PlaylistManager();
		
		
		return playMan.selectPlaylistOfCLientP(client);
	
	}
	private ArrayList<Playlist> getPlaylistsOfClientPP(Client client) throws SQLException, Exception {
		PlaylistManager playMan = new PlaylistManager();
		
		
		return playMan.selectPlaylistOfCLientPP(client);
	
	}
	
	private JPanel createPanel() {
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 115, 115);
		panel.setLayout(new BorderLayout(0, 0));
		panel.setOpaque(false);
		panel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		return panel;
	}
	
	private JLabel createLabel(JPanel panel) {
		JLabel label = new JLabel("");
		panel.add(label, BorderLayout.CENTER);
		return label;

	}
	
	private JPanel createPanelToFitGrid() {
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 115, 115);
		panel.setVisible(false);

		return panel;
	}
	
	

	private void addImagesToAlbums(JFrame frame, Client client) throws SQLException, Exception {
		if (client instanceof ClientP)
		playlists = getPlaylistsOfClientP(client);
		
		if (client instanceof ClientPP)
		playlists = getPlaylistsOfClientPP(client);
			
		if (null != playlists) {
			for (int i = 0; i < 5; i++) {
				if (i < playlists.size()) {
					Playlist playlist = playlists.get(i);
					String image = "img/icon/playlist_icon.png";

					JPanel panelAlbum = createPanel();
					panelAlbum.setToolTipText(playlist.getName() + " ("
							+ (new SimpleDateFormat("yyyy")).format(playlist.getCreationDate()) + ")");
					panelGridPlaylist.add(panelAlbum);

					JLabel lblAlbum = createLabel(panelAlbum);

					WindowUtils.addImage(panelAlbum, lblAlbum, image);

					//ArrayList<Song> songsOfAlbum = getSongsOfAlbum(playlist, artist);
					//playlist.setSongs(songsOfAlbum);

					//createSinglePanelListener(frame, panelAlbum, client, artist, playlist);

				} else {
					JPanel panelToFitGrid = createPanelToFitGrid();
					panelGridPlaylist.add(panelToFitGrid);
				}
			}
		}
	}
}
