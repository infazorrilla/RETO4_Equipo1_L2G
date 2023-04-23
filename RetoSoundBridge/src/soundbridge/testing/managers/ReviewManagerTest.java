package soundbridge.testing.managers;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import soundbridge.database.exception.NotFoundException;
import soundbridge.database.managers.pojomanagers.ReviewManager;
import soundbridge.database.pojos.Album;
import soundbridge.database.pojos.ClientPP;
import soundbridge.database.pojos.Review;

/**
 * Comprueba los métodos de la clase ReviewManager. Se especifica un orden para el
 * correcto funcionamiento de las pruebas.
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ReviewManagerTest {

	private static ReviewManager reviewManager = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		reviewManager = new ReviewManager();
	}

	/**
	 * Comprobación de que una valoración se inserta en la base de datos y las
	 * valoraciones se cargan correctamente en un ArrayList. Se compara el tamaño
	 * del array antes de la inserción y después.
	 */
	@Test
	public void testAInsertAndSelectAllReview() {
		boolean thrown = false;
		boolean inserted = false;

		Review review = new Review();

		ClientPP clientPP = new ClientPP();
		clientPP.setId(3);

		Album album = new Album();
		album.setId(1);

		review.setClientPP(clientPP);
		review.setAlbum(album);
		review.setStars(4);
		review.setTitle("Los Dragones son reales");
		review.setOpinion(
				"La banda vuelve a dar en la tecla adecuada, y mantiene la línea trazada en Evolve. Bien, muy bien.");

		ArrayList<Review> reviews = null;
		int arraySizeBefore = 0;
		int arraySizeAfter = 0;

		try {
			reviews = (ArrayList<Review>) reviewManager.doSelectAll();

			if (reviews != null)
				arraySizeBefore = reviews.size();

			reviewManager.insert(review);

			reviews = (ArrayList<Review>) reviewManager.selectAll();
			arraySizeAfter = reviews.size();

			if (arraySizeAfter > arraySizeBefore)
				inserted = true;

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

		assertFalse(thrown);
		assertNotNull(reviews);
		assertTrue(inserted);
	}

	/**
	 * Comprobación de que una valoración se actualiza en la base de datos.
	 */
	@Test
	public void testBUpdateReview() {
		boolean thrown = false;
		boolean isUpdated = true;
		
		ArrayList<Review> reviews = null;
		Review insertedReview = null;
		
		try {
			reviews = (ArrayList<Review>) reviewManager.selectAll();

			insertedReview = reviews.get(reviews.size() - 1);
			insertedReview.setStars(5);
			
			reviewManager.update(insertedReview);
			
			reviews = (ArrayList<Review>) reviewManager.selectAll();
			insertedReview = reviews.get(reviews.size() - 1);
			if (insertedReview.getStars() == 5)
				isUpdated = true;

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
		assertNotNull(reviews);
		assertTrue(isUpdated);
	}

	/**
	 * Comprobación de que una valoración se elimina de la base de datos.
	 */
	@Test
	public void testDeletePlay() {
		boolean thrown = false;
		boolean isDeleted = true;

		ArrayList<Review> reviews = null;
		Review insertedReview = null;
		
		int arraySizeBefore = 0;
		int arraySizeAfter = 0;

		try {
			reviews = (ArrayList<Review>) reviewManager.selectAll();
			arraySizeBefore = reviews.size();

			insertedReview = reviews.get(reviews.size() - 1);

			reviewManager.delete(insertedReview);

			reviews = (ArrayList<Review>) reviewManager.doSelectAll();
			if (reviews != null)
				arraySizeAfter = reviews.size();
			
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
