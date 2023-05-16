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
import soundbridge.utils.DBUtils;

/**
 * Defines access methods for the ArtGroup table on database.
 */
public class ArtGroupManager extends ManagerAbstract<ArtGroup> {

	/**
	 * Returns all instances of art groups in database or null if there are not art
	 * groups.
	 * 
	 * @return list of art groups or null
	 * @throws SQLException      if there is an error on database
	 * @throws NotFoundException if list is null
	 * @throws Exception         if there is a generic error
	 */
	@Override
	public List<ArtGroup> selectAll() throws SQLException, NotFoundException, Exception {

		ArrayList<ArtGroup> ret = (ArrayList<ArtGroup>) doSelectAll();

		if (null == ret) {
			throw new NotFoundException("There are no group of artists");
		}

		return ret;
	}

	/**
	 * Returns all instances of art groups in database or null if there are not art
	 * groups.
	 * 
	 * @return list of art groups or null
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
	 */
	public List<ArtGroup> doSelectAll() throws SQLException, Exception {
		ArrayList<ArtGroup> ret = null;
		String sql = "SELECT * FROM ArtGroup";

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
					ret = new ArrayList<ArtGroup>();

				ArtGroup artGroup = new ArtGroup();

				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				String description = resultSet.getString("description");
				String image = resultSet.getString("image");

				artGroup.setId(id);
				artGroup.setName(name);
				artGroup.setDescription(description);
				artGroup.setImage(image);

				ret.add(artGroup);
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
	 * Inserts an instance of art group into database.
	 * 
	 * @param artGroup art group to be inserted
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
	 */
	@Override
	public void insert(ArtGroup artGroup) throws SQLException, Exception {
		Connection connection = null;
		Statement statement = null;

		try {
			Class.forName(DBUtils.DRIVER);
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			statement = connection.createStatement();

			String sql = "INSERT INTO ArtGroup (id, name, description, image) VALUES ('" + artGroup.getId() + "', '"
					+ artGroup.getName() + "', '" + artGroup.getDescription() + "', '" + artGroup.getImage() + "')";

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
	 * Updates an instance of art group on database by id.
	 * 
	 * @param artGroup art group to be updated
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
	 */
	@Override
	public void update(ArtGroup artGroup) throws SQLException, Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			Class.forName(DBUtils.DRIVER);

			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);

			String sql = "UPDATE ArtGroup SET name = ?, description = ?, image = ? where id = ?";

			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setInt(4, artGroup.getId());
			preparedStatement.setString(1, artGroup.getName());
			preparedStatement.setString(2, artGroup.getDescription());
			preparedStatement.setString(3, artGroup.getImage());

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
	 * Deletes an instance of art group from database by id.
	 * 
	 * @param artGroup art group to be deleted
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
	 */
	@Override
	public void delete(ArtGroup artGroup) throws SQLException, Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			Class.forName(DBUtils.DRIVER);

			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);

			String sql = "DELETE FROM ArtGroup WHERE id = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, artGroup.getId());

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
	 * Returns an instance of art group by the name.
	 * 
	 * @param nameOfGroup name of the art group
	 * @return art group with the given name
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
	 */
	public ArtGroup getArtGroupByName(String nameOfGroup) throws SQLException, Exception {
		ArtGroup ret = null;

		ArrayList<ArtGroup> groups = (ArrayList<ArtGroup>) doSelectAll();

		if (groups != null) {
			for (ArtGroup group : groups) {
				if (group.getName().equalsIgnoreCase(nameOfGroup)) {
					ret = group;
					break;
				}
			}
		}

		return ret;
	}

	/**
	 * Returns an instance of art group by id.
	 * 
	 * @param id given id of the art group
	 * @return art group with the given id
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
	 */
	public ArtGroup selectGroupById(int id) throws SQLException, Exception {
		ArtGroup ret = null;
		String sql = "SELECT * FROM ArtGroup where id=?";

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
					ret = new ArtGroup();

				ArtGroup artGroup = new ArtGroup();

				int idGroup = resultSet.getInt("id");
				String name = resultSet.getString("name");
				String description = resultSet.getString("description");
				String image = resultSet.getString("image");

				artGroup.setId(idGroup);
				artGroup.setName(name);
				artGroup.setDescription(description);
				artGroup.setImage(image);

				ret = (artGroup);
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
