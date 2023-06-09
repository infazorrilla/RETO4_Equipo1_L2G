package soundbridge.database.managers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import soundbridge.database.exception.NotFoundException;
import soundbridge.database.pojos.Employee;
import soundbridge.utils.DBUtils;

/**
 * Defines access methods for the Employee table on database.
 */
public class EmployeeManager extends ManagerAbstract<Employee> {

	/**
	 * Returns all instances of employees in database or null if there are not
	 * employees.
	 * 
	 * @return list of employees or null
	 * @throws SQLException      if there is an error on database
	 * @throws NotFoundException if list is null
	 * @throws Exception         if there is a generic error
	 */
	@Override
	public List<Employee> selectAll() throws SQLException, NotFoundException, Exception {
		ArrayList<Employee> ret = (ArrayList<Employee>) doSelectAll();

		if (null == ret) {
			throw new NotFoundException("There are no Employees");
		}

		return ret;
	}

	/**
	 * Returns all instances of employees in database or null if there are not
	 * employees.
	 * 
	 * @return list of employees or null
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
	 */
	public List<Employee> doSelectAll() throws SQLException, Exception {
		ArrayList<Employee> ret = null;
		String sql = "SELECT * FROM Employee";

		Connection connection = null;

		Statement statement = null;
		ResultSet resultSet = null;

		try {
			Class.forName(DBUtils.DRIVER);

			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);

			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {

				if (null == ret)
					ret = new ArrayList<Employee>();

				Employee employee = new Employee();

				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				String lastName = resultSet.getString("lastName");
				String nationality = resultSet.getString("nationality");
				String gender = resultSet.getString("gender");

				java.sql.Date sqlBirthDate = resultSet.getDate("birthDate");
				java.util.Date birthDate = new java.util.Date(sqlBirthDate.getTime());

				String personalId = resultSet.getString("personalId");
				String telephone = resultSet.getString("telephone");
				String email = resultSet.getString("email");
				String address = resultSet.getString("address");
				String username = resultSet.getString("username");
				String passwd = resultSet.getString("passwd");
				String ssNum = resultSet.getString("ssNum");
				int salary = resultSet.getInt("salary");

				java.sql.Date sqlHireDate = resultSet.getDate("hireDate");
				java.util.Date hireDate = new java.util.Date(sqlHireDate.getTime());

				employee.setId(id);
				employee.setName(name);
				employee.setLastName(lastName);
				employee.setNationality(nationality);
				employee.setGender(gender);
				employee.setBirthDate(birthDate);
				employee.setPersonalId(personalId);
				employee.setTelephone(telephone);
				employee.setEmail(email);
				employee.setAddress(address);
				employee.setUsername(username);
				employee.setPasswd(passwd);
				employee.setSsNum(ssNum);
				employee.setSalary(salary);
				employee.setHireDate(hireDate);

				ret.add(employee);
			}
		} catch (SQLException sqle) {
			throw sqle;
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
			} catch (Exception e) {

			}
			;
			try {
				if (statement != null)
					statement.close();
			} catch (Exception e) {

			}
			;
			try {
				if (connection != null)
					connection.close();
			} catch (Exception e) {

			}
			;
		}

		return ret;
	}

	/**
	 * Inserts an instance of employee into database.
	 * 
	 * @param employee employee to be inserted
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
	 */
	@Override
	public void insert(Employee employee) throws SQLException, Exception {
		Connection connection = null;
		Statement statement = null;

		try {
			Class.forName(DBUtils.DRIVER);
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			statement = connection.createStatement();

			String sql = "INSERT INTO Employee (name, lastName, nationality, gender, birthDate, personalId, SSNum, hireDate, salary, telephone, email, address, username, passwd) VALUES ('"
					+ employee.getName() + "', '" + employee.getLastName() + "', '" + employee.getNationality() + "', '"
					+ employee.getGender() + "', '" + new java.sql.Date((employee.getBirthDate()).getTime()) + "', '"
					+ employee.getPersonalId() + "', '" + employee.getSsNum() + "', '"
					+ new java.sql.Date((employee.getHireDate()).getTime()) + "', " + employee.getSalary() + ", '"
					+ employee.getTelephone() + "', '" + employee.getEmail() + "', '" + employee.getAddress() + "', '"
					+ employee.getUsername() + "', '" + employee.getPasswd() + "')";

			statement.executeUpdate(sql);

		} catch (SQLException sqle) {
			throw sqle;
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (Exception e) {
			}
			;
			try {
				if (connection != null)
					connection.close();
			} catch (Exception e) {
			}
			;
		}

	}

	/**
	 * Updates an instance of employee on database by id.
	 * 
	 * @param employee employee to be updated
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
	 */
	@Override
	public void update(Employee employee) throws SQLException, Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			Class.forName(DBUtils.DRIVER);

			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);

			String sql = "UPDATE Employee SET name = ?, lastName = ?, nationality = ?, gender = ?, birthDate = ?, "
					+ "personalId = ?, SSNum = ?, hireDate = ?, salary = ?, telephone = ?, email = ?, address = ?, "
					+ "username = ?, passwd = ? where id = ?";

			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, employee.getName());
			preparedStatement.setString(2, employee.getLastName());
			preparedStatement.setString(3, employee.getNationality());
			preparedStatement.setString(4, employee.getGender());
			preparedStatement.setDate(5, new java.sql.Date((employee.getBirthDate()).getTime()));
			preparedStatement.setString(6, employee.getPersonalId());
			preparedStatement.setString(7, employee.getSsNum());
			preparedStatement.setDate(8, new java.sql.Date((employee.getHireDate()).getTime()));
			preparedStatement.setInt(9, employee.getSalary());
			preparedStatement.setString(10, employee.getTelephone());
			preparedStatement.setString(11, employee.getEmail());
			preparedStatement.setString(12, employee.getAddress());
			preparedStatement.setString(13, employee.getUsername());
			preparedStatement.setString(14, employee.getPasswd());
			preparedStatement.setInt(15, employee.getId());

			preparedStatement.executeUpdate();

		} catch (SQLException sqle) {
			throw sqle;
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} catch (Exception e) {
			}
			;
			try {
				if (connection != null)
					connection.close();
			} catch (Exception e) {
			}
			;
		}

	}

	/**
	 * Deletes an instance of employee from database by id.
	 * 
	 * @param employee employee to be deleted
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
	 */
	@Override
	public void delete(Employee employee) throws SQLException, Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			Class.forName(DBUtils.DRIVER);

			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);

			String sql = "DELETE FROM Employee WHERE id = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, employee.getId());

			preparedStatement.executeUpdate();

		} catch (SQLException sqle) {
			throw sqle;
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} catch (Exception e) {
			}
			;
			try {
				if (connection != null)
					connection.close();
			} catch (Exception e) {
			}
			;
		}

	}

	/**
	 * Checks if an instance of employee exists on database with given username and
	 * password.
	 * 
	 * @param username given username for the employee
	 * @param passwd   given username for the employee
	 * @return true if instance exists, otherwise false
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
	 */
	public boolean askForEmployeeUsingIdAndPasswd(String username, String passwd) throws SQLException, Exception {

		String sql = "select * from employee where username=? and passwd=?";

		Connection connection = null;

		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;

		try {

			Class.forName(DBUtils.DRIVER);

			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, passwd);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {

				return true;

			}
		} catch (SQLException sqle) {
			throw sqle;
		} catch (Exception e) {
			throw e;
		} finally {

			try {
				if (resultSet != null)
					resultSet.close();
			} catch (Exception e) {

			}
			;
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} catch (Exception e) {

			}
			;
			try {
				if (connection != null)
					connection.close();
			} catch (Exception e) {

			}
			;
		}
		return false;
	}

	/**
	 * Returns an instance of employee on database with given username.
	 * 
	 * @param username given username for the employee
	 * @return employee with the given username
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
	 */
	public Employee getEmployeeByUsername(String username) throws SQLException, Exception {
		Employee ret = null;
		ArrayList<Employee> employees = (ArrayList<Employee>) doSelectAll();
		for (Employee employee : employees) {
			if (employee.getUsername().equalsIgnoreCase(username)) {
				ret = employee;
				break;
			}
		}
		return ret;
	}

}
