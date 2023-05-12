package soundbridge.view.panels;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

import soundbridge.controller.Controller;
import soundbridge.database.managers.PlaylistManager;
import soundbridge.database.pojos.Client;
import soundbridge.database.pojos.ClientP;
import soundbridge.database.pojos.ClientPP;
import soundbridge.database.pojos.Playlist;
import soundbridge.view.factory.PanelFactory;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CreatePlaylist extends JPanel {

	private static final long serialVersionUID = -2776809426213236020L;
	private JTextField textField;
	private JTextField textField_1;
	

	public CreatePlaylist(JFrame frame, Client client) {
		initialize(frame, client);
	}

	private void initialize(JFrame frame, Client client) {
		setBounds(0, 0, 1000, 672);
		setLayout(null);
		setBackground(Color.black);

		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(51, 87, 46, 14);
		add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(51, 122, 86, 20);
		add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Descripcion");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(51, 172, 86, 14);
		add(lblNewLabel_1);

		textField_1 = new JTextField();
		textField_1.setBounds(51, 209, 86, 20);
		add(textField_1);
		textField_1.setColumns(10);

		JButton btnEnviarCrearPlaylist = new JButton("New button");
		
		btnEnviarCrearPlaylist.addActionListener(new ActionListener() {
			Playlist playlist = new Playlist();
			
			public void actionPerformed(ActionEvent e) {
				PlaylistManager playMan = new PlaylistManager();
				ClientP clientP = new ClientP();
				ClientPP clientPP = new ClientPP();
				playlist.setName(textField.getText());
				playlist.setDescription(textField_1.getText());
				
				if (client instanceof ClientPP) {
				clientPP.setId(client.getId());
				playlist.setClientPP(clientPP);
				try {
					playMan.insertPlaylistClienPP(playlist);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}
				
				if (client instanceof ClientP) {
					clientP.setId(client.getId());
					playlist.setClientP(clientP);
					try {
						playMan.insertPlaylistClienP(playlist);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				frame.getContentPane().removeAll();
				frame.getContentPane().add(
						PanelFactory.getJPanel(PanelFactory.PLAYLIST, frame, client, null, null, null, null, null, playlist));
				frame.revalidate();
				frame.repaint();
			}
		});
		btnEnviarCrearPlaylist.setBounds(48, 256, 89, 23);
		add(btnEnviarCrearPlaylist);
	}
}