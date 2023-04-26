package soundbridge.controller;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import soundbridge.database.managers.ClientManager;
import soundbridge.database.managers.EmployeeManager;

public class Controller {
	public void checkLogin(JTextField textFieldUserLogIn, JTextField passwordFieldLogIn) {
		String username=textFieldUserLogIn.getText();
		String passwd=passwordFieldLogIn.getText();
		ClientManager clientManager = new ClientManager();
		EmployeeManager employeeManager = new EmployeeManager();
		
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
	}
	else {
		JFrame jFrame = new JFrame();
		JOptionPane.showMessageDialog(jFrame, "Incorrecto");
		textFieldUserLogIn.setText("");
		passwordFieldLogIn.setText("");
	}
	
	
}
}
