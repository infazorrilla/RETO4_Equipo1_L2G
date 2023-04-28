package soundbridge.view.panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import soundbridge.controller.Controller;
import soundbridge.view.TextPrompt;
import soundbridge.view.factory.PanelFactory;

public class Login extends JPanel {
	
	private static final long serialVersionUID = -3238378133872295962L;
	private Controller controller;

	public Login(JFrame frame) {
		controller = new Controller();
		
		setBounds(0, 0, 1000, 672);
		setLayout(null);
		setBackground(Color.black);

		JTextField textFieldUserLogIn = new JTextField();
		textFieldUserLogIn.setBounds(800, 132, 121, 32);
		add(textFieldUserLogIn);
		textFieldUserLogIn.setColumns(10);

		JPasswordField passwordFieldLogIn = new JPasswordField();
		passwordFieldLogIn.setBounds(800, 195, 121, 32);
		add(passwordFieldLogIn);

		TextPrompt placeholderUser = new TextPrompt("usuario", textFieldUserLogIn);
		placeholderUser.changeAlpha(0.8f);
		placeholderUser.changeStyle(Font.ITALIC);
		placeholderUser.setHorizontalAlignment(SwingConstants.CENTER);

		TextPrompt placeholderPasswd = new TextPrompt("contraseña", passwordFieldLogIn);

		JButton botonAcceptLogIn = new JButton("Iniciar sesión");
		botonAcceptLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				controller.checkLogin(textFieldUserLogIn, passwordFieldLogIn);
				frame.getContentPane().removeAll();
				frame.getContentPane().add(PanelFactory.getJPanel(PanelFactory.LIBRARY, frame));
				frame.revalidate();
				frame.repaint();

			}
		});
		botonAcceptLogIn.setBounds(800, 257, 121, 32);
		add(botonAcceptLogIn);
		placeholderPasswd.changeAlpha(0.8f);
		placeholderPasswd.changeStyle(Font.ITALIC);
		placeholderPasswd.setHorizontalAlignment(SwingConstants.CENTER);
		botonAcceptLogIn.setBackground(Color.orange);
	
	}
}
