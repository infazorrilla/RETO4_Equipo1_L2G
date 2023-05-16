package soundbridge.view.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import soundbridge.database.managers.PlaylistManager;
import soundbridge.database.pojos.Client;

import soundbridge.database.pojos.ClientPP;
import soundbridge.database.pojos.Playlist;
import soundbridge.utils.WindowUtils;
import soundbridge.view.components.TextPrompt;
import soundbridge.view.factory.PanelFactory;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.Font;

/**
 * Pnale to create a playlist for a client. Here the client is able to choose
 * the name and the description of the playlist that is going to create.
 */
public class CreatePlaylist extends JPanel {

	private static final long serialVersionUID = -2776809426213236020L;
	private JTextField textFieldName;
	private JTextArea textAreaDescription;

	/**
	 * Initializes the panel.
	 * 
	 * @param frame  frame where the panel is added
	 * @param client logged client
	 */
	public CreatePlaylist(JFrame frame, Client client) {
		setBounds(0, 0, 1000, 672);
		setLayout(null);
		setBackground(Color.black);

		initialize(frame, client);
	}

	/**
	 * Initializes the components of the panel.
	 * 
	 * @param frame  frame where the panel is added
	 * @param client logged client
	 */
	private void initialize(JFrame frame, Client client) {
		JPanel panelPlaylistCover = new JPanel();
		panelPlaylistCover.setBounds(40, 40, 250, 250);
		add(panelPlaylistCover);
		panelPlaylistCover.setLayout(new BorderLayout(0, 0));
		panelPlaylistCover.setOpaque(false);

		JLabel lblPlayListCover = new JLabel("");
		panelPlaylistCover.add(lblPlayListCover, BorderLayout.CENTER);

		WindowUtils.addImage(panelPlaylistCover, lblPlayListCover, "img/icon/playlist_icon.png");

		textFieldName = new JTextField();
		textFieldName.setBounds(327, 45, 500, 40);
		add(textFieldName);
		textFieldName.setColumns(10);
		textFieldName.setForeground(Color.white);
		textFieldName.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		textFieldName.setBorder(new LineBorder(Color.WHITE, 2));
		textFieldName.setCaretColor(Color.WHITE);
		textFieldName.setOpaque(false);
		textFieldName.setHorizontalAlignment(SwingConstants.LEFT);
		textFieldName.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				textFieldName.setBorder(new LineBorder(new Color(244, 135, 244), 2));
			}

			@Override
			public void focusLost(FocusEvent e) {
				textFieldName.setBorder(new LineBorder(Color.WHITE, 2));
			}
		});

		TextPrompt placeholderName = new TextPrompt("Nombre de la lista", textFieldName);
		placeholderName.changeAlpha(0.8f);
		placeholderName.changeStyle(Font.BOLD + Font.ITALIC);
		placeholderName.setHorizontalAlignment(SwingConstants.LEFT);

		JLabel lblCreator = new JLabel("@" + client.getUsername().toString());
		lblCreator.setFont(new Font("Dialog", Font.BOLD, 16));
		lblCreator.setForeground(new Color(244, 135, 244));
		lblCreator.setBounds(327, 97, 584, 39);
		add(lblCreator);

		textAreaDescription = new JTextArea("");
		textAreaDescription.setOpaque(false);
		textAreaDescription.setForeground(Color.WHITE);
		textAreaDescription.setFont(new Font("Dialog", Font.PLAIN, 15));
		textAreaDescription.setColumns(10);
		textAreaDescription.setCaretColor(Color.WHITE);
		textAreaDescription.setBorder(new LineBorder(Color.WHITE, 2));
		textAreaDescription.setBounds(327, 147, 500, 65);
		add(textAreaDescription);
		textAreaDescription.setLineWrap(true);
		textAreaDescription.setWrapStyleWord(true);
		textAreaDescription.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				textAreaDescription.setBorder(new LineBorder(new Color(244, 135, 244), 2));
			}

			@Override
			public void focusLost(FocusEvent e) {
				textAreaDescription.setBorder(new LineBorder(Color.WHITE, 2));
			}
		});

		TextPrompt placeholderDescription = new TextPrompt("Escribe una breve descripción de la lista...",
				textAreaDescription);
		placeholderDescription.setVerticalAlignment(SwingConstants.TOP);
		placeholderDescription.changeAlpha(0.8f);
		placeholderDescription.changeStyle(Font.BOLD + Font.ITALIC);
		placeholderDescription.setHorizontalAlignment(SwingConstants.LEFT);

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

		JButton btnCreatePlaylist = new JButton("CREAR LISTA DE REPRODUCCIÓN");
		btnCreatePlaylist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Playlist playlist = new Playlist();
				PlaylistManager playMan = new PlaylistManager();
				ClientPP clientPP = new ClientPP();
				playlist.setName(textFieldName.getText());
				playlist.setDescription(textAreaDescription.getText());

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
		btnCreatePlaylist.setBounds(327, 230, 500, 50);
		add(btnCreatePlaylist);
		btnCreatePlaylist.setForeground(Color.white);
		btnCreatePlaylist.setFont(new Font("Lucida Grande", Font.BOLD, 17));
		btnCreatePlaylist.setBackground(new Color(244, 135, 244, 20));
		btnCreatePlaylist.setBorder(new LineBorder(new Color(244, 135, 244), 2));
		btnCreatePlaylist.setOpaque(false);
		btnCreatePlaylist.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		JPanel panelBackground = new JPanel();
		panelBackground.setBounds(0, 0, 1000, 672);
		add(panelBackground);
		panelBackground.setLayout(new BorderLayout(0, 0));

		JLabel lblBackground = new JLabel("");
		panelBackground.add(lblBackground, BorderLayout.CENTER);

		WindowUtils.addImage(panelBackground, lblBackground, "img/panel/create_playlist_bg.jpeg");
	}

}