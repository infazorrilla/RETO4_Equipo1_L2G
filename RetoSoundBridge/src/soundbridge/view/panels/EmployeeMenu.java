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

public class EmployeeMenu extends JPanel {

	private static final long serialVersionUID = -5540530709811898264L;

	public EmployeeMenu(JFrame frame, Employee employee) {

		initialize(frame, employee);

	}

	private void initialize(JFrame frame, Employee employee) {

		setBounds(0, 0, 1000, 672);
		setBackground(Color.black);
		setLayout(null);

		JLabel lblTitle = new JLabel("¿Qué desea gestionar?");
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Dialog", Font.PLAIN, 22));
		lblTitle.setBounds(380, 44, 250, 36);
		add(lblTitle);
		
		JPanel panelLogOutIcon = new JPanel();
		panelLogOutIcon.setBounds(903, 45, 50, 50);
		add(panelLogOutIcon);
		panelLogOutIcon.setLayout(new BorderLayout(0, 0));
		panelLogOutIcon.setOpaque(false);
		panelLogOutIcon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				logOut(frame);
			}
		});
		panelLogOutIcon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelLogOutIcon.setToolTipText("Cerrar sesión");
		
		JLabel lblLogOutIcon = new JLabel("");
		panelLogOutIcon.add(lblLogOutIcon, BorderLayout.CENTER);

		JLabel lblClientes = new JLabel("Clientes");
		lblClientes.setForeground(Color.WHITE);
		lblClientes.setFont(new Font("Dialog", Font.PLAIN, 17));
		lblClientes.setBounds(220, 183, 119, 29);
		add(lblClientes);

		JPanel panelManageClients = new JPanel();
		panelManageClients.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.getContentPane().removeAll();
				frame.getContentPane().add(PanelFactory.getJPanel(PanelFactory.MANAGE_CLIENTS, frame, null, employee,
						null, null, null, null));
				frame.revalidate();
				frame.repaint();
			}
		});
		panelManageClients.setBounds(108, 150, 100, 100);
		panelManageClients.setOpaque(false);
		panelManageClients.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		add(panelManageClients);
		panelManageClients.setLayout(new BorderLayout(0, 0));

		JLabel lblManageClientsIcon = new JLabel("");
		panelManageClients.add(lblManageClientsIcon, BorderLayout.CENTER);

		WindowUtils.addImage(panelManageClients, lblManageClientsIcon, "img/icon/clients.png");
		WindowUtils.addImage(panelLogOutIcon, lblLogOutIcon, "img/icon/off.png");
	}
	
	private void logOut(JFrame frame) {
		frame.getContentPane().removeAll();
		frame.getContentPane()
				.add(PanelFactory.getJPanel(PanelFactory.LOGIN, frame, null, null, null, null, null, null));
		frame.revalidate();
		frame.repaint();
	}
}
