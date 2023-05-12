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

import soundbridge.database.pojos.Play;
import soundbridge.database.pojos.Playlist;
import soundbridge.database.pojos.Song;

import soundbridge.utils.WindowUtils;


public class PlayList extends JPanel {

	private static final long serialVersionUID = -611767121036203376L;
	private Player player;
	private ArrayList<Song> songs;
	private boolean isPlayerRunning = false;
	private JPanel panelPauseIcon;
	private JTable tableSongs;
	private Controller controller;
	private int indexx = 0;

	public PlayList(JFrame frame, Client client, Album album, Artist artist, ArtGroup artGroup,Playlist playlist) {
		initialize(frame, client, album, artist, artGroup,playlist);
	}

	public void initialize(JFrame frame, Client client, Album album, Artist artist, ArtGroup artGroup,Playlist playlist) {
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

		JLabel lblName = new JLabel(playlist.getName());
		lblName.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		lblName.setBounds(324, 90, 400, 40);
		lblName.setForeground(Color.white);
		add(lblName);

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
				playSelectedSong(client);
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

		WindowUtils.addImage(panelBackIcon, lblBackIcon, "img/icon/arrow.png");
		WindowUtils.addImage(panelPauseIcon, lblPauseIcon, "img/icon/pause.png");
		WindowUtils.addImage(panelAlbumCover, lblAlbumCover, album.getCover());
		addSongsToTable(album, tableModelSongs);
		adjustColumnsWidth();
		addArtistOrGroupName(lblArtistName, artist, artGroup);
		
	}

	

	private void addArtistOrGroupName(JLabel label, Artist artist, ArtGroup artGroup) {
		if (artist != null) {
			label.setText(artist.getName());
		} else if (artGroup != null) {
			label.setText(artGroup.getName());
		}
	}

	

	private void addSongsToTable(Album album, DefaultTableModel model) {
		songs = album.getSongs();
		if (songs != null) {
			for (int i = 0; i < songs.size(); i++) {
				Song song = songs.get(i);
				addInfoToTable(song, model, i);
			}
		}
	}

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

	private void adjustColumnsWidth() {
		tableSongs.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		tableSongs.getColumnModel().getColumn(0).setMaxWidth(50);
		tableSongs.getColumnModel().getColumn(1).setMaxWidth(50);
		tableSongs.getColumnModel().getColumn(2).setMinWidth(300);
		tableSongs.getColumnModel().getColumn(3).setMinWidth(100);
		tableSongs.getColumnModel().getColumn(4).setMinWidth(200);
	}

	private void goBack(JFrame frame, Client client, Artist artist, ArtGroup artGroup) {
		this.stop();
		if (artist != null) {
			
		} else if (artGroup != null) {
			
		}
	}

	private void playSelectedSong(Client client) {
		indexx = tableSongs.getSelectedColumn();
		if (indexx == 0) {
			if (null == controller)
				controller = new Controller();
			
			controller.addToFavourites(client,songs,tableSongs);
		}
		if (isPlayerRunning)
			this.stop();
		if (indexx == 1 || indexx == 2 || indexx == 3 || indexx == 4) {
			if (isPlayerRunning)
				this.stop();
			int index = tableSongs.getSelectedRow();
			Song song = songs.get(index);
			this.play(song.getSource());
			doInsertPlay(client, song);
			panelPauseIcon.setVisible(true);
		}
		
	}

	private void stopMusic() {
		if (isPlayerRunning)
			this.stop();
		panelPauseIcon.setVisible(false);
		tableSongs.getSelectionModel().clearSelection();
	}

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

	public void stop() {
		if (player != null) {
			player.close();
		}

		isPlayerRunning = false;
	}
	
	
}