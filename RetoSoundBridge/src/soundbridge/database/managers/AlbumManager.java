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
import soundbridge.database.pojos.Album;
import soundbridge.database.utils.DBUtils;

public class AlbumManager extends ManagerAbstract<Album> {
	public List<Album> selectAll() throws SQLException, NotFoundException, Exception {
		ArrayList<Album> ret = (ArrayList<Album>) doSelectAll();
		if (null == ret) {
			throw new NotFoundException("There are no Albums");
		}
		return ret;
	}

	public List<Album> doSelectAll() throws SQLException, Exception {
		ArrayList<Album> ret = null;
		String sql = "SELECT * FROM Album";
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
					ret = new ArrayList<Album>();
				Album album = new Album();
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				String cover = resultSet.getString("cover");
				java.sql.Date sqlreleaseYear = resultSet.getDate("releaseYear");
				java.util.Date releaseYear = new java.util.Date(sqlreleaseYear.getTime());

				album.setId(id);
				album.setName(name);
				album.setCover(cover);
				album.setReleaseYear(releaseYear);
				ret.add(album);
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
	public void insert(Album album) throws SQLException, Exception {

		Connection connection = null;
		Statement statement = null;
		try {
			Class.forName(DBUtils.DRIVER);
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			statement = connection.createStatement();
			String sql = "INSERT INTO Album (id, name, cover, releaseYear) VALUES ('" + album.getId() + "', '"
					+ album.getName() + "', '" + album.getCover() + "', '" + album.getReleaseYear() + "')";
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
	public void update(Album album) throws SQLException, Exception {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			Class.forName(DBUtils.DRIVER);
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			String sql = "UPDATE Album SET id = ?, name = ?, cover = ?,  releaseYear = ? where id = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, album.getId());
			preparedStatement.setString(2, album.getName());
			preparedStatement.setString(13, album.getCover());
			preparedStatement.setDate(5, new java.sql.Date((album.getReleaseYear()).getTime()));

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
	public void delete(Album album) throws SQLException, Exception {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			Class.forName(DBUtils.DRIVER);
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			String sql = "DELETE FROM Album WHERE id = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, album.getId());
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
