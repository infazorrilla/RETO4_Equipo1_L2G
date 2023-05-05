package soundbridge.testing.sprint2;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import soundbridge.database.pojos.Song;
import soundbridge.database.views.managers.Top20ViewManager;
import soundbridge.view.panels.Top20View;

/**
 * Comprueba que en el panel Top20View se cargan correctamente las canciones.
 *
 */
public class ArrayListToWindowTest {

	private static Top20ViewManager top20Manager = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		top20Manager = new Top20ViewManager();
	}

	/**
	 * Comprobación de que las canciones que conforman el top 20 se cargan
	 * correctamente en un ArrayList y que se muestran en la tabla del panel
	 * Top20View.
	 */
	@Test
	public void testArrayListToTable() {
		boolean thrown = false;
		boolean isEqualName = true;
		int arraySize = 0;
		int tableRowCount = 0;

		ArrayList<Song> top20Songs = null;
		try {
			top20Songs = top20Manager.selectViewTop20AndSongs();
		} catch (SQLException e) {
			thrown = true;
		} catch (Exception e) {
			thrown = true;
		}

		if (top20Songs != null) {
			Top20View top20View = new Top20View(null, null);
			for (int i = 0; i < top20Songs.size(); i++) {
				String songNameAtTable = (String) top20View.getTableSongsTop20().getValueAt(i, 2);
				String songNameAtArray = top20Songs.get(i).getName();
				if (!songNameAtTable.equalsIgnoreCase(songNameAtArray))
					isEqualName = false;
			}

			arraySize = top20Songs.size();
			tableRowCount = top20View.getTableSongsTop20().getRowCount();
		}

		assertFalse(thrown);
		assertNotNull(top20Songs);
		assertTrue(isEqualName);
		assertTrue((arraySize != 0) && (tableRowCount != 0) && (arraySize == tableRowCount));
	}

}
