package soundbridge.database.managers.pojomanagers;

import java.sql.SQLException;
import java.util.List;

import soundbridge.database.exception.NotFoundException;
import soundbridge.database.managers.ManagerAbstract;
import soundbridge.database.pojos.Album;

public class AlbumManager extends ManagerAbstract<Album> {

	@Override
	public List<Album> selectAll() throws SQLException, NotFoundException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Album t) throws SQLException, Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Album t) throws SQLException, Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Album t) throws SQLException, Exception {
		// TODO Auto-generated method stub
		
	}

}
