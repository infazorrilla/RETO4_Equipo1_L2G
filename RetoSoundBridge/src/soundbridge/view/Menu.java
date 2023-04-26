package soundbridge.view;

import java.awt.Color;
import java.awt.Font;


import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import soundbridge.controller.Controller;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
		textFieldUserLogIn.setBounds(800, 132, 121, 32);
		frame.getContentPane().add(textFieldUserLogIn);
		textFieldUserLogIn.setColumns(10);

		passwordFieldLogIn = new JPasswordField();
		passwordFieldLogIn.setBounds(800, 195, 121, 32);
		frame.getContentPane().add(passwordFieldLogIn);
		
		TextPrompt placeholderUser = new TextPrompt("usuario", textFieldUserLogIn);
		placeholderUser.changeAlpha(0.8f);
		placeholderUser.changeStyle(Font.ITALIC);
		placeholderUser.setHorizontalAlignment(SwingConstants.CENTER);
		
		TextPrompt placeholderPasswd = new TextPrompt("contraseña", passwordFieldLogIn);
		
		JButton botonAcceptLogIn = new JButton("Iniciar sesión");
		botonAcceptLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller controller = new Controller();
				
				controller.checkLoginCliente(textFieldUserLogIn, passwordFieldLogIn);
				
			}
		});
		botonAcceptLogIn.setBounds(800, 257, 121, 32);
		frame.getContentPane().add(botonAcceptLogIn);
		placeholderPasswd.changeAlpha(0.8f);
		placeholderPasswd.changeStyle(Font.ITALIC);
		placeholderPasswd.setHorizontalAlignment(SwingConstants.CENTER);
		botonAcceptLogIn.setBackground(Color.orange);

	}
}
