package soundbridge.testing.managers;

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
 * Comprueba los métodos de la clase PlayManager. Se especifica un orden para el
 * correcto funcionamiento de las pruebas.
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PlayManagerTest {

	private static PlayManager playManager = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		playManager = new PlayManager();
	}

	/**
	 * Comprobación de que una reproducción se inserta en la base de datos y las
	 * reproducciones se cargan correctamente en un ArrayList. Se compara el tamaño
	 * del array antes de la inserción y después.
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
	 * Comprobación de que una reproducción se actualiza en la base de datos.
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
	 * Comprobación de que una reproducción se elimina de la base de datos.
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
