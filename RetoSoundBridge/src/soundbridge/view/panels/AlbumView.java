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
import soundbridge.database.pojos.Play;
import soundbridge.database.pojos.Song;
import soundbridge.database.views.pojos.AverageStars;
import soundbridge.utils.WindowUtils;
import soundbridge.view.factory.PanelFactory;

public class AlbumView extends JPanel {

	private static final long serialVersionUID = -611767121036203376L;
	private Player player;
	private JLabel lblStars;
	private ArrayList<Song> songs;
	private boolean isPlayerRunning = false;

	public AlbumView(JFrame frame, Client client, Album album, Artist artist, ArtGroup artGroup) {
		initialize(frame, client, album, artist, artGroup);
	}

	public void initialize(JFrame frame, Client client, Album album, Artist artist, ArtGroup artGroup) {
		setBounds(0, 0, 1000, 672);
		setLayout(null);
		setBackground(Color.black);

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

		JTable tableSongs = new JTable();
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
		tableSongs.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			private static final long serialVersionUID = 1852554938143426518L;

			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				if (isSelected) {
					hasFocus = false;
				}
				return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
			}
		});
		tableSongs.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				playSelectedSong(tableSongs, client);
			}
		});
		scrollPaneSongs.setViewportView(tableSongs);

		tableSongs.getTableHeader().setBackground(Color.black);
		tableSongs.getTableHeader().setPreferredSize(new Dimension(scrollPaneSongs.getWidth(), 50));

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
		WindowUtils.addImage(panelStarIcon, lblStarIcon, "img/icon/star.png");
		WindowUtils.addImage(panelAlbumCover, lblAlbumCover, album.getCover());
		addReviewStarsToLabel(album);
		addSongsToTable(album, tableModelSongs);
		adjustColumnsWidth(tableSongs);
		addArtistOrGroupName(lblArtistName, artist, artGroup);
	}

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

	private void addArtistOrGroupName(JLabel label, Artist artist, ArtGroup artGroup) {
		if (artist != null) {
			label.setText(artist.getName());
		} else if (artGroup != null) {
			label.setText(artGroup.getName());
		}
	}

	private void addReviewStarsToLabel(Album album) {
		AverageStars averageStars = doGetAverageStars(album);
		lblStars.setText("" + averageStars.getAverage());
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

	private void adjustColumnsWidth(JTable table) {
		table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		table.getColumnModel().getColumn(0).setMaxWidth(50);
		table.getColumnModel().getColumn(1).setMaxWidth(50);
		table.getColumnModel().getColumn(2).setMinWidth(300);
		table.getColumnModel().getColumn(3).setMinWidth(100);
		table.getColumnModel().getColumn(4).setMinWidth(200);
	}

	private void goBack(JFrame frame, Client client, Artist artist, ArtGroup artGroup) {
		this.stop();
		if (artist != null) {
			frame.getContentPane().removeAll();
			frame.getContentPane().add(
					PanelFactory.getJPanel(PanelFactory.ARTIST_PROFILE, frame, client, null, artist, null, null, null));
			frame.revalidate();
			frame.repaint();
		} else if (artGroup != null) {
			frame.getContentPane().removeAll();
			frame.getContentPane().add(PanelFactory.getJPanel(PanelFactory.GROUP_PROFILE, frame, client, null, null,
					artGroup, null, null));
			frame.revalidate();
			frame.repaint();
		}
	}

	private void playSelectedSong(JTable table, Client client) {
		if (isPlayerRunning)
			this.stop();

		int index = table.getSelectedRow();
		Song song = songs.get(index);
		this.play(song.getSource());
		doInsertPlay(client, song);
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
