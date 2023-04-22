package soundbridge.database.managers.pojomanagers;

import java.sql.SQLException;
import java.util.List;

import soundbridge.database.exception.NotFoundException;
import soundbridge.database.managers.ManagerAbstract;
import soundbridge.database.pojos.Artist;

public class ArtistManager extends ManagerAbstract<Artist> {

	@Override
	public List<Artist> selectAll() throws SQLException, NotFoundException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Artist t) throws SQLException, Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Artist t) throws SQLException, Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Artist t) throws SQLException, Exception {
		// TODO Auto-generated method stub
		
	}

}
