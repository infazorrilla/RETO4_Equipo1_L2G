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
import soundbridge.database.managers.pojomanagers.ClientPManager;
import soundbridge.database.pojos.Client;
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
	public void testAInsertClient() {
		boolean thrown = false;
		ClientP clientp = new ClientP();
		//Client client = new ClientP();
		//client.setId(3);
		
		//clientp.setId(client.getId());
		clientp.setBankAccount("1234567Abc");
		clientp.setSuscriptionDate(new java.util.Date(85, 3, 29));

		try {
			clientpManager.insert(clientp);
		} catch (SQLException sqle) {
			thrown = true;
		} catch (Exception e) {
			thrown = true;
		}

		assertFalse(thrown);
	}
}