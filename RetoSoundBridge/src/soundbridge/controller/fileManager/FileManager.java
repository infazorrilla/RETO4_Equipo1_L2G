package soundbridge.controller.fileManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import soundbridge.database.managers.ClientManager;
import soundbridge.database.pojos.Client;

public class FileManager {

	ArrayList<Client> allClients = null;

	public void crearTicket(String RUTA_FICHERO) {

		final String NOMBRE_FICHERO = "Ticket.txt";

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

	public String read(String RUTA_FICHERO) throws IOException {
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
		String linea = null;
		try {

			archivo = new File(RUTA_FICHERO);
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);

			linea = br.readLine();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != fr) {
					fr.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return linea;
	}

}
