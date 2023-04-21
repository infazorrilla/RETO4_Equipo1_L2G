package soundbridge.database.managers;

import java.sql.SQLException;
import java.util.List;

import soundbridge.database.exception.NotFoundException;
import soundbridge.database.pojos.Playlist;

public class PlaylistManager extends ManagerAbstract<Playlist> {

	@Override
	public List<Playlist> selectAll() throws SQLException, NotFoundException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Playlist playlist) throws SQLException, Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Playlist playlist) throws SQLException, Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Playlist playlist) throws SQLException, Exception {
		// TODO Auto-generated method stub
		
	}

}
