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
 * Checks that the songs are loaded correctly in the Top20View panel table.
 */
public class ArrayListToWindowTest {

	private static Top20ViewManager top20Manager = null;

	/**
	 * Class preparation.
	 */
	@BeforeClass
	public static void setUpBeforeClass() {
		top20Manager = new Top20ViewManager();
	}

	/**
	 * Checks that songs of the top 20 are uploaded correctly in an ArrayList and
	 * displayed in the table of the Top20View panel. Compares the size of the
	 * ArrayList and the row count of the table.
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
