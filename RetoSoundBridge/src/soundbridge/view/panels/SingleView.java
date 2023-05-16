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
import soundbridge.database.pojos.ArtGroup;
import soundbridge.database.pojos.Artist;
import soundbridge.database.pojos.Client;
import soundbridge.database.pojos.ClientPP;
import soundbridge.database.pojos.Play;
import soundbridge.database.pojos.Playlist;
import soundbridge.database.pojos.Song;
import soundbridge.utils.WindowUtils;
import soundbridge.view.factory.PanelFactory;

/**
 * Panel that contains the song not belonging to any album. A logged client can
 * play the single.
 */
public class SingleView extends JPanel {

	private static final long serialVersionUID = -611767121036203376L;
	private Player player;
	private boolean isPlayerRunning = false;
	private JPanel panelPauseIcon;
	private JTable tableSong;
	private Controller controller;

	/**
	 * Initializes the panel.
	 * 
	 * @param frame    frame where the panel is added
	 * @param client   logged client
	 * @param song     selected single
	 * @param artist   single's owner if not null
	 * @param artGroup single's owner if not null
	 */
	public SingleView(JFrame frame, Client client, Song song, Artist artist, ArtGroup artGroup) {
		setBounds(0, 0, 1000, 672);
		setLayout(null);
		setBackground(Color.black);

		initialize(frame, client, song, artist, artGroup);
	}

