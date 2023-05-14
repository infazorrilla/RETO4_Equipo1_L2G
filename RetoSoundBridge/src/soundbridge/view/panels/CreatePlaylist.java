package soundbridge.view.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JFrame;
import javax.swing.JPanel;

import soundbridge.database.managers.PlaylistManager;
import soundbridge.database.pojos.Client;

import soundbridge.database.pojos.ClientPP;
import soundbridge.database.pojos.Playlist;
import soundbridge.utils.WindowUtils;
import soundbridge.view.factory.PanelFactory;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

/**
 * Pnale to create a playlist for a client. Here the client is able to choose
 * the name and the description of the playlist that is going to create.
 */
public class CreatePlaylist extends JPanel {

	private static final long serialVersionUID = -2776809426213236020L;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Initializes the panel.
	 * 
	 * @param frame  frame where the panel is added
	 * @param client logged client
	 */
	public CreatePlaylist(JFrame frame, Client client) {
		initialize(frame, client);
	}

	/**
	 * Initializes the components of the panel.
	 * 
	 * @param frame  frame where the panel is added
	 * @param client logged client
	 */
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

		JPanel panelBackIcon = new JPanel();
		panelBackIcon.setBounds(900, 45, 50, 50);
		add(panelBackIcon);
		panelBackIcon.setLayout(new BorderLayout(0, 0));
		panelBackIcon.setOpaque(false);
		panelBackIcon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.getContentPane().removeAll();
				frame.getContentPane().add(PanelFactory.getJPanel(PanelFactory.LIBRARY, frame, client, null, null, null,
						null, null, null));
				frame.revalidate();
				frame.repaint();
			}
		});

		panelBackIcon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelBackIcon.setToolTipText("Volver a mi perfil.");

		JLabel lblBackIcon = new JLabel("");
		panelBackIcon.add(lblBackIcon, BorderLayout.CENTER);
		WindowUtils.addImage(panelBackIcon, lblBackIcon, "img/icon/arrow.png");
		JButton btnEnviarCrearPlaylist = new JButton("New button");

		btnEnviarCrearPlaylist.addActionListener(new ActionListener() {
			Playlist playlist = new Playlist();

			public void actionPerformed(ActionEvent e) {
				PlaylistManager playMan = new PlaylistManager();
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

				frame.getContentPane().removeAll();
				frame.getContentPane().add(PanelFactory.getJPanel(PanelFactory.PLAYLIST, frame, client, null, null,
						null, null, null, playlist));
				frame.revalidate();
				frame.repaint();
			}
		});
		btnEnviarCrearPlaylist.setBounds(48, 256, 89, 23);
		add(btnEnviarCrearPlaylist);
	}

}