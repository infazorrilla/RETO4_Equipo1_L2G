package soundbridge.view.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import soundbridge.database.managers.ReviewManager;
import soundbridge.database.pojos.Employee;
import soundbridge.database.pojos.Review;
import soundbridge.utils.WindowUtils;
import soundbridge.view.factory.PanelFactory;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;

import java.awt.GridLayout;

public class EmployeeReviews extends JPanel {

	private static final long serialVersionUID = -2335885884396696647L;
	private ArrayList<Review> nonValidatedReviews = null;
	private ArrayList<Review> selectedReviews = null;

	public EmployeeReviews(JFrame frame, Employee employee) {

		initialize(frame, employee);

	}

	private void initialize(JFrame frame, Employee employee) {

		setBounds(0, 0, 1000, 672);
		setBackground(Color.black);
		setLayout(null);

		JLabel lblTitle = new JLabel("Revisión de valoraciones");
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
				goBack(frame, employee);
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

		JPanel panelGridList = new JPanel();
		panelGridList.setBounds(0, 105, 1000, 40);
		add(panelGridList);
		panelGridList.setLayout(new GridLayout(1, 0, 0, 0));
		
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


		JButton btnValidate = new JButton("Validar selección");
		btnValidate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doValidate(panelGridReviews, scrollPane);
			}
		});
		panelGridList.add(btnValidate);
		btnValidate.setForeground(Color.BLACK);
		btnValidate.setFont(new Font("Dialog", Font.BOLD, 15));
		btnValidate.setBackground(new Color(104, 197, 111));
		btnValidate.setBorder(new LineBorder(Color.WHITE, 0));
		btnValidate.setOpaque(true);
		btnValidate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		JButton btnReject = new JButton("Invalidar selección");
		btnReject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doDelete(panelGridReviews, scrollPane);
			}
		});
		panelGridList.add(btnReject);
		btnReject.setForeground(Color.WHITE);
		btnReject.setFont(new Font("Dialog", Font.BOLD, 15));
		btnReject.setBackground(new Color(236, 64, 68));
		btnReject.setBorder(new LineBorder(Color.WHITE, 0));
		btnReject.setOpaque(true);
		btnReject.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		
		getAllNonValidatedReviews();
		createReviewPanel(panelGridReviews, scrollPane);
	}

	private void goBack(JFrame frame, Employee employee) {
		frame.getContentPane().removeAll();
		frame.getContentPane()
				.add(PanelFactory.getJPanel(PanelFactory.EMPLOYEE_MENU, frame, null, employee, null, null, null, null, null));
		frame.revalidate();
		frame.repaint();
	}

	private void getAllNonValidatedReviews() {
		ReviewManager reviewManager = new ReviewManager();
		try {
			nonValidatedReviews = reviewManager.nonValidatedReviewsWithAllInformation();
		} catch (SQLException e) {
			WindowUtils.errorPane("No se han podido cargar las valoraciones.", "Error en la base de datos");
		} catch (Exception e) {
			WindowUtils.errorPane("No se han podido cargar las valoraciones.", "Error en la base de datos");
		}
	}

	private void createReviewPanel(JPanel panel, JScrollPane scrollPane) {
		if (null != nonValidatedReviews) {
			int height = 400;
			for (int i = 0; i < nonValidatedReviews.size(); i++) {

				Review review = nonValidatedReviews.get(i);

				ReviewPanel reviewPanel = new ReviewPanel(review);
				reviewPanel.checkBox.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (reviewPanel.checkBox.isSelected()) {
							if (null == selectedReviews)
								selectedReviews = new ArrayList<Review>();
							selectedReviews.add(review);
						} else {
							selectedReviews.remove(review);
							if (selectedReviews.isEmpty())
								selectedReviews = null;
						}
					}
				});

				panel.setPreferredSize(new Dimension(900, height));

				height += 400;

				panel.add(reviewPanel);
			}
		} else {
			scrollPane.setVisible(false);
		}
	}

	private void doValidate(JPanel panel, JScrollPane scrollPane) {
		if (null != selectedReviews) {
			try {

				validateSelectedReviews();
				WindowUtils.confirmationPane("Se han validado las valoraciones seleccionadas.", "Confirmación");
			} catch (Exception e) {
				WindowUtils.errorPane("No se han podido validar las valoraciones.", "Error");
			}
			panel.removeAll();
			panel.revalidate();
			panel.repaint();
			getAllNonValidatedReviews();
			createReviewPanel(panel, scrollPane);
		} else {
			WindowUtils.errorPane("No hay valoraciones seleccionadas.", "Error");
		}
	}

	private void validateSelectedReviews() throws SQLException, Exception {
		if (selectedReviews != null) {
			ReviewManager reviewManager = new ReviewManager();
			for (Review review : selectedReviews) {
				review.setValidated(true);
				reviewManager.update(review);
			}
		}
	}

	private void doDelete(JPanel panel, JScrollPane scrollPane) {
		if (null != selectedReviews) {
			try {
				deleteSelectedReviews();
				WindowUtils.confirmationPane("Se han eliminado las valoraciones seleccionadas.", "Confirmación");
			} catch (Exception e) {
				WindowUtils.errorPane("No se han podido eliminar las valoraciones.", "Error");
			}

			panel.removeAll();
			panel.revalidate();
			panel.repaint();
			getAllNonValidatedReviews();
			createReviewPanel(panel, scrollPane);
		} else {
			WindowUtils.errorPane("No hay valoraciones seleccionadas.", "Error");
		}
	}

	private void deleteSelectedReviews() throws SQLException, Exception {
		ReviewManager reviewManager = new ReviewManager();
		for (Review review : selectedReviews) {
			review.setValidated(true);
			reviewManager.delete(review);
		}
	}
}
