package soundbridge.database.managers.pojomanagers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import soundbridge.database.exception.NotFoundException;
import soundbridge.database.managers.ManagerAbstract;
import soundbridge.database.pojos.Song;
import soundbridge.database.utils.DBUtils;

public class SongManager extends ManagerAbstract<Song> {
	@Override
	public List<Song> selectAll() throws SQLException, NotFoundException, Exception {
		ArrayList<Song> ret = (ArrayList<Song>) doSelectAll();
		if (null == ret) {
			throw new NotFoundException("There are no Songs");
		}
		return ret;
	}

	public List<Song> doSelectAll() throws SQLException, Exception {
		ArrayList<Song> ret = null;
		String sql = "SELECT * FROM Song";
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
					ret = new ArrayList<Song>();
				Song song = new Song();
				int id = resultSet.getInt("id");
				String name = null;

				java.sql.Date sqlCreation = resultSet.getDate("creation");
				java.util.Date creation = new java.util.Date(sqlCreation.getTime());

				int duration = 0;
				String cover = null;
				String lang = null;
				
				song.setId(id);
				song.setName(name);
				song.setCreation(creation);
				song.setDuration(duration);
				song.setCover(cover);
				song.setLang(lang);
				ret.add(song);
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
	public void insert(Song song) throws SQLException, Exception {
		Connection connection = null;
		Statement statement = null;
		try {
			Class.forName(DBUtils.DRIVER);
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			statement = connection.createStatement();
			String sql = "INSERT INTO Song (id, name, creation, duration, cover, lang) VALUES ('" + song.getId()
					+ "', '" + song.getName() + "', '" + song.getCreation + "', '" + song.getDuration() + "', '"
					+ song.getCover() + "', '" + song.getLang() + "')";
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
	public void update(Song song) throws SQLException, Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			Class.forName(DBUtils.DRIVER);
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			String sql = "UPDATE Song SET  name = ?, creation = ?, duration = ?, cover = ?, lang = ? where id = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, song.getId());
			preparedStatement.setString(2, song.getName());
			preparedStatement.setDate(5, new java.sql.Date((song.getCreation()).getTime()));
			preparedStatement.setInt(13, song.getDuration);
			preparedStatement.setString(13, song.getCover);
			preparedStatement.setString(13, song.getLang);
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
	public void delete(Song song) throws SQLException, Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			Class.forName(DBUtils.DRIVER);
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			String sql = "DELETE FROM Song WHERE id = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, song.getId());
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
