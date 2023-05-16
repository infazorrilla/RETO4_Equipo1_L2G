package soundbridge.database.managers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import soundbridge.database.exception.NotFoundException;
import soundbridge.database.pojos.Album;
import soundbridge.database.pojos.ArtGroup;
import soundbridge.database.pojos.Artist;
import soundbridge.utils.DBUtils;

/**
 * Defines access methods for the Album table on database.
 */
public class AlbumManager extends ManagerAbstract<Album> {

	/**
	 * Returns all instances of albums in database or null if there are not albums.
	 * 
	 * @return list of albums or null
	 * @throws SQLException      if there is an error on database
	 * @throws NotFoundException if list is null
	 * @throws Exception         if there is a generic error
	 */
	@Override
	public List<Album> selectAll() throws SQLException, NotFoundException, Exception {
		ArrayList<Album> ret = (ArrayList<Album>) doSelectAll();
		if (null == ret) {
			throw new NotFoundException("There are no Albums");
		}
		return ret;
	}

	/**
	 * Returns all instances of albums in database or null if there are not albums.
	 * 
	 * @return list of albums or null
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
	 */
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

	/**
	 * Inserts an instance of album into database.
	 * 
	 * @param album album to be inserted
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
	 */
	@Override
	public void insert(Album album) throws SQLException, Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);

			String sql = "INSERT INTO Album (name, releaseYear, cover) " + "VALUES (?, ?, ?)";

			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, album.getName());
			preparedStatement.setString(2, new SimpleDateFormat("yyyy").format(album.getReleaseYear()));
			preparedStatement.setString(3, album.getCover());

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
	 * Updates an instance of album on database by id.
	 * 
	 * @param album album to be updated
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
	 */
	@Override
	public void update(Album album) throws SQLException, Exception {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			Class.forName(DBUtils.DRIVER);
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			String sql = "UPDATE Album SET name = ?, cover = ?, releaseYear = ? WHERE id = ?";
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, album.getName());
			preparedStatement.setString(2, album.getCover());
			preparedStatement.setString(3, new SimpleDateFormat("yyyy").format(album.getReleaseYear()));
			preparedStatement.setInt(4, album.getId());

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
	 * Deletes an instance of album from database by id.
	 * 
	 * @param album album to be deleted
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
	 */
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

	/**
	 * Returns all instances of albums of an artist.
	 * 
	 * @param artist owner of albums
	 * @return list of albums belonging to the artist
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
	 */
	public List<Album> albumsByArtist(Artist artist) throws SQLException, Exception {
		ArrayList<Album> ret = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			Class.forName(DBUtils.DRIVER);
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);

			String sql = "SELECT DISTINCT A.* FROM Album A JOIN Song S ON A.id = S.idAlbum WHERE S.idArtist = ?";

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, artist.getId());
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				if (null == ret)
					ret = new ArrayList<Album>();
				Album album = new Album();
				int id = resultSet.getInt("A.id");
				String name = resultSet.getString("A.name");
				String cover = resultSet.getString("A.cover");
				java.sql.Date sqlreleaseYear = resultSet.getDate("A.releaseYear");
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

	/**
	 * Returns all instances of albums of an art group.
	 * 
	 * @param artGroup owner of albums
	 * @return list of albums belonging to the art group
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
	 */
	public List<Album> albumsByArtGroup(ArtGroup artGroup) throws SQLException, Exception {
		ArrayList<Album> ret = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			Class.forName(DBUtils.DRIVER);
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);

			String sql = "SELECT DISTINCT A.* FROM Album A JOIN Song S ON A.id = S.idAlbum WHERE S.idGroup = ?";

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, artGroup.getId());
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				if (null == ret)
					ret = new ArrayList<Album>();
				Album album = new Album();
				int id = resultSet.getInt("A.id");
				String name = resultSet.getString("A.name");
				String cover = resultSet.getString("A.cover");
				java.sql.Date sqlreleaseYear = resultSet.getDate("A.releaseYear");
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

	/**
	 * Returns an instance of an album by id.
	 * 
	 * @param idAlbum id of album
	 * @return album with the given id
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
	 */
	public Album albumById(int idAlbum) throws SQLException, Exception {
		Album ret = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			Class.forName(DBUtils.DRIVER);
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);

			String sql = "SELECT * FROM Album WHERE id = ?";

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, idAlbum);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				if (null == ret)
					ret = new Album();

				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				String cover = resultSet.getString("cover");
				java.sql.Date sqlreleaseYear = resultSet.getDate("releaseYear");
				java.util.Date releaseYear = new java.util.Date(sqlreleaseYear.getTime());

				ret.setId(id);
				ret.setName(name);
				ret.setCover(cover);
				ret.setReleaseYear(releaseYear);

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
		return ret;
	}
}
