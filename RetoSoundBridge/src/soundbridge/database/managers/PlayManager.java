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
import soundbridge.database.pojos.Play;
import soundbridge.database.pojos.Song;
import soundbridge.database.utils.DBUtils;

public class PlayManager extends ManagerAbstract<Play> {

	@Override
	public List<Play> selectAll() throws SQLException, NotFoundException, Exception {
		ArrayList<Play> ret = (ArrayList<Play>) doSelectAll();

		if (null == ret) {
			throw new NotFoundException("There are no Plays");
		}

		return ret;
	}
	
	public List<Play> doSelectAll() throws SQLException, Exception {
		ArrayList<Play> ret = null;
		String sql = "SELECT * FROM Play";

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
					ret = new ArrayList<Play>();

				Play play = new Play();

				int id = resultSet.getInt("id");

				java.sql.Timestamp sqlPlayDate = resultSet.getTimestamp("playDate");
				java.util.Date playDate = new java.util.Date(sqlPlayDate.getTime());
				
				int idClient = resultSet.getInt("idClient");				
				int idSong = resultSet.getInt("idSong");
				
				play.setId(id);
				play.setPlayDate(playDate);
				play.setClient(new Client());
				play.getClient().setId(idClient);
				play.setSong(new Song());
				play.getSong().setId(idSong);

				ret.add(play);
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
	public void insert(Play play) throws SQLException, Exception {
		Connection connection = null;
		Statement statement = null;

		try {
			Class.forName(DBUtils.DRIVER);
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			statement = connection.createStatement();

			String sql = "INSERT INTO Play (idClient, idSong) VALUES ("
					+ play.getClient().getId() + ", " + play.getSong().getId() + ")";

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
	public void update(Play play) throws SQLException, Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			Class.forName(DBUtils.DRIVER);

			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);

			String sql = "UPDATE Play SET idClient = ?, idSong = ?, playDate = ? where id = ?";

			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setInt(1, play.getClient().getId());
			preparedStatement.setInt(2, play.getSong().getId());
			preparedStatement.setDate(3, new java.sql.Date((play.getPlayDate()).getTime()));
			preparedStatement.setInt(4, play.getId());

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
	public void delete(Play play) throws SQLException, Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			Class.forName(DBUtils.DRIVER);

			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);

			String sql = "DELETE FROM Play WHERE id = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, play.getId());

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
