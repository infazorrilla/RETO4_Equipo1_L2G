package soundbridge.view.panels;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;

import soundbridge.view.factory.PanelFactory;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
		
		JButton btnManagerClients = new JButton("New button");
		btnManagerClients.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				frame.getContentPane().add(PanelFactory.getJPanel(PanelFactory.EMPLOYEEMANAGESCLIENTS, frame, null));
				frame.revalidate();
				frame.repaint();
			}
		});
		btnManagerClients.setBounds(108, 134, 96, 73);
		add(btnManagerClients);
		
		JLabel lblClientes = new JLabel("Clientes");
		lblClientes.setForeground(Color.WHITE);
		lblClientes.setFont(new Font("Dialog", Font.PLAIN, 17));
		lblClientes.setBounds(214, 156, 73, 29);
		add(lblClientes);
	}
}
