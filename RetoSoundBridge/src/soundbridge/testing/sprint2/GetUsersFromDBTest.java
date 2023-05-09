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
import soundbridge.database.managers.EmployeeManager;
import soundbridge.database.pojos.Client;
import soundbridge.database.pojos.Employee;

/**
 * Comprueba que se obtienen todos los empleados y clientes con sus respectivos
 * perfiles de usuario. Se especifica un orden para el correcto funcionamiento
 * de las pruebas.
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GetUsersFromDBTest {

	private static EmployeeManager employeeManager;
	private static ClientManager clientManager = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		employeeManager = new EmployeeManager();
		clientManager = new ClientManager();
	}

	/**
	 * Comprobación de que un empleado se inserta en la base de datos.
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void testAInsertEmployee() {
		boolean thrown = false;
		Employee employee = new Employee();

		employee.setName("Ana");
		employee.setLastName("García");
		employee.setNationality("España");
		employee.setGender("Mujer");
		employee.setPersonalId("19077452L");
		employee.setTelephone("+34693545796");
		employee.setEmail("ana_garcia@gmail.com");
		employee.setAddress("Calle del Licienciado Poza 13, 2A, 48008 Bilbao, Bizkaia, España");
		employee.setUsername("anagarcia");
		employee.setPasswd("!ana48008!");
		employee.setSsNum("975318294729");
		employee.setSalary(1200);

		employee.setBirthDate(new java.util.Date(91, 12, 9));
		employee.setHireDate(new java.util.Date(118, 3, 28));

		try {
			employeeManager.insert(employee);
		} catch (SQLException sqle) {
			thrown = true;
		} catch (Exception e) {
			thrown = true;
		}

		assertFalse(thrown);

	}

	/**
	 * Comprobación de que los empleados se cargan correctamente en un ArrayList y
	 * que el empleado anteriormente insertado está incluido.
	 */
	@Test
	public void testBSelectAllEmployees() {
		boolean thrown = false;
		boolean isInserted = false;

		ArrayList<Employee> employees = null;

		try {
			employees = (ArrayList<Employee>) employeeManager.selectAll();

			for (Employee employee : employees) {
				if (employee.getUsername().equalsIgnoreCase("anagarcia"))
					isInserted = true;
			}

		} catch (NotFoundException nfe) {
			thrown = true;
		} catch (SQLException sqle) {
			thrown = true;
		} catch (Exception e) {
			thrown = true;
		}

		assertNotNull(employees);
		assertFalse(thrown);
		assertTrue(isInserted);

	}

	/**
	 * Comprobación de que un cliente se inserta en la base de datos.
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void testCInsertClient() {
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
	public void testDSelectAllClients() {
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
	 * Reestablece la base de datos, eliminando el empleado y cliente insertados.
	 */
	@Test
	public void testEDelete() {
		boolean thrown = false;

		ArrayList<Employee> employees = null;
		Employee insertedEmployee = null;

		ArrayList<Client> clients = null;
		Client insertedClient = null;

		try {
			employees = (ArrayList<Employee>) employeeManager.selectAll();

			for (Employee employee : employees) {
				if (employee.getUsername().equalsIgnoreCase("anagarcia"))
					insertedEmployee = employee;
			}

			employeeManager.delete(insertedEmployee);

			clients = (ArrayList<Client>) clientManager.selectAll();

			for (Client client : clients) {
				if (client.getUsername().equalsIgnoreCase("pedrolopez"))
					insertedClient = client;
			}

			clientManager.delete(insertedClient);

		} catch (NotFoundException nfe) {

		} catch (SQLException sqle) {
			thrown = true;
		} catch (Exception e) {
			thrown = true;
		}

		assertFalse(thrown);
	}

}
