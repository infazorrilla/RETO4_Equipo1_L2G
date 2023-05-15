package soundbridge.controller.fileManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Manages the creation of files. Also manages how to write and read files.
 */
public class FileManager {

	/**
	 * Creates a file of the given name in the given path with the text written
	 * inside.
	 * 
	 * @param path given path of the file
	 * @param name given name of the file
	 * @param text text to write inside the file
	 */
	public void write(String path, String name, String text) {
		File file = new File(path + name);

		try {

			if (file.createNewFile())
				System.out.println("El fichero se ha creado correctamente");
			else
				System.out.println("No ha podido ser creado el fichero");
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

		FileWriter fileWriter = null;
		PrintWriter printWriter = null;

		try {

			fileWriter = new FileWriter(path + name);

			printWriter = new PrintWriter(fileWriter);
			printWriter.println(text);

		} catch (IOException e) {
			System.out.println("IOException - Error de escritura en el fichero " + path + name);
		} finally {
			printWriter.close();
			try {
				fileWriter.close();

			} catch (IOException e) {

			}
		}
	}

	/**
	 * Reads the file of the given path.
	 * 
	 * @param path given path of the file
	 * @return text written inside the file
	 * @throws IOException
	 */
	public String read(String path) throws IOException {
		File file = null;
		FileReader fr = null;
		BufferedReader br = null;
		String line = null;
		try {

			file = new File(path);
			fr = new FileReader(file);
			br = new BufferedReader(fr);

			line = br.readLine();

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
		return line;
	}

}
