package soundbridge.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import soundbridge.database.utils.DBUtils;

public class Controller {

	public boolean comprobarCliente(String userLogin, String passwdLogin) {

		String sql = "select * from cliente where personalId=? and passwd=?";

		Connection connection = null;

		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;

		try {

			Class.forName(DBUtils.DRIVER);

			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, personalId);
			preparedStatement.setString(2, passwd);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {

				return true;

			}
		} catch (SQLException sqle) {
			System.out.println("Error con la BBDD - " + sqle.getMessage());
		} catch (Exception e) {
			System.out.println("Error generico - " + e.getMessage());
		} finally {

			try {
				if (resultSet != null)
					resultSet.close();
			} catch (Exception e) {

			}
			;
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} catch (Exception e) {

			}
			;
			try {
				if (connection != null)
					connection.close();
			} catch (Exception e) {

			}
			;
		}
		return false;
	}
}
