package soundbridge.controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import soundbridge.database.managers.ArtGroupManager;
import soundbridge.database.managers.ArtistManager;
import soundbridge.database.managers.ClientManager;
import soundbridge.database.managers.ClientPManager;
import soundbridge.database.managers.ClientPPManager;
import soundbridge.database.managers.EmployeeManager;
import soundbridge.database.pojos.ArtGroup;
import soundbridge.database.pojos.Artist;
import soundbridge.database.pojos.Client;
import soundbridge.database.pojos.ClientP;
import soundbridge.database.pojos.ClientPP;
import soundbridge.database.pojos.Employee;
import soundbridge.utils.WindowUtils;
import soundbridge.view.components.AutoCompleteTextField;

public class Controller {

	private ClientManager clientManager = null;
	private EmployeeManager employeeManager = null;

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
		
		if (null == clientManager) {
			clientManager = new ClientManager();
		}
		
		if (null == employeeManager) {
			employeeManager = new EmployeeManager();
		}

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
		
		if (null == clientManager) {
			clientManager = new ClientManager();
		}

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
		
		if (null == employeeManager) {
			employeeManager = new EmployeeManager();
		}

		try {
			employee = employeeManager.doSelectAllUsingUsername(username);
		} catch (SQLException sqle) {
			WindowUtils.errorPane("No se ha encontrado el usuario.", "Error Empleado");
		} catch (Exception e) {
			WindowUtils.errorPane("No se ha encontrado el usuario.", "Error Empleado");
		}

		return employee;
	}

	public void deleteAccount(Client client, int reply) throws SQLException, Exception {
		if (null == clientManager) {
			clientManager = new ClientManager();
		}
		
		if (reply == 0) {

			clientManager.delete(client);
		}
	}

	public void addPossibilitiesToSearchBar(AutoCompleteTextField text) throws SQLException, Exception {
		ArtistManager artistManager = new ArtistManager();
		ArtGroupManager artGroupManager = new ArtGroupManager();

		ArrayList<Artist> artists = null;
		ArrayList<ArtGroup> groups = null;

		artists = (ArrayList<Artist>) artistManager.doSelectAll();
		if (artists != null) {
			for (Artist artist : artists) {
				if (artist.getArtGroup() == null) {
					text.addPossibility(artist.getName());
				}
			}
		}

		groups = (ArrayList<ArtGroup>) artGroupManager.doSelectAll();
		if (groups != null) {
			for (ArtGroup group : groups) {
				text.addPossibility(group.getName());
			}
		}

	}

	public void updateClient(Client client, JComboBox<String> combo, JTextField textNation, JTextField textBirth,
			JTextArea textAddr, JTextField textPhone, JTextField textEmail, JTextField textBank)
			throws ParseException, SQLException, Exception {
		client.setGender(combo.getSelectedItem().toString());
		client.setNationality(textNation.getText());

		String birthDate = textBirth.getText();
		Date date = new SimpleDateFormat("dd/MM/yyyy").parse(birthDate);

		client.setBirthDate(date);
		client.setAddress(textAddr.getText());
		client.setTelephone(textPhone.getText());
		client.setEmail(textEmail.getText());

		if (null == clientManager) {
			clientManager = new ClientManager();
		}
		
		ClientPManager clientPManager = new ClientPManager();
		ClientPPManager clientPPManager = new ClientPPManager();

		clientManager.update(client);

		if (client instanceof ClientP) {
			((ClientP) client).setBankAccount(textBank.getText());
			clientPManager.update((ClientP) client);
		} else if (client instanceof ClientPP) {
			((ClientPP) client).setBankAccount(textBank.getText());
			clientPPManager.update((ClientPP) client);
		}
	}

	public void changePasswd(Client client, JPasswordField passwd1, JPasswordField passwd2)
			throws SQLException, Exception {
		if (null == clientManager) {
			clientManager = new ClientManager();
		}
		
		client.setPasswd(String.valueOf(passwd1.getPassword()));

		clientManager.update(client);
	}

}
