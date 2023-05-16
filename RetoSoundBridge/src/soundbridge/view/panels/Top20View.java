package soundbridge.view.panels;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
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

import java.awt.Font;
import java.io.FileInputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Panel that contains a top of the 20 most eared songs, which is always
 * updated.
 * 
 *
 */
public class Top20View extends JPanel {
	private ArrayList<Song> top20songs;

	public JTable tableSongsTop20;
	private DefaultTableModel modelTop20Songs = null;
	private boolean isPlayerRunning = false;
	private Player player;
	private JPanel panelPauseIcon;
	private Controller controller;

	public Top20View(JFrame frame, Client client) {
		initialize(frame, client);
	}

	private static final long serialVersionUID = -5073547141433278673L;

	/**
	 * Initializes the components of the panel.
	 * 
	 * @param frame  frame where the panel is added
	 * @param client logged client
	 * 
	 */
	public void initialize(JFrame frame, Client client) {
		setBounds(0, 0, 1000, 672);
		setLayout(null);
		setBackground(Color.black);

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

		WindowUtils.addImage(panelPlaylistCover, lblPlayListCover, "img/icon/top_icon.png");

		JLabel lblTitle = new JLabel("Top 20");
		lblTitle.setFont(new Font("Dialog", Font.BOLD, 25));
		lblTitle.setForeground(new Color(255, 255, 255));
		lblTitle.setBounds(327, 70, 584, 39);
		add(lblTitle);

		JLabel lblCreator = new JLabel("@soundBridge");
		lblCreator.setFont(new Font("Dialog", Font.BOLD, 16));
		lblCreator.setForeground(new Color(244, 135, 244));
		lblCreator.setBounds(327, 110, 584, 39);
		add(lblCreator);

		JTextArea textBio = new JTextArea(
				"Listado de las 20 canciones más escuchadas en SoundBridge que se actualiza contínuamente.");
		textBio.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		textBio.setEditable(false);
		textBio.setOpaque(false);
		textBio.setForeground(Color.white);
		textBio.setBounds(327, 160, 487, 128);
		add(textBio);
		textBio.setLineWrap(true);
		textBio.setWrapStyleWord(true);

		JScrollPane scrollPaneTop20 = new JScrollPane();
		scrollPaneTop20.setBounds(44, 320, 867, 289);
		add(scrollPaneTop20);
		scrollPaneTop20.setOpaque(false);
		scrollPaneTop20.getViewport().setOpaque(false);
		scrollPaneTop20.setBorder(BorderFactory.createEmptyBorder());

		scrollPaneTop20.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
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

		tableSongsTop20 = new JTable();
		tableSongsTop20.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				playSelectedSong(client, frame);
			}
		});
		tableSongsTop20.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableSongsTop20.setDefaultEditor(Object.class, null);
		scrollPaneTop20.setViewportView(tableSongsTop20);
		tableSongsTop20.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		Object[] columnsSongs = { "", "", "Título", "Duración", "Género", "Artista", "" };

		tableSongsTop20.setShowGrid(false);
		tableSongsTop20.setBackground(Color.black);
		tableSongsTop20.setForeground(Color.white);
		tableSongsTop20.setSelectionForeground(new Color(244, 135, 244));
		tableSongsTop20.setRowHeight(35);
		tableSongsTop20.setSelectionBackground(Color.black);
		tableSongsTop20.setBorder(null);
		tableSongsTop20.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {

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
		tableSongsTop20.getTableHeader().setBackground(Color.black);
		tableSongsTop20.getTableHeader().setPreferredSize(new Dimension(scrollPaneTop20.getWidth(), 50));
		tableSongsTop20.getTableHeader().setReorderingAllowed(false);
		tableSongsTop20.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		TableCellRenderer renderer = tableSongsTop20.getTableHeader().getDefaultRenderer();
		tableSongsTop20.getTableHeader().setDefaultRenderer(new TableCellRenderer() {

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

		modelTop20Songs = new DefaultTableModel();
		modelTop20Songs.setColumnIdentifiers(columnsSongs);
		tableSongsTop20.setModel(modelTop20Songs);
		adjustColumnsWidth(tableSongsTop20);
		addSongsToTable(modelTop20Songs);

		JPanel panelHomeIcon = new JPanel();
		panelHomeIcon.setBounds(900, 45, 50, 50);
		add(panelHomeIcon);
		panelHomeIcon.setLayout(new BorderLayout(0, 0));
		panelHomeIcon.setOpaque(false);
		panelHomeIcon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				stop();
				frame.getContentPane().removeAll();
				frame.getContentPane().add(PanelFactory.getJPanel(PanelFactory.LIBRARY, frame, client, null, null, null,
						null, null, null));
				frame.revalidate();
				frame.repaint();
			}
		});
		panelHomeIcon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelHomeIcon.setToolTipText("Volver a mi biblioteca.");

		JLabel lblHomeIcon = new JLabel("");
		panelHomeIcon.add(lblHomeIcon, BorderLayout.CENTER);

		WindowUtils.addImage(panelHomeIcon, lblHomeIcon, "img/icon/home.png");
		WindowUtils.addImage(panelPauseIcon, lblPauseIcon, "img/icon/pause_black.png");
	}

	/**
	 * Takes the most eared 20 songs of the data base and show it on a table.
	 * 
	 * @param model the model of the table
	 */
	private void addSongsToTable(DefaultTableModel model) {

		if (null == controller)
			controller = new Controller();

		try {
			top20songs = controller.getTop20Songs();
		} catch (SQLException e) {
			WindowUtils.errorPane("No se ha podido añadir la canción.", "Error");
		} catch (Exception e) {
			WindowUtils.errorPane("No se ha podido añadir la canción.", "Error");
		}
		if (top20songs != null) {

			if (null == controller)
				controller = new Controller();

			for (int i = 0; i < top20songs.size(); i++) {
				Song song = top20songs.get(i);
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
					model.addRow(new String[] { "\u2661", number, title, duration, genre, artista.getName(), "+" });
				if (arttGroup != null)
					model.addRow(new String[] { "\u2661", number, title, duration, genre, arttGroup.getName(), "+" });
			}
		}
	}

	/**
	 * The amount of columns and each width.
	 * 
	 * @param table table which is adjusted
	 */
	private void adjustColumnsWidth(JTable table) {
		table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		table.getColumnModel().getColumn(0).setMaxWidth(50);
		table.getColumnModel().getColumn(1).setMinWidth(30);
		table.getColumnModel().getColumn(2).setMinWidth(300);
		table.getColumnModel().getColumn(3).setMinWidth(100);
		table.getColumnModel().getColumn(4).setMinWidth(200);
		table.getColumnModel().getColumn(5).setMinWidth(160);
	}

	/**
	 * Reproduce the selected song using the index of the table.
	 * 
	 * @param client the client that had done the log in
	 * @param frame  frame where the panel is added
	 */

	private void playSelectedSong(Client client, JFrame frame) {

		int indexColumn = tableSongsTop20.getSelectedColumn();
		int indexRow = tableSongsTop20.getSelectedRow();

		if (null == controller)
			controller = new Controller();

		if (indexColumn == 0) {
			
			controller.addToFavourites(client, top20songs, tableSongsTop20);
			
		} else if (indexColumn >= 1 && indexColumn <= 5) {
			if (isPlayerRunning)
				this.stop();

			Song song = top20songs.get(indexRow);
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
					Song song = top20songs.get(indexRow);
					goToAddSong(frame, client, song);
				} else {
					WindowUtils.errorPane("Primero debe crear una lista.", "Error");
				}
			}
		}
	}

	/**
	 * Stops the music.
	 */
	private void stopMusic() {
		if (isPlayerRunning)
			this.stop();
		panelPauseIcon.setVisible(false);
		tableSongsTop20.getSelectionModel().clearSelection();
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
	 * Gets the table of the songs.
	 * 
	 * @return the table of the top 20
	 */
	public JTable getTableSongsTop20() {
		return tableSongsTop20;
	}

	/**
	 * Sets the table.
	 * 
	 * @param recives a table to set
	 */
	public void setTableSongsTop20(JTable tableSongsTop20) {
		this.tableSongsTop20 = tableSongsTop20;
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

}
