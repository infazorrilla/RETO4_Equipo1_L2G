package soundbridge.testing.sprint2;






import java.io.IOException;

import org.junit.Test;

import soundbridge.controller.fileManager.FileManager;

public class FileTest {
	final String RUTA_FICHERO = "C:\\Users\\in1dw3\\git\\RETO4_Equipo1_L2G1\\RetoSoundBridge\\src\\soundbridge\\testing\\sprint2\\";
	final String RUTA_FICHERO_LEER = "C:\\Users\\in1dw3\\git\\RETO4_Equipo1_L2G1\\RetoSoundBridge\\src\\soundbridge\\testing\\sprint2\\Ticket.txt";
	@Test
	public void test() {
		
		FileManager fileManager = new FileManager();
	
			fileManager.crearTicket(RUTA_FICHERO);
			
			try {
				fileManager.read(RUTA_FICHERO_LEER);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
