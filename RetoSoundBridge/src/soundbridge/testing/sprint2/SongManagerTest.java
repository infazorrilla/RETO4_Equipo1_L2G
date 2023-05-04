package soundbridge.testing.sprint2;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import soundbridge.database.exception.NotFoundException;
import soundbridge.database.managers.SongManager;
import soundbridge.database.pojos.Album;
import soundbridge.database.pojos.Artist;
import soundbridge.database.pojos.Song;
/**
 * Comprueba los métodos de la clase SongManager. Se especifica un orden
 * para el correcto funcionamiento de las pruebas.
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SongManagerTest {
	
	private static SongManager songManager = null;

	/**
	 * Preparación de la clase.
	 */
	@BeforeClass
	public static void setUpBeforeClass() {
		songManager = new SongManager();
	}

	/**
	 * Comprobación de que una canción se inserta en la base de datos.
	 */
	@Test
	public void testAInsertSong() {
		boolean thrown = false;
		Song song = new Song();

		song.setName("Qué Facilidad");
		try {
			song.setReleaseYear(new SimpleDateFormat("yyyy").parse("2021"));
		} catch (ParseException e1) {
			
		}
		song.setDuration(200);
		song.setLang("Español");
		song.setSource("music/quefacilidad.mp3");
		song.setGenre("Pop");
		song.setAlbum(new Album());
		song.getAlbum().setId(5);
		song.setArtist(new Artist());
		song.getArtist().setId(8);

		try {
			songManager.insert(song);
		} catch (SQLException sqle) {
			thrown = true;
			System.out.println(sqle);
		} catch (Exception e) {
			thrown = true;
		}

		assertFalse(thrown);
	}
	
	/**
	 * Comprobación de que las canciones se cargan correctamente en un ArrayList y
	 * que la canción anteriormente insertada está incluida.
	 */
	@Test
	public void testBSelectAllSongs() {
		boolean thrown = false;
		boolean isInserted = false;

		ArrayList<Song> songs = null;

		try {
			songs = (ArrayList<Song>) songManager.selectAll();

			for (Song song : songs) {
				if (song.getName().equalsIgnoreCase("Qué Facilidad"))
					isInserted = true;
			}

		} catch (NotFoundException nfe) {
			thrown = true;
			System.out.println(nfe);
		} catch (SQLException sqle) {
			thrown = true;
			System.out.println(sqle);
		} catch (Exception e) {
			thrown = true;
			System.out.println(e);
		}

		assertNotNull(songs);
		assertFalse(thrown);
		assertTrue(isInserted);
	}
	
	/**
	 * Comprobación de que una canción se actualiza en la base de datos.
	 */
	@Test
	public void testCUpdateSong() {
		boolean thrown = false;

		ArrayList<Song> songs = null;
		Song insertedSong = null;

		try {
			songs = (ArrayList<Song>) songManager.selectAll();

			for (Song song : songs) {
				if (song.getName().equalsIgnoreCase("Qué Facilidad"))
					insertedSong = song;
			}

			insertedSong.setDuration(250);
			songManager.update(insertedSong);

			songs = (ArrayList<Song>) songManager.selectAll();

			for (Song song : songs) {
				if (song.getName().equalsIgnoreCase("Qué Facilidad"))
					insertedSong = song;
			}

		} catch (NotFoundException nfe) {
			thrown = true;
		} catch (SQLException sqle) {
			thrown = true;
		} catch (Exception e) {
			thrown = true;
		}

		assertNotNull(songs);
		assertFalse(thrown);
		assertTrue(250 == insertedSong.getDuration());
	}
	
	/**
	 * Comprobación de que una canción se elimina de la base de datos.
	 */
	@Test
	public void testDDeleteSong() {
		boolean thrown = false;
		boolean isDeleted = true;

		ArrayList<Song> songs = null;
		Song insertedSong = null;

		try {
			songs = (ArrayList<Song>) songManager.selectAll();

			for (Song song : songs) {
				if (song.getName().equalsIgnoreCase("Qué Facilidad"))
					insertedSong = song;
			}

			songManager.delete(insertedSong);

			songs = (ArrayList<Song>) songManager.selectAll();

			if (songs != null) {
				for (Song song : songs) {
					if (song.getName().equalsIgnoreCase("Qué Facilidad"))
						isDeleted = true;
				}
			} else {
				isDeleted = true;
			}

		} catch (NotFoundException nfe) {

		} catch (SQLException sqle) {
			thrown = true;
		} catch (Exception e) {
			thrown = true;
		}

		assertFalse(thrown);
		assertTrue(isDeleted);
	}

}