	/**
	 * Initializes the components of the panel.
	 * 
	 * @param frame    frame where the panel is added
	 * @param client   logged client
	 * @param song     selected single
	 * @param artist   single's owner if not null
	 * @param artGroup single's owner if not null
	 */
	public void initialize(JFrame frame, Client client, Song song, Artist artist, ArtGroup artGroup) {
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

		JLabel lblArtistName = new JLabel();
		lblArtistName.setForeground(new Color(244, 135, 244));
		lblArtistName.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblArtistName.setBounds(324, 175, 400, 35);
		add(lblArtistName);

		JLabel lblBackIcon = new JLabel("");
		panelBackIcon.add(lblBackIcon, BorderLayout.CENTER);

		JLabel lblName = new JLabel(song.getName());
		lblName.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		lblName.setBounds(324, 90, 400, 40);
		lblName.setForeground(Color.white);
		add(lblName);

		JLabel lblReleaseYear = new JLabel("(" + (new SimpleDateFormat("yyyy")).format(song.getReleaseYear()) + ")");
		lblReleaseYear.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblReleaseYear.setBounds(324, 138, 400, 35);
		lblReleaseYear.setForeground(Color.white);
		add(lblReleaseYear);

		JScrollPane scrollPaneSongs = new JScrollPane();
		scrollPaneSongs.setBounds(40, 310, 904, 320);
		add(scrollPaneSongs);
		scrollPaneSongs.setOpaque(false);
		scrollPaneSongs.getViewport().setOpaque(false);
		scrollPaneSongs.setBorder(BorderFactory.createEmptyBorder());

		tableSong = new JTable();
		tableSong.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableSong.setDefaultEditor(Object.class, null);
		scrollPaneSongs.setViewportView(tableSong);
		tableSong.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		tableSong.setShowGrid(false);
		tableSong.setBackground(Color.black);
		tableSong.setForeground(Color.white);
		tableSong.setSelectionForeground(new Color(244, 135, 244));
		tableSong.setRowHeight(35);
		tableSong.setSelectionBackground(Color.black);
		tableSong.setBorder(null);
		tableSong.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tableSong.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
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
		tableSong.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				playSelectedSong(frame, song, client);
			}
		});
		scrollPaneSongs.setViewportView(tableSong);

		tableSong.getTableHeader().setBackground(Color.black);
		tableSong.getTableHeader().setPreferredSize(new Dimension(scrollPaneSongs.getWidth(), 50));
		tableSong.getTableHeader().setReorderingAllowed(false);

		TableCellRenderer renderer = tableSong.getTableHeader().getDefaultRenderer();
		tableSong.getTableHeader().setDefaultRenderer(new TableCellRenderer() {

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
		tableSong.setModel(tableModelSongs);

		WindowUtils.addImage(panelBackIcon, lblBackIcon, "img/icon/arrow.png");
		WindowUtils.addImage(panelAlbumCover, lblAlbumCover, song.getCover());
		WindowUtils.addImage(panelPauseIcon, lblPauseIcon, "img/icon/pause.png");
		addSongToTable(song, tableModelSongs);
		adjustColumnsWidth();
		addArtistOrGroupName(lblArtistName, artist, artGroup);
	}

	/**
	 * Prints the artist or art group name.
	 * 
	 * @param label    label where the name is printed
	 * @param artist   artist whose name is printed if not null
	 * @param artGroup art group whose name is printed if not null
	 */
	private void addArtistOrGroupName(JLabel label, Artist artist, ArtGroup artGroup) {
		if (artist != null) {
			label.setText(artist.getName());
		} else if (artGroup != null) {
			label.setText(artGroup.getName());
		}
	}

	/**
	 * Adds the single to the table.
	 * 
	 * @param song  single
	 * @param model table's default model
	 */
	private void addSongToTable(Song song, DefaultTableModel model) {
		String number = "1.";
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
		tableSong.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		tableSong.getColumnModel().getColumn(0).setMaxWidth(50);
		tableSong.getColumnModel().getColumn(1).setMaxWidth(50);
		tableSong.getColumnModel().getColumn(2).setMinWidth(300);
		tableSong.getColumnModel().getColumn(3).setMinWidth(100);
		tableSong.getColumnModel().getColumn(4).setMinWidth(200);
	}

	/**
	 * Takes the client back to the artist or art group's profile.
	 * 
	 * @param frame    frame where the panel is added
	 * @param client   logged client
	 * @param artist   artist whose single is displayed if it is not null
	 * @param artGroup art group whose single is displayed if it is not null
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
	 * Plays the song that has been selected on a table and adds a reproduction.
	 * 
	 * @param song   single
	 * @param client logged client
	 */
	private void playSelectedSong(JFrame frame, Song song, Client client) {
		if (isPlayerRunning)
			this.stop();
		int index = tableSong.getSelectedColumn();
		if (index == 0) {
			if (null == controller)
				controller = new Controller();

			controller.addToFavouritesSingleView(client, song, tableSong);

		} else if (index >= 1 && index <= 4) {
			this.play(song.getSource());
			doInsertPlay(client, song);
			panelPauseIcon.setVisible(true);
		} else if (index == 5) {
			if (client instanceof ClientPP) {
				ArrayList<Playlist> playlists = null;
				try {
					playlists = controller.selectPlaylistsOfClientPPById(client);
				} catch (SQLException e) {
					WindowUtils.errorPane("No se han encontrado listas de reproducción.", "Error en la base de datos");
				} catch (Exception e) {
					WindowUtils.errorPane("No se han encontrado listas de reproducción.", "Error genérico");
				}
				if (null != playlists) {
					goToAddSong(frame, client, song);
				} else {
					WindowUtils.errorPane("Primero debe crear una lista.", "Error");
				}
			}
		}
	}

	/**
	 * Stops the music if a song is playing.
	 */
	private void stopMusic() {
		if (isPlayerRunning)
			this.stop();
		panelPauseIcon.setVisible(false);
		tableSong.getSelectionModel().clearSelection();
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
			WindowUtils.errorPane("Error en la cuantificación de la reproducción.", "Error");
		} catch (Exception e) {
			WindowUtils.errorPane("Error en la cuantificación de la reproducción.", "Error");
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

	private void goToAddSong(JFrame frame, Client client, Song song) {
		frame.getContentPane().removeAll();
		frame.getContentPane().add(PanelFactory.getJPanel(PanelFactory.ADDSONGPLAYLIST, frame, client, null, null, null,
				null, song, null));
		frame.revalidate();
		frame.repaint();

	}
}
