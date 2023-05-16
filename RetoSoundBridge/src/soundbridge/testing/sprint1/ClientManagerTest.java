package soundbridge.testing.sprint1;

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
 * Check the methods of the ClientManager class. An order is specified for the
 * correct functioning of the tests.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ClientManagerTest {

	private static ClientManager clientManager = null;

	/**
	 * Class preparation.
	 */
	@BeforeClass
	public static void setUpBeforeClass() {
		clientManager = new ClientManager();
	}

	/**
	 * Checks the insert of a client into the database.
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
	 * Checks the loading of clients into an ArrayList and the inclusion of the
	 * previously inserted client.
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
	 * Checks the update of a client in the database.
	 */
	@Test
	public void testCUpdateClient() {
		boolean thrown = false;

		ArrayList<Client> clients = null;
		Client insertedClient = null;

		try {
			clients = (ArrayList<Client>) clientManager.selectAll();

			for (Client client : clients) {
				if (client.getUsername().equalsIgnoreCase("pedrolopez"))
					insertedClient = client;
			}

			insertedClient.setUsername("pedritolopez");
			clientManager.update(insertedClient);

			clients = (ArrayList<Client>) clientManager.selectAll();

			for (Client client : clients) {
				if (client.getUsername().equalsIgnoreCase("pedritolopez"))
					insertedClient = client;
			}

		} catch (NotFoundException nfe) {
			thrown = true;
		} catch (SQLException sqle) {
			thrown = true;
		} catch (Exception e) {
			thrown = true;
		}

		assertNotNull(clients);
		assertFalse(thrown);
		assertTrue(("pedritolopez").equals(insertedClient.getUsername()));
	}

	/**
	 * Checks the deletion of a client in the database.
	 */
	@Test
	public void testDDeleteClient() {
		boolean thrown = false;
		boolean isDeleted = true;

		ArrayList<Client> clients = null;
		Client insertedClient = null;

		try {
			clients = (ArrayList<Client>) clientManager.selectAll();

			for (Client client : clients) {
				if (client.getUsername().equalsIgnoreCase("pedritolopez"))
					insertedClient = client;
			}

			clientManager.delete(insertedClient);

			clients = (ArrayList<Client>) clientManager.selectAll();

			if (clients != null) {
				for (Client client : clients) {
					if ((client.getUsername().equalsIgnoreCase("pedritolopez")))
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
