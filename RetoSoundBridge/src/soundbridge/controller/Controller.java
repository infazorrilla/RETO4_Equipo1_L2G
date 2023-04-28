package soundbridge.controller;

import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import soundbridge.database.managers.ClientManager;
import soundbridge.database.managers.EmployeeManager;
import soundbridge.database.pojos.Client;
import soundbridge.database.pojos.Employee;

public class Controller {

	private ClientManager clientManager = null;
	private EmployeeManager employeeManager = null;

	public Controller() {
		clientManager = new ClientManager();
		employeeManager = new EmployeeManager();
	}

	public void checkLogin(JTextField textFieldUserLogIn, JTextField passwordFieldLogIn) {
		String username = textFieldUserLogIn.getText();
		String typeOfUser = checkTypeOfUser(textFieldUserLogIn, passwordFieldLogIn);

		if (typeOfUser == null) {
			JOptionPane.showMessageDialog(null, "Login incorrecto.", "Error",
					JOptionPane.ERROR_MESSAGE);
			textFieldUserLogIn.setText("");
			passwordFieldLogIn.setText("");
		} else {
			if (typeOfUser.equals("employee")) {
				JOptionPane.showMessageDialog(null, "¡Bienvenid@ " + username + "!", "Empleado de SoundBridge",
						JOptionPane.INFORMATION_MESSAGE);
				textFieldUserLogIn.setText("");
				passwordFieldLogIn.setText("");
			} else if (typeOfUser.equals("client")) {
				JOptionPane.showMessageDialog(null, "¡Bienvenid@ " + username + "!", "Cliente de SoundBridge",
						JOptionPane.INFORMATION_MESSAGE);
				textFieldUserLogIn.setText("");
				passwordFieldLogIn.setText("");
			}
		}

	}

	public String checkTypeOfUser(JTextField textFieldUserLogIn, JTextField passwordFieldLogIn) {
		String ret = null;
		String username = textFieldUserLogIn.getText();
		String passwd = passwordFieldLogIn.getText();

		boolean logInUser = clientManager.askForClientUsingIdAndPasswd(username, passwd);
		boolean logInAdmin = employeeManager.askForEmployeeUsingIdAndPasswd(username, passwd);

		if (!logInUser && logInAdmin) {
			ret = "employee";
		} else if (logInUser && !logInAdmin) {
			ret = "client";
		}

		return ret;
	}

	public Client returnLoggedClient(String username) {
		Client client = null;

		try {
			client = clientManager.doSelectAllUsingUsername(username);
		} catch (SQLException sqle) {
			JOptionPane.showMessageDialog(null, sqle, "Error", JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
		}

		return client;
	}
	
	public Employee returnLoggedEmployee(String username) {
		Employee employee = null;

		try {
			employee = employeeManager.doSelectAllUsingUsername(username);
		} catch (SQLException sqle) {
			JOptionPane.showMessageDialog(null, sqle, "Error", JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
		}

		return employee;
	}

}
