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
import soundbridge.database.pojos.ClientPP;

import soundbridge.utils.DBUtils;

/**
 * Defines access methods for the ClientPP table on database.
 */
public class ClientPPManager extends ManagerAbstract<ClientPP> {

	/**
	 * Returns all instances of premium plus clients in database or null if there
	 * are not premium plus clients.
	 * 
	 * @return list of premium plus clients or null
	 * @throws SQLException      if there is an error on database
	 * @throws NotFoundException if list is null
	 * @throws Exception         if there is a generic error
	 */
	@Override
	public List<ClientPP> selectAll() throws SQLException, NotFoundException, Exception {
		ArrayList<ClientPP> ret = (ArrayList<ClientPP>) doSelectAll();

		if (null == ret) {
			throw new NotFoundException("There are no ClientPPs");
		}

		return ret;
	}

	/**
	 * Returns all instances of premium plus clients in database or null if there
	 * are not premium plus clients.
	 * 
	 * @return list of premium plus clients or null
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
	 */
	public List<ClientPP> doSelectAll() throws SQLException, Exception {
		ArrayList<ClientPP> ret = null;
		String sql = "SELECT * FROM ClientPP";

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
					ret = new ArrayList<ClientPP>();

				ClientPP clientpp = new ClientPP();

				int idClient = resultSet.getInt("idClient");
				String bankAccount = resultSet.getString("bankAccount");
				java.sql.Date sqlsuscriptionDate = resultSet.getDate("suscriptionDate");
				java.util.Date suscriptionDate = new java.util.Date(sqlsuscriptionDate.getTime());

				clientpp.setId(idClient);
				clientpp.setBankAccount(bankAccount);
				clientpp.setSuscriptionDate(suscriptionDate);

				ret.add(clientpp);
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
	 * Inserts an instance of premium plus client into database, when a client
	 * already exists in client table.
	 * 
	 * @param clientpp premium plus client to be inserted
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
	 */
	@Override
	public void insert(ClientPP clientpp) throws SQLException, Exception {
		Connection connection = null;
		Statement statement = null;

		try {
			Class.forName(DBUtils.DRIVER);
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			statement = connection.createStatement();

			ClientManager clientManager = new ClientManager();
			clientManager.insert(clientpp);
			ArrayList<Client> clients = (ArrayList<Client>) clientManager.selectAll();

			int idClient = 0;

			for (Client client : clients) {
				if (client.getPersonalId().equalsIgnoreCase(clientpp.getPersonalId()))
					idClient = client.getId();
			}

			String sql = "INSERT INTO ClientPP (idClient,bankAccount) VALUES ( " + idClient + ", '"
					+ clientpp.getBankAccount() + "')";

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
	 * Updates an instance of premium plus client on database by id.
	 * 
	 * @param clientpp premium plus client to be updated
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
	 */
	@Override
	public void update(ClientPP clientpp) throws SQLException, Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			Class.forName(DBUtils.DRIVER);

			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);

			String sql = "UPDATE ClientPP SET bankAccount = ?";

			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, clientpp.getBankAccount());

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
	 * Deletes an instance of premium plus client from database by id.
	 * 
	 * @param clientpp premium plus client to be deleted
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
	 */
	@Override
	public void delete(ClientPP clientpp) throws SQLException, Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			Class.forName(DBUtils.DRIVER);

			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);

			String sql = "DELETE FROM ClientPP WHERE idClient = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, clientpp.getId());

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
	 * Returns an instance of premium plus client on database by id.
	 * 
	 * @param idClient given id of client
	 * @return premium plus client
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
	 */
	public ClientPP getClientPPById(int idClient) throws SQLException, Exception {
		ClientPP ret = null;

		ArrayList<ClientPP> clientPPs = (ArrayList<ClientPP>) doSelectAll();
		if (clientPPs != null) {
			for (ClientPP clientPP : clientPPs) {
				if (clientPP.getId() == idClient) {
					ret = clientPP;
					break;
				}
			}
		}

		return ret;
	}

	/**
	 * Inserts an instance of premium plus client into database.
	 * 
	 * @param clientp premium plus client to be inserted
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
	 */
	public void insertReal(ClientPP clientpp) throws SQLException, Exception {
		Connection connection = null;
		Statement statement = null;

		try {
			Class.forName(DBUtils.DRIVER);
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			statement = connection.createStatement();

			String sql = "INSERT INTO ClientPP (idClient,bankAccount) VALUES ( " + clientpp.getId() + ",'"
					+ clientpp.getBankAccount() + "')";

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

}