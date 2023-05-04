package soundbridge.testing.sprint1;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.junit.Test;

import soundbridge.database.exception.NotFoundException;
import soundbridge.database.managers.EmployeeManager;
import soundbridge.database.pojos.Employee;

/**
 * Comprueba los métodos de la clase EmployeeManager. Se especifica un orden
 * para el correcto funcionamiento de las pruebas.
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmployeeManagerTest {

	private static EmployeeManager employeeManager;

	/**
	 * Preparación de la clase.
	 */
	@BeforeClass
	public static void setUpBeforeClass() {
		employeeManager = new EmployeeManager();
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
	 * Comprobación de que un empleado se actualiza en la base de datos.
	 */
	@Test
	public void testCUpdateEmployee() {
		boolean thrown = false;

		ArrayList<Employee> employees = null;
		Employee insertedEmployee = null;

		try {
			employees = (ArrayList<Employee>) employeeManager.selectAll();

			for (Employee employee : employees) {
				if (employee.getUsername().equalsIgnoreCase("anagarcia"))
					insertedEmployee = employee;
			}

			insertedEmployee.setUsername("anitagarcia");
			employeeManager.update(insertedEmployee);

			employees = (ArrayList<Employee>) employeeManager.selectAll();

			for (Employee employee : employees) {
				if (employee.getUsername().equalsIgnoreCase("anitagarcia"))
					insertedEmployee = employee;
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
		assertTrue(("anitagarcia").equals(insertedEmployee.getUsername()));

	}

	/**
	 * Comprobación de que un empleado se elimina de la base de datos.
	 */
	@Test
	public void testDDeleteEmployee() {
		boolean thrown = false;
		boolean isDeleted = true;

		ArrayList<Employee> employees = null;
		Employee insertedEmployee = null;

		try {
			employees = (ArrayList<Employee>) employeeManager.selectAll();

			for (Employee employee : employees) {
				if (employee.getUsername().equalsIgnoreCase("anitagarcia"))
					insertedEmployee = employee;
			}

			employeeManager.delete(insertedEmployee);

			employees = (ArrayList<Employee>) employeeManager.selectAll();

			if (employees != null) {
				for (Employee employee : employees) {
					if ((employee.getUsername().equalsIgnoreCase("anitagarcia")))
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

}
