package soundbridge.testing.managers;

import static org.junit.Assert.*;





import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import soundbridge.database.exception.NotFoundException;
import soundbridge.database.managers.ClientPManager;

import soundbridge.database.pojos.ClientP;


/**
 * Comprueba los métodos de la clase ClientManager. Se especifica un orden
 * para el correcto funcionamiento de las pruebas.
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ClientPManagerTest {
	
	private static ClientPManager clientpManager = null;

	/**
	 * Preparación de la clase.
	 */
	@BeforeClass
	public static void setUpBeforeClass() {
		clientpManager = new ClientPManager();
	}

	/**
	 * Comprobación de que un cliente se inserta en la base de datos.
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void testAInsertClientP() {
		boolean thrown = false;
		ClientP clientp = new ClientP();

		clientp.setBankAccount("1234567Abc");
		clientp.setSuscriptionDate(new java.util.Date(85, 3, 29));
		clientp.setName("Pedro");
		clientp.setLastName("López");
		clientp.setNationality("España");
		clientp.setGender("Hombre");
		clientp.setPersonalId("16178402X");
		clientp.setTelephone("+34689234430");
		clientp.setEmail("pedro_lopez@gmail.com");
		clientp.setAddress("Calle del Licienciado Poza 10, 4C, 48008 Bilbao, Bizkaia, España");
		clientp.setUsername("pedrolopez");
		clientp.setPasswd("pedrolopez85");
		clientp.setBirthDate(new java.util.Date(85, 3, 29));
		try {
			clientpManager.insert(clientp);
		} catch (SQLException sqle) {
			thrown = true;
		} catch (Exception e) {
			thrown = true;
		}

		assertFalse(thrown);
	}
	@Test
	public void testBSelectAllClientPs() {
		boolean thrown = false;
		boolean isInserted = false;

		ArrayList<ClientP> clientps = null;

		try {
			clientps = (ArrayList<ClientP>) clientpManager.selectAll();

			for (ClientP clientp : clientps) {
				if (clientp.getBankAccount().equalsIgnoreCase("1234567Abc"))
					isInserted = true;
			}

		} catch (NotFoundException nfe) {
			thrown = true;
		} catch (SQLException sqle) {
			thrown = true;
		} catch (Exception e) {
			thrown = true;
		}

		assertNotNull(clientps);
		assertFalse(thrown);
		assertTrue(isInserted);
	}
	@Test
	public void testDDeleteClientP() {
		boolean thrown = false;
		boolean isDeleted = true;

		ArrayList<ClientP> clientps = null;
		ClientP insertedClientp = null;

		try {
			clientps = (ArrayList<ClientP>) clientpManager.selectAll();

			for (ClientP clientp : clientps) {
				if (clientp.getBankAccount().equalsIgnoreCase("1234567Abc"))
					insertedClientp = clientp;
			}

			clientpManager.delete(insertedClientp);

			clientps = (ArrayList<ClientP>) clientpManager.selectAll();

			if (clientps != null) {
				for (ClientP client : clientps) {
					if ((client.getUsername().equalsIgnoreCase("pedritolopez")))
						isDeleted = true;
				}
			} else {
				isDeleted = true;
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
	
	@Test
	public void testCUpdateClientP() {
		boolean thrown = false;

		ArrayList<ClientP> clientps = null;
		ClientP insertedClientp = null;

		try {
			clientps = (ArrayList<ClientP>) clientpManager.selectAll();

			for (ClientP clientp : clientps) {
				if (clientp.getBankAccount().equalsIgnoreCase("1234567Abc"))
					insertedClientp = clientp;
			}

			insertedClientp.setBankAccount("1234567Abc");
			clientpManager.update(insertedClientp);

			clientps = (ArrayList<ClientP>) clientpManager.selectAll();

			for (ClientP clientp : clientps) {
				if (clientp.getBankAccount().equalsIgnoreCase("1234567Abc"))
					insertedClientp = clientp;
			}

		} catch (NotFoundException nfe) {
			thrown = true;
		} catch (SQLException sqle) {
			thrown = true;
		} catch (Exception e) {
			thrown = true;
		}

		assertNotNull(clientps);
		assertFalse(thrown);
		assertTrue(("1234567Abc").equals(insertedClientp.getBankAccount()));
	}

}