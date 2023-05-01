package soundbridge.view.panels;

import java.awt.Color;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;


import soundbridge.database.managers.ClientManager;
import soundbridge.database.pojos.Client;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;


public class EmployeeManagesClients extends JPanel {


	private static final long serialVersionUID = -6622744341750440365L;
	private JTable tableClients;
	DefaultTableModel modelClients=null;

	public EmployeeManagesClients(JFrame frame) {

		initialize(frame);

	}

	private void initialize(JFrame frame) {

		setBounds(0, 0, 1000, 672);
		setBackground(Color.black);
		setLayout(null);
		
		JLabel lblTitle = new JLabel("Gestión de clientes");
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Dialog", Font.PLAIN, 22));
		lblTitle.setBounds(380, 44, 250, 36);
		add(lblTitle);
		
		JButton btnSelectAllClients = new JButton("Lista de clientes");
		btnSelectAllClients.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClientManager clientmanager = new ClientManager();
				try {
					 ArrayList<Client> allClients=(ArrayList<Client>) clientmanager.doSelectAll();
					for (int i = 0; i < allClients.size(); i++) {
						Client client = allClients.get(i);
						String nombre= client.getName();
						String apellido=client.getLastName();
						String genero=client.getGender();
						String dNI =client.getPersonalId();
						String username=client.getUsername();
						String contrasena=client.getPasswd();
						modelClients.addRow(new String[] { nombre, apellido, genero, dNI, username, contrasena });
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnSelectAllClients.setBounds(139, 123, 126, 23);
		add(btnSelectAllClients);
		
		JScrollPane scrollPaneResumenCompra = new JScrollPane();
		scrollPaneResumenCompra.setBounds(44, 179, 904, 383);
		add(scrollPaneResumenCompra);

		tableClients = new JTable();
		tableClients.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableClients.setDefaultEditor(Object.class, null);
		scrollPaneResumenCompra.setViewportView(tableClients);
		tableClients.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		scrollPaneResumenCompra.setViewportView(tableClients);
		Object[] columnsManagesClients = { "Nombre", "Apellido", "Género", "DNI", "Username", "Contraseña"  };

		 modelClients= new DefaultTableModel();
		modelClients.setColumnIdentifiers(columnsManagesClients);
		tableClients.setModel(modelClients);
		
		JButton btnBloqClient = new JButton("Bloquear cliente");
		btnBloqClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClientManager clientmanager = new ClientManager();
				Client client = new Client();
				String username = (String) modelClients.getValueAt(tableClients.getSelectedRow(), 4);
				client.setUsername(username);
				try {
					Client selectedClient =clientmanager.getClientByUsername(username);
					selectedClient.setBlocked(true);
					clientmanager.update(selectedClient);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnBloqClient.setBounds(139, 600, 126, 23);
		add(btnBloqClient);
		
	}
}