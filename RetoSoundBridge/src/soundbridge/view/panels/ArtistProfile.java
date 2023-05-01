package soundbridge.view.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import soundbridge.database.managers.AlbumManager;
import soundbridge.database.pojos.Album;
import soundbridge.database.pojos.Artist;
import soundbridge.database.pojos.Client;
import soundbridge.utils.WindowUtils;
import soundbridge.view.factory.PanelFactory;
import javax.swing.JTextArea;
import java.awt.GridLayout;
import java.awt.FlowLayout;

public class ArtistProfile extends JPanel {

	private static final long serialVersionUID = -5060067084701215720L;
	private ArrayList<Album> albums = null;
	private ArrayList<JPanel> albumPanels = null;
	private ArrayList<JLabel> albumLabels = null;

	public ArtistProfile(JFrame frame, Client client, Artist artist) {
		albumPanels = new ArrayList<JPanel>();
		albumLabels = new  ArrayList<JLabel>();
		initialize(frame, client, artist);
		addImagesToAlbums(artist);
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
				frame.getContentPane().add(PanelFactory.getJPanel(PanelFactory.LIBRARY, frame, client, null, null));
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
		lblAlbums.setBounds(40, 312, 400, 35);
		add(lblAlbums);

		JPanel panelAlbums = new JPanel();
		panelAlbums.setBounds(40, 360, 920, 115);
		add(panelAlbums);
		panelAlbums.setLayout(new GridLayout(1, 6, 69, 0));
		panelAlbums.setOpaque(false);

		JPanel panelAlbum1 = new JPanel();
		panelAlbums.add(panelAlbum1);
		panelAlbum1.setBounds(0,0,115,115);
		panelAlbum1.setLayout(new BorderLayout(0, 0));
		panelAlbum1.setOpaque(false);
		
		JLabel lblAlbum1 = new JLabel("");
		panelAlbum1.add(lblAlbum1, BorderLayout.CENTER);

		JPanel panelAlbum2 = new JPanel();
		panelAlbum2.setBounds(0,0,115,115);
		panelAlbum2.setLayout(new BorderLayout(0, 0));
		panelAlbums.add(panelAlbum2);
		panelAlbum2.setOpaque(false);

		JPanel panelAlbum3 = new JPanel();
		panelAlbum3.setBounds(0,0,115,115);
		panelAlbum3.setLayout(new BorderLayout(0, 0));
		panelAlbums.add(panelAlbum3);
		panelAlbum3.setOpaque(false);

		JPanel panelAlbum4 = new JPanel();
		panelAlbum4.setBounds(0,0,115,115);
		panelAlbum4.setLayout(new BorderLayout(0, 0));
		panelAlbums.add(panelAlbum4);
		panelAlbum4.setOpaque(false);

		JPanel panelAlbum5 = new JPanel();
		panelAlbum5.setBounds(0,0,115,115);
		panelAlbum5.setLayout(new BorderLayout(0, 0));
		panelAlbums.add(panelAlbum5);
		panelAlbum5.setOpaque(false);
		
		albumPanels.add(panelAlbum1);
		albumPanels.add(panelAlbum2);
		albumPanels.add(panelAlbum3);
		albumPanels.add(panelAlbum4);
		albumPanels.add(panelAlbum5);
		albumLabels.add(lblAlbum1);
	}

	private void addImagesToAlbums(Artist artist) {
		AlbumManager albumManager = new AlbumManager();
		try {
			albums = (ArrayList<Album>) albumManager.albumsWithSongsByArtist(artist);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (null != albums) {
			for (int i = 0; i < albums.size(); i++) {
				Album album = albums.get(i);
				String image = album.getCover();
				WindowUtils.addImage(albumPanels.get(i), albumLabels.get(i), image);
			}
		}
	}
}
