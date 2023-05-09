package soundbridge.view.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import soundbridge.database.pojos.Album;
import soundbridge.database.pojos.ArtGroup;
import soundbridge.database.pojos.Artist;
import soundbridge.database.pojos.Client;
import soundbridge.view.factory.PanelFactory;

public class WriteReview extends JPanel {

	private static final long serialVersionUID = 8019879665852364145L;

	public WriteReview (JFrame frame, Client client, Album album, Artist artist, ArtGroup artGroup) {
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
}
