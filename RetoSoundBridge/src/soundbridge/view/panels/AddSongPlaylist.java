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

import soundbridge.database.managers.PlaylistManager;
import soundbridge.database.pojos.Client;
import soundbridge.database.pojos.ClientPP;
import soundbridge.database.pojos.Playlist;
import soundbridge.utils.WindowUtils;
import soundbridge.view.factory.PanelFactory;

import javax.swing.JLabel;

/**
 * Panel where the client can see her playlists and choose in which he want to
 * put the song.
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
	 */
	public AddSongPlaylist(JFrame frame, Client client) {
		initialize(frame, client);
	}

	/**
	 * Initializes the components of the panel.
	 * 
	 * @param frame  frame where the panel is added
	 * @param client logged client
	 */

	public void initialize(JFrame frame, Client client) {
		setBounds(0, 0, 1000, 672);
		setLayout(null);
		setBackground(Color.black);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(252, 65, 91, 14);
		add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(252, 130, 148, 46);
		add(lblNewLabel_1);

		JPanel panelBackIcon = new JPanel();
		panelBackIcon.setBounds(900, 45, 50, 50);
		add(panelBackIcon);
		panelBackIcon.setLayout(new BorderLayout(0, 0));
		panelBackIcon.setOpaque(false);
		panelBackIcon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.getContentPane().removeAll();
				frame.getContentPane().add(PanelFactory.getJPanel(PanelFactory.LIBRARY, frame, client, null, null, null,
						null, null, null));
				frame.revalidate();
				frame.repaint();
			}
		});

		panelBackIcon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelBackIcon.setToolTipText("Volver a mi perfil.");

		JLabel lblBackIcon = new JLabel("");
		panelBackIcon.add(lblBackIcon, BorderLayout.CENTER);
		WindowUtils.addImage(panelBackIcon, lblBackIcon, "img/icon/arrow.png");

		panelGridAddSong = new JPanel();
		panelGridAddSong.setBounds(90, 386, 920, 115);
		add(panelGridAddSong);
		panelGridAddSong.setLayout(new GridLayout(1, 6, 0, 0));
		panelGridAddSong.setOpaque(false);

		try {
			addImagesToAlbums(frame, client);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	/**
	 * Gets all the playlist of a client
	 * 
	 * @param client the client to take the playlists.
	 * 
	 * @return returns a list of playlists.
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
					panelGridAddSong.add(panelAlbum);

					JLabel lblAlbum = createLabel(panelAlbum);

					WindowUtils.addImage(panelAlbum, lblAlbum, image);

					// ArrayList<Song> songsOfAlbum = getSongsOfAlbum(playlist, artist);
					// playlist.setSongs(songsOfAlbum);

					createPlaylistPanelListener(frame, panelAlbum, client, playlist);

				} else {
					JPanel panelToFitGrid = createPanelToFitGrid();
					panelGridAddSong.add(panelToFitGrid);
				}
			}
		}
	}
	/**
	 * Creates a listener for the panel. When it is clicked it takes the client to
	 * the playlist panel.
	 * 
	 * @param frame  frame where the panel is added
	 * @param panel  panel to which the listener is being created
	 * @param client logged client
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