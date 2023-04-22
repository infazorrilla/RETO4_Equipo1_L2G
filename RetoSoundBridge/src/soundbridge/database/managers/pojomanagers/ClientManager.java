package soundbridge.database.managers.pojomanagers;

import java.sql.SQLException;
import java.util.List;

import soundbridge.database.exception.NotFoundException;
import soundbridge.database.managers.ManagerAbstract;
import soundbridge.database.pojos.Client;

public class ClientManager extends ManagerAbstract<Client> {

	@Override
	public List<Client> selectAll() throws SQLException, NotFoundException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Client client) throws SQLException, Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Client client) throws SQLException, Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Client client) throws SQLException, Exception {
		// TODO Auto-generated method stub
		
	}

}
