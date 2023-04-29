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
import soundbridge.database.pojos.ClientPP;
import soundbridge.database.pojos.Review;
import soundbridge.utils.DBUtils;

public class ReviewManager extends ManagerAbstract<Review> {

	@Override
	public List<Review> selectAll() throws SQLException, NotFoundException, Exception {
		ArrayList<Review> ret = (ArrayList<Review>) doSelectAll();

		if (null == ret) {
			throw new NotFoundException("There are no Reviews");
		}

		return ret;
	}

	public List<Review> doSelectAll() throws SQLException, Exception {
		ArrayList<Review> ret = null;
		String sql = "SELECT * FROM Review";

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
					ret = new ArrayList<Review>();

				Review review = new Review();

				int idClientPP = resultSet.getInt("idClientPP");
				int idAlbum = resultSet.getInt("idAlbum");
				int stars = resultSet.getInt("stars");
				String title = resultSet.getString("title");
				String opinion = resultSet.getString("opinion");
				boolean isValidated = resultSet.getBoolean("isValidated");

				java.sql.Timestamp sqlReviewDate = resultSet.getTimestamp("reviewDate");
				java.util.Date reviewDate = new java.util.Date(sqlReviewDate.getTime());

				review.setClientPP(new ClientPP());
				review.getClientPP().setId(idClientPP);
				review.setAlbum(new Album());
				review.getAlbum().setId(idAlbum);
				review.setStars(stars);
				review.setTitle(title);
				review.setOpinion(opinion);
				review.setReviewDate(reviewDate);
				review.setValidated(isValidated);

				ret.add(review);
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
	public void insert(Review review) throws SQLException, Exception {
		Connection connection = null;
		Statement statement = null;

		try {
			Class.forName(DBUtils.DRIVER);
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			statement = connection.createStatement();

			String sql = "INSERT INTO Review (idClientPP, idAlbum, stars, title, opinion) VALUES ("
					+ review.getClientPP().getId() + ", " + review.getAlbum().getId() + ", " + review.getStars() + ", '"
					+ review.getTitle() + "', '" + review.getOpinion() + "')";

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
	public void update(Review review) throws SQLException, Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			Class.forName(DBUtils.DRIVER);

			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);

			String sql = "UPDATE Review SET stars = ?, title = ?, opinion = ?, reviewDate = ?, isValidaded = ? "
					+ "where idClientPP = ? AND idAlbum = ?";

			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setInt(1, review.getStars());
			preparedStatement.setString(2, review.getTitle());
			preparedStatement.setString(3, review.getOpinion());
			preparedStatement.setDate(4, new java.sql.Date((review.getReviewDate()).getTime()));
			preparedStatement.setInt(5, review.getClientPP().getId());
			preparedStatement.setBoolean(6, review.isValidated());
			preparedStatement.setInt(7, review.getAlbum().getId());

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
	public void delete(Review review) throws SQLException, Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			Class.forName(DBUtils.DRIVER);

			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);

			String sql = "DELETE FROM Review WHERE idClientPP = ? AND idAlbum = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, review.getClientPP().getId());
			preparedStatement.setInt(2, review.getAlbum().getId());

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
