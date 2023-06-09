package soundbridge.view.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import javazoom.jl.player.Player;
import soundbridge.controller.Controller;
import soundbridge.database.pojos.Album;
import soundbridge.database.pojos.ArtGroup;
import soundbridge.database.pojos.Artist;
import soundbridge.database.pojos.Client;
import soundbridge.database.pojos.ClientPP;
import soundbridge.database.pojos.Play;
import soundbridge.database.pojos.Playlist;
import soundbridge.database.pojos.Song;
import soundbridge.database.views.pojos.AverageStars;
import soundbridge.utils.WindowUtils;
import soundbridge.view.factory.PanelFactory;

/**
 * Panel that contains the songs belonging to the album. A logged client can
 * play songs and watch reviews of the album if there is any. When a logged
 * client's subscription is Premium Plus, it's enabled the option to write a
 * review or edit it if the client has already written one.
 */
public class AlbumView extends JPanel {

	private static final long serialVersionUID = -611767121036203376L;
	private Player player;
	private JLabel lblStars;
	private ArrayList<Song> songs;
	private boolean isPlayerRunning = false;
	private JPanel panelPauseIcon;
	private JTable tableSongs;
	private JPanel panelEditReview;
	private Controller controller;

	/**
	 * Initializes the panel.
	 * 
	 * @param frame    frame where the panel is added
	 * @param client   logged client
	 * @param album    selected album
	 * @param artist   album's owner if not null
	 * @param artGroup album's owner if not null
	 */
	public AlbumView(JFrame frame, Client client, Album album, Artist artist, ArtGroup artGroup) {
		setBounds(0, 0, 1000, 672);
		setLayout(null);
		setBackground(Color.black);

		initialize(frame, client, album, artist, artGroup);
	}

