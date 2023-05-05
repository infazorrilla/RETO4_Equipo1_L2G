package soundbridge.view.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import soundbridge.database.managers.ReviewManager;
import soundbridge.database.pojos.Employee;
import soundbridge.database.pojos.Review;
import soundbridge.utils.WindowUtils;
import soundbridge.view.factory.PanelFactory;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.GridLayout;

public class EmployeeReviews extends JPanel {

	private static final long serialVersionUID = -2335885884396696647L;
	private ArrayList<Review> nonValidatedReviews = null;
	
	public EmployeeReviews(JFrame frame, Employee employee) {

		initialize(frame, employee);

	}

	private void initialize(JFrame frame, Employee employee) {

		setBounds(0, 0, 1000, 672);
		setBackground(Color.black);
		setLayout(null);
		
		getAllNonValidatedReviews();
		
		JPanel panelBackIcon = new JPanel();
		panelBackIcon.setBounds(900, 45, 50, 50);
		add(panelBackIcon);
		panelBackIcon.setLayout(new BorderLayout(0, 0));
		panelBackIcon.setOpaque(false);
		panelBackIcon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				goBack(frame, employee);
			}
		});
		panelBackIcon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelBackIcon.setToolTipText("Volver");

		JLabel lblBackIcon = new JLabel("");
		panelBackIcon.add(lblBackIcon, BorderLayout.CENTER);
		
		WindowUtils.addImage(panelBackIcon, lblBackIcon, "img/icon/arrow.png");
		
		JPanel panelGridReviews = new JPanel();
		panelGridReviews.setBackground(Color.red);
		panelGridReviews.setBounds(0, 0, 1000, 800);
		panelGridReviews.setLayout(new GridLayout(nonValidatedReviews.size(), 0, 0, 0));
		
		JScrollPane scrollPane = new JScrollPane(panelGridReviews);
		scrollPane.setBounds(0, 0, 1000, 672);
		add(scrollPane);
	
		
		
		createReviewPanel(panelGridReviews);
	}
	
	private void goBack(JFrame frame, Employee employee) {
		frame.getContentPane().removeAll();
		frame.getContentPane()
				.add(PanelFactory.getJPanel(PanelFactory.EMPLOYEE_MENU, frame, null, employee, null, null, null, null));
		frame.revalidate();
		frame.repaint();
	}
	
	private void getAllNonValidatedReviews() {
		ReviewManager reviewManager = new ReviewManager();
		try {
			nonValidatedReviews = reviewManager.nonValidatedReviews();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void createReviewPanel(JPanel panel) {
		System.out.println(nonValidatedReviews.toString());
		if (null != nonValidatedReviews) {
			for (Review review : nonValidatedReviews) {
				JPanel reviewPanel = new JPanel();
				reviewPanel.setBounds(0, 0, 600, 400);
				reviewPanel.setBackground(Color.black);
				reviewPanel.setLayout(null);
				
				JLabel lblStars = new JLabel("" + review.getStars());
				lblStars.setFont(new Font("Dialog", Font.PLAIN, 16));
				lblStars.setForeground(Color.WHITE);
				lblStars.setBounds(50, 49, 184, 30);
				reviewPanel.add(lblStars);
				
				JLabel lblTitle = new JLabel(review.getTitle());
				lblTitle.setFont(new Font("Dialog", Font.PLAIN, 16));
				lblTitle.setForeground(Color.WHITE);
				lblTitle.setBounds(216, 49, 184, 30);
				reviewPanel.add(lblTitle);
				
				JTextArea textArea = new JTextArea(review.getOpinion());
				textArea.setBounds(51, 126, 715, 177);
				reviewPanel.add(textArea);
				textArea.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
				textArea.setEditable(false);
				textArea.setOpaque(false);
				textArea.setForeground(Color.white);
				textArea.setLineWrap(true);
				textArea.setWrapStyleWord(true);
				
				panel.add(reviewPanel);
			}
		}
	}

}
