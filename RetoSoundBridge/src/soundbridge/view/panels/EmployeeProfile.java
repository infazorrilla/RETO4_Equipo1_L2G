package soundbridge.view.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import soundbridge.controller.Controller;
import soundbridge.database.pojos.Employee;
import soundbridge.utils.WindowUtils;
import soundbridge.view.factory.PanelFactory;
import javax.swing.JPasswordField;

public class EmployeeProfile extends JPanel {

	private static final long serialVersionUID = 9048482263295991631L;
	private Controller controller = null;
	
	public EmployeeProfile(JFrame frame, Employee employee) {
		initialize(frame, employee);
	}
	
	private void initialize(JFrame frame, Employee employee) {
		setBounds(0, 0, 1000, 672);
		setLayout(null);
		setBackground(Color.black);
		
		JPanel panelProfileIcon = new JPanel();
		panelProfileIcon.setBounds(30, 30, 150, 150);
		add(panelProfileIcon);
		panelProfileIcon.setLayout(new BorderLayout(0, 0));
		panelProfileIcon.setOpaque(false);

		JLabel lblProfileIcon = new JLabel("");
		panelProfileIcon.add(lblProfileIcon, BorderLayout.CENTER);

		JPanel panelHomeIcon = new JPanel();
		panelHomeIcon.setBounds(900, 45, 50, 50);
		add(panelHomeIcon);
		panelHomeIcon.setLayout(new BorderLayout(0, 0));
		panelHomeIcon.setOpaque(false);
		panelHomeIcon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.getContentPane().removeAll();
				frame.getContentPane()
						.add(PanelFactory.getJPanel(PanelFactory.EMPLOYEE_MENU, frame, null, employee, null, null, null, null, null));
				frame.revalidate();
				frame.repaint();
			}
		});
		panelHomeIcon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelHomeIcon.setToolTipText("Volver a mi biblioteca.");

		JLabel lblHomeIcon = new JLabel("");
		panelHomeIcon.add(lblHomeIcon, BorderLayout.CENTER);

		JLabel lblNameOfClient = new JLabel(employee.getName() + " " + employee.getLastName());
		lblNameOfClient.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblNameOfClient.setBounds(200, 50, 301, 27);
		lblNameOfClient.setForeground(Color.white);
		add(lblNameOfClient);

		JLabel lblUsername = new JLabel("@" + employee.getUsername());
		lblUsername.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		lblUsername.setBounds(200, 90, 301, 27);
		lblUsername.setForeground(new Color(244, 135, 244));
		add(lblUsername);

		JButton btnLogOut = new JButton("Cerrar Sesión");
		btnLogOut.setHorizontalAlignment(SwingConstants.LEFT);
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logOut(frame);
			}
		});
		btnLogOut.setBounds(200, 130, 113, 30);
		add(btnLogOut);
		btnLogOut.setForeground(Color.WHITE);
		btnLogOut.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 15));
		btnLogOut.setBorder(new LineBorder(Color.black, 0));
		btnLogOut.setOpaque(false);
		btnLogOut.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		JLabel lblInfo = new JLabel("Datos de la cuenta:");
		lblInfo.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblInfo.setBounds(446, 200, 301, 27);
		lblInfo.setForeground(Color.white);
		add(lblInfo);

		JLabel lblDni = new JLabel("DNI:");
		lblDni.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		lblDni.setBounds(446, 250, 120, 20);
		lblDni.setForeground(new Color(244, 135, 244));
		lblDni.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblDni);

		JLabel lblDniValue = new JLabel(employee.getPersonalId());
		lblDniValue.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblDniValue.setBounds(576, 250, 301, 20);
		lblDniValue.setForeground(Color.white);
		add(lblDniValue);

		JLabel lblGender = new JLabel("Género:");
		lblGender.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		lblGender.setBounds(446, 300, 120, 20);
		lblGender.setForeground(new Color(244, 135, 244));
		lblGender.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblGender);

		JLabel lblGenderValue = new JLabel(employee.getGender());
		lblGenderValue.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblGenderValue.setBounds(576, 300, 301, 20);
		lblGenderValue.setForeground(Color.white);
		add(lblGenderValue);

		JLabel lblNationality = new JLabel("Nacionalidad:");
		lblNationality.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		lblNationality.setBounds(446, 350, 120, 20);
		lblNationality.setForeground(new Color(244, 135, 244));
		lblNationality.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblNationality);

		JLabel lblNationalityValue = new JLabel(employee.getNationality());
		lblNationalityValue.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblNationalityValue.setBounds(576, 350, 301, 20);
		lblNationalityValue.setForeground(Color.white);
		add(lblNationalityValue);

		JLabel lblBirthDate = new JLabel("Nacimiento:");
		lblBirthDate.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		lblBirthDate.setBounds(446, 400, 120, 20);
		lblBirthDate.setForeground(new Color(244, 135, 244));
		lblBirthDate.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblBirthDate);

		JLabel lblBirthDateValue = new JLabel((new SimpleDateFormat("dd-MM-yyyy")).format(employee.getBirthDate()));
		lblBirthDateValue.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblBirthDateValue.setBounds(576, 400, 301, 20);
		lblBirthDateValue.setForeground(Color.white);
		add(lblBirthDateValue);

		JLabel lblAddress = new JLabel("Dirección:");
		lblAddress.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		lblAddress.setBounds(446, 450, 120, 20);
		lblAddress.setForeground(new Color(244, 135, 244));
		lblAddress.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblAddress);

		JLabel lblAddressValue = new JLabel(employee.getAddress());
		lblAddressValue.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblAddressValue.setBounds(576, 450, 400, 20);
		lblAddressValue.setForeground(Color.white);
		add(lblAddressValue);

		JLabel lblTelephone = new JLabel("Teléfono:");
		lblTelephone.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		lblTelephone.setBounds(446, 500, 120, 20);
		lblTelephone.setForeground(new Color(244, 135, 244));
		lblTelephone.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblTelephone);

		JLabel lblTelephoneValue = new JLabel(employee.getTelephone());
		lblTelephoneValue.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblTelephoneValue.setBounds(576, 500, 301, 20);
		lblTelephoneValue.setForeground(Color.white);
		add(lblTelephoneValue);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		lblEmail.setBounds(446, 550, 120, 20);
		lblEmail.setForeground(new Color(244, 135, 244));
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblEmail);

		JLabel lblEmailValue = new JLabel(employee.getEmail());
		lblEmailValue.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblEmailValue.setBounds(576, 550, 301, 20);
		lblEmailValue.setForeground(Color.white);
		add(lblEmailValue);
		
		JLabel lblPasswdTitle = new JLabel("Cambio de contraseña:");
		lblPasswdTitle.setForeground(Color.WHITE);
		lblPasswdTitle.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblPasswdTitle.setBounds(51, 200, 301, 27);
		add(lblPasswdTitle);
		
		JPasswordField passwordField1 = new JPasswordField();
		passwordField1.setOpaque(false);
		passwordField1.setHorizontalAlignment(SwingConstants.CENTER);
		passwordField1.setForeground(Color.WHITE);
		passwordField1.setFont(new Font("Dialog", Font.PLAIN, 15));
		passwordField1.setColumns(10);
		passwordField1.setCaretColor(Color.WHITE);
		passwordField1.setBorder(new LineBorder(Color.WHITE, 2));
		passwordField1.setBounds(51, 250, 200, 35);
		add(passwordField1);
		passwordField1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				validatePasswdField(passwordField1);
			}
		});
		
		JPasswordField passwordField2 = new JPasswordField();
		passwordField2.setOpaque(false);
		passwordField2.setHorizontalAlignment(SwingConstants.CENTER);
		passwordField2.setForeground(Color.WHITE);
		passwordField2.setFont(new Font("Dialog", Font.PLAIN, 15));
		passwordField2.setColumns(10);
		passwordField2.setCaretColor(Color.WHITE);
		passwordField2.setBorder(new LineBorder(Color.WHITE, 2));
		passwordField2.setBounds(51, 310, 200, 35);
		add(passwordField2);
		passwordField2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				validatePasswdField(passwordField2);
			}
		});
		
		
		JButton btnChangePasswd = new JButton("Confirmar");
		btnChangePasswd.setOpaque(true);
		btnChangePasswd.setForeground(Color.BLACK);
		btnChangePasswd.setFont(new Font("Dialog", Font.BOLD, 17));
		btnChangePasswd.setBorder(new LineBorder(new Color(244, 135, 244), 2));
		btnChangePasswd.setBackground(new Color(244, 135, 244));
		btnChangePasswd.setBounds(51, 370, 200, 40);
		add(btnChangePasswd);
		btnChangePasswd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doChangePasswd(employee, passwordField1, passwordField2);
			}
		});
		
		WindowUtils.addImage(panelProfileIcon, lblProfileIcon, "img/icon/profile.png");
		WindowUtils.addImage(panelHomeIcon, lblHomeIcon, "img/icon/home.png");
	}
	
	private void logOut(JFrame frame) {
		frame.getContentPane().removeAll();
		frame.getContentPane()
				.add(PanelFactory.getJPanel(PanelFactory.LOGIN, frame, null, null, null, null, null, null, null));
		frame.revalidate();
		frame.repaint();
	}
	
	private void doChangePasswd(Employee employee, JPasswordField passwd1, JPasswordField passwd2) {
		if (null == controller) {
			controller = new Controller();
		}

		if (isPasswdOk(passwd1, passwd2)) {
			try {
				controller.changePasswdEmployee(employee, passwd1, passwd2);
				WindowUtils.confirmationPane("Su contraseña se ha cambiado.", "Confirmación");
			} catch (SQLException e) {
				WindowUtils.errorPane("No se ha podido cambiar la contraseña.", "Error en la base de datos");
			} catch (Exception e) {
				WindowUtils.errorPane("No se ha podido cambiar la contraseña.", "Error general");
			}
		} else {
			WindowUtils.errorPane(
					"<html>Sus contraseñas no coinciden o tienen<br>una longitud menor de 10 caracteres.</html>",
					"Error");
		}
		
		passwd1.setBorder(new LineBorder(Color.WHITE, 2));
		passwd2.setBorder(new LineBorder(Color.WHITE, 2));

		passwd1.setText("");
		passwd2.setText("");
	}
	
	private void validatePasswdField(JPasswordField passwdField) {
		if (controller == null)
			controller = new Controller();

		if (controller.isLengthCorrectInPasswdField(passwdField, 10))
			passwdField.setBorder(new LineBorder(new Color(0, 205, 20), 2));
		else
			passwdField.setBorder(new LineBorder(new Color(255, 40, 40), 2));
	}

	private boolean isPasswdOk(JPasswordField passwd1, JPasswordField passwd2) {
		boolean ret = false;
		String pass1 = String.valueOf(passwd1.getPassword());
		String pass2 = String.valueOf(passwd2.getPassword());

		if (controller == null)
			controller = new Controller();

		if (controller.isLengthCorrectInPasswdField(passwd1, 10) && controller.isLengthCorrectInPasswdField(passwd2, 10)
				&& pass1.equals(pass2)) {
			ret = true;
		}

		return ret;
	}
}
