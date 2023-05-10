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
import soundbridge.database.pojos.Client;
import soundbridge.database.pojos.ClientP;
import soundbridge.database.pojos.ClientPP;
import soundbridge.database.pojos.Contain;
import soundbridge.database.pojos.Playlist;
import soundbridge.database.pojos.Song;
import soundbridge.utils.DBUtils;

public class PlaylistManager extends ManagerAbstract<Playlist> {

	@Override
	public List<Playlist> selectAll() throws SQLException, NotFoundException, Exception {
		ArrayList<Playlist> ret = (ArrayList<Playlist>) doSelectAll();

		if (null == ret) {
			throw new NotFoundException("There are no Playlists");
		}

		return ret;
	}

	public List<Playlist> doSelectAll() throws SQLException, Exception {
		ArrayList<Playlist> ret = null;
		String sql = "SELECT * FROM Playlist";

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
					ret = new ArrayList<Playlist>();

				Playlist playlist = new Playlist();

				int id = resultSet.getInt("id");
				int idClientP = resultSet.getInt("idClientP");
				int idClientPp = resultSet.getInt("idClientPp");
				String name = resultSet.getString("name");
				String description = resultSet.getString("description");

				java.sql.Date sqlCreationDate = resultSet.getDate("creationDate");
				java.util.Date creationDate = new java.util.Date(sqlCreationDate.getTime());

				playlist.setId(id);
				playlist.setClientPP(new ClientPP());
				playlist.getClientPP().setId(idClientPp);
				playlist.setClientP(new ClientP());
				playlist.getClientP().setId(idClientP);
				playlist.setName(name);
				playlist.setDescription(description);
				playlist.setCreationDate(creationDate);

				ret.add(playlist);
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
	public void insert(Playlist playlist) throws SQLException, Exception {
		Connection connection = null;
		Statement statement = null;

		try {
			Class.forName(DBUtils.DRIVER);
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			statement = connection.createStatement();

			String sql = "INSERT INTO Playlist (name, description,creationDate,idClienteP,idClientePp) VALUES ("
					+ playlist.getClientPP().getId() + ", " + playlist.getClientP().getId() + ", " + playlist.getId()
					+ ", '" + playlist.getName() + "', '" + playlist.getDescription() + "','"
					+ playlist.getCreationDate() + "')";

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
	public void update(Playlist playlist) throws SQLException, Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			Class.forName(DBUtils.DRIVER);

			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);

			String sql = "UPDATE Playlist SET id = ?, name = ?,creationDate = ?, idClienteP = ?, idClientePp = ?";

			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setInt(1, playlist.getId());
			preparedStatement.setString(2, playlist.getName());
			preparedStatement.setDate(3, new java.sql.Date((playlist.getCreationDate()).getTime()));
			preparedStatement.setInt(4, playlist.getClientP().getId());
			preparedStatement.setInt(5, playlist.getClientPP().getId());

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
	public void delete(Playlist playlist) throws SQLException, Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			Class.forName(DBUtils.DRIVER);

			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);

			String sql = "DELETE FROM Playlist WHERE id = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, playlist.getId());

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

	public ArrayList<Playlist> getPlaylistsOfClientPPById(Client client) throws SQLException, Exception {
		ArrayList<Playlist> ret = null;
		ArrayList<Playlist> playlists = (ArrayList<Playlist>) doSelectAll();
		ArrayList<Contain> playlistContains = new ArrayList<Contain>();

		ContainManager containManager = new ContainManager();
		SongManager songManager = new SongManager();

		if (playlists != null) {
			ret = new ArrayList<Playlist>();
			for (Playlist playlist : playlists) {
				if (playlist.getClientPP().getId() == client.getId()) {

					ArrayList<Song> songs = (ArrayList<Song>) songManager.doSelectAll();
					ArrayList<Contain> contains = (ArrayList<Contain>) containManager.doSelectAll();

					for (Contain contain : contains) {
						if (playlist.getId() == contain.getPlaylist().getId()) {
							for (Song song : songs) {
								if (song.getId() == contain.getSong().getId()) {
									contain.setSong(song);
								}
							}
							playlistContains.add(contain);
						}
					}
					playlist.setContains(playlistContains);
					ret.add(playlist);
				}
			}
		}

		return ret;
	}
	public ArrayList<Playlist> getPlaylistsOfClientPById(Client client) throws SQLException, Exception {
		ArrayList<Playlist> ret = null;
		ArrayList<Playlist> playlists = (ArrayList<Playlist>) doSelectAll();
		ArrayList<Contain> playlistContains = new ArrayList<Contain>();

		ContainManager containManager = new ContainManager();
		SongManager songManager = new SongManager();

		if (playlists != null) {
			ret = new ArrayList<Playlist>();
			for (Playlist playlist : playlists) {
				if (playlist.getClientP().getId() == client.getId()) {

					ArrayList<Song> songs = (ArrayList<Song>) songManager.doSelectAll();
					ArrayList<Contain> contains = (ArrayList<Contain>) containManager.doSelectAll();

					for (Contain contain : contains) {
						if (playlist.getId() == contain.getPlaylist().getId()) {
							for (Song song : songs) {
								if (song.getId() == contain.getSong().getId()) {
									contain.setSong(song);
								}
							}
							playlistContains.add(contain);
						}
					}
					playlist.setContains(playlistContains);
					ret.add(playlist);
				}
			}
		}

		return ret;
	}

}