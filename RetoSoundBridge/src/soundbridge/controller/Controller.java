package soundbridge.controller;

import java.sql.SQLException;

import javax.swing.JTextField;

import soundbridge.database.managers.ClientManager;
import soundbridge.database.managers.EmployeeManager;
import soundbridge.database.pojos.Client;
import soundbridge.database.pojos.Employee;
import soundbridge.utils.WindowUtils;

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
			WindowUtils.errorPane("Login incorrecto.", "Error");
			textFieldUserLogIn.setText("");
			passwordFieldLogIn.setText("");
			textFieldUserLogIn.requestFocus();
		} else {
			if (typeOfUser.equals("employee")) {
				WindowUtils.confirmationPane("¡Bienvenid@ " + username + "!", "Empleado de SoundBridge");
				textFieldUserLogIn.setText("");
				passwordFieldLogIn.setText("");
			} else if (typeOfUser.equals("client")) {
				WindowUtils.confirmationPane("¡Bienvenid@ " + username + "!", "Cliente de SoundBridge");
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
			client = clientManager.getClientByUsername(username);
		} catch (SQLException sqle) {
			WindowUtils.errorPane("No se ha encontrado el usuario.", "Error Cliente");
		} catch (Exception e) {
			WindowUtils.errorPane("No se ha encontrado el usuario.", "Error Cliente");
		}

		return client;
	}
	
	public Employee returnLoggedEmployee(String username) {
		Employee employee = null;

		try {
			employee = employeeManager.doSelectAllUsingUsername(username);
		} catch (SQLException sqle) {
			WindowUtils.errorPane("No se ha encontrado el usuario.", "Error Empleado");
		} catch (Exception e) {
			WindowUtils.errorPane("No se ha encontrado el usuario.", "Error Empleado");
		}

		return employee;
	}

}
