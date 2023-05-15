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
 * Check the methods of the SongManager class. An order is specified for the
 * correct functioning of the tests.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SongManagerTest {

	private static SongManager songManager = null;

	/**
	 * Class preparation.
	 */
	@BeforeClass
	public static void setUpBeforeClass() {
		songManager = new SongManager();
	}

	/**
	 * Checks the insert of a song into the database.
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
	 * Checks the loading of songs into an ArrayList and the inclusion of the
	 * previously inserted song.
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
	 * Checks the update of a song in the database.
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
	 * Checks the deletion of a song in the database.
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
						isDeleted = false;
				}
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
