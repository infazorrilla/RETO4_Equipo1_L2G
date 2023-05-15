package soundbridge.testing.sprint2;

import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import soundbridge.controller.fileManager.FileManager;
import soundbridge.database.managers.ClientManager;
import soundbridge.database.pojos.Client;
/**
 * Checks that a file with the information of all the clients is created. And then checks that the information
 * of the file is correct.
 */
public class FileTest {
	ArrayList<Client> allClients = null;
	final String RUTA_FICHERO = "C:\\Users\\in1dw3\\git\\RETO4_Equipo1_L2G1\\RetoSoundBridge\\src\\soundbridge\\testing\\sprint2\\";
	final String RUTA_FICHERO_LEER = "C:\\Users\\in1dw3\\git\\RETO4_Equipo1_L2G1\\RetoSoundBridge\\src\\soundbridge\\testing\\sprint2\\Ticket.txt";

	@Test
	public void test() {
		boolean correct = false;
		FileManager fileManager = new FileManager();
		ClientManager clientManager = new ClientManager();
		fileManager.crearTicket(RUTA_FICHERO);
		try {
			allClients = (ArrayList<Client>) clientManager.doSelectAll();
			String linea = fileManager.read(RUTA_FICHERO_LEER);
			if (allClients.toString().equals(linea)) {
				correct = true;
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		assertTrue(correct);
	}

}
