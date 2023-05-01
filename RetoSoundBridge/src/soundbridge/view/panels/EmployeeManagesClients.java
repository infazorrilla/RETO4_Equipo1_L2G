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
				showClients();
			}

			
		});
		btnSelectAllClients.setBounds(139, 123, 156, 23);
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
		Object[] columnsManagesClients = { "Nombre", "Apellido", "Género", "DNI", "Username", "Contraseña", "Bloqueado"  };

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
					showClients();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnBloqClient.setBounds(139, 600, 143, 23);
		add(btnBloqClient);
		
		JButton btnDesBloqClient = new JButton("Desbloquear cliente");
		btnDesBloqClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClientManager clientmanager = new ClientManager();
				Client client = new Client();
				String username = (String) modelClients.getValueAt(tableClients.getSelectedRow(), 4);
				client.setUsername(username);
				Client selectedClient;
				try {
					
					selectedClient = clientmanager.getClientByUsername(username);
					selectedClient.setBlocked(false);
					clientmanager.update(selectedClient);
					showClients();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnDesBloqClient.setBounds(474, 600, 156, 23);
		add(btnDesBloqClient);
		
		JButton btnSelectBloqClients = new JButton("Lista de clientes bloqueados");
		btnSelectBloqClients.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClientManager clientmanager = new ClientManager();
				 try {
					 tableClients.removeAll();
						modelClients.setRowCount(0);
					ArrayList<Client> allClients=(ArrayList<Client>) clientmanager.doSelectAll();
					for (int i = 0; i < allClients.size(); i++) {
						Client client = allClients.get(i);
						if(client.isBlocked()==true) {
						String nombre= client.getName();
						String apellido=client.getLastName();
						String genero=client.getGender();
						String dNI =client.getPersonalId();
						String username=client.getUsername();
						String contrasena=client.getPasswd();
						Boolean bloqueado=client.isBlocked();
						modelClients.addRow(new String[] { nombre, apellido, genero, dNI, username, contrasena,bloqueado.toString() });
						}
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
		btnSelectBloqClients.setBounds(423, 123, 207, 23);
		add(btnSelectBloqClients);
		
	}
	private void showClients() {
		ClientManager clientmanager = new ClientManager();
		try {
			tableClients.removeAll();
			modelClients.setRowCount(0);
			 ArrayList<Client> allClients=(ArrayList<Client>) clientmanager.doSelectAll();
			for (int i = 0; i < allClients.size(); i++) {
				Client client = allClients.get(i);
				String nombre= client.getName();
				String apellido=client.getLastName();
				String genero=client.getGender();
				String dNI =client.getPersonalId();
				String username=client.getUsername();
				String contrasena=client.getPasswd();
				Boolean bloqueado=client.isBlocked();
				modelClients.addRow(new String[] { nombre, apellido, genero, dNI, username, contrasena,bloqueado.toString() });
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	
	
	
}
