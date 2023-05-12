package soundbridge.view.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.plaf.basic.BasicScrollBarUI;

import soundbridge.database.managers.ReviewManager;
import soundbridge.database.pojos.Album;
import soundbridge.database.pojos.ArtGroup;
import soundbridge.database.pojos.Artist;
import soundbridge.database.pojos.Client;
import soundbridge.database.pojos.Review;
import soundbridge.utils.WindowUtils;
import soundbridge.view.factory.PanelFactory;
import javax.swing.SwingConstants;

/**
 * Panel that contains the validated reviews of the album.
 */
public class ClientsReviews extends JPanel {

	private static final long serialVersionUID = -2335885884396696647L;
	private ArrayList<Review> validatedReviews = null;

	/**
	 * Initializes the panel.
	 * 
	 * @param frame		frame where panel is added
	 * @param client	logged client
	 * @param album		selected album
	 * @param artist	owner of the album if not null
	 * @param artGroup	owner of the album if not null
	 */
	public ClientsReviews(JFrame frame, Client client, Album album, Artist artist, ArtGroup artGroup) {
		setBounds(0, 0, 1000, 672);
		setBackground(Color.black);
		setLayout(null);
		
		initialize(frame, client, album, artist, artGroup);
	}

	/**
	 * Initializes the components of the panel.
	 * 
	 * @param frame		frame where panel is added
	 * @param client	logged client
	 * @param album		selected album
	 * @param artist	owner of the album if not null
	 * @param artGroup	owner of the album if not null
	 */
	private void initialize(JFrame frame, Client client, Album album, Artist artist, ArtGroup artGroup) {
		JLabel lblTitle = new JLabel("Valoraciones");
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

		WindowUtils.addImage(panelBackIcon, lblBackIcon, "img/icon/arrow.png");

		JPanel panelGridReviews = new JPanel();
		panelGridReviews.setBackground(Color.black);
		panelGridReviews.setLayout(new GridLayout(0, 1, 0, 0));

		JScrollPane scrollPane = new JScrollPane(panelGridReviews, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(50, 150, 900, 500);
		add(scrollPane);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());

		scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {

			@Override
			protected JButton createDecreaseButton(int orientation) {
				return createZeroButton();
			}

			@Override
			protected JButton createIncreaseButton(int orientation) {
				return createZeroButton();
			}

			private JButton createZeroButton() {
				JButton jbutton = new JButton();
				jbutton.setPreferredSize(new Dimension(0, 0));
				jbutton.setMinimumSize(new Dimension(0, 0));
				jbutton.setMaximumSize(new Dimension(0, 0));
				return jbutton;
			}

			@Override
			protected void configureScrollBarColors() {
				this.thumbColor = Color.black;
				this.thumbHighlightColor = Color.WHITE;
				this.trackColor = Color.BLACK;
			}
		});

		getAllValidatedReviews(album);
		createReviewPanel(panelGridReviews, scrollPane);
	}

	/**
	 * Takes the client back to the album panel.
	 * 
	 * @param frame		frame where panel is added
	 * @param client	logged client
	 * @param album		selected album
	 * @param artist	owner of the album if not null
	 * @param artGroup	owner of the album if not null
	 */
	private void goBack(JFrame frame, Client client, Album album, Artist artist, ArtGroup artGroup) {
		if (artist != null) {
			frame.getContentPane().removeAll();
			frame.getContentPane().add(PanelFactory.getJPanel(PanelFactory.ALBUM_VIEW, frame, client, null, artist,
					null, album, null, null));
			frame.revalidate();
			frame.repaint();
		} else if (artGroup != null) {
			frame.getContentPane().removeAll();
			frame.getContentPane().add(PanelFactory.getJPanel(PanelFactory.ALBUM_VIEW, frame, client, null, null,
					artGroup, album, null, null));
			frame.revalidate();
			frame.repaint();
		}
	}

	private void getAllValidatedReviews(Album album) {
		ReviewManager reviewManager = new ReviewManager();
		try {
			validatedReviews = reviewManager.validatedReviewsWithAllInformation(album);
		} catch (SQLException e) {
			WindowUtils.errorPane("No se han podido cargar las valoraciones.", "Error en la base de datos");
		} catch (Exception e) {
			WindowUtils.errorPane("No se han podido cargar las valoraciones.", "Error general");
		}
	}

	private void createReviewPanel(JPanel panel, JScrollPane scrollPane) {
		if (null != validatedReviews) {
			int height = 400;
			for (int i = 0; i < validatedReviews.size(); i++) {

				Review review = validatedReviews.get(i);

				ReviewPanel reviewPanel = new ReviewPanel(review);
				reviewPanel.checkBox.setVisible(false);

				panel.setPreferredSize(new Dimension(900, height));

				height += 400;

				panel.add(reviewPanel);
			}
		} else {
			scrollPane.setVisible(false);
		}
	}
}