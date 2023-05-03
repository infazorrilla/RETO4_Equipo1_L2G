package soundbridge.database.views.managers;


import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import soundbridge.utils.DBUtils;
import soundbridge.database.pojos.Artist;
import soundbridge.database.pojos.Song;
import soundbridge.database.views.pojos.Top20;

public class Top20ViewManager {
	
	public ArrayList<Top20> selectView() throws SQLException, Exception {
		ArrayList<Top20> ret = null;
		String sql = "SELECT * FROM soundBridge.top20";
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
					ret = new ArrayList<Top20>();
				
				Top20 top = new Top20();
					
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				int plays = resultSet.getInt("plays");
				
				top.setId(id);
				top.setName(name);
				top.setPlays(plays);
				
				ret.add(top);
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
	
	public ArrayList<Song> selectViewTop20AndSongs() throws SQLException, Exception {
		ArrayList<Song> ret = null;
		String sql = "SELECT * FROM soundBridge.top20 join song on soundBridge.top20.name=song.name";
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
					ret = new ArrayList<Song>();
				
				Song song = new Song();
					
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				int duration = resultSet.getInt("duration");
				String source = resultSet.getString("source");
				String genre = resultSet.getString("genre");
				String lang = resultSet.getString("lang");
				//int idAlbum = resultSet.getInt("idAlbum");
				int idArtist = resultSet.getInt("idArtist");
				//int idGroup = resultSet.getInt("idGroup");
				
				if(idArtist!=0) {
					song.setArtist(new Artist());
					song.getArtist().setId(idArtist);
				}
				
				
				song.setId(id);
				song.setName(name);
				song.setDuration(duration);
				song.setSource(source);
				song.setGenre(genre);
				song.setLang(lang);
				//song.setAlbum();
				//song.setArtGroup();
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
