package soundbridge.testing.managers;

import static org.junit.Assert.*;



import java.sql.SQLException;


import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;


import soundbridge.database.managers.pojomanagers.ClientpppManager;

import soundbridge.database.pojos.Clientppp;


/**
 * Comprueba los métodos de la clase ClientManager. Se especifica un orden
 * para el correcto funcionamiento de las pruebas.
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ClientpppPManagerTest {
	
	private static ClientpppManager ClientpppManager = null;

	/**
	 * Preparación de la clase.
	 */
	@BeforeClass
	public static void setUpBeforeClass() {
		ClientpppManager = new ClientpppManager();
	}

	/**
	 * Comprobación de que un cliente se inserta en la base de datos.
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void testAInsertClient() {
		boolean thrown = false;
		Clientppp Clientppp = new Clientppp();

		Clientppp.setBankAccount("1234567Abc");
		Clientppp.setSuscriptionDate(new java.util.Date(85, 3, 29));
		Clientppp.setName("Pedro");
		Clientppp.setLastName("López");
		Clientppp.setNationality("España");
		Clientppp.setGender("Hombre");
		Clientppp.setPersonalId("16178402X");
		Clientppp.setTelephone("+34689234430");
		Clientppp.setEmail("pedro_lopez@gmail.com");
		Clientppp.setAddress("Calle del Licienciado Poza 10, 4C, 48008 Bilbao, Bizkaia, España");
		Clientppp.setUsername("pedrolopez");
		Clientppp.setPasswd("pedrolopez85");
		Clientppp.setBirthDate(new java.util.Date(85, 3, 29));
		try {
			ClientpppManager.insert(Clientppp);
		} catch (SQLException sqle) {
			thrown = true;
		} catch (Exception e) {
			thrown = true;
		}

		assertFalse(thrown);
	}
}