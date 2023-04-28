package soundbridge.view.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import soundbridge.database.managers.ArtGroupManager;
import soundbridge.database.managers.ArtistManager;
import soundbridge.database.pojos.ArtGroup;
import soundbridge.database.pojos.Artist;
import soundbridge.view.components.AutoCompleteTextField;

public class Library extends JPanel {

	private static final long serialVersionUID = -2776809426213236020L;

	public Library(JFrame frame) {
		setBounds(0, 0, 1000, 672);
		setLayout(null);
		setBackground(Color.black);
		
		JPanel panelProfileIcon = new JPanel();
		panelProfileIcon.setBounds(903, 30, 70, 70);
		add(panelProfileIcon);
		panelProfileIcon.setLayout(new BorderLayout(0, 0));
		panelProfileIcon.setOpaque(false);
		panelProfileIcon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		
		JLabel lblProfileIcon = new JLabel("");
		panelProfileIcon.add(lblProfileIcon, BorderLayout.CENTER);
		
		AutoCompleteTextField searchBar = new AutoCompleteTextField();
		searchBar.setBounds(100, 55, 600, 30);
		searchBar.setBackground(Color.white);
		searchBar.setForeground(Color.black);
		searchBar.setCaretColor(Color.WHITE);
		searchBar.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		searchBar.setOpaque(false);
		add(searchBar);

		JLabel lblPlaylists = new JLabel("Mis listas de reproducción:");
		lblPlaylists.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblPlaylists.setBounds(90, 120, 301, 27);
		lblPlaylists.setForeground(Color.white);
		add(lblPlaylists);
		
		JPanel panelBackground = new JPanel();
		panelBackground.setBounds(0, 0, 1000, 672);
		add(panelBackground);
		panelBackground.setLayout(new BorderLayout(0, 0));
		
		JLabel lblBackground = new JLabel("");
		panelBackground.add(lblBackground, BorderLayout.CENTER);
		
		addImage(panelProfileIcon, lblProfileIcon, "img/icon/profile.png");
		addImage(panelBackground, lblBackground, "img/panel/library_bg.jpeg");
		addPosibilitiesToSearchBar(searchBar);
	}
	
	private void addImage(JPanel panel, JLabel label, String path) {
		ImageIcon icon = new ImageIcon(path);
		Image img = icon.getImage();
		Image resizedImg = img.getScaledInstance(panel.getWidth(), panel.getHeight(), Image.SCALE_SMOOTH);
		icon.setImage(resizedImg);
		label.setIcon(icon);
	}
	
	private void addPosibilitiesToSearchBar(AutoCompleteTextField text) {
		ArtistManager artistManager = new ArtistManager();
		ArtGroupManager artGroupManager = new ArtGroupManager();
		
		ArrayList<Artist> artists = null;
		ArrayList<ArtGroup> groups = null;
		
		try {
			artists = (ArrayList<Artist>) artistManager.selectAll();
			for (Artist artist : artists) {
				if (artist.getArtGroup().getId() == 0) {
					text.addPossibility(artist.getName());
				}
			}
			
			groups = (ArrayList<ArtGroup>) artGroupManager.selectAll();
			for (ArtGroup group : groups) {
				text.addPossibility(group.getName());
			}
			
		} catch (SQLException sqle) {
			JOptionPane.showMessageDialog(null, sqle, "Error", JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
