package soundbridge.view.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import soundbridge.database.pojos.Album;
import soundbridge.database.pojos.ArtGroup;
import soundbridge.database.pojos.Artist;
import soundbridge.database.pojos.Client;
import soundbridge.utils.WindowUtils;
import soundbridge.view.components.TextPrompt;
import soundbridge.view.factory.PanelFactory;

public class WriteReview extends JPanel {

	private static final long serialVersionUID = 8019879665852364145L;
	
	private ArrayList<JPanel> panels = null;
	private ArrayList<JLabel> labels = null;
	private int selectedStars = 0;

	public WriteReview (JFrame frame, Client client, Album album, Artist artist, ArtGroup artGroup) {
		panels = new ArrayList<JPanel>();
		labels = new ArrayList<JLabel>();
		initialize (frame, client, album, artist, artGroup);
	}
	
	private void initialize (JFrame frame, Client client, Album album, Artist artist, ArtGroup artGroup) {
		setBounds(0, 0, 1000, 672);
		setLayout(null);
		setBackground(Color.black);
		
		JLabel lblTitle = new JLabel("Mi reseña");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Dialog", Font.BOLD, 22));
		lblTitle.setBounds(365, 44, 300, 36);
		add(lblTitle);
		
		JPanel panelBackIcon = new JPanel();
		panelBackIcon.setBounds(900, 45, 50, 50);
		add(panelBackIcon);
		panelBackIcon.setLayout(new BorderLayout(0, 0));
		panelBackIcon.setOpaque(false);
		panelBackIcon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				goBack(frame, client, album, artist, artGroup);
			}
		});
		panelBackIcon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelBackIcon.setToolTipText("Volver");
		
		JLabel lblBackIcon = new JLabel("");
		panelBackIcon.add(lblBackIcon, BorderLayout.CENTER);

		
		JTextArea textAreaOpinion = new JTextArea();
		textAreaOpinion.setBounds(50, 328, 900, 190);
		add(textAreaOpinion);
		textAreaOpinion.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		textAreaOpinion.setOpaque(false);
		textAreaOpinion.setForeground(Color.white);
		textAreaOpinion.setLineWrap(true);
		textAreaOpinion.setWrapStyleWord(true);
		textAreaOpinion.setCaretColor(Color.WHITE);
		textAreaOpinion.setBorder(new LineBorder(Color.WHITE, 2));
		textAreaOpinion.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				textAreaOpinion.setBorder(new LineBorder(new Color(244, 135, 244), 2));
			}

			@Override
			public void focusLost(FocusEvent e) {
				textAreaOpinion.setBorder(new LineBorder(Color.WHITE, 2));
			}
		});
		
		TextPrompt placeholderOpinion = new TextPrompt("Escribe tú opinión...", textAreaOpinion);
		placeholderOpinion.setVerticalAlignment(SwingConstants.TOP);
		placeholderOpinion.changeAlpha(0.8f);
		placeholderOpinion.changeStyle(Font.ITALIC);
		placeholderOpinion.setHorizontalAlignment(SwingConstants.LEFT);
		
		JTextArea textAreaTitle = new JTextArea();
		textAreaTitle.setWrapStyleWord(true);
		textAreaTitle.setOpaque(false);
		textAreaTitle.setLineWrap(true);
		textAreaTitle.setForeground(Color.WHITE);
		textAreaTitle.setFont(new Font("Dialog", Font.BOLD, 18));
		textAreaTitle.setBounds(234, 156, 716, 30);
		add(textAreaTitle);
		textAreaTitle.setCaretColor(Color.WHITE);
		textAreaTitle.setBorder(new LineBorder(Color.WHITE, 2));
		textAreaTitle.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				textAreaTitle.setBorder(new LineBorder(new Color(244, 135, 244), 2));
			}

			@Override
			public void focusLost(FocusEvent e) {
				textAreaTitle.setBorder(new LineBorder(Color.WHITE, 2));
			}
		});
		
		TextPrompt placeholderTitle = new TextPrompt("Título", textAreaTitle);
		placeholderTitle.changeAlpha(0.8f);
		placeholderTitle.changeStyle(Font.ITALIC);
		placeholderTitle.setHorizontalAlignment(SwingConstants.LEFT);
		
		JPanel panelStar1 = new JPanel();
		panelStar1.setFont(new Font("Dialog", Font.BOLD, 16));
		panelStar1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selectedStars = 1;
				showStars();
			}
		});
		panelStar1.setBounds(234, 211, 30, 30);
		add(panelStar1);
		panelStar1.setLayout(new BorderLayout(0, 0));
		panelStar1.setOpaque(false);
		panels.add(panelStar1);

		JLabel lblStar1 = new JLabel("");
		panelStar1.add(lblStar1, BorderLayout.CENTER);
		labels.add(lblStar1);

		JPanel panelStar2 = new JPanel();
		panelStar2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selectedStars = 2;
				showStars();
			}
		});
		panelStar2.setBounds(276, 211, 30, 30);
		add(panelStar2);
		panelStar2.setLayout(new BorderLayout(0, 0));
		panelStar2.setOpaque(false);
		panels.add(panelStar2);

		JLabel lblStar2 = new JLabel("");
		panelStar2.add(lblStar2, BorderLayout.CENTER);
		labels.add(lblStar2);

		JPanel panelStar3 = new JPanel();
		panelStar3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selectedStars = 3;
				showStars();
			}
		});
		panelStar3.setBounds(318, 211, 30, 30);
		add(panelStar3);
		panelStar3.setLayout(new BorderLayout(0, 0));
		panelStar3.setOpaque(false);
		panels.add(panelStar3);

		JLabel lblStar3 = new JLabel("");
		panelStar3.add(lblStar3, BorderLayout.CENTER);
		labels.add(lblStar3);

		JPanel panelStar4 = new JPanel();
		panelStar4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selectedStars = 4;
				showStars();
			}
		});
		panelStar4.setBounds(360, 211, 30, 30);
		add(panelStar4);
		panelStar4.setLayout(new BorderLayout(0, 0));
		panelStar4.setOpaque(false);
		panels.add(panelStar4);

		JLabel lblStar4 = new JLabel("");
		panelStar4.add(lblStar4, BorderLayout.CENTER);
		labels.add(lblStar4);

		JPanel panelStar5 = new JPanel();
		panelStar5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selectedStars = 5;
				showStars();
			}
		});
		panelStar5.setBounds(402, 211, 30, 30);
		add(panelStar5);
		panelStar5.setLayout(new BorderLayout(0, 0));
		panelStar5.setOpaque(false);
		panels.add(panelStar5);

		JLabel lblStar5 = new JLabel("");
		panelStar5.add(lblStar5, BorderLayout.CENTER);
		labels.add(lblStar5);

		JPanel panelAlbumCover = new JPanel();
		panelAlbumCover.setOpaque(false);
		panelAlbumCover.setBounds(50, 140, 150, 150);
		add(panelAlbumCover);
		panelAlbumCover.setLayout(new BorderLayout(0, 0));

		JLabel lblUsername = new JLabel("@" + client.getUsername());
		lblUsername.setForeground(new Color(244, 135, 244));
		lblUsername.setFont(new Font("Dialog", Font.BOLD, 16));
		lblUsername.setBounds(234, 252, 249, 30);
		add(lblUsername);
		
		JButton btnSend = new JButton("Enviar");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnSend.setBounds(402, 571, 200, 40);
		add(btnSend);
		btnSend.setForeground(Color.black);
		btnSend.setFont(new Font("Lucida Grande", Font.BOLD, 17));
		btnSend.setBackground(new Color(244, 135, 244));
		btnSend.setBorder(new LineBorder(new Color(244, 135, 244), 2));
		btnSend.setOpaque(true);
		btnSend.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		WindowUtils.addImage(panelBackIcon, lblBackIcon, "img/icon/arrow.png");
				
						JLabel lblAlbumCover = new JLabel("");
						panelAlbumCover.add(lblAlbumCover, BorderLayout.CENTER);
						WindowUtils.addImage(panelAlbumCover, lblAlbumCover, album.getCover());
		showStars();
	}
	
	private void goBack(JFrame frame, Client client, Album album, Artist artist, ArtGroup artGroup) {
		if (artist != null) {
			frame.getContentPane().removeAll();
			frame.getContentPane().add(
					PanelFactory.getJPanel(PanelFactory.ALBUM_VIEW, frame, client, null, artist, null, album, null));
			frame.revalidate();
			frame.repaint();
		} else if (artGroup != null) {
			frame.getContentPane().removeAll();
			frame.getContentPane().add(PanelFactory.getJPanel(PanelFactory.ALBUM_VIEW, frame, client, null, null,
					artGroup, album, null));
			frame.revalidate();
			frame.repaint();
		}
	}
	
	private void showStars() {
		String path = "img/icon/star";

		switch (selectedStars) {
		case 0:
			for (int i = 0; i < 5; i++) {
				JPanel panel = panels.get(i);
				JLabel label = labels.get(i);
				WindowUtils.addImage(panel, label, path + "_grey.png");

			}
			break;
		case 1:
			for (int i = 0; i < 5; i++) {
				JPanel panel = panels.get(i);
				JLabel label = labels.get(i);
				if (i == 0) {
					WindowUtils.addImage(panel, label, path + ".png");
				} else {
					WindowUtils.addImage(panel, label, path + "_grey.png");
				}

			}
			break;
		case 2:
			for (int i = 0; i < 5; i++) {
				JPanel panel = panels.get(i);
				JLabel label = labels.get(i);
				if (i == 0 || i == 1) {
					WindowUtils.addImage(panel, label, path + ".png");
				} else {
					WindowUtils.addImage(panel, label, path + "_grey.png");
				}

			}
			break;
		case 3:
			for (int i = 0; i < 5; i++) {
				JPanel panel = panels.get(i);
				JLabel label = labels.get(i);
				if (i != 3 && i != 4) {
					WindowUtils.addImage(panel, label, path + ".png");
				} else {
					WindowUtils.addImage(panel, label, path + "_grey.png");
				}

			}
			break;
		case 4:
			for (int i = 0; i < 5; i++) {
				JPanel panel = panels.get(i);
				JLabel label = labels.get(i);
				if (i != 4) {
					WindowUtils.addImage(panel, label, path + ".png");
				} else {
					WindowUtils.addImage(panel, label, path + "_grey.png");
				}

			}
			break;
		case 5:
			for (int i = 0; i < 5; i++) {
				JPanel panel = panels.get(i);
				JLabel label = labels.get(i);
				WindowUtils.addImage(panel, label, path + ".png");
			}
			break;
		}
	}
}
