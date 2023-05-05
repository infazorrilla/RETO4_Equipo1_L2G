package soundbridge.view.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import soundbridge.controller.Controller;
import soundbridge.database.pojos.Client;
import soundbridge.database.pojos.Employee;
import soundbridge.utils.WindowUtils;
import soundbridge.view.components.TextPrompt;
import soundbridge.view.factory.PanelFactory;

public class Login extends JPanel {

	private static final long serialVersionUID = -3238378133872295962L;
	private Controller controller;
	private Client client;
	private Employee employee;
	private JLabel lblBackground;

	public Login(JFrame frame) {
		initialize(frame);

		WindowUtils.addGif(lblBackground, "img/panel/new_smoke.gif");

	}

	private void initialize(JFrame frame) {

		setBounds(0, 0, 1000, 672);
		setLayout(null);
		setBackground(Color.black);

		JTextField textFieldUserLogIn = new JTextField();
		textFieldUserLogIn.setBounds(700, 200, 200, 50);
		add(textFieldUserLogIn);
		textFieldUserLogIn.setColumns(10);
		textFieldUserLogIn.setForeground(Color.white);
		textFieldUserLogIn.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		textFieldUserLogIn.setBorder(new LineBorder(Color.WHITE, 2));
		textFieldUserLogIn.setCaretColor(Color.WHITE);
		textFieldUserLogIn.setOpaque(false);
		textFieldUserLogIn.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldUserLogIn.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				textFieldUserLogIn.setBorder(new LineBorder(new Color(244, 135, 244), 2));
			}

