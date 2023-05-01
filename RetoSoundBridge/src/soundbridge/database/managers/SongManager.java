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
import soundbridge.database.pojos.ArtGroup;
import soundbridge.database.pojos.Artist;
import soundbridge.database.pojos.Song;
import soundbridge.utils.DBUtils;

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
				String name = resultSet.getString("name");

				java.sql.Date sqlReleaseYear = resultSet.getDate("releaseYear");
				java.util.Date releaseYear = new java.util.Date(sqlReleaseYear.getTime());

				int duration = resultSet.getInt("duration");
				String cover = resultSet.getString("cover");
				String lang = resultSet.getString("lang");
				String source = resultSet.getString("source");
				int idAlbum = resultSet.getInt("idAlbum");
				int idArtist = resultSet.getInt("idArtist");
				int idGroup = resultSet.getInt("idGroup");
				
				song.setId(id);
				song.setName(name);
				song.setReleaseYear(releaseYear);
				song.setDuration(duration);
				song.setCover(cover);
				song.setLang(lang);
				song.setSource(source);
				if (idAlbum != 0) {
					song.setAlbum(new Album());
					song.getAlbum().setId(idAlbum);
				}
				if (idArtist != 0) {
					song.setArtist(new Artist());
					song.getArtist().setId(idArtist);
				}
				if (idGroup != 0) {
					song.setArtGroup(new ArtGroup());
					song.getArtGroup().setId(idGroup);
				}
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
					+ "', '" + song.getName() + "', '" + new java.sql.Date((song.getReleaseYear()).getTime()) + "', '" + song.getDuration() + "', '"
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
			String sql = "UPDATE Song SET  name = ?, releaseYear = ?, duration = ?, cover = ?, lang = ? where id = ?";
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, song.getName());
			preparedStatement.setDate(2, new java.sql.Date((song.getReleaseYear()).getTime()));
			preparedStatement.setInt(3, song.getDuration());
			preparedStatement.setString(4, song.getCover());
			preparedStatement.setString(5, song.getLang());
			preparedStatement.setInt(6, song.getId());
			
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
	
	public Song getSongById(int idSong) throws SQLException, Exception {
		Song ret = null;
	
		ArrayList<Song> songs = (ArrayList<Song>) doSelectAll();
		
		for (Song song : songs) {
			if (song.getId() == idSong)
				ret = song;
		}
		
		return ret;
	}
}
