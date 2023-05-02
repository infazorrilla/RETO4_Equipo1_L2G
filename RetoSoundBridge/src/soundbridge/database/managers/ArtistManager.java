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
import soundbridge.database.utils.DBUtils;

public class ArtistManager extends ManagerAbstract<Artist> {

	@Override
	public List<Artist> selectAll() throws SQLException, NotFoundException, Exception {
		
			ArrayList<Artist> ret = (ArrayList<Artist>) doSelectAll();

			if (null == ret) {
				throw new NotFoundException("There are no Artists");
			}

			return ret;
		}
		
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
					java.util.Date birthDate = new java.util.Date(sqlBirthDate.getTime());

					artist.setId(id);
					artist.setName(name);
					artist.setLastName(lastName);
					artist.setNationality(nationality);
					artist.setGender(gender);
					artist.setBirthDate(birthDate);
					artist.setArtGroup(new ArtGroup());
					artist.getArtGroup().setId(idGroup);
					artist.setDescription(description);
					artist.setImage(image);
					artist.setRole(role);

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

	@Override
	public void update(Artist artist) throws SQLException, Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			Class.forName(DBUtils.DRIVER);

			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);

			String sql = "UPDATE Artist SET name = ?, lastName = ?, nationality = ?, gender = ?, birthDate = ? where id = ? "
					;

			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, artist.getName());
			preparedStatement.setString(2, artist.getLastName());
			preparedStatement.setString(3, artist.getNationality());
			preparedStatement.setString(4, artist.getGender());
			preparedStatement.setDate(5, new java.sql.Date((artist.getBirthDate()).getTime()));
			

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
		
	}
