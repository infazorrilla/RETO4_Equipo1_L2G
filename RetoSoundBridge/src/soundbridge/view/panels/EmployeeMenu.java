package soundbridge.view.panels;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;

import soundbridge.database.pojos.Employee;
import soundbridge.utils.WindowUtils;
import soundbridge.view.factory.PanelFactory;

import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Panel that contains the edition menu allowing an employee to manage clients,
 * reviews or content.
 */
public class EmployeeMenu extends JPanel {

	private static final long serialVersionUID = -5540530709811898264L;

	/**
	 * Initializes the panel.
	 * 
	 * @param frame    frame where the panel is added
	 * @param employee logged employee
	 */
	public EmployeeMenu(JFrame frame, Employee employee) {
		setBounds(0, 0, 1000, 672);
		setBackground(Color.black);
		setLayout(null);

		initialize(frame, employee);

	}

	/**
	 * Initializes the components of the panel.
	 * 
	 * @param frame    frame where the panel is added
	 * @param employee logged employee
	 */
	private void initialize(JFrame frame, Employee employee) {

		JLabel lblTitle = new JLabel("¿Qué desea gestionar?");
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Dialog", Font.PLAIN, 22));
		lblTitle.setBounds(380, 44, 250, 36);
		add(lblTitle);

		JPanel panelProfileIcon = new JPanel();
		panelProfileIcon.setBounds(903, 45, 50, 50);
		add(panelProfileIcon);
		panelProfileIcon.setLayout(new BorderLayout(0, 0));
		panelProfileIcon.setOpaque(false);
		panelProfileIcon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				goToProfile(frame, employee);
			}
		});
		panelProfileIcon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelProfileIcon.setToolTipText("Ir a mi perfil.");

		JLabel lblProfileIcon = new JLabel("");
		panelProfileIcon.add(lblProfileIcon, BorderLayout.CENTER);

		JLabel lblClientes = new JLabel("Clientes");
		lblClientes.setForeground(Color.WHITE);
		lblClientes.setFont(new Font("Dialog", Font.PLAIN, 17));
		lblClientes.setBounds(220, 183, 119, 29);
		add(lblClientes);

		JPanel panelManageClients = new JPanel();
		panelManageClients.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				goToManageClients(frame, employee);
			}
		});
		panelManageClients.setBounds(108, 150, 100, 100);
		panelManageClients.setOpaque(false);
		panelManageClients.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		add(panelManageClients);
		panelManageClients.setLayout(new BorderLayout(0, 0));

		JLabel lblManageClientsIcon = new JLabel("");
		panelManageClients.add(lblManageClientsIcon, BorderLayout.CENTER);

		JPanel panelReviews = new JPanel();
		panelReviews.setOpaque(false);
		panelReviews.setBounds(108, 279, 100, 100);
		add(panelReviews);
		panelReviews.setLayout(new BorderLayout(0, 0));
		panelReviews.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelReviews.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				goToReviews(frame, employee);
			}
		});

		JLabel lblReviewIcon = new JLabel("");
		panelReviews.add(lblReviewIcon, BorderLayout.CENTER);

		JLabel lblReviews = new JLabel("Valoraciones");
		lblReviews.setForeground(Color.WHITE);
		lblReviews.setFont(new Font("Dialog", Font.PLAIN, 17));
		lblReviews.setBounds(220, 315, 119, 29);
		add(lblReviews);

		WindowUtils.addImage(panelManageClients, lblManageClientsIcon, "img/icon/clients.png");
		WindowUtils.addImage(panelReviews, lblReviewIcon, "img/icon/review.png");
		WindowUtils.addImage(panelProfileIcon, lblProfileIcon, "img/icon/profile.png");
	}

	/**
	 * Takes the employee to the panel that enables managing clients.
	 * 
	 * @param frame    frame where the panel is added
	 * @param employee logged employee
	 */
	private void goToManageClients(JFrame frame, Employee employee) {
		frame.getContentPane().removeAll();
		frame.getContentPane().add(PanelFactory.getJPanel(PanelFactory.MANAGE_CLIENTS, frame, null, employee, null,
				null, null, null, null));
		frame.revalidate();
		frame.repaint();
	}

	/**
	 * Takes the employee to the panel that enables managing clients' reviews.
	 * 
	 * @param frame    frame where the panel is added
	 * @param employee logged employee
	 */
	private void goToReviews(JFrame frame, Employee employee) {
		frame.getContentPane().removeAll();
		frame.getContentPane().add(PanelFactory.getJPanel(PanelFactory.EMPLOYEE_REVIEWS, frame, null, employee, null,
				null, null, null, null));
		frame.revalidate();
		frame.repaint();
	}

	/**
	 * Takes the employee to his profile.
	 * 
	 * @param frame    frame where the panel is added
	 * @param employee logged employee
	 */
	private void goToProfile(JFrame frame, Employee employee) {
		frame.getContentPane().removeAll();
		frame.getContentPane().add(PanelFactory.getJPanel(PanelFactory.EMPLOYEE_PROFILE, frame, null, employee, null,
				null, null, null, null));
		frame.revalidate();
		frame.repaint();
	}
}
