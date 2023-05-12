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

import soundbridge.controller.Controller;
import soundbridge.database.pojos.Album;
import soundbridge.database.pojos.Artist;
import soundbridge.database.pojos.Client;
import soundbridge.database.pojos.Song;
import soundbridge.utils.WindowUtils;
import soundbridge.view.factory.PanelFactory;
import javax.swing.JTextArea;
import java.awt.GridLayout;

/**
 * Panel that shows the profile of an artist. A logged client can select an
 * album or single.
 */
public class ArtistProfile extends JPanel {

	private static final long serialVersionUID = -5060067084701215720L;
	private ArrayList<Album> albums = null;
	private ArrayList<Song> singles = null;
	private JPanel panelGridAlbums;
	private JPanel panelGridSingles;
	private JLabel lblSingles;
	private Controller controller = null;

	/**
	 * Initializes the panel.
	 * 
	 * @param frame  frame where the panel is added
	 * @param client logged client
	 * @param artist artist whose profile is shown
	 */
	public ArtistProfile(JFrame frame, Client client, Artist artist) {
		initialize(frame, client, artist);
	}

	/**
	 * Initializes the components of the panel.
	 * 
	 * @param frame  frame where the panel is added
	 * @param client logged client
	 * @param artist artist whose profile is shown
	 */
	private void initialize(JFrame frame, Client client, Artist artist) {
		setBounds(0, 0, 1000, 672);
		setLayout(null);
		setBackground(Color.black);

		JPanel panelArtistImage = new JPanel();
		panelArtistImage.setBounds(40, 40, 250, 250);
		add(panelArtistImage);
		panelArtistImage.setLayout(new BorderLayout(0, 0));
		panelArtistImage.setOpaque(false);

		JLabel lblArtistImage = new JLabel("");
		panelArtistImage.add(lblArtistImage, BorderLayout.CENTER);

		JPanel panelHomeIcon = new JPanel();
		panelHomeIcon.setBounds(900, 45, 50, 50);
		add(panelHomeIcon);
		panelHomeIcon.setLayout(new BorderLayout(0, 0));
		panelHomeIcon.setOpaque(false);
		panelHomeIcon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				goToLibrary(frame, client);
			}
		});
		panelHomeIcon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelHomeIcon.setToolTipText("Volver a mi biblioteca.");

		JLabel lblHomeIcon = new JLabel("");
		panelHomeIcon.add(lblHomeIcon, BorderLayout.CENTER);

		JLabel lblName = new JLabel(artist.getName());
		lblName.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblName.setBounds(324, 103, 400, 35);
		lblName.setForeground(Color.white);
		add(lblName);

		JTextArea textBio = new JTextArea(artist.getDescription());
		textBio.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		textBio.setEditable(false);
		textBio.setOpaque(false);
		textBio.setForeground(Color.white);
		textBio.setBounds(324, 172, 487, 128);
		add(textBio);
		textBio.setLineWrap(true);
		textBio.setWrapStyleWord(true);

		WindowUtils.addImage(panelHomeIcon, lblHomeIcon, "img/icon/home.png");
		WindowUtils.addImage(panelArtistImage, lblArtistImage, artist.getImage());

		JLabel lblAlbums = new JLabel("Álbumes");
		lblAlbums.setForeground(Color.WHITE);
		lblAlbums.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblAlbums.setBounds(40, 302, 400, 35);
		add(lblAlbums);

		panelGridAlbums = new JPanel();
		panelGridAlbums.setBounds(40, 350, 920, 115);
		add(panelGridAlbums);
		panelGridAlbums.setLayout(new GridLayout(1, 6, 0, 0));
		panelGridAlbums.setOpaque(false);

		lblSingles = new JLabel("Singles");
		lblSingles.setForeground(Color.WHITE);
		lblSingles.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblSingles.setBounds(40, 480, 400, 35);
		add(lblSingles);

		panelGridSingles = new JPanel();
		panelGridSingles.setBounds(40, 528, 920, 115);
		add(panelGridSingles);
		panelGridSingles.setLayout(new GridLayout(1, 5, 69, 0));
		panelGridSingles.setOpaque(false);

		doCreateFunctionalAlbumPanels(frame, client, artist);
		doCreateFunctionalSinglePanels(frame, client, artist);
	}
	
	/**
	 * Takes the client back to the library panel.
	 * 
	 * @param frame		frame where the panel is added
	 * @param client	logged client
	 */
	private void goToLibrary(JFrame frame, Client client) {
		frame.getContentPane().removeAll();
		frame.getContentPane().add(PanelFactory.getJPanel(PanelFactory.LIBRARY, frame, client, null, null, null,
				null, null, null));
		frame.revalidate();
		frame.repaint();
	}

	/**
	 * Creates the corresponding panel for each album.
	 * 
	 * @param frame  frame where the panel is added
	 * @param client logged client
	 * @param artist artist whose profile is shown
	 */
	private void doCreateFunctionalAlbumPanels(JFrame frame, Client client, Artist artist) {
		try {
			createFunctionalAlbumPanels(frame, client, artist);
		} catch (SQLException e) {
			WindowUtils.errorPane("No se han podido cargar los álbumes.", "Error en la base de datos");
		} catch (Exception e) {
			WindowUtils.errorPane("No se han podido cargar los álbumes.", "Error general");
		}
	}

	/**
	 * Creates the corresponding panel for each single.
	 * 
	 * @param frame  frame where the panel is added
	 * @param client logged client
	 * @param artist artist whose profile is shown
	 */
	private void doCreateFunctionalSinglePanels(JFrame frame, Client client, Artist artist) {
		try {
			createFunctionalSinglePanels(frame, client, artist);
		} catch (SQLException e) {
			WindowUtils.errorPane("No se han podido cargar los singles.", "Error en la base de datos");
		} catch (Exception e) {
			WindowUtils.errorPane("No se han podido cargar los singles.", "Error general");
		}
	}

	/**
	 * Returns every album that belongs to the artist.
	 * 
	 * @param artist artist whose profile is shown
	 * @return albums of the artist
	 * @throws SQLException
	 * @throws Exception
	 */
	private ArrayList<Album> getAlbums(Artist artist) throws SQLException, Exception {
		if (null == controller)
			controller = new Controller();
		return controller.getAlbumsByArtist(artist);

	}

	/**
	 * Returns every song that belongs to the album.
	 * 
	 * @param album  album that contains the songs
	 * @param artist artist whose profile is shown
	 * @return songs of the album
	 * @throws SQLException
	 * @throws Exception
	 */
	private ArrayList<Song> getSongsOfAlbum(Album album, Artist artist) throws SQLException, Exception {
		if (null == controller)
			controller = new Controller();
		return controller.getSongsByAlbumAndArtist(album, artist);

	}

	/**
	 * Creates a maximum of five panels that correspond to the albums of the artist.
	 * 
	 * @param frame  frame where the panel is added
	 * @param client logged client
	 * @param artist artist whose profile is shown
	 * @throws SQLException
	 * @throws Exception
	 */
	private void createFunctionalAlbumPanels(JFrame frame, Client client, Artist artist) throws SQLException, Exception {

		albums = getAlbums(artist);

		if (null != albums) {
			for (int i = 0; i < 6; i++) {
				if (i < albums.size()) {
					Album album = albums.get(i);
					String image = album.getCover();

					JPanel panelAlbum = createPanel();
					panelAlbum.setToolTipText(album.getName() + " ("
							+ (new SimpleDateFormat("yyyy")).format(album.getReleaseYear()) + ")");
					panelGridAlbums.add(panelAlbum);

					JLabel lblAlbum = createLabel(panelAlbum);

					WindowUtils.addImage(panelAlbum, lblAlbum, image);

					ArrayList<Song> songsOfAlbum = getSongsOfAlbum(album, artist);
					album.setSongs(songsOfAlbum);

					createAlbumPanelListener(frame, panelAlbum, client, artist, album);

				} else {
					JPanel panelToFitGrid = createPanelToFitGrid();
					panelGridAlbums.add(panelToFitGrid);
				}
			}
		}
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
	 * Creates a listener for the panel. When it is clicked it takes the client to
	 * the album panel.
	 * 
	 * @param frame		frame where the panel is added
	 * @param panel		panel to which the listener is being created
	 * @param client	logged client
	 * @param artist	artist whose profile is shown
	 * @param album		related album
	 */
	private void createAlbumPanelListener(JFrame frame, JPanel panel, Client client, Artist artist, Album album) {
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.getContentPane().removeAll();
				frame.getContentPane().add(PanelFactory.getJPanel(PanelFactory.ALBUM_VIEW, frame, client, null, artist,
						null, album, null, null));
				frame.revalidate();
				frame.repaint();
			}
		});
	}

	/**
	 * Returns the songs that are not included in any album.
	 * 
	 * @param artist	artist whose profile is shown
	 * @return			songs that have no related album
	 * @throws SQLException
	 * @throws Exception
	 */
	private ArrayList<Song> getSingles(Artist artist) throws SQLException, Exception {
		if (null == controller)
			controller = new Controller();
		return controller.getSinglesByArtist(artist);
	}

	/**
	 * Creates a listener for the single. When it is clicked it takes the client to
	 * the single panel.
	 * 
	 * @param frame		frame where the panel is added
	 * @param panel		panel to which the listener is being created
	 * @param client	logged client
	 * @param artist	artist whose profile is shown
	 * @param song		related song
	 */
	private void createSinglePanelListener(JFrame frame, JPanel panel, Client client, Artist artist, Song song) {
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.getContentPane().removeAll();
				frame.getContentPane().add(PanelFactory.getJPanel(PanelFactory.SINGLE_VIEW, frame, client, null, artist,
						null, null, song, null));
				frame.revalidate();
				frame.repaint();
			}
		});
	}

	/**
	 * Creates a maximum of five panels that correspond to the singles of the artist.
	 * 
	 * @param frame  frame where the panel is added
	 * @param client logged client
	 * @param artist artist whose profile is shown
	 * @throws SQLException
	 * @throws Exception
	 */
	private void createFunctionalSinglePanels(JFrame frame, Client client, Artist artist) throws SQLException, Exception {
		singles = getSingles(artist);

		if (null != singles) {
			for (int i = 0; i < 5; i++) {
				if (i < singles.size()) {
					Song song = singles.get(i);
					String image = song.getCover();

					JPanel panelSingle = createPanel();
					panelSingle.setToolTipText(
							song.getName() + " (" + (new SimpleDateFormat("yyyy")).format(song.getReleaseYear()) + ")");
					panelGridSingles.add(panelSingle);

					JLabel lblSingle = createLabel(panelSingle);

					WindowUtils.addImage(panelSingle, lblSingle, image);

					createSinglePanelListener(frame, panelSingle, client, artist, song);

				} else {
					JPanel panelToFitGrid = createPanelToFitGrid();
					panelGridSingles.add(panelToFitGrid);
				}
			}
		} else {
			panelGridSingles.setVisible(false);
			lblSingles.setVisible(false);
		}
	}
}
