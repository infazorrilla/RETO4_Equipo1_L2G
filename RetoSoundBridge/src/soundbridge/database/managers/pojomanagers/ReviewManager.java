package soundbridge.database.managers.pojomanagers;

import java.sql.SQLException;
import java.util.List;

import soundbridge.database.exception.NotFoundException;
import soundbridge.database.managers.ManagerAbstract;
import soundbridge.database.pojos.Review;

public class ReviewManager extends ManagerAbstract<Review>{

	@Override
	public List<Review> selectAll() throws SQLException, NotFoundException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Review review) throws SQLException, Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Review review) throws SQLException, Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Review review) throws SQLException, Exception {
		// TODO Auto-generated method stub
		
	}

}
