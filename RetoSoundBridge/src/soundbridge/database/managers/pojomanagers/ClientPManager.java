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

import soundbridge.database.pojos.Playlist;
import soundbridge.database.utils.DBUtils;

public class ClientPManager extends ManagerAbstract<ClientP> {

	@Override
	public List<ClientP> selectAll() throws SQLException, NotFoundException, Exception {
		ArrayList<ClientP> ret = (ArrayList<ClientP>) doSelectAll();

		if (null == ret) {
			throw new NotFoundException("There are no ClientPs");
		}

		return ret;
	}

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
				if (client.getPersonalId().equalsIgnoreCase(clientp.getPersonalId()))
					idClient = client.getId();
			}

			String sql = "INSERT INTO ClientP (idClient,suscriptionDate,bankAccount) VALUES ( " + idClient + ", '"
					+ new java.sql.Date((clientp.getSuscriptionDate()).getTime()) + "','" + clientp.getBankAccount()
					+ "')";

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

	@Override
	public void delete(ClientP clientp) throws SQLException, Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			Class.forName(DBUtils.DRIVER);

			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);

			String sql = "DELETE FROM ClientP WHERE bankAccount = ?";
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

}
