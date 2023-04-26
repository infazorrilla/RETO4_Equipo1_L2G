package soundbridge.controller;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import soundbridge.database.managers.ClientManager;

public class Controller {
	public void checkLoginCliente(JTextField textFieldUserLogIn, JTextField passwordFieldLogIn) {
		String personalId=textFieldUserLogIn.getText();
		String passwd=passwordFieldLogIn.getText();
		ClientManager clientManager = new ClientManager();
		
	boolean logInCorrecto = clientManager.askForClientUsingIdAndPasswd(personalId, passwd);
	if (!logInCorrecto) {
		JFrame jFrame = new JFrame();
		JOptionPane.showMessageDialog(jFrame, "Login incorrecto");
		textFieldUserLogIn.setText("");
		passwordFieldLogIn.setText("");
	} else {
		JFrame jFrame = new JFrame();
		JOptionPane.showMessageDialog(jFrame, "Tonto el que lo lea");
		textFieldUserLogIn.setText("");
		passwordFieldLogIn.setText("");
	}

}
}
