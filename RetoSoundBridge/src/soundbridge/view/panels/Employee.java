package soundbridge.view.panels;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;

import soundbridge.utils.WindowUtils;
import soundbridge.view.factory.PanelFactory;

import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Employee extends JPanel {

	private static final long serialVersionUID = -5540530709811898264L;

	public Employee(JFrame frame) {

		initialize(frame);

	}

	private void initialize(JFrame frame) {

		setBounds(0, 0, 1000, 672);
		setBackground(Color.black);
		setLayout(null);

		JLabel lblTitle = new JLabel("¿Qué desea gestionar?");
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Dialog", Font.PLAIN, 22));
		lblTitle.setBounds(380, 44, 250, 36);
		add(lblTitle);

		JLabel lblClientes = new JLabel("Clientes");
		lblClientes.setForeground(Color.WHITE);
		lblClientes.setFont(new Font("Dialog", Font.PLAIN, 17));
		lblClientes.setBounds(214, 156, 73, 29);
		add(lblClientes);
		
		JPanel panelManageClients = new JPanel();
		panelManageClients.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.getContentPane().removeAll();
				frame.getContentPane()
						.add(PanelFactory.getJPanel(PanelFactory.MANAGE_CLIENTS, frame, null, null, null, null, null));
				frame.revalidate();
				frame.repaint();
			}
		});
		panelManageClients.setBounds(108, 219, 100, 100);
		panelManageClients.setOpaque(false);
		panelManageClients.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		add(panelManageClients);
		panelManageClients.setLayout(new BorderLayout(0, 0));
		
		JLabel lblManageClientsIcon = new JLabel("");
		panelManageClients.add(lblManageClientsIcon, BorderLayout.CENTER);
		
		WindowUtils.addImage(panelManageClients, lblManageClientsIcon, "img/icon/clients.png");
	}
}
