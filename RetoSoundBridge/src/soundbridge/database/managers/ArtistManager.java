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
import soundbridge.database.pojos.ArtGroup;
import soundbridge.database.pojos.Artist;
import soundbridge.utils.DBUtils;

/**
 * Defines access methods for the Artist table on database.
 */
public class ArtistManager extends ManagerAbstract<Artist> {

	/**
	 * Returns all instances of artists in database or null if there are not
	 * artists. Throws the NotFoundException.
	 * 
	 * @return list of artists or null
	 * @throws SQLException if there is an error on database
	 * @throws NotFoundException if list is null
	 * @throws Exception if there is a generic error
	 */
	@Override
	public List<Artist> selectAll() throws SQLException, NotFoundException, Exception {

		ArrayList<Artist> ret = (ArrayList<Artist>) doSelectAll();

		if (null == ret) {
			throw new NotFoundException("There are no Artists");
		}

		return ret;
	}

	/**
	 * Selects all artists of the database.
	 * 
	 * @return list of all the artists
	 * @throws SQLException
	 * @throws Exception
	 */
	public List<Artist> doSelectAll() throws SQLException, Exception {
		ArrayList<Artist> ret = null;
		String sql = "SELECT * FROM Artist";

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
					ret = new ArrayList<Artist>();

				Artist artist = new Artist();

				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				String lastName = resultSet.getString("lastName");
				String nationality = resultSet.getString("nationality");
				String gender = resultSet.getString("gender");
				int idGroup = resultSet.getInt("idGroup");
				String description = resultSet.getString("description");
				String image = resultSet.getString("image");
				String role = resultSet.getString("role");

				java.sql.Date sqlBirthDate = resultSet.getDate("birthDate");
				java.util.Date birthDate = null;
				if (null != sqlBirthDate) {
					birthDate = new java.util.Date(sqlBirthDate.getTime());
				}

				artist.setId(id);
				artist.setName(name);
				artist.setLastName(lastName);
				artist.setNationality(nationality);
				artist.setGender(gender);
				artist.setBirthDate(birthDate);
				artist.setDescription(description);
				artist.setImage(image);
				artist.setRole(role);

				if (idGroup != 0) {
					artist.setArtGroup(new ArtGroup());
					artist.getArtGroup().setId(idGroup);
				}

				ret.add(artist);
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
	 * Inserts an artist into the database.
	 * 
	 * @param artist artist to be inserted
	 * @throws SQLException
	 * @throws Exception
	 */
	@Override
	public void insert(Artist artist) throws SQLException, Exception {
		Connection connection = null;
		Statement statement = null;

		try {
			Class.forName(DBUtils.DRIVER);
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			statement = connection.createStatement();

			String sql = "INSERT INTO Artist (name, lastName, nationality, gender, birthDate) VALUES ('"
					+ artist.getName() + "', '" + artist.getLastName() + "', '" + artist.getNationality() + "', '"
					+ artist.getGender() + "', '" + new java.sql.Date((artist.getBirthDate()).getTime()) + "')";

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
	 * Updates an artist by id in the database.
	 * 
	 * @param artist artist to be updated
	 * @throws SQLException
	 * @throws Exception
	 */
	@Override
	public void update(Artist artist) throws SQLException, Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			Class.forName(DBUtils.DRIVER);

			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);

			String sql = "UPDATE Artist SET name = ?, lastName = ?, nationality = ?, gender = ?, birthDate = ? where id = ? ";

			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, artist.getName());
			preparedStatement.setString(2, artist.getLastName());
			preparedStatement.setString(3, artist.getNationality());
			preparedStatement.setString(4, artist.getGender());
			preparedStatement.setDate(5, new java.sql.Date((artist.getBirthDate()).getTime()));
			preparedStatement.setInt(6, artist.getId());
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
	 * Deletes an artist from the database.
	 * 
	 * @param artist artist to be deleted
	 * @throws SQLException
	 * @throws Exception
	 */
	@Override
	public void delete(Artist artist) throws SQLException, Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			Class.forName(DBUtils.DRIVER);

			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);

			String sql = "DELETE FROM Artist WHERE id = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, artist.getId());

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
	 * Selects an artist by the name.
	 * 
	 * @param nameOfArtist given name of the artist
	 * @return artist with the given name
	 * @throws SQLException
	 * @throws Exception
	 */
	public Artist getArtistByName(String nameOfArtist) throws SQLException, Exception {
		Artist ret = null;

		ArrayList<Artist> artists = (ArrayList<Artist>) doSelectAll();

		if (artists != null) {
			for (Artist artist : artists) {
				if (artist.getName().equalsIgnoreCase(nameOfArtist) && (artist.getArtGroup() == null)) {
					ret = artist;
					break;
				}
			}
		}

		return ret;
	}

	/**
	 * Selects an artist by id.
	 * 
	 * @param id given id for the artist
	 * @return artist with the given id
	 * @throws SQLException
	 * @throws Exception
	 */
	public Artist selectArtistById(int id) throws SQLException, Exception {
		Artist ret = null;
		String sql = "SELECT * FROM Artist where id=?";

		Connection connection = null;

		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;

		try {
			Class.forName(DBUtils.DRIVER);

			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				if (null == ret)
					ret = new Artist();

				Artist artist = new Artist();

				int idArtist = resultSet.getInt("id");
				String name = resultSet.getString("name");
				String lastName = resultSet.getString("lastName");
				String nationality = resultSet.getString("nationality");
				String gender = resultSet.getString("gender");

				String description = resultSet.getString("description");
				String image = resultSet.getString("image");
				String role = resultSet.getString("role");

				java.sql.Date sqlBirthDate = resultSet.getDate("birthDate");
				java.util.Date birthDate = null;
				if (null != sqlBirthDate) {
					birthDate = new java.util.Date(sqlBirthDate.getTime());
				}

				artist.setId(idArtist);
				artist.setName(name);
				artist.setLastName(lastName);
				artist.setNationality(nationality);
				artist.setGender(gender);

				artist.setBirthDate(birthDate);
				artist.setDescription(description);
				artist.setImage(image);
				artist.setRole(role);

				ret = artist;
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
