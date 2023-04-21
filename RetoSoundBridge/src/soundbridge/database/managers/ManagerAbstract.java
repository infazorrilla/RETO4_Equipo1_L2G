package soundbridge.database.managers;

import java.sql.SQLException;
import java.util.List;

import soundbridge.database.exception.NotFoundException;

public abstract class ManagerAbstract<T> implements ManagerInterface<T> {

	@Override
	public abstract List<T> selectAll() throws SQLException, NotFoundException, Exception;

	@Override
	public abstract void insert(T t) throws SQLException, Exception;

	@Override
	public abstract void update(T t) throws SQLException, Exception;

	@Override
	public abstract void delete(T t) throws SQLException, Exception;

}
