package soundbridge.database.managers;

import java.util.List;

import soundbridge.database.exception.NotFoundException;

import java.sql.SQLException;

/**
 * Defines a manager interface that holds the CRUD access methods for tables on
 * database which must be implemented by other database managers.
 * 
 * @param <T> type of object
 */
public interface ManagerInterface<T> {

	/**
	 * Returns all instances of T in database or null if there are not T.
	 * 
	 * @return list of all instances of T
	 * @throws SQLException      if there is an error on database
	 * @throws NotFoundException if list is null
	 * @throws Exception         if there is a generic error
	 */
	public List<T> selectAll() throws SQLException, NotFoundException, Exception;

	/**
	 * Inserts an instance of T into database.
	 * 
	 * @param t type of object
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
	 */
	public void insert(T t) throws SQLException, Exception;

	/**
	 * Updates an instance of T from database by id.
	 * 
	 * @param t type of object
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
	 */
	public void update(T t) throws SQLException, Exception;

	/**
	 * Deletes an instance of T from database by id.
	 * 
	 * @param t type of object
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
	 */
	public void delete(T t) throws SQLException, Exception;

}
