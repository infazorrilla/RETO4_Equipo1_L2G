package soundbridge.view.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import javazoom.jl.player.Player;
import soundbridge.controller.Controller;
import soundbridge.database.managers.ArtGroupManager;
import soundbridge.database.managers.ArtistManager;
import soundbridge.database.pojos.ArtGroup;
import soundbridge.database.pojos.Artist;
import soundbridge.database.pojos.Client;
import soundbridge.database.pojos.Play;
import soundbridge.database.pojos.Song;
import soundbridge.database.views.managers.Top20ViewManager;
import soundbridge.utils.WindowUtils;
import soundbridge.view.factory.PanelFactory;

import java.awt.Font;
import java.io.FileInputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Top20View extends JPanel {
	private ArrayList<Song> top20songs;
	private JTable tableSongsTop20;
	DefaultTableModel modelTop20Songs = null;
	private boolean isPlayerRunning = false;
	private Player player;

	public Top20View(JFrame frame, Client client) {
		initialize(frame, client);
	}

	private static final long serialVersionUID = -5073547141433278673L;

	public void initialize(JFrame frame, Client client) {
		setBounds(0, 0, 1000, 672);
		setLayout(null);
		setBackground(Color.black);

		JPanel panelPlaylistCover = new JPanel();
		panelPlaylistCover.setBounds(40, 40, 250, 250);
		add(panelPlaylistCover);
		panelPlaylistCover.setLayout(new BorderLayout(0, 0));
		panelPlaylistCover.setOpaque(false);
		panelPlaylistCover.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		JLabel lblPlayListCover = new JLabel("");
		panelPlaylistCover.add(lblPlayListCover, BorderLayout.CENTER);

		WindowUtils.addImage(panelPlaylistCover, lblPlayListCover, "img/icon/top_icon.png");

		JLabel lblNewLabel = new JLabel("TOP 20 CANCIONES MÁS ESCUCHADAS");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 25));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(327, 53, 584, 39);
		add(lblNewLabel);

		JScrollPane scrollPaneTop20 = new JScrollPane();
		scrollPaneTop20.setBounds(44, 335, 867, 289);
		add(scrollPaneTop20);
		scrollPaneTop20.setOpaque(false);
		scrollPaneTop20.getViewport().setOpaque(false);
		scrollPaneTop20.setBorder(BorderFactory.createEmptyBorder());

		tableSongsTop20 = new JTable();
		tableSongsTop20.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				playSelectedSong(tableSongsTop20, client);
			}
		});
		tableSongsTop20.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableSongsTop20.setDefaultEditor(Object.class, null);
		scrollPaneTop20.setViewportView(tableSongsTop20);
		tableSongsTop20.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		scrollPaneTop20.setViewportView(tableSongsTop20);
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
				if (isSelected) {
					hasFocus = false;
				}
				return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
			}
		});
		tableSongsTop20.getTableHeader().setBackground(Color.black);
		tableSongsTop20.getTableHeader().setForeground(Color.white);
		tableSongsTop20.getTableHeader().setFont(new Font("Lucida Grande", Font.BOLD, 18));
		tableSongsTop20.getTableHeader().setPreferredSize(new Dimension(scrollPaneTop20.getWidth(), 50));
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setBorder(null);
		tableSongsTop20.getTableHeader().setDefaultRenderer(renderer);
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
				frame.getContentPane().removeAll();
				frame.getContentPane()
						.add(PanelFactory.getJPanel(PanelFactory.LIBRARY, frame, client, null, null, null, null));
				frame.revalidate();
				frame.repaint();
			}
		});
		panelHomeIcon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelHomeIcon.setToolTipText("Volver a mi biblioteca.");

		JLabel lblHomeIcon = new JLabel("");
		panelHomeIcon.add(lblHomeIcon, BorderLayout.CENTER);
		WindowUtils.addImage(panelHomeIcon, lblHomeIcon, "img/icon/home.png");
	}

	private void addSongsToTable(DefaultTableModel model) {
		Top20ViewManager top20 = new Top20ViewManager();
		try {
			top20songs = top20.selectViewTop20AndSongs();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (top20songs != null) {
			ArtGroupManager artGroup = new ArtGroupManager();
			ArtistManager artman = new ArtistManager();
			for (int i = 0; i < top20songs.size(); i++) {
				Song song = top20songs.get(i);
				Artist artista = null;
				ArtGroup arttGroup = null;
				try {
					if (song.getArtist() != null)
						artista = artman.selectArtistById(song.getArtist().getId());
					else
						arttGroup = artGroup.selectGroupById(song.getArtGroup().getId());

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
				String duration = minutes + ":" + seconds;
				String genre = song.getGenre();

				if (artista != null)
					model.addRow(new String[] { "\u2661", number, title, duration, genre, artista.getName(), "+" });
				if (arttGroup != null)
					model.addRow(new String[] { "\u2661", number, title, duration, genre, arttGroup.getName(), "+" });
			}
		}
	}

	private void adjustColumnsWidth(JTable table) {
		table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		table.getColumnModel().getColumn(0).setMaxWidth(50);
		table.getColumnModel().getColumn(1).setMaxWidth(50);
		table.getColumnModel().getColumn(2).setMinWidth(300);
		table.getColumnModel().getColumn(3).setMinWidth(100);
		table.getColumnModel().getColumn(4).setMinWidth(200);
		table.getColumnModel().getColumn(5).setMinWidth(160);
	}
	
	private void playSelectedSong(JTable table, Client client) {
		if (isPlayerRunning)
			this.stop();
		
		int index = table.getSelectedRow();
		Song song = top20songs.get(index);
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
		} catch(Exception e){
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
