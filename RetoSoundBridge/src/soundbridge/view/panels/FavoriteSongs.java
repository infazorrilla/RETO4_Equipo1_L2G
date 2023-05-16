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
 * Panel where are the songs that the client choose as his favorites.
 */
public class FavoriteSongs extends JPanel {
	private ArrayList<Song> favouriteSongs;

	public JTable tableFavouriteSongs;
	private DefaultTableModel modelFavouriteSongs = null;
	private boolean isPlayerRunning = false;
	private Player player;
	private JPanel panelPauseIcon;
	private Controller controller;
	private int indexx = 0;

	/**
	 * Initializes the panel.
	 * 
	 * @param frame  frame where the panel is added
	 * @param client logged client
	 */
	public FavoriteSongs(JFrame frame, Client client) {
		initialize(frame, client);
	}

	/**
	 * Initializes the components of the panel.
	 * 
	 * @param frame  frame where the panel is added
	 * @param client logged client
	 */
	private static final long serialVersionUID = -5073547141433278673L;

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

		WindowUtils.addImage(panelPlaylistCover, lblPlayListCover, "img/icon/fav_icon.png");

		JLabel lblTitle = new JLabel("Favourites");
		lblTitle.setFont(new Font("Dialog", Font.BOLD, 25));
		lblTitle.setForeground(new Color(255, 255, 255));
		lblTitle.setBounds(327, 70, 584, 39);
		add(lblTitle);

		JLabel lblCreator = new JLabel("@" + client.getUsername().toString());
		lblCreator.setFont(new Font("Dialog", Font.BOLD, 16));
		lblCreator.setForeground(new Color(244, 135, 244));
		lblCreator.setBounds(327, 110, 584, 39);
		add(lblCreator);

		JTextArea textBio = new JTextArea(
				"Listado de las canciones que te han gustado.");
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

		tableFavouriteSongs = new JTable();
		tableFavouriteSongs.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				playSelectedSong(client);
			}
		});
		tableFavouriteSongs.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableFavouriteSongs.setDefaultEditor(Object.class, null);
		scrollPaneTop20.setViewportView(tableFavouriteSongs);
		tableFavouriteSongs.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		Object[] columnsSongs = { "", "", "Título", "Duración", "Género", "Artista", "" };

		tableFavouriteSongs.setShowGrid(false);
		tableFavouriteSongs.setBackground(Color.black);
		tableFavouriteSongs.setForeground(Color.white);
		tableFavouriteSongs.setSelectionForeground(new Color(244, 135, 244));
		tableFavouriteSongs.setRowHeight(35);
		tableFavouriteSongs.setSelectionBackground(Color.black);
		tableFavouriteSongs.setBorder(null);
		tableFavouriteSongs.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {

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
		tableFavouriteSongs.getTableHeader().setBackground(Color.black);
		tableFavouriteSongs.getTableHeader().setPreferredSize(new Dimension(scrollPaneTop20.getWidth(), 50));
		tableFavouriteSongs.getTableHeader().setReorderingAllowed(false);
		tableFavouriteSongs.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		TableCellRenderer renderer = tableFavouriteSongs.getTableHeader().getDefaultRenderer();
		tableFavouriteSongs.getTableHeader().setDefaultRenderer(new TableCellRenderer() {

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

		modelFavouriteSongs = new DefaultTableModel();
		modelFavouriteSongs.setColumnIdentifiers(columnsSongs);
		tableFavouriteSongs.setModel(modelFavouriteSongs);
		adjustColumnsWidth(tableFavouriteSongs);
		addSongsToTable(modelFavouriteSongs,client);

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

		WindowUtils.addImage(panelHomeIcon, lblHomeIcon, "img/icon/home.png");
		WindowUtils.addImage(panelPauseIcon, lblPauseIcon, "img/icon/pause_black.png");
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
	 */
	private void playSelectedSong(Client client) {

		indexx = tableFavouriteSongs.getSelectedColumn();
		if (indexx == 0) {
			if (null == controller)
				controller = new Controller();

			controller.addToFavourites(client, favouriteSongs, tableFavouriteSongs);
		}
		if (indexx == 1 || indexx == 2 || indexx == 3 || indexx == 4) {
			if (isPlayerRunning)
				this.stop();
			int index = tableFavouriteSongs.getSelectedRow();
			Song song = favouriteSongs.get(index);
			this.play(song.getSource());
			doInsertPlay(client, song);
			panelPauseIcon.setVisible(true);
		}
	}
	/**
	 * Stops the music.
	 */
	private void stopMusic() {
		if (isPlayerRunning)
			this.stop();
		panelPauseIcon.setVisible(false);
		tableFavouriteSongs.getSelectionModel().clearSelection();
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
	 * Takes the favorite songs of a client of the data base and shows it on a table.
	 * 
	 * @param model the model of the table to insert the info
	 * @param client the client to take his favorite songs
	 */
	private void addSongsToTable(DefaultTableModel model,Client client) {

		if (null == controller)
			controller = new Controller();

		try {
			if (client instanceof ClientPP) {
				favouriteSongs = controller.selectFavouriteSongOfClientPP(client);
			}
			else {
				favouriteSongs = controller.selectFavouriteSongOfClientP(client);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (favouriteSongs != null) {

			if (null == controller)
				controller = new Controller();

			for (int i = 0; i < favouriteSongs.size(); i++) {
				Song song = favouriteSongs.get(i);
				Artist artista = null;
				ArtGroup arttGroup = null;
				try {
					if (song.getArtist() != null)
						artista = controller.getArtistById(song.getArtist().getId());
					else
						arttGroup = controller.getGroupById(song.getArtGroup().getId());

				} catch (SQLException e) {
					System.out.println(e);
					e.printStackTrace();
				} catch (Exception e) {
					System.out.println(e);
					e.printStackTrace();
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
}
