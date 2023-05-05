package soundbridge.testing.sprint2;

import static org.junit.Assert.*;

import java.sql.SQLException;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import soundbridge.database.exception.NotFoundException;
import soundbridge.database.managers.ArtGroupManager;

import soundbridge.database.pojos.ArtGroup;

/**
 * Comprueba los métodos de la clase SongManager. Se especifica un orden para el
 * correcto funcionamiento de las pruebas.
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ArtGroupManagerTest {

	private static ArtGroupManager artgroupmanager = null;

	/**
	 * Preparación de la clase.
	 */
	@BeforeClass
	public static void setUpBeforeClass() {
		artgroupmanager = new ArtGroupManager();
	}

	/**
	 * Comprobación de que una canción se inserta en la base de datos.
	 */
	@Test
	public void testAInsertArtGroup() {
		boolean thrown = false;
		ArtGroup artgroup = new ArtGroup();

		artgroup.setName("Gonzalo y los panas");

		artgroup.setDescription("Hola k ase");
		artgroup.setImage(null);

		try {
			artgroupmanager.insert(artgroup);
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
	public void testBSelectAllArtGroups() {
		boolean thrown = false;
		boolean isInserted = false;

		ArrayList<ArtGroup> artgroups = null;

		try {
			artgroups = (ArrayList<ArtGroup>) artgroupmanager.selectAll();

			for (ArtGroup artgroup : artgroups) {
				if (artgroup.getName().equalsIgnoreCase("Gonzalo y los panas"))
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

		assertNotNull(artgroups);
		assertFalse(thrown);
		assertTrue(isInserted);
	}

	/**
	 * Comprobación de que una canción se actualiza en la base de datos.
	 */
	@Test
	public void testCUpdateArtGroups() {
		boolean thrown = false;

		ArrayList<ArtGroup> artgroups = null;
		ArtGroup insertedArtGroup = null;

		try {
			artgroups = (ArrayList<ArtGroup>) artgroupmanager.selectAll();

			for (ArtGroup artgroup : artgroups) {
				if (artgroup.getName().equalsIgnoreCase("Gonzalo y los panas"))
					insertedArtGroup = artgroup;
			}

			insertedArtGroup.setName("Los panas y Gonzalo");

			artgroupmanager.update(insertedArtGroup);

			artgroups = (ArrayList<ArtGroup>) artgroupmanager.selectAll();

			for (ArtGroup artgroup : artgroups) {
				if (artgroup.getName().equalsIgnoreCase("Los panas y Gonzalo"))
					insertedArtGroup = artgroup;
			}

		} catch (NotFoundException nfe) {
			thrown = true;
		} catch (SQLException sqle) {
			thrown = true;
		} catch (Exception e) {
			thrown = true;
		}

		assertNotNull(artgroups);
		assertFalse(thrown);
		assertTrue(insertedArtGroup.getName().equals("Los panas y Gonzalo"));
	}

	/**
	 * Comprobación de que una canción se elimina de la base de datos.
	 */
	@Test
	public void testDDeleteArtGroups() {
		boolean thrown = false;
		boolean isDeleted = true;

		ArrayList<ArtGroup> artgroups = null;
		ArtGroup insertedArtGroup = null;

		try {
			artgroups = (ArrayList<ArtGroup>) artgroupmanager.selectAll();

			for (ArtGroup artgroup : artgroups) {
				if (artgroup.getName().equalsIgnoreCase("Los panas y Gonzalo"))
					insertedArtGroup = artgroup;
			}

			artgroupmanager.delete(insertedArtGroup);

			artgroups = (ArrayList<ArtGroup>) artgroupmanager.selectAll();

			if (artgroups != null) {
				for (ArtGroup artgroup : artgroups) {
					if (artgroup.getName().equalsIgnoreCase("Los panas y Gonzalo"))
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