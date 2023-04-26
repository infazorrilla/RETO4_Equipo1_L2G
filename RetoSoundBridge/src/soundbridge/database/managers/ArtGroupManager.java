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
import soundbridge.database.utils.DBUtils;

public class ArtGroupManager extends ManagerAbstract<ArtGroup> {

	@Override
	public List<ArtGroup> selectAll() throws SQLException, NotFoundException, Exception {

		ArrayList<ArtGroup> ret = (ArrayList<ArtGroup>) doSelectAll();

		if (null == ret) {
			throw new NotFoundException("There are no Group of artists");
		}

		return ret;
	}

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

	@Override
	public void update(ArtGroup artGroup) throws SQLException, Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			Class.forName(DBUtils.DRIVER);

			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);

			String sql = "UPDATE ArtGroup SET name = ?, description = ?, image = ? where id = ?";

			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setInt(1, artGroup.getId());
			preparedStatement.setString(2, artGroup.getName());
			preparedStatement.setString(13, artGroup.getDescription());
			preparedStatement.setString(13, artGroup.getImage());

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

}
