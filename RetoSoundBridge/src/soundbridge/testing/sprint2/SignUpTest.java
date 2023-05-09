package soundbridge.testing.sprint2;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import soundbridge.database.exception.NotFoundException;
import soundbridge.database.managers.ClientManager;
import soundbridge.database.pojos.Client;

/**
 * Comprueba que un cliente se inserta en la base de datos. Se especifica un
 * orden para el correcto funcionamiento de las pruebas.
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SignUpTest {

	private static ClientManager clientManager = null;

	/**
	 * Preparación de la clase.
	 */
	@BeforeClass
	public static void setUpBeforeClass() {
		clientManager = new ClientManager();
	}

	/**
	 * Comprobación de que un cliente se inserta en la base de datos.
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void testAInsertClient() {
		boolean thrown = false;
		Client client = new Client();

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
		client.setBlocked(false);
		client.setBirthDate(new java.util.Date(85, 3, 29));

		try {
			clientManager.insert(client);
		} catch (SQLException sqle) {
			thrown = true;
		} catch (Exception e) {
			thrown = true;
		}

		assertFalse(thrown);
	}

	/**
	 * Comprobación de que los clientes se cargan correctamente en un ArrayList y
	 * que el cliente anteriormente insertado está incluido.
	 */
	@Test
	public void testBSelectAllClients() {
		boolean thrown = false;
		boolean isInserted = false;

		ArrayList<Client> clients = null;

		try {
			clients = (ArrayList<Client>) clientManager.selectAll();

			for (Client client : clients) {
				if (client.getUsername().equalsIgnoreCase("pedrolopez"))
					isInserted = true;
			}

		} catch (NotFoundException nfe) {
			thrown = true;
			System.out.println(nfe);
		} catch (SQLException sqle) {
			thrown = true;
			System.out.println(sqle);
		} catch (Exception e) {
			thrown = true;
			System.out.println(e);
		}

		assertNotNull(clients);
		assertFalse(thrown);
		assertTrue(isInserted);
	}

	/**
	 * Reestablece la base de datos, eliminando el cliente insertado.
	 */
	@Test
	public void testCDeleteClient() {
		boolean thrown = false;
		boolean isDeleted = true;

		ArrayList<Client> clients = null;
		Client insertedClient = null;

		try {
			clients = (ArrayList<Client>) clientManager.selectAll();

			for (Client client : clients) {
				if (client.getUsername().equalsIgnoreCase("pedrolopez"))
					insertedClient = client;
			}

			clientManager.delete(insertedClient);

			clients = (ArrayList<Client>) clientManager.selectAll();

			if (clients != null) {
				for (Client client : clients) {
					if ((client.getUsername().equalsIgnoreCase("pedrolopez")))
						isDeleted = false;
				}
			}

		} catch (NotFoundException nfe) {

		} catch (SQLException sqle) {
			thrown = true;
		} catch (Exception e) {
			thrown = true;
		}

		assertFalse(thrown);
		assertTrue(isDeleted);
	}

}
