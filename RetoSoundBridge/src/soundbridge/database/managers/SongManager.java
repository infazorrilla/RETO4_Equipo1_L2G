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
import soundbridge.database.pojos.Client;
import soundbridge.database.pojos.Playlist;
import soundbridge.database.pojos.Song;
import soundbridge.utils.DBUtils;

/**
 * Defines access methods for the Song table on database.
 */
public class SongManager extends ManagerAbstract<Song> {

	/**
	 * Returns all instances of songs in database or null if there are not songs.
	 * 
	 * @return list of songs or null
	 * @throws SQLException      if there is an error on database
	 * @throws NotFoundException if list is null
	 * @throws Exception         if there is a generic error
	 */
	@Override
	public List<Song> selectAll() throws SQLException, NotFoundException, Exception {
		ArrayList<Song> ret = (ArrayList<Song>) doSelectAll();
		if (null == ret) {
			throw new NotFoundException("There are no Songs");
		}
		return ret;
	}

	/**
	 * Returns all instances of songs in database or null if there are not songs.
	 * 
	 * @return list of songs or null
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
	 */
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
				String genre = resultSet.getString("genre");
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
				song.setGenre(genre);
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

	/**
	 * Inserts an instance of song into database.
	 * 
	 * @param song song to be inserted
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
	 */
	@Override
	public void insert(Song song) throws SQLException, Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);

			String sql = "INSERT INTO Song (name, releaseYear, duration, cover, lang, source, genre, idAlbum, idArtist, idGroup) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, song.getName());
			preparedStatement.setString(2, new SimpleDateFormat("yyyy").format(song.getReleaseYear()));
			preparedStatement.setInt(3, song.getDuration());
			preparedStatement.setString(4, song.getCover());
			preparedStatement.setString(5, song.getLang());
			preparedStatement.setString(6, song.getSource());
			preparedStatement.setString(7, song.getGenre());
			if (song.getAlbum() != null) {
				preparedStatement.setInt(8, song.getAlbum().getId());
			} else {
				preparedStatement.setString(8, null);
			}
			if (song.getArtist() != null) {
				preparedStatement.setInt(9, song.getArtist().getId());
			} else {
				preparedStatement.setString(9, null);
			}
			if (song.getArtGroup() != null) {
				preparedStatement.setInt(10, song.getArtGroup().getId());
			} else {
				preparedStatement.setString(10, null);
			}

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
	 * Updates an instance of song on database by id.
	 * 
	 * @param song song to be updated
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
	 */
	@Override
	public void update(Song song) throws SQLException, Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			Class.forName(DBUtils.DRIVER);
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			String sql = "UPDATE Song SET  name = ?, releaseYear = ?, duration = ?, cover = ?, lang = ?, source = ?, genre = ? where id = ?";
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, song.getName());
			preparedStatement.setString(2, new SimpleDateFormat("yyyy").format(song.getReleaseYear()));
			preparedStatement.setInt(3, song.getDuration());
			preparedStatement.setString(4, song.getCover());
			preparedStatement.setString(5, song.getLang());
			preparedStatement.setString(6, song.getSource());
			preparedStatement.setString(7, song.getGenre());
			preparedStatement.setInt(8, song.getId());

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
	 * Deletes an instance of song from database by id.
	 * 
	 * @param song song to be deleted
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
	 */
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

	/**
	 * Returns an instance of song on database by id.
	 * 
	 * @param idSong id of song
	 * @return song with given id
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
	 */
	public Song getSongById(int idSong) throws SQLException, Exception {
		Song ret = null;

		ArrayList<Song> songs = (ArrayList<Song>) doSelectAll();

		for (Song song : songs) {
			if (song.getId() == idSong)
				ret = song;
		}

		return ret;
	}

	/**
	 * Returns all instances of songs on database by artist which are not included
	 * in any album.
	 * 
	 * @param artist artist owner of songs
	 * @return array list of songs belonging to the artist not included in any album
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
	 */
	public ArrayList<Song> getSinglesByArtist(Artist artist) throws SQLException, Exception {
		ArrayList<Song> ret = null;

		ArrayList<Song> songs = (ArrayList<Song>) doSelectAll();

		for (Song song : songs) {
			if ((song.getArtist() != null) && (song.getArtist().getId() == artist.getId())
					&& (song.getAlbum() == null)) {
				if (ret == null) {
					ret = new ArrayList<Song>();
				}
				ret.add(song);
			}
		}

		return ret;
	}

	/**
	 * Returns all instances of songs on database by art group which are not
	 * included in any album.
	 * 
	 * @param artGroup art group owner of songs
	 * @return array list of songs belonging to the art group not included in any
	 *         album
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
	 */
	public ArrayList<Song> getSinglesByGroup(ArtGroup artGroup) throws SQLException, Exception {
		ArrayList<Song> ret = null;

		ArrayList<Song> songs = (ArrayList<Song>) doSelectAll();

		for (Song song : songs) {
			if ((song.getArtGroup() != null) && (song.getArtGroup().getId() == artGroup.getId())
					&& (song.getAlbum() == null)) {
				if (ret == null) {
					ret = new ArrayList<Song>();
				}
				ret.add(song);
			}
		}

		return ret;
	}

	/**
	 * Returns all instances of songs on database by album and artist.
	 * 
	 * @param album  album to which songs belong
	 * @param artist artist owner of songs
	 * @return array list of all songs of the artist and album
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
	 */
	public ArrayList<Song> getSongsByAlbumWithArtist(Album album, Artist artist) throws SQLException, Exception {
		ArrayList<Song> ret = null;

		ArrayList<Song> songs = (ArrayList<Song>) doSelectAll();

		for (Song song : songs) {
			if ((song.getAlbum() != null) && (song.getAlbum().getId() == album.getId())) {
				if (ret == null) {
					ret = new ArrayList<Song>();
				}
				song.setArtist(artist);
				ret.add(song);
			}
		}

		return ret;
	}

	/**
	 * Returns all instances of songs on database by album and art group.
	 * 
	 * @param album    album to which songs belong
	 * @param artGroup art group owner of songs
	 * @return array list of all songs of the art group and album
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
	 */
	public ArrayList<Song> getSongsByAlbumWithGroup(Album album, ArtGroup artGroup) throws SQLException, Exception {
		ArrayList<Song> ret = null;

		ArrayList<Song> songs = (ArrayList<Song>) doSelectAll();

		for (Song song : songs) {
			if ((song.getAlbum() != null) && (song.getAlbum().getId() == album.getId())) {
				if (ret == null) {
					ret = new ArrayList<Song>();
				}
				song.setArtGroup(artGroup);
				ret.add(song);
			}
		}

		return ret;
	}

	/**
	 * TODO (no tiene sentido este select porque es un select normal)
	 */
	public List<Song> selectFavouriteSongs() throws SQLException, Exception {
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
				String genre = resultSet.getString("genre");
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
				song.setGenre(genre);
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

	/**
	 * Returns all instances of songs on database by premium plus client that are
	 * included in his favorite named playlist.
	 * 
	 * @param client client owner of playlist
	 * @return array list of all songs included in the playlist
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
	 */
	public ArrayList<Song> selectFavouriteSongOfClientPP(Client client) throws SQLException, Exception {
		ArrayList<Song> ret = null;
		String sql = "select s.id,s.name,s.duration,s.source,s.genre,s.lang,s.idartist,s.idalbum,s.idGroup "
				+ "from contain c join playlist p on p.id=c.playlistid join song s on s.id=c.songid join clientpp cp on cp.idClient=p.idclientpp "
				+ "where p.name='Favoritos' and cp.idClient= ? group by p.name,s.name";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			Class.forName(DBUtils.DRIVER);
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, client.getId());
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				if (null == ret)
					ret = new ArrayList<Song>();

				Song song = new Song();

				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				int duration = resultSet.getInt("duration");
				String source = resultSet.getString("source");
				String genre = resultSet.getString("genre");
				String lang = resultSet.getString("lang");
				// int idAlbum = resultSet.getInt("idAlbum");
				int idArtist = resultSet.getInt("idArtist");
				int idGroup = resultSet.getInt("idGroup");

				if (idArtist != 0) {
					song.setArtist(new Artist());
					song.getArtist().setId(idArtist);
				}
				if (idGroup != 0) {
					song.setArtGroup(new ArtGroup());
					song.getArtGroup().setId(idGroup);
				}

				song.setId(id);
				song.setName(name);
				song.setDuration(duration);
				song.setSource(source);
				song.setGenre(genre);
				song.setLang(lang);
				// song.setAlbum();
				// song.setArtGroup();
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
	 * Returns all instances of songs on database by premium client that are
	 * included in his favorite named playlist.
	 * 
	 * @param client client owner of playlist
	 * @return array list of all songs included in the playlist
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
	 */
	public ArrayList<Song> selectFavouriteSongOfClientP(Client client) throws SQLException, Exception {
		ArrayList<Song> ret = null;
		String sql = "select s.id,s.name,s.duration,s.source,s.genre,s.lang,s.idartist,s.idalbum,s.idGroup "
				+ "from contain c join playlist p on p.id=c.playlistid join song s on s.id=c.songid join clientp cp on cp.idClient=p.idclientp "
				+ "where p.name='Favoritos' and cp.idClient= ? group by p.name,s.name";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			Class.forName(DBUtils.DRIVER);
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, client.getId());
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				if (null == ret)
					ret = new ArrayList<Song>();

				Song song = new Song();

				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				int duration = resultSet.getInt("duration");
				String source = resultSet.getString("source");
				String genre = resultSet.getString("genre");
				String lang = resultSet.getString("lang");
				// int idAlbum = resultSet.getInt("idAlbum");
				int idArtist = resultSet.getInt("idArtist");
				int idGroup = resultSet.getInt("idGroup");

				if (idArtist != 0) {
					song.setArtist(new Artist());
					song.getArtist().setId(idArtist);
				}
				if (idGroup != 0) {
					song.setArtGroup(new ArtGroup());
					song.getArtGroup().setId(idGroup);
				}

				song.setId(id);
				song.setName(name);
				song.setDuration(duration);
				song.setSource(source);
				song.setGenre(genre);
				song.setLang(lang);
				// song.setAlbum();
				// song.setArtGroup();
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
	 * Returns all instances of songs on database by playlist.
	 * 
	 * @param playlist playlist containing the songs
	 * @return array list of songs included on the playlist
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
	 */
	public ArrayList<Song> selectSongsOfPlaylist(Playlist playlist) throws SQLException, Exception {
		ArrayList<Song> ret = null;
		String sql = "select s.id,s.name,s.releaseYear,s.duration,s.cover,s.source,s.genre,s.lang,s.idAlbum,s.idartist,s.idgroup\r\n"
				+ "from song s join contain c on s.id=c.songid\r\n" + "where playlistid=?;";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			Class.forName(DBUtils.DRIVER);
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, playlist.getId());
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				if (null == ret)
					ret = new ArrayList<Song>();

				Song song = new Song();

				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				int duration = resultSet.getInt("duration");
				String source = resultSet.getString("source");
				String genre = resultSet.getString("genre");
				String lang = resultSet.getString("lang");
				// int idAlbum = resultSet.getInt("idAlbum");
				int idArtist = resultSet.getInt("idArtist");
				int idGroup = resultSet.getInt("idGroup");

				if (idArtist != 0) {
					song.setArtist(new Artist());
					song.getArtist().setId(idArtist);
				}
				if (idGroup != 0) {
					song.setArtGroup(new ArtGroup());
					song.getArtGroup().setId(idGroup);
				}

				song.setId(id);
				song.setName(name);
				song.setDuration(duration);
				song.setSource(source);
				song.setGenre(genre);
				song.setLang(lang);
				// song.setAlbum();
				// song.setArtGroup();
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
