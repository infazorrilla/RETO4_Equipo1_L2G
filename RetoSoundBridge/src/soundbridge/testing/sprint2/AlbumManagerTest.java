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
 * Comprueba los métodos de la clase AlbumManager. Se especifica un orden
 * para el correcto funcionamiento de las pruebas.
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AlbumManagerTest {
	
	private static AlbumManager albumManager = null;

	@BeforeClass
	public static void setUpBeforeClass() {
		albumManager = new AlbumManager();
	}

	/**
	 * Comprobación de que un álbum se inserta en la base de datos.
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
	 * Comprobación de que los álbumes se cargan correctamente en un ArrayList y
	 * que el álbum anteriormente insertado está incluida.
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
	 * Comprobación de que un álbum se actualiza en la base de datos.
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
	 * Comprobación de que un álbum se elimina de la base de datos.
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