	/**
	 * Initializes the components of the panel.
	 * 
	 * @param frame    frame where the panel is added
	 * @param client   logged client
	 * @param album    selected album
	 * @param artist   album's owner if not null
	 * @param artGroup album's owner if not null
	 */
	public void initialize(JFrame frame, Client client, Album album, Artist artist, ArtGroup artGroup) {
		panelPauseIcon = new JPanel();
		panelPauseIcon.setBounds(115, 115, 100, 100);
		add(panelPauseIcon);
		panelPauseIcon.setLayout(new BorderLayout(0, 0));
		panelPauseIcon.setOpaque(false);
		panelPauseIcon.setVisible(false);
		panelPauseIcon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelPauseIcon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				stopMusic();
			}
		});

		JLabel lblPauseIcon = new JLabel("");
		panelPauseIcon.add(lblPauseIcon, BorderLayout.CENTER);

		JPanel panelAlbumCover = new JPanel();
		panelAlbumCover.setBounds(40, 40, 250, 250);
		add(panelAlbumCover);
		panelAlbumCover.setLayout(new BorderLayout(0, 0));
		panelAlbumCover.setOpaque(false);

		JLabel lblAlbumCover = new JLabel("");
		panelAlbumCover.add(lblAlbumCover, BorderLayout.CENTER);

		JPanel panelBackIcon = new JPanel();
		panelBackIcon.setBounds(900, 45, 50, 50);
		add(panelBackIcon);
		panelBackIcon.setLayout(new BorderLayout(0, 0));
		panelBackIcon.setOpaque(false);
		panelBackIcon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				goBack(frame, client, artist, artGroup);
			}
		});
		panelBackIcon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelBackIcon.setToolTipText("Volver");

		JLabel lblBackIcon = new JLabel("");
		panelBackIcon.add(lblBackIcon, BorderLayout.CENTER);

		JLabel lblArtistName = new JLabel();
		lblArtistName.setForeground(new Color(244, 135, 244));
		lblArtistName.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblArtistName.setBounds(324, 175, 400, 35);
		add(lblArtistName);

		JPanel panelStarIcon = new JPanel();
		panelStarIcon.setBounds(330, 218, 30, 30);
		panelStarIcon.setLayout(new BorderLayout(0, 0));
		add(panelStarIcon);
		panelStarIcon.setOpaque(false);
		panelStarIcon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelStarIcon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				goToReviews(frame, client, album, artist, artGroup);
			}
		});

		JLabel lblStarIcon = new JLabel("");
		panelStarIcon.add(lblStarIcon, BorderLayout.CENTER);

		JLabel lblName = new JLabel(album.getName());
		lblName.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		lblName.setBounds(324, 90, 400, 40);
		lblName.setForeground(Color.white);
		add(lblName);

		JLabel lblReleaseYear = new JLabel("(" + (new SimpleDateFormat("yyyy")).format(album.getReleaseYear()) + ")");
		lblReleaseYear.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblReleaseYear.setBounds(324, 138, 400, 35);
		lblReleaseYear.setForeground(Color.white);
		add(lblReleaseYear);

		lblStars = new JLabel("");
		lblStars.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblStars.setBounds(377, 220, 100, 35);
		lblStars.setForeground(Color.white);
		add(lblStars);

		JScrollPane scrollPaneSongs = new JScrollPane();
		scrollPaneSongs.setBounds(40, 310, 904, 320);
		add(scrollPaneSongs);
		scrollPaneSongs.setOpaque(false);
		scrollPaneSongs.getViewport().setOpaque(false);
		scrollPaneSongs.setBorder(BorderFactory.createEmptyBorder());

		tableSongs = new JTable();
		tableSongs.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableSongs.setDefaultEditor(Object.class, null);
		scrollPaneSongs.setViewportView(tableSongs);
		tableSongs.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		tableSongs.setShowGrid(false);
		tableSongs.setBackground(Color.black);
		tableSongs.setForeground(Color.white);
		tableSongs.setSelectionForeground(new Color(244, 135, 244));
		tableSongs.setRowHeight(35);
		tableSongs.setSelectionBackground(Color.black);
		tableSongs.setBorder(null);
		tableSongs.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tableSongs.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			private static final long serialVersionUID = 1852554938143426518L;

			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				if (isSelected || !isSelected) {
					hasFocus = false;
				}
				return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
			}
		});
		tableSongs.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				playSelectedSong(client, frame);
			}
		});
		scrollPaneSongs.setViewportView(tableSongs);

		tableSongs.getTableHeader().setBackground(Color.black);
		tableSongs.getTableHeader().setPreferredSize(new Dimension(scrollPaneSongs.getWidth(), 50));
		tableSongs.getTableHeader().setReorderingAllowed(false);

		TableCellRenderer renderer = tableSongs.getTableHeader().getDefaultRenderer();
		tableSongs.getTableHeader().setDefaultRenderer(new TableCellRenderer() {

			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				JLabel lbl = (JLabel) renderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row,
						column);
				lbl.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
				lbl.setHorizontalAlignment(SwingConstants.LEFT);
				lbl.setForeground(Color.white);
				lbl.setFont(new Font("Lucida Grande", Font.BOLD, 18));
				return lbl;
			}
		});

		Object[] columnsSongs = { "", "", "Título", "Duración", "Género", "" };

		DefaultTableModel tableModelSongs = new DefaultTableModel();
		tableModelSongs.setColumnCount(6);
		tableModelSongs.setColumnIdentifiers(columnsSongs);
		tableSongs.setModel(tableModelSongs);

		panelEditReview = new JPanel();
		panelEditReview.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				goToWriteReview(frame, client, album, artist, artGroup);
			}
		});
		panelEditReview.setOpaque(false);
		panelEditReview.setBounds(410, 225, 20, 20);
		add(panelEditReview);
		panelEditReview.setLayout(new BorderLayout(0, 0));
		panelEditReview.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		JLabel lblEditReviewIcon = new JLabel("");
		panelEditReview.add(lblEditReviewIcon, BorderLayout.CENTER);

		WindowUtils.addImage(panelBackIcon, lblBackIcon, "img/icon/arrow.png");
		WindowUtils.addImage(panelStarIcon, lblStarIcon, "img/icon/star.png");
		WindowUtils.addImage(panelPauseIcon, lblPauseIcon, "img/icon/pause.png");
		WindowUtils.addImage(panelEditReview, lblEditReviewIcon, "img/icon/pen.png");
		WindowUtils.addImage(panelAlbumCover, lblAlbumCover, album.getCover());
		addReviewStarsToLabel(album);
		addSongsToTable(album, tableModelSongs);
		adjustColumnsWidth();
		addArtistOrGroupName(lblArtistName, artist, artGroup);
		showEditReviewPanel(client);
	}

	/**
	 * Returns the average stars of an album. Exceptions are captured.
	 * 
	 * @param album album from which the average rating is obtained
	 * @return average stars
	 */
	private AverageStars doGetAverageStars(Album album) {
		AverageStars averageStars = null;
		Controller controller = new Controller();

		try {
			averageStars = controller.getAverageStars(album);
		} catch (SQLException e) {
			System.out.println(e);
			WindowUtils.errorPane("No se ha podido añadir la valoración media.", "Error en la base de datos");
		} catch (Exception e) {
			WindowUtils.errorPane("No se ha podido añadir la valoración media.", "Error general");
			System.out.println(e);
		}

		return averageStars;
	}

	/**
	 * Prints the name of an artist or group on a label.
	 * 
	 * @param label    label where the name is printed
	 * @param artist   artist whose name is printed if it is not null
	 * @param artGroup art group whose name is printed if it is not null
	 */
	private void addArtistOrGroupName(JLabel label, Artist artist, ArtGroup artGroup) {
		if (artist != null) {
			label.setText(artist.getName());
		} else if (artGroup != null) {
			label.setText(artGroup.getName());
		}
	}

	/**
	 * Prints the average stars' value on a label.
	 * 
	 * @param album album from which the average rating is obtained
	 */
	private void addReviewStarsToLabel(Album album) {
		AverageStars averageStars = doGetAverageStars(album);
		lblStars.setText("" + averageStars.getAverage());
	}

	/**
	 * Adds every song's information to a table.
	 * 
	 * @param album album to which songs belong
	 * @param model model of the table
	 */
	private void addSongsToTable(Album album, DefaultTableModel model) {
		songs = album.getSongs();
		if (songs != null) {
			for (int i = 0; i < songs.size(); i++) {
				Song song = songs.get(i);
				addInfoToTable(song, model, i);
			}
		}
	}

	/**
	 * Gets one song's information and adds it to a table.
	 * 
	 * @param song  song from which information is obtained
	 * @param model model of the table
	 * @param i     position of the song on the array list
	 */
	private void addInfoToTable(Song song, DefaultTableModel model, int i) {
		String number = (i + 1) + ".";
		String title = song.getName();
		int totalSeconds = song.getDuration();
		int minutes = (totalSeconds % 3600) / 60;
		int seconds = totalSeconds % 60;
		String secondsStr = String.format("%02d", seconds);
		String duration = minutes + ":" + secondsStr;
		String genre = song.getGenre();

		model.addRow(new String[] { "\u2661", number, title, duration, genre, "+" });
	}

	/**
	 * Adjusts widths of a table's columns.
	 */
	private void adjustColumnsWidth() {
		tableSongs.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		tableSongs.getColumnModel().getColumn(0).setMaxWidth(50);
		tableSongs.getColumnModel().getColumn(1).setMaxWidth(50);
		tableSongs.getColumnModel().getColumn(2).setMinWidth(300);
		tableSongs.getColumnModel().getColumn(3).setMinWidth(100);
		tableSongs.getColumnModel().getColumn(4).setMinWidth(200);
	}

	/**
	 * Takes the client back to the artist or art group's profile.
	 * 
	 * @param frame    frame where the panel is added
	 * @param client   logged client
	 * @param artist   artist whose album is displayed if it is not null
	 * @param artGroup art group whose album is displayed if it is not null
	 */
	private void goBack(JFrame frame, Client client, Artist artist, ArtGroup artGroup) {
		this.stop();
		if (artist != null) {
			frame.getContentPane().removeAll();
			frame.getContentPane().add(PanelFactory.getJPanel(PanelFactory.ARTIST_PROFILE, frame, client, null, artist,
					null, null, null, null));
			frame.revalidate();
			frame.repaint();
		} else if (artGroup != null) {
			frame.getContentPane().removeAll();
			frame.getContentPane().add(PanelFactory.getJPanel(PanelFactory.GROUP_PROFILE, frame, client, null, null,
					artGroup, null, null, null));
			frame.revalidate();
			frame.repaint();
		}
	}

	/**
	 * Plays the song that has been selected on a table, adds a reproduction and if
	 * the client selects the first column the song is added to the favorites play
	 * list.
	 * 
	 * @param client logged client
	 * @param frame frame where the panel is added
	 */
	private void playSelectedSong(Client client, JFrame frame) {
		int indexColumn = tableSongs.getSelectedColumn();
		int indexRow = tableSongs.getSelectedRow();

		if (null == controller)
			controller = new Controller();

		if (indexColumn == 0) {
			controller.addToFavourites(client, songs, tableSongs);
		} else if (indexColumn >= 1 && indexColumn <= 4) {
			if (isPlayerRunning)
				this.stop();

			Song song = songs.get(indexRow);
			this.play(song.getSource());
			doInsertPlay(client, song);
			panelPauseIcon.setVisible(true);
		} else if (indexColumn == 5) {
			if (client instanceof ClientPP) {
				ArrayList<Playlist> playlists = null;
				try {
					playlists = controller.getPlaylistsOfClientPP(client);
				} catch (SQLException e) {
					WindowUtils.errorPane("No se han encontrado listas de reproducción.", "Error en la base de datos");
				} catch (Exception e) {
					WindowUtils.errorPane("No se han encontrado listas de reproducción.", "Error genérico");
				}
				
				if (null != playlists) {
					Song song = songs.get(indexRow);
					goToAddSong(frame, client, song);
				} else {
					WindowUtils.errorPane("Primero debe crear una lista.", "Error");
				}
			}
		}

	}
	
	/**
	 * Goes to an other panel to add song to a playlist.
	 * 
	 * @param frame  frame where the panel is added
	 * @param client logged client
	 */
	private void goToAddSong(JFrame frame, Client client, Song song) {
		frame.getContentPane().removeAll();
		frame.getContentPane().add(PanelFactory.getJPanel(PanelFactory.ADDSONGPLAYLIST, frame, client, null, null, null,
				null, song, null));
		frame.revalidate();
		frame.repaint();

	}

	/**
	 * Stops the music if a song is playing.
	 */
	private void stopMusic() {
		if (isPlayerRunning)
			this.stop();
		panelPauseIcon.setVisible(false);
		tableSongs.getSelectionModel().clearSelection();
	}

	/**
	 * Inserts a reproduction every time a song is played.
	 * 
	 * @param client logged client
	 * @param song   selected song
	 */
	private void doInsertPlay(Client client, Song song) {
		Controller controller = new Controller();
		Play play = new Play();
		play.setClient(client);
		play.setSong(song);

		try {
			controller.insertPlay(play);
		} catch (SQLException e) {
			WindowUtils.errorPane("Error en la reproducción.", "Error");

		} catch (Exception e) {
			WindowUtils.errorPane("Error en la reproducción.", "Error");
		}

	}

	/**
	 * Starts the player given the song at path. Path cannot be null.
	 * 
	 * @param path path of the music file
	 */
	private void play(String path) {
		new Thread() {
			@Override
			public void run() {
				try {
					FileInputStream fileInputStream = new FileInputStream(path);
					player = new Player(fileInputStream);
					player.play();
				} catch (Exception e) {
					WindowUtils.errorPane("No se ha podido reproducir la canción.", "Error");
				}
			}
		}.start();

		isPlayerRunning = true;
	}

	/**
	 * Stops the player.
	 */
	public void stop() {
		if (player != null) {
			player.close();
		}

		isPlayerRunning = false;
	}

	/**
	 * Takes the customer to the reviews panel if there is any review.
	 * 
	 * @param frame    frame where the panel is added
	 * @param client   logged client
	 * @param album    selected album
	 * @param artist   artist whose album is displayed if it is not null
	 * @param artGroup art group whose album is displayed if it is not null
	 */
	private void goToReviews(JFrame frame, Client client, Album album, Artist artist, ArtGroup artGroup) {
		this.stop();
		if (lblStars.getText().equals("0.0")) {
			WindowUtils.errorPane("No hay valoraciones.", "Error");
		} else {
			if (artist != null) {
				frame.getContentPane().removeAll();
				frame.getContentPane().add(PanelFactory.getJPanel(PanelFactory.CLIENTS_REVIEWS, frame, client, null,
						artist, null, album, null, null));
				frame.revalidate();
				frame.repaint();
			} else if (artGroup != null) {
				frame.getContentPane().removeAll();
				frame.getContentPane().add(PanelFactory.getJPanel(PanelFactory.CLIENTS_REVIEWS, frame, client, null,
						null, artGroup, album, null, null));
				frame.revalidate();
				frame.repaint();
			}
		}
	}

	/**
	 * Takes the customer to the panel where a review can be written if the client
	 * is premium plus.
	 * 
	 * @param frame    frame where the panel is added
	 * @param client   logged client
	 * @param album    selected album
	 * @param artist   artist whose album is displayed if it is not null
	 * @param artGroup art group whose album is displayed if it is not null
	 */
	private void goToWriteReview(JFrame frame, Client client, Album album, Artist artist, ArtGroup artGroup) {
		this.stop();
		if (artist != null) {
			frame.getContentPane().removeAll();
			frame.getContentPane().add(PanelFactory.getJPanel(PanelFactory.WRITE_REVIEW, frame, client, null, artist,
					null, album, null, null));
			frame.revalidate();
			frame.repaint();
		} else if (artGroup != null) {
			frame.getContentPane().removeAll();
			frame.getContentPane().add(PanelFactory.getJPanel(PanelFactory.WRITE_REVIEW, frame, client, null, null,
					artGroup, album, null, null));
			frame.revalidate();
			frame.repaint();
		}
	}

	/**
	 * Shows the review edition panel if the client is premium plus. If not, the
	 * panel is hidden.
	 * 
	 * @param client logged client
	 */
	private void showEditReviewPanel(Client client) {
		if (client instanceof ClientPP) {
			panelEditReview.setVisible(true);
		} else {
			panelEditReview.setVisible(false);

		}

	}
}
