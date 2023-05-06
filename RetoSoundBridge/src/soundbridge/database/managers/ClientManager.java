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
import soundbridge.database.pojos.Client;
import soundbridge.database.pojos.ClientP;
import soundbridge.database.pojos.ClientPP;
import soundbridge.utils.DBUtils;

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

				String type = resultSet.getString("type");
				if (type.equalsIgnoreCase("basic")) {
					client = new Client();
				} else if (type.equalsIgnoreCase("premium")) {
					client = new ClientP();
				} else if (type.equalsIgnoreCase("premium plus")) {
					client = new ClientPP();
				}

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
				boolean isBlocked = resultSet.getBoolean("isBlocked");

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
				client.setBlocked(isBlocked);

				if (client instanceof ClientP) {
					ClientPManager clientPManager = new ClientPManager();
					ClientP clientP = clientPManager.getClientPById(id);
					if (clientP != null) {
						((ClientP) client).setBankAccount(clientP.getBankAccount());
						((ClientP) client).setSuscriptionDate(clientP.getSuscriptionDate());
					}
				} else if (client instanceof ClientPP) {
					ClientPPManager clientPPManager = new ClientPPManager();
					ClientPP clientPP = clientPPManager.getClientPPById(id);
					if (clientPP != null) {
						((ClientPP) client).setBankAccount(clientPP.getBankAccount());
						((ClientPP) client).setSuscriptionDate(clientPP.getSuscriptionDate());
					}
				}

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
			
			String type = null;
			
			if (client instanceof ClientP) {
				type = "Premium";
			} else if (client instanceof ClientPP) {
				type = "Premium Plus";
			} else {
				type = "Basic";
			}

			String sql = "INSERT INTO Client (name, lastName, nationality, gender, birthDate, personalId, telephone, email, address, username, passwd, type) VALUES ('"
					+ client.getName() + "', '" + client.getLastName() + "', '" + client.getNationality() + "', '"
					+ client.getGender() + "', '" + new java.sql.Date((client.getBirthDate()).getTime()) + "', '"
					+ client.getPersonalId() + "', '" + client.getTelephone() + "', '" + client.getEmail() + "', '"
					+ client.getAddress() + "', '" + client.getUsername() + "', '" + client.getPasswd() + "', '" + type + "')";

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
					+ "username = ?, passwd = ?, type = ?, isBlocked = ? where id = ?";

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
			preparedStatement.setBoolean(13, client.isBlocked());
			preparedStatement.setInt(14, client.getId());

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

	public boolean askForClientUsingIdAndPasswd(String username, String passwd) throws SQLException, Exception {

		String sql = "select * from client where username=? and passwd=?";

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

	public Client getClientByUsername(String username) throws SQLException, Exception {
		Client ret = null;
		ArrayList<Client> clients = (ArrayList<Client>) doSelectAll();
		for (Client client : clients) {
			if (client.getUsername().equalsIgnoreCase(username)) {
				ret = client;
				break;
			}
		}

		return ret;
	}

	public void changeSubscription(int clientId, String bankNumber, String actualSubscription, String newSubscription)
			throws SQLException, Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			Class.forName(DBUtils.DRIVER);

			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);

			String sql = "CALL changeSubscription(?, ?, ?, ?)";

			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setInt(1, clientId);
			preparedStatement.setString(2, bankNumber);
			preparedStatement.setString(3, actualSubscription);
			preparedStatement.setString(4, newSubscription);

			preparedStatement.execute();

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
	
	public Client clientById(int idClient) throws SQLException, Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Client ret = null;

		try {
			Class.forName(DBUtils.DRIVER);

			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);

			String sql = "SELECT * FROM Client WHERE id = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, idClient);

			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {

				if (null == ret)
					ret = new Client();

				String type = resultSet.getString("type");
				if (type.equalsIgnoreCase("basic")) {
					ret = new Client();
				} else if (type.equalsIgnoreCase("premium")) {
					ret = new ClientP();
				} else if (type.equalsIgnoreCase("premium plus")) {
					ret = new ClientPP();
				}

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
				boolean isBlocked = resultSet.getBoolean("isBlocked");

				ret.setId(id);
				ret.setName(name);
				ret.setLastName(lastName);
				ret.setNationality(nationality);
				ret.setGender(gender);
				ret.setBirthDate(birthDate);
				ret.setPersonalId(personalId);
				ret.setTelephone(telephone);
				ret.setEmail(email);
				ret.setAddress(address);
				ret.setUsername(username);
				ret.setPasswd(passwd);
				ret.setBlocked(isBlocked);

				if (ret instanceof ClientP) {
					ClientPManager clientPManager = new ClientPManager();
					ClientP clientP = clientPManager.getClientPById(id);
					if (clientP != null) {
						((ClientP) ret).setBankAccount(clientP.getBankAccount());
						((ClientP) ret).setSuscriptionDate(clientP.getSuscriptionDate());
					}
				} else if (ret instanceof ClientPP) {
					ClientPPManager clientPPManager = new ClientPPManager();
					ClientPP clientPP = clientPPManager.getClientPPById(id);
					if (clientPP != null) {
						((ClientPP) ret).setBankAccount(clientPP.getBankAccount());
						((ClientPP) ret).setSuscriptionDate(clientPP.getSuscriptionDate());
					}
				}
			}

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
		
		return ret;

	}

}
