package soundbridge.testing.fileManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileManager {

	private File fichero;
	
	public FileManager(File fichero) {
		this.fichero = fichero;
	}

	public void escribir(String texto) throws IOException {
		FileWriter fileWriter = new FileWriter(fichero);
		print(fileWriter, texto);
		fileWriter.close();
	}

	private void print(FileWriter fW, String texto) {
		PrintWriter printWriter = new PrintWriter(fW);
		printWriter.println(texto);
		printWriter.close();
	}

	public String leer() throws IOException {
		String ret = "";
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;

		try {
			fileReader = new FileReader(fichero);
			bufferedReader = new BufferedReader(fileReader);

			String linea = null;
			while ((linea = bufferedReader.readLine()) != null) {
				ret += linea + "\n";
			}

		} catch (FileNotFoundException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		} finally {
			try {
				if (null != bufferedReader)
					bufferedReader.close();
			} catch (IOException e) {
			}
			try {
				if (null != fileReader)
					fileReader.close();
			} catch (IOException e) {
			}
		}
		return ret;
	}
}
