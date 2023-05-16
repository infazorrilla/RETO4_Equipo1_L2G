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
import soundbridge.database.managers.AlbumManager;
import soundbridge.database.pojos.Album;

/**
 * Check the methods of the AlbumManager class. An order is specified for the
 * correct functioning of the tests.
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AlbumManagerTest {

	private static AlbumManager albumManager = null;

	/**
	 * Class preparation.
	 */
	@BeforeClass
	public static void setUpBeforeClass() {
		albumManager = new AlbumManager();
	}

	/**
	 * Checks the insert of an album into the database.
	 */
	@Test
	public void testAInsertAlbum() {
		boolean thrown = false;
		Album album = new Album();

		album.setName("Sensaciones");
		try {
			album.setReleaseYear(new SimpleDateFormat("yyyy").parse("2019"));
		} catch (ParseException e1) {

		}

		album.setCover("img/cover/sensaciones.png");

		try {
			albumManager.insert(album);
		} catch (SQLException sqle) {
			thrown = true;
			System.out.println(sqle);
		} catch (Exception e) {
			thrown = true;
		}

		assertFalse(thrown);
	}

	/**
	 * Checks the loading of albums into an ArrayList and the inclusion of the
	 * previously inserted album.
	 */
	@Test
	public void testBSelectAllAlbums() {
		boolean thrown = false;
		boolean isInserted = false;

		ArrayList<Album> albums = null;

		try {
			albums = (ArrayList<Album>) albumManager.selectAll();

			for (Album album : albums) {
				if (album.getName().equalsIgnoreCase("Sensaciones"))
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

		assertNotNull(albums);
		assertFalse(thrown);
		assertTrue(isInserted);
	}

	/**
	 * Checks the update of an album in the database.
	 */
	@Test
	public void testCUpdateAlbum() {
		boolean thrown = false;

		ArrayList<Album> albums = null;
		Album insertedAlbum = null;

		try {
			albums = (ArrayList<Album>) albumManager.selectAll();

			for (Album album : albums) {
				if (album.getName().equalsIgnoreCase("Sensaciones"))
					insertedAlbum = album;
			}

			insertedAlbum.setCover("img/cover/sensaciones_sensenra.png");
			albumManager.update(insertedAlbum);

			albums = (ArrayList<Album>) albumManager.selectAll();

			for (Album album : albums) {
				if (album.getName().equalsIgnoreCase("Sensaciones"))
					insertedAlbum = album;
			}

		} catch (NotFoundException nfe) {
			thrown = true;
		} catch (SQLException sqle) {
			thrown = true;
		} catch (Exception e) {
			thrown = true;
		}

		assertNotNull(albums);
		assertFalse(thrown);
		assertTrue(("img/cover/sensaciones_sensenra.png").equalsIgnoreCase(insertedAlbum.getCover()));
	}

	/**
	 * Checks the deletion of an album in the database.
	 */
	@Test
	public void testDDeleteSong() {
		boolean thrown = false;
		boolean isDeleted = true;

		ArrayList<Album> albums = null;
		Album insertedAlbum = null;

		try {
			albums = (ArrayList<Album>) albumManager.selectAll();

			for (Album song : albums) {
				if (song.getName().equalsIgnoreCase("Sensaciones"))
					insertedAlbum = song;
			}

			albumManager.delete(insertedAlbum);

			albums = (ArrayList<Album>) albumManager.selectAll();

			if (albums != null) {
				for (Album song : albums) {
					if (song.getName().equalsIgnoreCase("Sensaciones"))
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
