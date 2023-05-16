package soundbridge.database.views.managers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Statement;

import soundbridge.utils.DBUtils;
import soundbridge.database.pojos.Album;
import soundbridge.database.views.pojos.AverageStars;

/**
 * Defines access methods for the AverageStars view on database which contains
 * the average stars of albums.
 */
public class AverageStarsManager {

	/**
	 * Returns all instances of average stars in database or null if there are not
	 * average stars.
	 * 
	 * @return array list of average stars or null
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
	 */
	public ArrayList<AverageStars> selectView() throws SQLException, Exception {
		ArrayList<AverageStars> ret = null;
		String sql = "SELECT * FROM soundBridge.averagestars";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			Class.forName(DBUtils.DRIVER);
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery(sql);
			while (resultSet.next()) {
				if (null == ret)
					ret = new ArrayList<AverageStars>();

				AverageStars averageStars = new AverageStars();

				int idAlbum = resultSet.getInt("id");
				String name = resultSet.getString("name");
				double average = resultSet.getDouble("average");

				averageStars.setId(idAlbum);
				averageStars.setName(name);
				averageStars.setAverage(average);

				ret.add(averageStars);
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
	 * Returns an instance of average stars on database by album.
	 * 
	 * @param album given album
	 * @return average stars of given album
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
	 */
	public AverageStars getAverageStarsByAlbum(Album album) throws SQLException, Exception {
		AverageStars ret = null;
		String sql = "SELECT * FROM soundBridge.averagestars WHERE id = " + album.getId();

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
					ret = new AverageStars();

				int idAlbum = resultSet.getInt("id");
				String name = resultSet.getString("name");
				double average = resultSet.getDouble("average");

				ret.setId(idAlbum);
				ret.setName(name);
				ret.setAverage(average);

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

}
