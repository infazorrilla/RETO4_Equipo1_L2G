package soundbridge.testing.managers;

import static org.junit.Assert.*;



import java.sql.SQLException;


import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;


import soundbridge.database.managers.pojomanagers.ClientPManager;

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
}