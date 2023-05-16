package soundbridge.testing.sprint2;




import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import soundbridge.database.managers.PlaylistManager;
import soundbridge.database.pojos.Client;
import soundbridge.database.pojos.ClientPP;
import soundbridge.database.pojos.Playlist;

public class SelectPlaylistOfClientTest {

	@Test
	public void test() {
		PlaylistManager playMan = new PlaylistManager();
		Client client = new Client();
			client.setId(6);
		Playlist playlist = new Playlist();
		ClientPP clientPP = new ClientPP();
		ArrayList<Playlist> playlists=null;
		Boolean correct = false;
		playlist.setName("Hola");
		playlist.setDescription("Adios");
		clientPP.setId(6);
		playlist.setClientPP(clientPP);
		try {
			playMan.insertPlaylistClienPP(playlist);
			 playlists=	playMan.selectPlaylistsOfClientPPById(client);
		} catch (Exception e) {
			
		}
		
		if (playlist.getName().equals(playlists.get(0).getName()) && playlist.getDescription().equals(playlists.get(0).getDescription()) && playlist.getClientPP().getId()==playlists.get(0).getClientPP().getId()) {
			correct=true;
		}
		assertTrue(correct);
	}

}
