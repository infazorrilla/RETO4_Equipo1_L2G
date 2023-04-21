package soundbridge.database.managers;

import java.sql.SQLException;
import java.util.List;

import soundbridge.database.exception.NotFoundException;
import soundbridge.database.pojos.Employee;

public class ManagerEmployee extends ManagerAbstract<Employee> {

	@Override
	public List<Employee> selectAll() throws SQLException, NotFoundException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Employee t) throws SQLException, Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Employee t) throws SQLException, Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Employee t) throws SQLException, Exception {
		// TODO Auto-generated method stub
		
	}

}
