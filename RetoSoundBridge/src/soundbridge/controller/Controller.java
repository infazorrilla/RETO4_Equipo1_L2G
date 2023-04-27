package soundbridge.controller;


import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import soundbridge.database.managers.ClientManager;
import soundbridge.database.managers.EmployeeManager;

public class Controller {

	private ClientManager clientManager = null;
	private EmployeeManager employeeManager = null;
	
	public Controller() {
		clientManager = new ClientManager();
		employeeManager = new EmployeeManager();
	}

	public void checkLogin(JTextField textFieldUserLogIn, JTextField passwordFieldLogIn, JPanel panelMain, JPanel panelLogin) {
		String username = textFieldUserLogIn.getText();
		String passwd = passwordFieldLogIn.getText();

		boolean logInUser = clientManager.askForClientUsingIdAndPasswd(username, passwd);
		boolean logInAdmin = employeeManager.askForEmployeeUsingIdAndPasswd(username, passwd);

		if (!logInUser && logInAdmin) {
			JFrame jFrame = new JFrame();
			JOptionPane.showMessageDialog(jFrame, "Eres trabajador");
			textFieldUserLogIn.setText("");
			passwordFieldLogIn.setText("");
		} else if (logInUser && !logInAdmin) {
			JFrame jFrame = new JFrame();
			JOptionPane.showMessageDialog(jFrame, "Eres cliente");
			textFieldUserLogIn.setText("");
			passwordFieldLogIn.setText("");
			
			panelMain.setVisible(true);
			panelLogin.setVisible(false);
		} else {
			JFrame jFrame = new JFrame();
			JOptionPane.showMessageDialog(jFrame, "Incorrecto");
			textFieldUserLogIn.setText("");
			passwordFieldLogIn.setText("");
		}

	}

}
