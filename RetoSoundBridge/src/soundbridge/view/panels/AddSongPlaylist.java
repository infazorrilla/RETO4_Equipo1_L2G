package soundbridge.view.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JFrame;

import javax.swing.JPanel;
import javax.swing.SwingConstants;

import soundbridge.database.managers.ContainManager;
import soundbridge.database.managers.PlaylistManager;
import soundbridge.database.pojos.Client;
import soundbridge.database.pojos.ClientPP;
import soundbridge.database.pojos.Contain;
import soundbridge.database.pojos.Playlist;
import soundbridge.database.pojos.Song;
import soundbridge.utils.WindowUtils;
import soundbridge.view.factory.PanelFactory;

import javax.swing.JLabel;
import java.awt.Font;

/**
 * Panel where the client can see his playlists and choose in which one he wants
 * add the song.
 */
public class AddSongPlaylist extends JPanel {
	private JPanel panelGridAddSong;
	private ArrayList<Playlist> playlists = null;
	private static final long serialVersionUID = -1249195852721143677L;

	/**
	 * Initializes the panel.
	 * 
	 * @param frame  frame where the panel is added
	 * @param client logged client
	 * @param song   selected song
	 */
	public AddSongPlaylist(JFrame frame, Client client, Song song) {
		setBounds(0, 0, 1000, 672);
		setLayout(null);
		setBackground(Color.black);

		initialize(frame, client, song);
	}

	/**
	 * Initializes the components of the panel.
	 * 
	 * @param frame  frame where the panel is added
	 * @param client logged client
	 * @param song   selected song
	 */

	public void initialize(JFrame frame, Client client, Song song) {
		JPanel panelBackIcon = new JPanel();
		panelBackIcon.setBounds(900, 45, 50, 50);
		add(panelBackIcon);
		panelBackIcon.setLayout(new BorderLayout(0, 0));
		panelBackIcon.setOpaque(false);
		panelBackIcon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				goToLibrary(frame, client);
			}
		});

		panelBackIcon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelBackIcon.setToolTipText("Volver a mi biblioteca.");

		JLabel lblBackIcon = new JLabel("");
		panelBackIcon.add(lblBackIcon, BorderLayout.CENTER);
		WindowUtils.addImage(panelBackIcon, lblBackIcon, "img/icon/home.png");

		panelGridAddSong = new JPanel();
		panelGridAddSong.setBounds(90, 250, 920, 115);
		add(panelGridAddSong);
		panelGridAddSong.setLayout(new GridLayout(1, 6, 0, 0));
		panelGridAddSong.setOpaque(false);

		JLabel lblTitle = new JLabel("Selecciona una lista");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Dialog", Font.BOLD, 22));
		lblTitle.setBounds(365, 44, 300, 36);
		add(lblTitle);

		try {
			addImagesToAlbums(frame, client, song);
		} catch (Exception e1) {
			WindowUtils.errorPane("No se han podido añadir las playlists.", "Error");
		}

		JPanel panelBackground = new JPanel();
		panelBackground.setBounds(0, 0, 1000, 672);
		add(panelBackground);
		panelBackground.setLayout(new BorderLayout(0, 0));

		JLabel lblBackground = new JLabel("");
		panelBackground.add(lblBackground, BorderLayout.CENTER);

		WindowUtils.addImage(panelBackground, lblBackground, "img/panel/create_playlist_bg.jpeg");

	}

	/**
	 * Gets all the playlist of a client.
	 * 
	 * @param client the client to take the playlists
	 * 
	 * @return a list of playlists
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
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
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
	 */
	private void addImagesToAlbums(JFrame frame, Client client, Song song) throws SQLException, Exception {

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
					panelGridAddSong.add(panelAlbum);

					JLabel lblAlbum = createLabel(panelAlbum);

					WindowUtils.addImage(panelAlbum, lblAlbum, image);

					// ArrayList<Song> songsOfAlbum = getSongsOfAlbum(playlist, artist);
					// playlist.setSongs(songsOfAlbum);

					createPlaylistPanelListener(frame, panelAlbum, client, playlist, song);

				} else {
					JPanel panelToFitGrid = createPanelToFitGrid();
					panelGridAddSong.add(panelToFitGrid);
				}
			}
		}
	}

	/**
	 * Takes the client to the library.
	 * 
	 * @param frame  frame where the panel is added
	 * @param client logged client
	 */
	private void goToLibrary(JFrame frame, Client client) {
		frame.getContentPane().removeAll();
		frame.getContentPane()
				.add(PanelFactory.getJPanel(PanelFactory.LIBRARY, frame, client, null, null, null, null, null, null));
		frame.revalidate();
		frame.repaint();
	}

	/**
	 * Creates a listener for the panel. When it is clicked it takes the client to
	 * the playlist panel.
	 * 
	 * @param frame    frame where the panel is added
	 * @param panel    panel to which the listener is being created
	 * @param client   logged client
	 * @param playlist playlist the choosen playlist
	 * @param song     selected song
	 */
	private void createPlaylistPanelListener(JFrame frame, JPanel panel, Client client, Playlist playlist, Song song) {
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ContainManager contMan = new ContainManager();
				Contain contain = new Contain();
				contain.setPlaylist(playlist);
				contain.setSong(song);
				try {
					contMan.insert(contain);
					WindowUtils.confirmationPane("La canción se ha añadido a la lista.", "Confirmación");
					goToPlaylist(frame, client, playlist);
				} catch (Exception e1) {
					WindowUtils.errorPane("No se ha podido añadir a la lista.", "Error");
				}
			}
		});
	}

	/**
	 * Takes the client to the playlist.
	 * 
	 * @param frame    frame where the panel is added
	 * @param client   logged client
	 * @param playlist selected playlist
	 */
	private void goToPlaylist(JFrame frame, Client client, Playlist playlist) {
		frame.getContentPane().removeAll();
		frame.getContentPane().add(
				PanelFactory.getJPanel(PanelFactory.PLAYLIST, frame, client, null, null, null, null, null, playlist));
		frame.revalidate();
		frame.repaint();
	}
}