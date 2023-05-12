package soundbridge.view.panels;

import java.awt.Color;

import javax.swing.JFrame;

import javax.swing.JPanel;

import soundbridge.database.pojos.Client;

import soundbridge.database.pojos.Playlist;
import javax.swing.JLabel;

public class PlayList extends JPanel {

	private static final long serialVersionUID = -3405591680500453035L;

	public PlayList(JFrame frame, Client client, Playlist playlist) {
		initialize(frame, client, playlist);
	}

	public void initialize(JFrame frame, Client client, Playlist playlist) {
		setBounds(0, 0, 1000, 672);
		setLayout(null);
		setBackground(Color.black);
		
		JLabel lblNewLabel = new JLabel(playlist.getName());
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(252, 65, 91, 14);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel(playlist.getDescription());
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(252, 130, 148, 46);
		add(lblNewLabel_1);
	}

}
