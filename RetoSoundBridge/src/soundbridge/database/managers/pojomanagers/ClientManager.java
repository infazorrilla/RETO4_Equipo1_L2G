package soundbridge.database.managers.pojomanagers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import soundbridge.database.exception.NotFoundException;
import soundbridge.database.managers.ManagerAbstract;
import soundbridge.database.pojos.Client;
import soundbridge.database.pojos.ClientP;
import soundbridge.database.pojos.ClientPP;
import soundbridge.database.utils.DBUtils;

public class ClientManager extends ManagerAbstract<Client> {

	@Override
	public List<Client> selectAll() throws SQLException, NotFoundException, Exception {
		ArrayList<Client> ret = (ArrayList<Client>) doSelectAll();

		if (null == ret) {
			throw new NotFoundException("There are no Clients");
		}

		return ret;
	}

	public List<Client> doSelectAll() throws SQLException, Exception {
		ArrayList<Client> ret = null;
		String sql = "SELECT * FROM Client";

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
					ret = new ArrayList<Client>();

				Client client = new Client();

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
				String type = resultSet.getString("type");
				String username = resultSet.getString("username");
				String passwd = resultSet.getString("passwd");

				client.setId(id);
				client.setName(name);
				client.setLastName(lastName);
				client.setNationality(nationality);
				client.setGender(gender);
				client.setBirthDate(birthDate);
				client.setPersonalId(personalId);
				client.setTelephone(telephone);
				client.setEmail(email);
				client.setAddress(address);
				client.setUsername(username);
				client.setPasswd(passwd);

				if (type.equalsIgnoreCase("Premium"))
					client = (ClientP) client;
				else if (type.equalsIgnoreCase("Premium Plus"))
					client = (ClientPP) client;

				ret.add(client);
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

	@Override
	public void insert(Client client) throws SQLException, Exception {
		Connection connection = null;
		Statement statement = null;

		try {
			Class.forName(DBUtils.DRIVER);
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			statement = connection.createStatement();

			String sql = "INSERT INTO Client (name, lastName, nationality, gender, birthDate, personalId, telephone, email, address, username, passwd) VALUES ('"
					+ client.getName() + "', '" + client.getLastName() + "', '" + client.getNationality() + "', '"
					+ client.getGender() + "', '" + new java.sql.Date((client.getBirthDate()).getTime()) + "', '"
					+ client.getPersonalId() + "', '" + client.getTelephone() + "', '" + client.getEmail() + "', '"
					+ client.getAddress() + "', '" + client.getUsername() + "', '" + client.getPasswd() + "')";

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

	@Override
	public void update(Client client) throws SQLException, Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			Class.forName(DBUtils.DRIVER);

			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);

			String sql = "UPDATE Client SET name = ?, lastName = ?, nationality = ?, gender = ?, birthDate = ?, "
					+ "personalId = ?, telephone = ?, email = ?, address = ?, "
					+ "username = ?, passwd = ?, type = ? where id = ?";

			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, client.getName());
			preparedStatement.setString(2, client.getLastName());
			preparedStatement.setString(3, client.getNationality());
			preparedStatement.setString(4, client.getGender());
			preparedStatement.setDate(5, new java.sql.Date((client.getBirthDate()).getTime()));
			preparedStatement.setString(6, client.getPersonalId());
			preparedStatement.setString(7, client.getTelephone());
			preparedStatement.setString(8, client.getEmail());
			preparedStatement.setString(9, client.getAddress());
			preparedStatement.setString(10, client.getUsername());
			preparedStatement.setString(11, client.getPasswd());
			preparedStatement.setInt(13, client.getId());

			if (client instanceof ClientP)
				preparedStatement.setString(12, "Premium");
			else if (client instanceof ClientPP)
				preparedStatement.setString(12, "Premium Plus");
			else
				preparedStatement.setString(12, "Basic");

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

	@Override
	public void delete(Client client) throws SQLException, Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			Class.forName(DBUtils.DRIVER);

			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);

			String sql = "DELETE FROM Client WHERE id = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, client.getId());

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

}
