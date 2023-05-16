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
import soundbridge.database.pojos.Client;
import soundbridge.database.pojos.ClientPP;
import soundbridge.database.pojos.Review;
import soundbridge.utils.DBUtils;

/**
 * Defines access methods for the Review table on database.
 */
public class ReviewManager extends ManagerAbstract<Review> {

	/**
	 * Returns all instances of reviews in database or null if there are not
	 * reviews.
	 * 
	 * @return list of reviews or null
	 * @throws SQLException      if there is an error on database
	 * @throws NotFoundException if list is null
	 * @throws Exception         if there is a generic error
	 */
	@Override
	public List<Review> selectAll() throws SQLException, NotFoundException, Exception {
		ArrayList<Review> ret = (ArrayList<Review>) doSelectAll();

		if (null == ret) {
			throw new NotFoundException("There are no Reviews");
		}

		return ret;
	}

	/**
	 * Returns all instances of reviews in database or null if there are not
	 * reviews.
	 * 
	 * @return list of reviews or null
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
	 */
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

	/**
	 * Inserts an instance of review into database.
	 * 
	 * @param review review to be inserted
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
	 */
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

	/**
	 * Updates an instance of review on database by id.
	 * 
	 * @param review review to be updated
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
	 */
	@Override
	public void update(Review review) throws SQLException, Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			Class.forName(DBUtils.DRIVER);

			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);

			String sql = "UPDATE Review SET stars = ?, title = ?, opinion = ?, reviewDate = ?, isValidated = ? "
					+ "where idClientPP = ? AND idAlbum = ?";

			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setInt(1, review.getStars());
			preparedStatement.setString(2, review.getTitle());
			preparedStatement.setString(3, review.getOpinion());
			preparedStatement.setDate(4, new java.sql.Date((review.getReviewDate()).getTime()));
			preparedStatement.setBoolean(5, review.isValidated());
			preparedStatement.setInt(6, review.getClientPP().getId());
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

	/**
	 * Deletes an instance of review from database by id.
	 * 
	 * @param review review to be deleted
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
	 */
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

	/**
	 * Returns all instances of non validated reviews on database.
	 * 
	 * @return array list of all non validated reviews
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
	 */
	public ArrayList<Review> nonValidatedReviews() throws SQLException, Exception {
		ArrayList<Review> ret = null;
		String sql = "SELECT * FROM Review WHERE isValidated = false ORDER BY reviewDate";

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

	/**
	 * Returns all instances of validated reviews on database by album.
	 * 
	 * @param album album reviewed
	 * @return array list of all validated reviews
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
	 */
	public ArrayList<Review> validatedReviewsByAlbum(Album album) throws SQLException, Exception {
		ArrayList<Review> ret = null;
		String sql = "SELECT * FROM Review WHERE isValidated = true && idAlbum = ? ORDER BY reviewDate DESC";

		Connection connection = null;

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			Class.forName(DBUtils.DRIVER);

			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, album.getId());
			resultSet = preparedStatement.executeQuery();

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
	 * Returns all instances of non validated reviews on database with corresponding
	 * album and client.
	 * 
	 * @return array list of all non validated reviews
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
	 */
	public ArrayList<Review> nonValidatedReviewsWithAllInformation() throws SQLException, Exception {
		ArrayList<Review> ret = nonValidatedReviews();

		AlbumManager albumManager = new AlbumManager();
		ClientManager clientManager = new ClientManager();

		if (ret != null) {
			for (Review review : ret) {
				Album album = albumManager.albumById(review.getAlbum().getId());
				review.setAlbum(album);

				Client client = clientManager.clientById(review.getClientPP().getId());
				review.setClientPP((ClientPP) client);
			}
		}

		return ret;
	}

	/**
	 * Returns all instances of validated reviews on database by album with
	 * corresponding client.
	 * 
	 * @param album album reviewed
	 * @return array list of all validated reviews
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
	 */
	public ArrayList<Review> validatedReviewsWithAllInformation(Album album) throws SQLException, Exception {
		ArrayList<Review> ret = validatedReviewsByAlbum(album);

		ClientManager clientManager = new ClientManager();

		if (ret != null) {
			for (Review review : ret) {
				Client client = clientManager.clientById(review.getClientPP().getId());
				review.setClientPP((ClientPP) client);
				review.setAlbum(album);
			}
		}

		return ret;
	}

	/**
	 * Returns an instance of review on database by premium plus client and album
	 * 
	 * @param clientPP premium plus client that has written the review
	 * @param album    reviewed album
	 * @return review of given client and album
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
	 */
	public Review getReviewByClientPPAndAlbum(ClientPP clientPP, Album album) throws SQLException, Exception {
		Review ret = null;
		String sql = "SELECT * FROM Review WHERE idClientPP = ? && idAlbum = ?";

		Connection connection = null;

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			Class.forName(DBUtils.DRIVER);

			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, clientPP.getId());
			preparedStatement.setInt(2, album.getId());
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				if (null == ret)
					ret = new Review();

				int stars = resultSet.getInt("stars");
				String title = resultSet.getString("title");
				String opinion = resultSet.getString("opinion");
				boolean isValidated = resultSet.getBoolean("isValidated");

				java.sql.Timestamp sqlReviewDate = resultSet.getTimestamp("reviewDate");
				java.util.Date reviewDate = new java.util.Date(sqlReviewDate.getTime());

				ret.setClientPP(clientPP);
				ret.setAlbum(album);
				ret.setStars(stars);
				ret.setTitle(title);
				ret.setOpinion(opinion);
				ret.setReviewDate(reviewDate);
				ret.setValidated(isValidated);
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
