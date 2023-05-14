package soundbridge.view.panels;

import java.awt.Color;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import soundbridge.database.pojos.Review;
import soundbridge.utils.WindowUtils;

import java.awt.BorderLayout;

import javax.swing.JCheckBox;

/**
 * Panel that contains all the information of an album review written by a
 * client.
 */
public class ReviewPanel extends JPanel {

	private static final long serialVersionUID = -7552632715076935682L;
	private ArrayList<JPanel> panels = null;
	private ArrayList<JLabel> labels = null;
	public JCheckBox checkBox;

	/**
	 * Initializes the panel.
	 * 
	 * @param review review of the client
	 */
	public ReviewPanel(Review review) {
		setBounds(0, 0, 900, 400);
		setBackground(Color.black);
		setLayout(null);

		panels = new ArrayList<JPanel>();
		labels = new ArrayList<JLabel>();
		initialize(review);
		showStars(review);
	}

	/**
	 * Initializes the components of the panel.
	 * 
	 * @param review review of the client
	 */
	private void initialize(Review review) {
		JLabel lblTitle = new JLabel(review.getTitle());
		lblTitle.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setBounds(288, 35, 500, 30);
		add(lblTitle);

		JTextArea textArea = new JTextArea(review.getOpinion());
		textArea.setBounds(49, 235, 715, 142);
		add(textArea);
		textArea.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		textArea.setEditable(false);
		textArea.setOpaque(false);
		textArea.setForeground(Color.white);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);

		JPanel panelStar1 = new JPanel();
		panelStar1.setBounds(49, 35, 30, 30);
		add(panelStar1);
		panelStar1.setLayout(new BorderLayout(0, 0));
		panelStar1.setOpaque(false);
		panels.add(panelStar1);

		JLabel lblStar1 = new JLabel("");
		panelStar1.add(lblStar1, BorderLayout.CENTER);
		labels.add(lblStar1);

		JPanel panelStar2 = new JPanel();
		panelStar2.setBounds(91, 35, 30, 30);
		add(panelStar2);
		panelStar2.setLayout(new BorderLayout(0, 0));
		panelStar2.setOpaque(false);
		panels.add(panelStar2);

		JLabel lblStar2 = new JLabel("");
		panelStar2.add(lblStar2, BorderLayout.CENTER);
		labels.add(lblStar2);

		JPanel panelStar3 = new JPanel();
		panelStar3.setBounds(133, 35, 30, 30);
		add(panelStar3);
		panelStar3.setLayout(new BorderLayout(0, 0));
		panelStar3.setOpaque(false);
		panels.add(panelStar3);

		JLabel lblStar3 = new JLabel("");
		panelStar3.add(lblStar3, BorderLayout.CENTER);
		labels.add(lblStar3);

		JPanel panelStar4 = new JPanel();
		panelStar4.setBounds(175, 35, 30, 30);
		add(panelStar4);
		panelStar4.setLayout(new BorderLayout(0, 0));
		panelStar4.setOpaque(false);
		panels.add(panelStar4);

		JLabel lblStar4 = new JLabel("");
		panelStar4.add(lblStar4, BorderLayout.CENTER);
		labels.add(lblStar4);

		JPanel panelStar5 = new JPanel();
		panelStar5.setBounds(217, 35, 30, 30);
		add(panelStar5);
		panelStar5.setLayout(new BorderLayout(0, 0));
		panelStar5.setOpaque(false);
		panels.add(panelStar5);

		JLabel lblStar5 = new JLabel("");
		panelStar5.add(lblStar5, BorderLayout.CENTER);
		labels.add(lblStar5);

		JPanel panelAlbumCover = new JPanel();
		panelAlbumCover.setOpaque(false);
		panelAlbumCover.setBounds(698, 40, 150, 150);
		add(panelAlbumCover);
		panelAlbumCover.setLayout(new BorderLayout(0, 0));

		JLabel lblAlbumCover = new JLabel("");
		panelAlbumCover.add(lblAlbumCover, BorderLayout.CENTER);

		JLabel lblUsername = new JLabel("@" + review.getClientPP().getUsername());
		lblUsername.setForeground(new Color(244, 135, 244));
		lblUsername.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblUsername.setBounds(46, 84, 249, 30);
		add(lblUsername);

		JLabel lblDate = new JLabel((new SimpleDateFormat("dd/MM/yyyy")).format(review.getReviewDate()));
		lblDate.setForeground(Color.WHITE);
		lblDate.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblDate.setBounds(46, 129, 249, 30);
		add(lblDate);

		JLabel lblAlbum = new JLabel(review.getAlbum().getName());
		lblAlbum.setForeground(Color.WHITE);
		lblAlbum.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblAlbum.setBounds(46, 169, 249, 30);
		add(lblAlbum);

		checkBox = new JCheckBox("");
		checkBox.setBounds(835, 350, 25, 25);
		add(checkBox);
		checkBox.setOpaque(false);

		JPanel panelBackground = new JPanel();
		panelBackground.setBounds(5, 5, 890, 390);
		add(panelBackground);
		panelBackground.setLayout(new BorderLayout(0, 0));

		JLabel lblBackground = new JLabel("");
		panelBackground.add(lblBackground, BorderLayout.CENTER);

		WindowUtils.addImage(panelBackground, lblBackground, "img/panel/panelReview_bg.png");
		WindowUtils.addImage(panelAlbumCover, lblAlbumCover, review.getAlbum().getCover());
	}

	/**
	 * Shows the corresponding star image, depending on the review's stars.
	 * 
	 * @param review review of the client
	 */
	private void showStars(Review review) {
		int stars = review.getStars();
		String path = "img/icon/star";

		switch (stars) {
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
