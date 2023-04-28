package soundbridge.controller;




import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import soundbridge.database.managers.ClientManager;
import soundbridge.database.managers.EmployeeManager;
import soundbridge.database.pojos.Client;

public class Controller {

	private ClientManager clientManager = null;
	private EmployeeManager employeeManager = null;
	
	public Controller() {
		clientManager = new ClientManager();
		employeeManager = new EmployeeManager();
	}

	public void checkLogin(JTextField textFieldUserLogIn, JTextField passwordFieldLogIn) {
		String username = textFieldUserLogIn.getText();
		String passwd = passwordFieldLogIn.getText();
		Client client = new Client();
		

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
			client.setUsername(textFieldUserLogIn.getText());
			try {
				 client = clientManager.doSelectAllUsingUsername(username);
				System.out.println(client.toString());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		} else {
			JFrame jFrame = new JFrame();
			JOptionPane.showMessageDialog(jFrame, "Incorrecto");
			textFieldUserLogIn.setText("");
			passwordFieldLogIn.setText("");
		}

	}

}
