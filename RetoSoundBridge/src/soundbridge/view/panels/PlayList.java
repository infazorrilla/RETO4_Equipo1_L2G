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
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;

import javax.swing.JPanel;

import soundbridge.controller.Controller;
import soundbridge.database.managers.ContainManager;
import soundbridge.database.managers.PlaylistManager;
import soundbridge.database.managers.SongManager;
import soundbridge.database.pojos.ArtGroup;
import soundbridge.database.pojos.Artist;
import soundbridge.database.pojos.Client;
import soundbridge.database.pojos.ClientPP;
import soundbridge.database.pojos.Contain;
import soundbridge.database.pojos.Play;
import soundbridge.database.pojos.Playlist;
import soundbridge.database.pojos.Song;
import soundbridge.utils.WindowUtils;
import soundbridge.view.factory.PanelFactory;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import javazoom.jl.player.Player;

/**
 * Panel that shows the normal playlist, with the title,description and songs
 * dynamically.
 */
public class PlayList extends JPanel {

	private static final long serialVersionUID = -3405591680500453035L;
	private ArrayList<Song> playlistSongs;
	public JTable tableSongsPlaylist;
	private DefaultTableModel modelSongsPlaylist = null;
	private Player player;
	private Controller controller;
	private boolean isPlayerRunning = false;
	private JPanel panelPauseIcon;

	/**
	 * Initializes the panel.
	 * 
	 * @param frame    frame where the panel is added
	 * @param client   logged client
	 * @param playlist selected playlist
	 */
	public PlayList(JFrame frame, Client client, Playlist playlist) {
		initialize(frame, client, playlist);
	}

