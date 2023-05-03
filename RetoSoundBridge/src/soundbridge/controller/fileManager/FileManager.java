package soundbridge.controller.fileManager;

import java.io.File;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import soundbridge.database.managers.ClientManager;
import soundbridge.database.pojos.Client;

public class FileManager {

	ArrayList<Client> allClients = null;

	public void crearTicket() {

		final String NOMBRE_FICHERO = "Ticket.txt";
		final String RUTA_FICHERO = "C:\\Users\\in1dw3\\git\\RETO4_Equipo1_L2G1\\RetoSoundBridge\\src\\soundbridge\\testing\\sprint2\\";

		File fichero = new File(RUTA_FICHERO + NOMBRE_FICHERO);

		try {

			if (fichero.createNewFile())
				System.out.println("El fichero se ha creado correctamente");
			else
				System.out.println("No ha podido ser creado el fichero");
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

		FileWriter fileWriter = null;
		PrintWriter printWriter = null;

		try {

			fileWriter = new FileWriter(RUTA_FICHERO + NOMBRE_FICHERO);

			printWriter = new PrintWriter(fileWriter);
			ClientManager clientManager = new ClientManager();
			try {
				allClients = (ArrayList<Client>) clientManager.doSelectAll();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String texto = allClients.toString();
			printWriter.println(texto);

		} catch (IOException e) {
			System.out.println("IOException - Error de escritura en el fichero " + RUTA_FICHERO + NOMBRE_FICHERO);
		} finally {
			printWriter.close();
			try {
				fileWriter.close();

			} catch (IOException e) {

			}
		}
	}

}
