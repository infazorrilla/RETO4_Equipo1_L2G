package soundbridge.view.panels;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import soundbridge.controller.Controller;

import soundbridge.database.pojos.Client;
import soundbridge.database.pojos.Employee;
import soundbridge.utils.WindowUtils;
import soundbridge.view.factory.PanelFactory;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class ManageClients extends JPanel {

	private static final long serialVersionUID = -6622744341750440365L;
	private JTable tableClients;
	private DefaultTableModel modelClients = null;
	private Controller controller = null;
	public ManageClients(JFrame frame, Employee employee) {

		initialize(frame, employee);

	}

	private void initialize(JFrame frame, Employee employee) {

		setBounds(0, 0, 1000, 672);
		setBackground(Color.black);
		setLayout(null);

		JLabel lblTitle = new JLabel("Gestión de clientes");
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Dialog", Font.PLAIN, 22));
		lblTitle.setBounds(380, 44, 250, 36);
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

		JButton btnSelectAllClients = new JButton("Lista de clientes");
		btnSelectAllClients.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showClients();
			}

		});
		btnSelectAllClients.setBounds(139, 123, 156, 23);
		add(btnSelectAllClients);

		JScrollPane scrollPaneClients = new JScrollPane();
		scrollPaneClients.setBounds(44, 179, 904, 383);
		add(scrollPaneClients);
		scrollPaneClients.setOpaque(false);
		scrollPaneClients.getViewport().setOpaque(false);
		scrollPaneClients.setBorder(BorderFactory.createEmptyBorder());

		scrollPaneClients.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
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


		tableClients = new JTable();
		tableClients.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableClients.setDefaultEditor(Object.class, null);
		scrollPaneClients.setViewportView(tableClients);
		tableClients.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		Object[] columnsManagesClients = { "Nombre", "Apellido", "Género", "DNI", "Username", "Contraseña",
				"Bloqueado" };
		
		tableClients.setShowGrid(false);
		tableClients.setBackground(Color.black);
		tableClients.setForeground(Color.white);
		tableClients.setSelectionForeground(new Color(244, 135, 244));
		tableClients.setRowHeight(35);
		tableClients.setSelectionBackground(Color.black);
		tableClients.setBorder(null);
		tableClients.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {

			private static final long serialVersionUID = 5651737319882097189L;

			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				if (isSelected || !isSelected) {
					hasFocus = false;
				}
				return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
			}
		});
		tableClients.getTableHeader().setBackground(Color.black);
		tableClients.getTableHeader().setPreferredSize(new Dimension(scrollPaneClients.getWidth(), 50));
		tableClients.getTableHeader().setReorderingAllowed(false);
		tableClients.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		TableCellRenderer renderer = tableClients.getTableHeader().getDefaultRenderer();
		tableClients.getTableHeader().setDefaultRenderer(new TableCellRenderer() {

			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				JLabel lbl = (JLabel) renderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row,
						column);
				lbl.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
				lbl.setHorizontalAlignment(SwingConstants.LEFT);
				lbl.setForeground(Color.white);
				lbl.setFont(new Font("Lucida Grande", Font.BOLD, 18));
				return lbl;
			}
		});

		modelClients = new DefaultTableModel();
		modelClients.setColumnIdentifiers(columnsManagesClients);
		tableClients.setModel(modelClients);

		JButton btnBloqClient = new JButton("Bloquear cliente");
		btnBloqClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				blockUser();

			}

		});
		btnBloqClient.setBounds(139, 600, 143, 23);
		add(btnBloqClient);

		JButton btnDesBloqClient = new JButton("Desbloquear cliente");
		btnDesBloqClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (null == controller)
					controller = new Controller();
				
				Client client = new Client();
				
				Client selectedClient = null;
				try {
					String username = (String) modelClients.getValueAt(tableClients.getSelectedRow(), 4);
					client.setUsername(username);
					selectedClient = controller.getClientByUsername(username);
					selectedClient.setBlocked(false);
					controller.updateClient(selectedClient);
					showClients();
				} catch (Exception e1) {
					WindowUtils.errorPane("No se ha seleccionado ningún cliente", "Error general");

				}

			}
		});
		btnDesBloqClient.setBounds(474, 600, 156, 23);
		add(btnDesBloqClient);

		JButton btnSelectBloqClients = new JButton("Lista de clientes bloqueados");
		btnSelectBloqClients.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (null == controller)
					controller = new Controller();
				try {
					tableClients.removeAll();
					modelClients.setRowCount(0);
					ArrayList<Client> allClients = (ArrayList<Client>) controller.getAllClients();
					for (int i = 0; i < allClients.size(); i++) {
						Client client = allClients.get(i);
						if (client.isBlocked() == true) {
							String nombre = client.getName();
							String apellido = client.getLastName();
							String genero = client.getGender();
							String dNI = client.getPersonalId();
							String username = client.getUsername();
							String contrasena = client.getPasswd();
							Boolean bloqueado = client.isBlocked();
							modelClients.addRow(new String[] { nombre, apellido, genero, dNI, username, contrasena,
									bloqueado.toString() });
						}
					}
				} catch (SQLException e1) {
					WindowUtils.errorPane("No se ha seleccionado ningún cliente", "Error en la base de datos");
					e1.printStackTrace();
				} catch (Exception e1) {
					WindowUtils.errorPane("No se ha seleccionado ningún cliente", "Error general");
					e1.printStackTrace();
				}
			}
		});
		btnSelectBloqClients.setBounds(423, 123, 207, 23);
		add(btnSelectBloqClients);

		WindowUtils.addImage(panelBackIcon, lblBackIcon, "img/icon/arrow.png");

	}

	private void showClients() {
		if (null == controller)
			controller = new Controller();
		try {
			tableClients.removeAll();
			modelClients.setRowCount(0);
			ArrayList<Client> allClients = (ArrayList<Client>) controller.getAllClients();
			for (int i = 0; i < allClients.size(); i++) {
				Client client = allClients.get(i);
				String nombre = client.getName();
				String apellido = client.getLastName();
				String genero = client.getGender();
				String dNI = client.getPersonalId();
				String username = client.getUsername();
				String contrasena = client.getPasswd();
				Boolean bloqueado = client.isBlocked();
				modelClients.addRow(
						new String[] { nombre, apellido, genero, dNI, username, contrasena, bloqueado.toString() });
			}
		} catch (SQLException e1) {
			WindowUtils.errorPane("No se ha seleccionado ningún cliente", "Error en la base de datos");
			e1.printStackTrace();
		} catch (Exception e1) {
			WindowUtils.errorPane("No se ha seleccionado ningún cliente", "Error general");
			e1.printStackTrace();
		}

	}

	private void goBack(JFrame frame, Employee employee) {
		frame.getContentPane().removeAll();
		frame.getContentPane()
				.add(PanelFactory.getJPanel(PanelFactory.EMPLOYEE_MENU, frame, null, employee, null, null, null, null));
		frame.revalidate();
		frame.repaint();
	}

	private void blockUser() {
		
		if (null == controller)
			controller = new Controller();
		Client client = new Client();
		
		
		try {
			String username = (String) modelClients.getValueAt(tableClients.getSelectedRow(), 4);
			client.setUsername(username);
			Client selectedClient = controller.getClientByUsername(username);
			selectedClient.setBlocked(true);
			controller.updateClient(selectedClient);
			showClients();
		} catch (SQLException e1) {
			WindowUtils.errorPane("No se ha seleccionado ningún cliente", "Error en la base de datos");
			
		} catch (Exception e1) {
			WindowUtils.errorPane("No se ha seleccionado ningún cliente", "Error general");
			
		}

	}

}
