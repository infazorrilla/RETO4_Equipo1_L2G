package soundbridge.database.managers;

import java.util.List;

import soundbridge.database.exception.NotFoundException;

import java.sql.SQLException;

public interface ManagerInterface<T> {

	public List<T> selectAll() throws SQLException, NotFoundException, Exception;

	public void insert(T t) throws SQLException, Exception;

	public void update(T t) throws SQLException, Exception;

	public void delete(T t) throws SQLException, Exception;

}
