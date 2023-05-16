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
import soundbridge.database.pojos.Contain;
import soundbridge.database.pojos.Playlist;
import soundbridge.database.pojos.Song;
import soundbridge.utils.DBUtils;

/**
 * Defines access methods for the Contain table on database.
 */
public class ContainManager extends ManagerAbstract<Contain> {

	/**
	 * Returns all instances of contains in database or null if there are not
	 * contains instances.
	 * 
	 * @return list of contains or null
	 * @throws SQLException      if there is an error on database
	 * @throws NotFoundException if list is null
	 * @throws Exception         if there is a generic error
	 */
	@Override
	public List<Contain> selectAll() throws SQLException, NotFoundException, Exception {
		ArrayList<Contain> ret = (ArrayList<Contain>) doSelectAll();

		if (null == ret) {
			throw new NotFoundException("There are no Contains");
		}

		return ret;
	}

	/**
	 * Returns all instances of contains in database or null if there are not
	 * contains instances.
	 * 
	 * @return list of contains or null
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
	 */
	public List<Contain> doSelectAll() throws SQLException, Exception {
		ArrayList<Contain> ret = null;
		String sql = "SELECT * FROM Contain";

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
					ret = new ArrayList<Contain>();

				Contain contain = new Contain();

				int idPlaylist = resultSet.getInt("playlistId");
				int idSong = resultSet.getInt("songId");

				contain.setPlaylist(new Playlist());
				contain.getPlaylist().setId(idPlaylist);
				contain.setSong(new Song());
				contain.getSong().setId(idSong);

				ret.add(contain);
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
	 * Inserts an instance of contain into database.
	 * 
	 * @param contain contain to be inserted
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
	 */
	@Override
	public void insert(Contain contain) throws SQLException, Exception {
		Connection connection = null;
		Statement statement = null;

		try {
			Class.forName(DBUtils.DRIVER);
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			statement = connection.createStatement();

			String sql = "INSERT INTO Contain (playlistId, songId) VALUES (" + contain.getPlaylist().getId() + ", "
					+ contain.getSong().getId() + ")";

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
	 * Updates an instance of contain on database by id.
	 * 
	 * @param contain contain to be updated
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
	 */
	@Override
	public void update(Contain contain) throws SQLException, Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			Class.forName(DBUtils.DRIVER);

			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);

			String sql = "UPDATE Contain SET playlistId = ?, songId = ?";

			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setInt(1, contain.getPlaylist().getId());
			preparedStatement.setInt(2, contain.getSong().getId());

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
	 * Deletes an instance of contain from database by id.
	 * 
	 * @param contain contain to be deleted
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
	 */
	@Override
	public void delete(Contain contain) throws SQLException, Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			Class.forName(DBUtils.DRIVER);

			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);

			String sql = "DELETE FROM Contain WHERE playlistId = ? AND songId = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, contain.getPlaylist().getId());
			preparedStatement.setInt(2, contain.getSong().getId());

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