package soundbridge.testing.sprint1;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import soundbridge.database.exception.NotFoundException;
import soundbridge.database.managers.PlayManager;
import soundbridge.database.pojos.Client;
import soundbridge.database.pojos.Play;
import soundbridge.database.pojos.Song;

/**
 * Check the methods of the PlayManager class. An order is specified for the
 * correct functioning of the tests.
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PlayManagerTest {

	private static PlayManager playManager = null;

	/**
	 * Class preparation.
	 */
	@BeforeClass
	public static void setUpBeforeClass() {
		playManager = new PlayManager();
	}

	/**
	 * Checks the insert of a play into the database. Also checks the loading of
	 * plays into an ArrayList and the inclusion of the previously inserted play.
	 * Compares the ArrayList size before and after the insert.
	 */
	@Test
	public void testAInsertAndSelectAllPlay() {
		boolean thrown = false;
		boolean isInserted = false;

		Play play = new Play();

		Client client = new Client();
		client.setId(1);

		Song song = new Song();
		song.setId(1);

		play.setClient(client);
		play.setSong(song);

		ArrayList<Play> plays = null;
		int arraySizeBefore = 0;
		int arraySizeAfter = 0;

		try {
			plays = (ArrayList<Play>) playManager.doSelectAll();

			if (plays != null)
				arraySizeBefore = plays.size();

			playManager.insert(play);

			plays = (ArrayList<Play>) playManager.selectAll();
			arraySizeAfter = plays.size();

			if (arraySizeAfter > arraySizeBefore)
				isInserted = true;

		} catch (NotFoundException nfe) {
			thrown = true;
		} catch (SQLException sqle) {
			thrown = true;
		} catch (Exception e) {
			thrown = true;
		}

		assertFalse(thrown);
		assertNotNull(plays);
		assertTrue(isInserted);
	}

	/**
	 * Checks the update of a play in the database.
	 */
	@Test
	public void testBUpdatePlay() {
		boolean thrown = false;
		boolean isUpdated = true;

		ArrayList<Play> plays = null;
		Play insertedPlay = null;

		try {
			plays = (ArrayList<Play>) playManager.selectAll();

			insertedPlay = plays.get(plays.size() - 1);
			insertedPlay.getClient().setId(2);

			playManager.update(insertedPlay);

			plays = (ArrayList<Play>) playManager.selectAll();
			for (Play play : plays) {
				if (play.getId() == insertedPlay.getId()) {
					if (play.getClient().getId() == 2)
						isUpdated = true;
				}
			}

		} catch (NotFoundException nfe) {
			thrown = true;
		} catch (SQLException sqle) {
			thrown = true;
			System.out.println(sqle);
		} catch (Exception e) {
			thrown = true;
			System.out.println(e);
		}

		assertFalse(thrown);
		assertNotNull(plays);
		assertTrue(isUpdated);
	}

	/**
	 * Checks the deletion of a play in the database.
	 */
	@Test
	public void testDeletePlay() {
		boolean thrown = false;
		boolean isDeleted = true;

		ArrayList<Play> plays = null;
		Play insertedPlay = null;

		int arraySizeBefore = 0;
		int arraySizeAfter = 0;

		try {
			plays = (ArrayList<Play>) playManager.selectAll();
			arraySizeBefore = plays.size();

			insertedPlay = plays.get(plays.size() - 1);

			playManager.delete(insertedPlay);

			plays = (ArrayList<Play>) playManager.doSelectAll();
			if (plays != null)
				arraySizeAfter = plays.size();

			if (arraySizeAfter < arraySizeBefore)
				isDeleted = true;

		} catch (NotFoundException nfe) {
			thrown = true;
		} catch (SQLException sqle) {
			thrown = true;
		} catch (Exception e) {
			thrown = true;
		}

		assertFalse(thrown);
		assertTrue(isDeleted);
	}

}