	/**
	 * Initializes the components of the panel.
	 * 
	 * @param frame    frame where the panel is added
	 * @param client   logged client
	 * @param playlist selected playlist
	 */
	public void initialize(JFrame frame, Client client, Playlist playlist) {
		setBounds(0, 0, 1000, 672);
		setBackground(Color.black);
		setLayout(null);

		JPanel panelRemovePlaylist = new JPanel();
		panelRemovePlaylist.setToolTipText("Eliminar playlist.");
		panelRemovePlaylist.setOpaque(false);
		panelRemovePlaylist.setBounds(240, 55, 40, 40);
		add(panelRemovePlaylist);
		panelRemovePlaylist.setLayout(new BorderLayout(0, 0));
		panelRemovePlaylist.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelRemovePlaylist.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				removePlaylist(playlist, frame, client);
			}
		});

		JLabel lblRemoveIcon = new JLabel("");
		panelRemovePlaylist.add(lblRemoveIcon, BorderLayout.CENTER);

		WindowUtils.addImage(panelRemovePlaylist, lblRemoveIcon, "img/icon/remove.png");

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

		JPanel panelPlaylistCover = new JPanel();
		panelPlaylistCover.setBounds(40, 40, 250, 250);
		add(panelPlaylistCover);
		panelPlaylistCover.setLayout(new BorderLayout(0, 0));
		panelPlaylistCover.setOpaque(false);

		JLabel lblPlayListCover = new JLabel("");
		panelPlaylistCover.add(lblPlayListCover, BorderLayout.CENTER);

		WindowUtils.addImage(panelPlaylistCover, lblPlayListCover, "img/icon/playlist_icon.png");

		JLabel lblTitle = new JLabel(playlist.getName());
		lblTitle.setFont(new Font("Dialog", Font.BOLD, 25));
		lblTitle.setForeground(new Color(255, 255, 255));
		lblTitle.setBounds(327, 70, 584, 39);
		add(lblTitle);

		JLabel lblCreator = new JLabel("@" + client.getUsername().toString());
		lblCreator.setFont(new Font("Dialog", Font.BOLD, 16));
		lblCreator.setForeground(new Color(244, 135, 244));
		lblCreator.setBounds(327, 110, 584, 39);
		add(lblCreator);

		JTextArea textBio = new JTextArea(playlist.getDescription());
		textBio.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		textBio.setEditable(false);
		textBio.setOpaque(false);
		textBio.setForeground(Color.white);
		textBio.setBounds(327, 160, 487, 128);
		add(textBio);
		textBio.setLineWrap(true);
		textBio.setWrapStyleWord(true);

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
		WindowUtils.addImage(panelBackIcon, lblBackIcon, "img/icon/arrow.png");

		JScrollPane scrollPanePlaylist = new JScrollPane();
		scrollPanePlaylist.setBounds(44, 320, 867, 289);
		add(scrollPanePlaylist);
		scrollPanePlaylist.setOpaque(false);
		scrollPanePlaylist.getViewport().setOpaque(false);
		scrollPanePlaylist.setBorder(BorderFactory.createEmptyBorder());

		scrollPanePlaylist.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
			@Override
			protected JButton createDecreaseButton(int orientation) {
				return createZeroButton();
			}

			@Override
			protected JButton createIncreaseButton(int orientation) {
				return createZeroButton();
			}

			private JButton createZeroButton() {
				JButton jbutton = new JButton();
				jbutton.setPreferredSize(new Dimension(0, 0));
				jbutton.setMinimumSize(new Dimension(0, 0));
				jbutton.setMaximumSize(new Dimension(0, 0));
				return jbutton;
			}

			@Override
			protected void configureScrollBarColors() {
				this.thumbColor = Color.black;
				this.thumbHighlightColor = Color.WHITE;
				this.trackColor = Color.BLACK;
			}
		});

		tableSongsPlaylist = new JTable();
		tableSongsPlaylist.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				playSelectedSong(client, frame, playlist);
			}
		});
		tableSongsPlaylist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableSongsPlaylist.setDefaultEditor(Object.class, null);
		scrollPanePlaylist.setViewportView(tableSongsPlaylist);
		tableSongsPlaylist.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		Object[] columnsSongs = { "", "", "Título", "Duración", "Género", "Artista", "", "" };

		tableSongsPlaylist.setShowGrid(false);
		tableSongsPlaylist.setBackground(Color.black);
		tableSongsPlaylist.setForeground(Color.white);
		tableSongsPlaylist.setSelectionForeground(new Color(244, 135, 244));
		tableSongsPlaylist.setRowHeight(35);
		tableSongsPlaylist.setSelectionBackground(Color.black);
		tableSongsPlaylist.setBorder(null);
		tableSongsPlaylist.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {

			private static final long serialVersionUID = 5651737319882097189L;

			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				if (isSelected || !isSelected) {
					hasFocus = false;
				}
				return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
			}
		});
		tableSongsPlaylist.getTableHeader().setBackground(Color.black);
		tableSongsPlaylist.getTableHeader().setPreferredSize(new Dimension(scrollPanePlaylist.getWidth(), 50));
		tableSongsPlaylist.getTableHeader().setReorderingAllowed(false);
		tableSongsPlaylist.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		TableCellRenderer renderer = tableSongsPlaylist.getTableHeader().getDefaultRenderer();
		tableSongsPlaylist.getTableHeader().setDefaultRenderer(new TableCellRenderer() {

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

		modelSongsPlaylist = new DefaultTableModel();
		modelSongsPlaylist.setColumnIdentifiers(columnsSongs);
		tableSongsPlaylist.setModel(modelSongsPlaylist);
		adjustColumnsWidth();
		addSongsToTable(modelSongsPlaylist, playlist);

		WindowUtils.addImage(panelPauseIcon, lblPauseIcon, "img/icon/pause_black.png");

	}

	/**
	 * Asks the client to confirm the removal of his playlist.
	 * 
	 * @return reply of the client
	 */
	private int askToConfirmDeletion() {
		int reply = WindowUtils.yesOrNoPaneWithIcon("¿Desea eliminar la lista actual?", "Eliminar Lista",
				"img/icon/alert.png");
		return reply;
	}

	/**
	 * Deletes the actual playlist if client confirms the removal. Then takes the
	 * client to library.
	 * 
	 * @param playlist playlist to be deleted
	 * @param frame    frame where the panel is added
	 * @param client   logged client
	 */
	private void removePlaylist(Playlist playlist, JFrame frame, Client client) {
		int reply = askToConfirmDeletion();
		if (reply == 0) {
			PlaylistManager playlistManager = new PlaylistManager();
			try {
				playlistManager.delete(playlist);
				WindowUtils.confirmationPane("Su lista ha sido eliminada.", "Confirmación");
				goToLibrary(frame, client);
			} catch (SQLException e) {
				WindowUtils.errorPane("No se ha podido eliminar la lista.", "Error en la base de datos");
			} catch (Exception e) {
				WindowUtils.errorPane("No se ha podido eliminar la lista.", "Error genérico");
			}
		}

	}

	/**
	 * Reproduce the selected song using the index of the table.
	 * 
	 * @param client   the client that had done the log in
	 * @param frame    frame where the panel is added
	 * @param playlist playlist containing the song
	 */
	private void playSelectedSong(Client client, JFrame frame, Playlist playlist) {

		int indexColumn = tableSongsPlaylist.getSelectedColumn();
		int indexRow = tableSongsPlaylist.getSelectedRow();

		if (null == controller)
			controller = new Controller();

		if (indexColumn == 0) {
			controller.addToFavourites(client, playlistSongs, tableSongsPlaylist);
		} else if (indexColumn >= 1 && indexColumn <= 5) {
			if (isPlayerRunning)
				this.stop();

			Song song = playlistSongs.get(indexRow);
			this.play(song.getSource());
			doInsertPlay(client, song);
			panelPauseIcon.setVisible(true);
		} else if (indexColumn == 6) {
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
					Song song = playlistSongs.get(indexRow);
					goToAddSong(frame, client, song);
				} else {
					WindowUtils.errorPane("Primero debe crear una lista.", "Error");
				}
			}
		} else if (indexColumn == 7) {
			Song song = playlistSongs.get(indexRow);
			ContainManager containManager = new ContainManager();
			Contain contain = new Contain();
			contain.setPlaylist(playlist);
			contain.setSong(song);
			try {
				containManager.delete(contain);
				WindowUtils.confirmationPane("Se ha eliminado la canción " + song.getName() + ".", "Confirmación");
				modelSongsPlaylist.setRowCount(0);
				addSongsToTable(modelSongsPlaylist, playlist);
			} catch (SQLException e) {
				WindowUtils.errorPane("No se ha podido eliminar la canción de la lista.", "Error en la base de datos");
			} catch (Exception e) {
				WindowUtils.errorPane("No se ha podido eliminar la canción de la lista.", "Error genérico");
			}
		}
	}

	/**
	 * Insert on the date base when a song plays.
	 * 
	 * @param client logged client
	 * @param song   song that is being played.
	 */
	private void doInsertPlay(Client client, Song song) {
		if (null == controller)
			controller = new Controller();
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
	 * Plays the music.
	 * 
	 * @param path path of the song to play
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
	 * Stops the song.
	 */
	public void stop() {
		if (player != null) {
			player.close();
		}

		isPlayerRunning = false;
	}

	/**
	 * Adjust column widths.
	 */
	private void adjustColumnsWidth() {
		tableSongsPlaylist.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		tableSongsPlaylist.getColumnModel().getColumn(0).setMaxWidth(50);
		tableSongsPlaylist.getColumnModel().getColumn(1).setMinWidth(30);
		tableSongsPlaylist.getColumnModel().getColumn(2).setMinWidth(300);
		tableSongsPlaylist.getColumnModel().getColumn(3).setMinWidth(100);
		tableSongsPlaylist.getColumnModel().getColumn(4).setMinWidth(200);
		tableSongsPlaylist.getColumnModel().getColumn(5).setMinWidth(160);
	}

	private void addSongsToTable(DefaultTableModel model, Playlist playlist) {

		if (null == controller)
			controller = new Controller();
		SongManager sonMan = new SongManager();
		try {
			playlistSongs = sonMan.selectSongsOfPlaylist(playlist);
		} catch (SQLException e) {
			WindowUtils.errorPane("No se ha podido añadir la canción.", "Error");
		} catch (Exception e) {
			WindowUtils.errorPane("No se ha podido añadir la canción.", "Error");
		}
		if (playlistSongs != null) {

			if (null == controller)
				controller = new Controller();

			for (int i = 0; i < playlistSongs.size(); i++) {
				Song song = playlistSongs.get(i);
				Artist artista = null;
				ArtGroup arttGroup = null;
				try {
					if (song.getArtist() != null)
						artista = controller.getArtistById(song.getArtist().getId());
					else
						arttGroup = controller.getGroupById(song.getArtGroup().getId());

				} catch (SQLException e) {
					WindowUtils.errorPane("No se ha podido añadir la canción.", "Error");
				} catch (Exception e) {
					WindowUtils.errorPane("No se ha podido añadir la canción.", "Error");
				}
				String number = (i + 1) + ".";
				String title = song.getName();
				int totalSeconds = song.getDuration();
				int minutes = (totalSeconds % 3600) / 60;
				int seconds = totalSeconds % 60;
				String secondsStr = String.format("%02d", seconds);
				String duration = minutes + ":" + secondsStr;
				String genre = song.getGenre();

				if (artista != null)
					model.addRow(
							new String[] { "\u2661", number, title, duration, genre, artista.getName(), "+", "-" });
				if (arttGroup != null)
					model.addRow(
							new String[] { "\u2661", number, title, duration, genre, arttGroup.getName(), "+", "-" });
			}
		}
	}

	/**
	 * Goes to an other panel to add song to a playlist.
	 * 
	 * @param frame  frame where the panel is added
	 * @param client logged client
	 * @param song   to be added
	 */
	private void goToAddSong(JFrame frame, Client client, Song song) {
		stop();
		frame.getContentPane().removeAll();
		frame.getContentPane().add(PanelFactory.getJPanel(PanelFactory.ADDSONGPLAYLIST, frame, client, null, null, null,
				null, song, null));
		frame.revalidate();
		frame.repaint();

	}

	/**
	 * Stops the music.
	 */
	private void stopMusic() {
		if (isPlayerRunning)
			this.stop();
		panelPauseIcon.setVisible(false);
		tableSongsPlaylist.getSelectionModel().clearSelection();
	}

	/**
	 * Takes the client back to the library.
	 * 
	 * @param frame  frame where the panel is added
	 * @param client logged client
	 */
	private void goToLibrary(JFrame frame, Client client) {
		stop();
		frame.getContentPane().removeAll();
		frame.getContentPane()
				.add(PanelFactory.getJPanel(PanelFactory.LIBRARY, frame, client, null, null, null, null, null, null));
		frame.revalidate();
		frame.repaint();
	}
}
