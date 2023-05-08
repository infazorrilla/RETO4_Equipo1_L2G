package soundbridge.testing.sprint2;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import soundbridge.database.exception.NotFoundException;
import soundbridge.database.managers.ArtistManager;
import soundbridge.database.pojos.Artist;

public class InclusionArraylist {

	@Test
	public void test() {

		ArtistManager artistman = new ArtistManager();
		ArrayList<Artist> artists = null;
		Artist artistt = null;
		boolean correct = false;

		try {

			artists = (ArrayList<Artist>) artistman.selectAll();
			artistt = artists.get(0);
			if (artistt.getName().equals("Dan") && artistt.getRole().equals("Cantante")) {
				artistt = artists.get(1);
				if (artistt.getName().equals("Wayne") && artistt.getRole().equals("Guitarrista")) {
					correct = true;
				}
			}

		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(correct = true);
	}

}
