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

import soundbridge.database.pojos.Playlist;
import soundbridge.utils.DBUtils;

/**
 * Defines access methods for the ClientP table on database.
 */
public class ClientPManager extends ManagerAbstract<ClientP> {

	/**
	 * Returns all instances of premium clients in database or null if there are not
	 * premium clients.
	 * 
	 * @return list of premium clients or null
	 * @throws SQLException      if there is an error on database
	 * @throws NotFoundException if list is null
	 * @throws Exception         if there is a generic error
	 */
	@Override
	public List<ClientP> selectAll() throws SQLException, NotFoundException, Exception {
		ArrayList<ClientP> ret = (ArrayList<ClientP>) doSelectAll();

		if (null == ret) {
			throw new NotFoundException("There are no ClientPs");
		}

		return ret;
	}

	/**
	 * Returns all instances of premium clients in database or null if there are not
	 * premium clients.
	 * 
	 * @return list of premium clients or null
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
	 */
	public List<ClientP> doSelectAll() throws SQLException, Exception {
		ArrayList<ClientP> ret = null;
		String sql = "SELECT * FROM ClientP";

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
					ret = new ArrayList<ClientP>();

				ClientP clientp = new ClientP();

				int idClient = resultSet.getInt("idClient");
				String bankAccount = resultSet.getString("bankAccount");
				java.sql.Date sqlsuscriptionDate = resultSet.getDate("suscriptionDate");
				java.util.Date suscriptionDate = new java.util.Date(sqlsuscriptionDate.getTime());

				clientp.setId(idClient);
				clientp.setPlaylist(new Playlist());
				clientp.setBankAccount(bankAccount);
				clientp.setSuscriptionDate(suscriptionDate);

				ret.add(clientp);
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
	 * Inserts an instance of premium client into database, when a client already
	 * exists in client table.
	 * 
	 * @param clientp premium client to be inserted
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
	 */
	@Override
	public void insert(ClientP clientp) throws SQLException, Exception {
		Connection connection = null;
		Statement statement = null;

		try {
			Class.forName(DBUtils.DRIVER);
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			statement = connection.createStatement();

			ClientManager clientManager = new ClientManager();
			clientManager.insert(clientp);
			ArrayList<Client> clients = (ArrayList<Client>) clientManager.selectAll();

			int idClient = 0;

			for (Client client : clients) {
				if ((client instanceof ClientP) && (client.getPersonalId().equalsIgnoreCase(clientp.getPersonalId()))) {
					idClient = client.getId();
				}
			}

			String sql = "INSERT INTO ClientP (idClient,bankAccount) VALUES ( " + idClient + ",'"
					+ clientp.getBankAccount() + "')";

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
	 * Updates an instance of premium client on database by id.
	 * 
	 * @param clientp premium client to be updated
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
	 */
	@Override
	public void update(ClientP clientp) throws SQLException, Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			Class.forName(DBUtils.DRIVER);

			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);

			String sql = "UPDATE ClientP SET bankAccount = ?";

			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, clientp.getBankAccount());

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
	 * Deletes an instance of premium client from database by id.
	 * 
	 * @param clientp premium client to be deleted
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
	 */
	@Override
	public void delete(ClientP clientp) throws SQLException, Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			Class.forName(DBUtils.DRIVER);

			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);

			String sql = "DELETE FROM ClientP WHERE idClient = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, clientp.getId());

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
	 * Returns an instance of premium client on database by id.
	 * 
	 * @param idClient given id of client
	 * @return premium client with given id
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
	 */
	public ClientP getClientPById(int idClient) throws SQLException, Exception {
		ClientP ret = null;

		ArrayList<ClientP> clientPs = (ArrayList<ClientP>) doSelectAll();
		if (clientPs != null) {
			for (ClientP clientP : clientPs) {
				if (clientP.getId() == idClient) {
					ret = clientP;
					break;
				}
			}
		}

		return ret;
	}

	/**
	 * Inserts an instance of premium client into database.
	 * 
	 * @param clientp premium client to be inserted
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
	 */
	public void insertReal(ClientP clientp) throws SQLException, Exception {
		Connection connection = null;
		Statement statement = null;

		try {
			Class.forName(DBUtils.DRIVER);
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			statement = connection.createStatement();

			String sql = "INSERT INTO ClientP (idClient,bankAccount) VALUES ( " + clientp.getId() + ",'"
					+ clientp.getBankAccount() + "')";

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
