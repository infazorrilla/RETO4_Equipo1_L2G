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
import soundbridge.database.managers.ArtistManager;

import soundbridge.database.pojos.Artist;

/**
 * Check the methods of the ArtistManager class. An order is specified for the
 * correct functioning of the tests.
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ArtistManagerTest {

	private static ArtistManager artistManager = null;

	/**
	 * Class preparation.
	 */
	@BeforeClass
	public static void setUpBeforeClass() {
		artistManager = new ArtistManager();
	}

	/**
	 * Checks the insert of an artist into the database.
	 */
	@Test
	public void testAInsertArtist() {
		boolean thrown = false;
		Artist artist = new Artist();

		artist.setName("DJGonzalo");
		try {
			artist.setBirthDate(new SimpleDateFormat("yyyy/MM/dd").parse("2003/10/01"));
		} catch (ParseException e1) {

		}
		artist.setLastName("Barrasa");
		artist.setNationality("Españita");
		artist.setGender("Hombre");
		artist.setDescription("Hola");
		artist.setImage(null);

		try {
			artistManager.insert(artist);
		} catch (SQLException sqle) {
			thrown = true;
			System.out.println(sqle);
		} catch (Exception e) {
			thrown = true;
		}

		assertFalse(thrown);
	}

	/**
	 * Checks the loading of artists into an ArrayList and the inclusion of the
	 * previously inserted album.
	 */
	@Test
	public void testBSelectAllArtists() {
		boolean thrown = false;
		boolean isInserted = false;

		ArrayList<Artist> artists = null;

		try {
			artists = (ArrayList<Artist>) artistManager.selectAll();

			for (Artist artist : artists) {
				if (artist.getName().equalsIgnoreCase("DJGonzalo"))
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

		assertNotNull(artists);
		assertFalse(thrown);
		assertTrue(isInserted);
	}

	/**
	 * Checks the update of an artist in the database.
	 */
	@Test
	public void testCUpdateArtist() {
		boolean thrown = false;

		ArrayList<Artist> artists = null;
		Artist insertedArtist = null;

		try {
			artists = (ArrayList<Artist>) artistManager.selectAll();

			for (Artist artist : artists) {
				if (artist.getName().equalsIgnoreCase("DJGonzalo"))
					insertedArtist = artist;
			}

			insertedArtist.setNationality("Peruano");

			artistManager.update(insertedArtist);

			artists = (ArrayList<Artist>) artistManager.selectAll();

			for (Artist artist : artists) {
				if (artist.getName().equalsIgnoreCase("DJGonzalo"))
					insertedArtist = artist;
			}

		} catch (NotFoundException nfe) {
			thrown = true;
		} catch (SQLException sqle) {
			thrown = true;
		} catch (Exception e) {
			thrown = true;
		}

		assertNotNull(artists);
		assertFalse(thrown);
		assertTrue(insertedArtist.getNationality().equals("Peruano"));
	}

	/**
	 * Checks the deletion of an artist in the database.
	 */
	@Test
	public void testDDeleteArtist() {
		boolean thrown = false;
		boolean isDeleted = true;

		ArrayList<Artist> artists = null;
		Artist insertedArtist = null;

		try {
			artists = (ArrayList<Artist>) artistManager.selectAll();

			for (Artist artist : artists) {
				if (artist.getName().equalsIgnoreCase("DJGonzalo"))
					insertedArtist = artist;
			}

			artistManager.delete(insertedArtist);

			artists = (ArrayList<Artist>) artistManager.selectAll();

			if (artists != null) {
				for (Artist artist : artists) {
					if (artist.getName().equalsIgnoreCase("DJGonzalo"))
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
