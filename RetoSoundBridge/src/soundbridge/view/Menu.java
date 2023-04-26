package soundbridge.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPasswordField;

public class Menu {

	public JFrame frame;
	private JTextField textFieldUserLogIn;
	private JPasswordField passwordFieldLogIn;

	/**
	 * Create the application.
	 */
	public Menu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		
		textFieldUserLogIn = new JTextField();
		textFieldUserLogIn.setBounds(45, 80, 86, 20);
		frame.getContentPane().add(textFieldUserLogIn);
		textFieldUserLogIn.setColumns(10);
		
		JLabel LabelUserLogIn = new JLabel("USUARIO");
		LabelUserLogIn.setBounds(45, 50, 70, 20);
		frame.getContentPane().add(LabelUserLogIn);
		
		JLabel LabelPasswdLogIn = new JLabel("PASSWORD");
		LabelPasswdLogIn.setBounds(45, 127, 70, 20);
		frame.getContentPane().add(LabelPasswdLogIn);
		
		passwordFieldLogIn = new JPasswordField();
		passwordFieldLogIn.setBounds(45, 158, 86, 20);
		frame.getContentPane().add(passwordFieldLogIn);
	}
}
