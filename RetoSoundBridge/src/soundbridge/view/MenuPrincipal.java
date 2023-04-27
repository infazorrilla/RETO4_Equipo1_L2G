package soundbridge.view;

import java.awt.EventQueue;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import soundbridge.database.exception.NotFoundException;
import soundbridge.database.managers.ClientManager;
import soundbridge.database.managers.PlaylistManager;
import soundbridge.database.managers.SongManager;
import soundbridge.database.pojos.Client;
import soundbridge.database.pojos.ClientP;
import soundbridge.database.pojos.ClientPP;
import soundbridge.database.pojos.Playlist;
import soundbridge.database.pojos.Song;
import soundbridge.database.views.managers.Top20ViewManager;
import soundbridge.database.views.pojos.Top20;

import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;

public class MenuPrincipal {

	private JFrame frame;
	private Client client = null;
	JPanel panelTop20;
	JLabel lblTop20Img;
	JPanel panelFavourites;
	JLabel lblFavouritesImg;
	private JPanel panelGrid;
	private JLabel lblTop20;
	private JLabel lblFavourites;
	ArrayList<JPanel> playlistPanels = null;
	ArrayList<JLabel> playlistLabels = null;
	GridBagConstraints c;
	GridBagLayout gridbag;
	private JLabel lblPlaylistName1;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuPrincipal window = new MenuPrincipal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MenuPrincipal() {
		initialize();
		addTop20Playlist();
		playlistPanels = new ArrayList<JPanel>();
		
		playlistLabels = new ArrayList<JLabel>();
		getTypeOfClient();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setBackground(Color.BLACK);

		JTextArea textArea = new JTextArea();
		textArea.setBounds(58, 26, 549, 27);
		frame.getContentPane().add(textArea);

		JLabel lblPlaylists = new JLabel("Mis listas de reproducción:");
		lblPlaylists.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblPlaylists.setBounds(58, 90, 301, 27);
		lblPlaylists.setForeground(Color.white);
		frame.getContentPane().add(lblPlaylists);

		panelTop20 = new JPanel();
		panelTop20.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				showTop20();
			}
		});
		panelTop20.setBounds(58, 149, 114, 114);
		frame.getContentPane().add(panelTop20);
		panelTop20.setLayout(new BorderLayout(0, 0));
		panelTop20.setOpaque(false);
		
		lblTop20Img = new JLabel("");
		panelTop20.add(lblTop20Img, BorderLayout.CENTER);

		panelFavourites = new JPanel();
		panelFavourites.setBounds(200, 149, 114, 114);
		frame.getContentPane().add(panelFavourites);
		panelFavourites.setLayout(new BorderLayout(0, 0));
		panelFavourites.setOpaque(false);
		
		lblFavouritesImg = new JLabel("");
		panelFavourites.add(lblFavouritesImg, BorderLayout.CENTER);
		
		panelGrid = new JPanel();
		panelGrid.setBounds(58, 346, 819, 114);
		frame.getContentPane().add(panelGrid);
		panelGrid.setOpaque(false);
		
		gridbag = new GridBagLayout();
		c = new GridBagConstraints();
		panelGrid.setLayout(gridbag);
		c.fill = GridBagConstraints.HORIZONTAL; 
		
		lblTop20 = new JLabel("Top20");
		lblTop20.setHorizontalAlignment(SwingConstants.CENTER);
		lblTop20.setForeground(Color.white);
		lblTop20.setBounds(58, 276, 114, 27);
		frame.getContentPane().add(lblTop20);
		
		lblFavourites = new JLabel("Favoritos");
		lblFavourites.setHorizontalAlignment(SwingConstants.CENTER);
		lblFavourites.setForeground(Color.white);
		lblFavourites.setBounds(200, 276, 114, 27);
		frame.getContentPane().add(lblFavourites);
		
		lblPlaylistName1 = new JLabel("");
		lblPlaylistName1.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlaylistName1.setForeground(Color.WHITE);
		lblPlaylistName1.setBounds(58, 472, 114, 27);
		frame.getContentPane().add(lblPlaylistName1);

	}

	private void getTypeOfClient() {
		ClientManager clientMan = new ClientManager();

		ArrayList<Client> clients = null;

		try {
			clients = (ArrayList<Client>) clientMan.selectAll();
			client = clients.get(2);
			
			addImage(panelTop20, lblTop20Img, "img/icon/top_icon.png");

			if (client instanceof ClientP) {
				JOptionPane.showMessageDialog(null, "Eres Premium", "Cliente", JOptionPane.INFORMATION_MESSAGE);
				addImage(panelFavourites, lblFavouritesImg, "img/icon/fav_icon.png");
				
			} else if (client instanceof ClientPP) {
				JOptionPane.showMessageDialog(null, "Eres Premium Plus", "Cliente", JOptionPane.INFORMATION_MESSAGE);
				addImage(panelFavourites, lblFavouritesImg, "img/icon/fav_icon.png");
				PlaylistManager playlistManager = new PlaylistManager();
				ArrayList<Playlist> clientPlaylists = playlistManager.getPlaylistsOfClientPPById(client);
				
				for (int i = 1; i < clientPlaylists.size(); i++) {
					if (clientPlaylists.get(i) != null) {
						JPanel panel = new JPanel();	
						panel.setLayout(new BorderLayout(0, 0));
						panel.setOpaque(false);
						panel.setSize(114, 114);

						c.weightx = 0;
						c.anchor = GridBagConstraints.WEST;
						c.gridx = i-1;
						c.gridy = 0;
						c.insets = new Insets(0,0,0,27);
						gridbag.setConstraints(panel, c);
						panelGrid.add(panel);
						
						playlistPanels.add(panel);
						
						JLabel lblPlaylistImg = new JLabel("");
						lblPlaylistImg.setSize(114, 144);
						panel.add(lblPlaylistImg, BorderLayout.CENTER);
						
						playlistLabels.add(lblPlaylistImg);
						
						addImage(panel, lblPlaylistImg, "img/icon/playlist_icon.png");
						
						lblPlaylistName1.setText(clientPlaylists.get(i).getName());
					}
				}
			} else {
				JOptionPane.showMessageDialog(null, "Eres Basic", "Cliente", JOptionPane.INFORMATION_MESSAGE);
			}

		} catch (NotFoundException nfe) {
			JOptionPane.showMessageDialog(null, nfe, "Error", JOptionPane.ERROR_MESSAGE);
		} catch (SQLException sqle) {
			JOptionPane.showMessageDialog(null, sqle, "Error", JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void addTop20Playlist() {
		Top20ViewManager top20Man = new Top20ViewManager();

		ArrayList<Top20> top20s;
		try {
			top20s = top20Man.selectView();
			ArrayList<Song> top20songs = new ArrayList<Song>();

			SongManager songMan = new SongManager();

			for (Top20 top20 : top20s) {
				Song song = songMan.getSongById(top20.getId());
				top20songs.add(song);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void addImage(JPanel panel, JLabel label, String path) {
		ImageIcon icon = new ImageIcon(path);
		Image img = icon.getImage();
		Image resizedImg = img.getScaledInstance(panel.getWidth(), panel.getHeight(), Image.SCALE_SMOOTH);
		icon.setImage(resizedImg);
		label.setIcon(icon);
	}
	
	private void showTop20() {
		
	}
}
