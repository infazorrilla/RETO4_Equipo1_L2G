package soundbridge.testing.managers;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import soundbridge.database.exception.NotFoundException;
import soundbridge.database.managers.pojomanagers.ClientManager;
import soundbridge.database.managers.pojomanagers.PlayManager;
import soundbridge.database.pojos.Client;
import soundbridge.database.pojos.Play;

/**
 * Comprueba los métodos de la clase PlayManager. Se especifica un orden
 * para el correcto funcionamiento de las pruebas.
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PlayManagerTest {
	
	private static PlayManager playManager = null;
	private static Client client = null;

	@SuppressWarnings("deprecation")
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		playManager = new PlayManager();
		client = new Client();
		
		client.setName("Pedro");
		client.setLastName("López");
		client.setNationality("España");
		client.setGender("Hombre");
		client.setPersonalId("16178402X");
		client.setTelephone("+34689234430");
		client.setEmail("pedro_lopez@gmail.com");
		client.setAddress("Calle del Licienciado Poza 10, 4C, 48008 Bilbao, Bizkaia, España");
		client.setUsername("pedrolopez");
		client.setPasswd("pedrolopez85");
		client.setBirthDate(new java.util.Date(85, 3, 29));
	}

	/**
	 * Comprobación de que una reproducción se inserta en la base de datos.
	 */
	@Test
	public void testInsertPlay() {
		boolean thrown = false;
		
		Play play = new Play();
		ArrayList<Client> clients = null;
		Client insertedClient = null;
		
		ClientManager clientManager = new ClientManager();

		try {
			clientManager.insert(client);
			
			clients = (ArrayList<Client>) clientManager.selectAll();

			for (Client client : clients) {
				if (client.getUsername().equalsIgnoreCase("pedrolopez")) {
					insertedClient = client;
				}

			}
			
			play.setClient(insertedClient);
			
			
			playManager.insert(play);
			
		} catch (NotFoundException nfe) {
			thrown = true;	
		} catch (SQLException sqle) {
			thrown = true;
		} catch (Exception e) {
			thrown = true;
		}

		assertFalse(thrown);
	}
	
	@Test
	public void testSelectAll() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdatePlay() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeletePlay() {
		fail("Not yet implemented");
	}

}
