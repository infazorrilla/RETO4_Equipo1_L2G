package soundbridge.database.managers.pojomanagers;

import java.sql.SQLException;
import java.util.List;

import soundbridge.database.exception.NotFoundException;
import soundbridge.database.managers.ManagerAbstract;
import soundbridge.database.pojos.Employee;

public class EmployeeManager extends ManagerAbstract<Employee> {

	@Override
	public List<Employee> selectAll() throws SQLException, NotFoundException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Employee employee) throws SQLException, Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Employee employee) throws SQLException, Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Employee employee) throws SQLException, Exception {
		// TODO Auto-generated method stub
		
	}

}
