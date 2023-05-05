package soundbridge.database.managers;

import java.awt.Color;

import javax.swing.JPanel;

import soundbridge.database.pojos.Review;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextArea;

public class ReviewPanel extends JPanel {

	private static final long serialVersionUID = -4827526679967403039L;
	
	public ReviewPanel(Review review) {

		initialize(review);

	}

	private void initialize(Review review) {

		setBounds(0, 0, 600, 400);
		setBackground(Color.black);
		setLayout(null);
		
		JLabel lblStars = new JLabel("" + review.getStars());
		lblStars.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblStars.setForeground(Color.WHITE);
		lblStars.setBounds(50, 49, 184, 30);
		add(lblStars);
		
		JLabel lblTitle = new JLabel(review.getTitle());
		lblTitle.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setBounds(216, 49, 184, 30);
		add(lblTitle);
		
		JTextArea textArea = new JTextArea(review.getOpinion());
		textArea.setBounds(51, 126, 715, 177);
		add(textArea);
		textArea.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		textArea.setEditable(false);
		textArea.setOpaque(false);
		textArea.setForeground(Color.white);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
	}
}
