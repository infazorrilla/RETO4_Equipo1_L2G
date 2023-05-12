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

public class ArtistProfile extends JPanel {

	private static final long serialVersionUID = -5060067084701215720L;
	private ArrayList<Album> albums = null;
	private ArrayList<Song> singles = null;
	private JPanel panelGridAlbums;
	private JPanel panelGridSingles;
	private JLabel lblSingles;
	private Controller controller = null;

	public ArtistProfile(JFrame frame, Client client, Artist artist) {
		initialize(frame, client, artist);
	}

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
				frame.getContentPane().removeAll();
				frame.getContentPane()
						.add(PanelFactory.getJPanel(PanelFactory.LIBRARY, frame, client, null, null, null, null, null, null));
				frame.revalidate();
				frame.repaint();
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
		panelGridAlbums.setLayout(new GridLayout(1, 5, 69, 0));
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

		doAddImagesToAlbums(frame, client, artist);
		doAddImagesToSingles(frame, client, artist);
	}

	private void doAddImagesToAlbums(JFrame frame, Client client, Artist artist) {
		try {
			addImagesToAlbums(frame, client, artist);
		} catch (SQLException e) {
			WindowUtils.errorPane("No se han podido cargar los álbumes.", "Error en la base de datos");
		} catch (Exception e) {
			WindowUtils.errorPane("No se han podido cargar los álbumes.", "Error general");
		}
	}
	
	private void doAddImagesToSingles(JFrame frame, Client client, Artist artist) {
		try {
			addImagesToSingles(frame, client, artist);
		} catch (SQLException e) {
			WindowUtils.errorPane("No se han podido cargar los singles.", "Error en la base de datos");
		} catch (Exception e) {
			WindowUtils.errorPane("No se han podido cargar los singles.", "Error general");
		}
	}

	private ArrayList<Album> getAlbums(Artist artist) throws SQLException, Exception {
		if (null == controller)
			controller = new Controller();
		return controller.getAlbumsByArtist(artist);

	}

	private ArrayList<Song> getSongsOfAlbum(Album album, Artist artist) throws SQLException, Exception {
		if (null == controller)
			controller = new Controller();
		return controller.getSongsByAlbumAndArtist(album, artist);

	}

	private void addImagesToAlbums(JFrame frame, Client client, Artist artist) throws SQLException, Exception {

		albums = getAlbums(artist);

		if (null != albums) {
			for (int i = 0; i < 5; i++) {
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

	private JPanel createPanelToFitGrid() {
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 115, 115);
		panel.setVisible(false);

		return panel;
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

	private ArrayList<Song> getSingles(Artist artist) throws SQLException, Exception {
		if (null == controller)
			controller = new Controller();
		return controller.getSinglesByArtist(artist);
	}

	private void createSinglePanelListener(JPanel panel, JFrame frame, Client client, Artist artist, Song song) {
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

	private void addImagesToSingles(JFrame frame, Client client, Artist artist) throws SQLException, Exception {
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

					createSinglePanelListener(panelSingle, frame, client, artist, song);

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
