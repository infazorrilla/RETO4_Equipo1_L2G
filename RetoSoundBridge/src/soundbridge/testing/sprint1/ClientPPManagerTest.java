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
import soundbridge.database.managers.ClientPPManager;

import soundbridge.database.pojos.ClientPP;


/**
 * Comprueba los métodos de la clase ClientManager. Se especifica un orden
 * para el correcto funcionamiento de las pruebas.
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ClientPPManagerTest {
	
	private static ClientPPManager clientppManager = null;

	/**
	 * Preparación de la clase.
	 */
	@BeforeClass
	public static void setUpBeforeClass() {
		clientppManager = new ClientPPManager();
	}

	/**
	 * Comprobación de que un cliente se inserta en la base de datos.
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void testAInsertClientPP() {
		boolean thrown = false;
		ClientPP clientpp = new ClientPP();

		clientpp.setBankAccount("12345678901234567890");
		clientpp.setSuscriptionDate(new java.util.Date(85, 3, 29));
		clientpp.setName("Pedro");
		clientpp.setLastName("López");
		clientpp.setNationality("España");
		clientpp.setGender("Hombre");
		clientpp.setPersonalId("16178402X");
		clientpp.setTelephone("+34689234430");
		clientpp.setEmail("pedro_lopez@gmail.com");
		clientpp.setAddress("Calle del Licienciado Poza 10, 4C, 48008 Bilbao, Bizkaia, España");
		clientpp.setUsername("pedrolopez");
		clientpp.setPasswd("pedrolopez85");
		clientpp.setBlocked(false);
		clientpp.setBirthDate(new java.util.Date(85, 3, 29));
		try {
			clientppManager.insert(clientpp);
		} catch (SQLException sqle) {
			thrown = true;
			System.out.println(sqle);
		} catch (Exception e) {
			thrown = true;
			System.out.println(e);
		}

		assertFalse(thrown);
	}
	@Test
	public void testBSelectAllClientPPs() {
		boolean thrown = false;
		boolean isInserted = false;

		ArrayList<ClientPP> clientpps = null;

		try {
			clientpps = (ArrayList<ClientPP>) clientppManager.selectAll();

			for (ClientPP clientpp : clientpps) {
				if (clientpp.getBankAccount().equalsIgnoreCase("12345678901234567890"))
					isInserted = true;
			}

		} catch (NotFoundException nfe) {
			thrown = true;
		} catch (SQLException sqle) {
			thrown = true;
		} catch (Exception e) {
			thrown = true;
		}

		assertNotNull(clientpps);
		assertFalse(thrown);
		assertTrue(isInserted);
	}
	@Test
	public void testDDeleteClientPP() {
		boolean thrown = false;
		boolean isDeleted = true;

		ArrayList<ClientPP> clientpps = null;
		ClientPP insertedClientpp = null;

		try {
			clientpps = (ArrayList<ClientPP>) clientppManager.selectAll();

			for (ClientPP clientpp : clientpps) {
				if (clientpp.getBankAccount().equalsIgnoreCase("12345678901234567890"))
					insertedClientpp = clientpp;
			}

			clientppManager.delete(insertedClientpp);
			ClientManager clientManager = new ClientManager();
			clientManager.delete(insertedClientpp);

			clientpps = (ArrayList<ClientPP>) clientppManager.selectAll();

			if (clientpps != null) {
				for (ClientPP clientpp : clientpps) {
					if ((clientpp.getId() == insertedClientpp.getId()))
						isDeleted = false;
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
	public void testCUpdateClientPP() {
		boolean thrown = false;

		ArrayList<ClientPP> clientpps = null;
		ClientPP insertedClientpp = null;

		try {
			clientpps = (ArrayList<ClientPP>) clientppManager.selectAll();

			for (ClientPP clientpp : clientpps) {
				if (clientpp.getBankAccount().equalsIgnoreCase("12345678901234567890"))
					insertedClientpp = clientpp;
			}

			insertedClientpp.setBankAccount("12345678901234567890");
			clientppManager.update(insertedClientpp);

			clientpps = (ArrayList<ClientPP>) clientppManager.selectAll();

			for (ClientPP clientpp : clientpps) {
				if (clientpp.getId() == insertedClientpp.getId())
					insertedClientpp = clientpp;
			}

		} catch (NotFoundException nfe) {
			thrown = true;
		} catch (SQLException sqle) {
			thrown = true;
		} catch (Exception e) {
			thrown = true;
		}

		assertNotNull(clientpps);
		assertFalse(thrown);
		assertTrue(("12345678901234567890").equals(insertedClientpp.getBankAccount()));
	}

}