			@Override
			public void focusLost(FocusEvent e) {
				textFieldUserLogIn.setBorder(new LineBorder(Color.WHITE, 2));
			}
		});

		JPasswordField passwordFieldLogIn = new JPasswordField();
		passwordFieldLogIn.setBounds(700, 280, 200, 50);
		passwordFieldLogIn.setForeground(Color.white);
		passwordFieldLogIn.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		passwordFieldLogIn.setBorder(new LineBorder(Color.WHITE, 2));
		passwordFieldLogIn.setCaretColor(Color.WHITE);
		add(passwordFieldLogIn);
		passwordFieldLogIn.setOpaque(false);
		passwordFieldLogIn.setHorizontalAlignment(SwingConstants.CENTER);
		passwordFieldLogIn.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				passwordFieldLogIn.setBorder(new LineBorder(new Color(244, 135, 244), 2));
			}

			@Override
			public void focusLost(FocusEvent e) {
				passwordFieldLogIn.setBorder(new LineBorder(Color.WHITE, 2));
			}
		});
		passwordFieldLogIn.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent evt) {
				if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
					doLogIn(frame, textFieldUserLogIn, passwordFieldLogIn);
				}
			}
		});

		TextPrompt placeholderUser = new TextPrompt("Usuario", textFieldUserLogIn);
		placeholderUser.changeAlpha(0.8f);
		placeholderUser.changeStyle(Font.ITALIC);
		placeholderUser.setHorizontalAlignment(SwingConstants.CENTER);

		TextPrompt placeholderPasswd = new TextPrompt("Contraseña", passwordFieldLogIn);
		placeholderPasswd.changeAlpha(0.8f);
		placeholderPasswd.changeStyle(Font.ITALIC);
		placeholderPasswd.setHorizontalAlignment(SwingConstants.CENTER);

		JButton btnAcceptLogIn = new JButton("Iniciar sesión");
		btnAcceptLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doLogIn(frame, textFieldUserLogIn, passwordFieldLogIn);
			}
		});
		btnAcceptLogIn.setBounds(700, 360, 200, 50);
		add(btnAcceptLogIn);
		btnAcceptLogIn.setForeground(Color.white);
		btnAcceptLogIn.setFont(new Font("Lucida Grande", Font.BOLD, 17));
		btnAcceptLogIn.setBackground(new Color(244, 135, 244, 20));
		btnAcceptLogIn.setBorder(new LineBorder(new Color(244, 135, 244), 2));
		btnAcceptLogIn.setOpaque(false);
		btnAcceptLogIn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		JButton btnRegister = new JButton("REGISTRARME");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goToSignUp(frame);
			}

		});
		btnRegister.setBounds(700, 450, 200, 50);
		add(btnRegister);
		btnRegister.setForeground(Color.black);
		btnRegister.setFont(new Font("Lucida Grande", Font.BOLD, 17));
		btnRegister.setBackground(new Color(244, 135, 244));
		btnRegister.setBorder(new LineBorder(new Color(244, 135, 244), 2));
		btnRegister.setOpaque(true);
		btnRegister.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		JPanel panelLogo = new JPanel();
		panelLogo.setBounds(0, 0, 672, 672);
		add(panelLogo);
		panelLogo.setLayout(new BorderLayout(0, 0));
		panelLogo.setOpaque(false);

		JLabel lblLogo = new JLabel("");
		panelLogo.add(lblLogo, BorderLayout.CENTER);

		JPanel panelBackground = new JPanel();
		panelBackground.setBounds(0, 0, 1000, 672);
		add(panelBackground);
		panelBackground.setLayout(new BorderLayout(0, 0));
		panelBackground.setOpaque(false);

		lblBackground = new JLabel("");
		panelBackground.add(lblBackground, BorderLayout.CENTER);

		WindowUtils.addImage(panelLogo, lblLogo, "img/panel/logo_login.png");

	}

	private void logInClient(JFrame frame, JTextField textFieldUserLogIn, JPasswordField passwordFieldLogIn) {
		String username = textFieldUserLogIn.getText();
		if (null == controller)
			controller = new Controller();

		try {
			setClient(controller.returnLoggedClient(username));
			if (client.isBlocked() == true) {
				WindowUtils.messagePaneWithIcon("Su usuario está bloqueado.", "Usuario Bloqueado", "img/icon/lock.png");
				textFieldUserLogIn.setText("");
				passwordFieldLogIn.setText("");
				textFieldUserLogIn.requestFocus();
			} else {
				WindowUtils.confirmationPane("¡Bienvenid@ " + username + "!", "Cliente de SoundBridge");
				textFieldUserLogIn.setText("");
				passwordFieldLogIn.setText("");

				frame.getContentPane().removeAll();
				frame.getContentPane()
						.add(PanelFactory.getJPanel(PanelFactory.LIBRARY, frame, client, null, null, null, null));
				frame.revalidate();
				frame.repaint();
			}

		} catch (SQLException e) {
			WindowUtils.errorPane("Error en el login.", "Error en la base de datos");
			System.out.println(e);
		} catch (Exception e) {
			WindowUtils.errorPane("Error en el login.", "Error general");
			System.out.println(e);
		}
	}

	private void logInEmployee(JFrame frame, JTextField textFieldUserLogIn, JPasswordField passwordFieldLogIn) {
		String username = textFieldUserLogIn.getText();
		if (null == controller)
			controller = new Controller();

		try {
			setEmployee(controller.returnLoggedEmployee(username));
			WindowUtils.confirmationPane("¡Bienvenid@ " + username + "!", "Cliente de SoundBridge");
			textFieldUserLogIn.setText("");
			passwordFieldLogIn.setText("");
			
			frame.getContentPane().removeAll();
			frame.getContentPane()
					.add(PanelFactory.getJPanel(PanelFactory.EMPLOYEE, frame, null, null, null, null, null));
			frame.revalidate();
			frame.repaint();

		} catch (SQLException e) {
			WindowUtils.errorPane("Error en el login.", "Error en la base de datos");
			System.out.println(e);
		} catch (Exception e) {
			WindowUtils.errorPane("Error en el login.", "Error general");
			System.out.println(e);
		}
	}

	private void doLogIn(JFrame frame, JTextField textFieldUserLogIn, JPasswordField passwordFieldLogIn) {
		if (null == controller)
			controller = new Controller();
		
		try {
			boolean isClient = controller.isClient(textFieldUserLogIn, passwordFieldLogIn);
			boolean isEmployee = controller.isEmployee(textFieldUserLogIn, passwordFieldLogIn);
			
			if (isClient) {
				logInClient(frame, textFieldUserLogIn, passwordFieldLogIn);
			} else if (isEmployee) {
				logInEmployee(frame, textFieldUserLogIn, passwordFieldLogIn);
			} else {
				WindowUtils.errorPane("Login incorrecto.", "Error");
				textFieldUserLogIn.setText("");
				passwordFieldLogIn.setText("");
				textFieldUserLogIn.requestFocus();
			}
		} catch (SQLException e) {
			WindowUtils.errorPane("Error en el login.", "Error en la base de datos");
			System.out.println(e);
		} catch (Exception e) {
			WindowUtils.errorPane("Error en el login.", "Error general");
			System.out.println(e);
		}
	}

	private void goToSignUp(JFrame frame) {
		frame.getContentPane().removeAll();
		frame.getContentPane().add(PanelFactory.getJPanel(PanelFactory.SIGNUP, frame, null, null, null, null, null));
		frame.revalidate();
		frame.repaint();
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
}