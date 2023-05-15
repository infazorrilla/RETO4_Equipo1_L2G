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
import soundbridge.database.managers.SongManager;
import soundbridge.database.pojos.ArtGroup;
import soundbridge.database.pojos.Artist;
import soundbridge.database.pojos.Client;
import soundbridge.database.pojos.ClientPP;
import soundbridge.database.pojos.Play;
import soundbridge.database.pojos.Playlist;
import soundbridge.database.pojos.Song;
import soundbridge.utils.WindowUtils;
import soundbridge.view.factory.PanelFactory;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import javazoom.jl.player.Player;
/**
 * Panel that shows the normal playlist, with the title,description and songs dynamically.
 */
public class PlayList extends JPanel {

	private static final long serialVersionUID = -3405591680500453035L;
	private ArrayList<Song> playlistSongs;
	public JTable tableSongsPlaylist;
	private DefaultTableModel modelSongsPlaylist = null;
	private Player player;
	private Controller controller;
	private int indexx = 0;
	private boolean isPlayerRunning = false;
	/**
	 * Initializes the panel.
	 * 
	 * @param frame  frame where the panel is added
	 * @param client logged client
	 * @param playlist selected playlist
	 */
	public PlayList(JFrame frame, Client client, Playlist playlist) {
		initialize(frame, client, playlist);
	}
	/**
	 * Initializes the components of the panel.
	 * 
	 * @param frame  frame where the panel is added
	 * @param client logged client
	 * @param playlist selected playlist
	 */
	public void initialize(JFrame frame, Client client, Playlist playlist) {
		setBounds(0, 0, 1000, 672);
		setBackground(Color.black);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel(playlist.getName());
		lblNewLabel.setBounds(252, 65, 91, 14);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel(playlist.getDescription());
		lblNewLabel_1.setBounds(252, 130, 148, 46);
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
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
				frame.getContentPane()
						.add(PanelFactory.getJPanel(PanelFactory.LIBRARY, frame, client, null, null, null, null, null, null));
				frame.revalidate();
				frame.repaint();
			}
		});
		
		
		panelBackIcon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelBackIcon.setToolTipText("Volver a mi perfil.");
		
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
				playSelectedSong(client, frame);
			}
		});
		tableSongsPlaylist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableSongsPlaylist.setDefaultEditor(Object.class, null);
		scrollPanePlaylist.setViewportView(tableSongsPlaylist);
		tableSongsPlaylist.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		Object[] columnsSongs = { "", "", "Título", "Duración", "Género", "Artista", "" };

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
		adjustColumnsWidth(tableSongsPlaylist);
		addSongsToTable(modelSongsPlaylist,playlist);
		
	}
	private void playSelectedSong(Client client, JFrame frame) {

		indexx = tableSongsPlaylist.getSelectedColumn();
		if (indexx == 0) {
			if (null == controller)
				controller = new Controller();

			controller.addToFavourites(client, playlistSongs, tableSongsPlaylist);
		}
		if (indexx == 1 || indexx == 2 || indexx == 3 || indexx == 4) {
			if (isPlayerRunning)
				this.stop();
			int index = tableSongsPlaylist.getSelectedRow();
			Song song = playlistSongs.get(index);
			this.play(song.getSource());
			doInsertPlay(client, song);
		}
		if (indexx == 6) {
			if (client instanceof ClientPP) {
				goToAddSong(frame, client);
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
	private void adjustColumnsWidth(JTable table) {
		table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		table.getColumnModel().getColumn(0).setMaxWidth(50);
		table.getColumnModel().getColumn(1).setMinWidth(30);
		table.getColumnModel().getColumn(2).setMinWidth(300);
		table.getColumnModel().getColumn(3).setMinWidth(100);
		table.getColumnModel().getColumn(4).setMinWidth(200);
		table.getColumnModel().getColumn(5).setMinWidth(160);
	}
	private void addSongsToTable(DefaultTableModel model,Playlist playlist) {

		if (null == controller)
			controller = new Controller();
SongManager sonMan = new SongManager();
		try {
			playlistSongs = sonMan.selectSongsOfPlaylist(playlist);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	/**
	 * Goes to an other panel to add song to a playlist.
	 * 
	 * @param frame  frame where the panel is added
	 * @param client logged client
	 */
	private void goToAddSong(JFrame frame, Client client) {
		frame.getContentPane().removeAll();
		frame.getContentPane().add(PanelFactory.getJPanel(PanelFactory.ADDSONGPLAYLIST, frame, client, null, null, null,
				null, null, null));
		frame.revalidate();
		frame.repaint();

	}
}
