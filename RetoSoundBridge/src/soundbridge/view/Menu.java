package soundbridge.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import soundbridge.controller.Controller;

import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import soundbridge.view.components.TextPrompt;

public class Menu {

	public JFrame frame;
	private JTextField textFieldUserLogIn;
	private JPasswordField passwordFieldLogIn;
	private JPanel panelMainMenu;
	private JPanel panelLogin;
	private Controller controller = null;

	/**
	 * Create the application.
	 */
	public Menu() {
		controller = new Controller();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);

		createLogin();
		createMainMenu();
		
		panelMainMenu.setVisible(false);
	}
	
	private void createLogin() {
		panelLogin = new JPanel();
		panelLogin.setBounds(0, 0, 1000, 672);
		frame.getContentPane().add(panelLogin);
		panelLogin.setLayout(null);
		
		textFieldUserLogIn = new JTextField();
		textFieldUserLogIn.setBounds(800, 132, 121, 32);
		panelLogin.add(textFieldUserLogIn);
		textFieldUserLogIn.setColumns(10);

		passwordFieldLogIn = new JPasswordField();
		passwordFieldLogIn.setBounds(800, 195, 121, 32);
		panelLogin.add(passwordFieldLogIn);
		
		TextPrompt placeholderUser = new TextPrompt("usuario", textFieldUserLogIn);
		placeholderUser.changeAlpha(0.8f);
		placeholderUser.changeStyle(Font.ITALIC);
		placeholderUser.setHorizontalAlignment(SwingConstants.CENTER);
		
		TextPrompt placeholderPasswd = new TextPrompt("contraseña", passwordFieldLogIn);
		
		JButton botonAcceptLogIn = new JButton("Iniciar sesión");
		botonAcceptLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				controller.checkLogin(textFieldUserLogIn, passwordFieldLogIn);
				
			}
		});
		botonAcceptLogIn.setBounds(800, 257, 121, 32);
		panelLogin.add(botonAcceptLogIn);
		placeholderPasswd.changeAlpha(0.8f);
		placeholderPasswd.changeStyle(Font.ITALIC);
		placeholderPasswd.setHorizontalAlignment(SwingConstants.CENTER);
		botonAcceptLogIn.setBackground(Color.orange);

	}
	
	private void createMainMenu() {
		panelMainMenu = new JPanel();
		panelMainMenu.setBounds(0, 0, 1000, 672);
		frame.getContentPane().add(panelMainMenu);
		panelMainMenu.setLayout(null);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(58, 26, 549, 27);
		panelMainMenu.add(textArea);
		
		JLabel lblPlaylists = new JLabel("Mis listas de reproducción:");
		lblPlaylists.setBounds(58, 90, 253, 27);
		panelMainMenu.add(lblPlaylists);
		
		JPanel panelTop20 = new JPanel();
		panelTop20.setBounds(58, 149, 114, 110);
		panelMainMenu.add(panelTop20);
		
		JPanel panelFavourites = new JPanel();
		panelFavourites.setBounds(209, 149, 114, 110);
		panelMainMenu.add(panelFavourites);
		
		JPanel panelGrid = new JPanel();
		panelGrid.setBounds(58, 331, 885, 110);
		panelMainMenu.add(panelGrid);
		panelGrid.setLayout(new GridLayout(0, 6, 37, 0));
	}
}
