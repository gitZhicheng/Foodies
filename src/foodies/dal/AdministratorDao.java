package foodies.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import food.dal.ConnectionManager;
import food.model.Administrator;
import food.model.Users;
import foodies.model.*;


public class AdministratorDao extends UsersDao{
	protected ConnectionManager connectionManager;

	private static AdministratorDao instance = null;
	protected AdministratorDao() {
		connectionManager = new ConnectionManager();
	}
	public static AdministratorDao getInstance() {
		if(instance == null) {
			instance = new AdministratorDao();
		}
		return instance;
	}		



public Administrator create(Administrator administrator) throws SQLException {
	Users u=create(new Users(administrator.getUserName(),
			administrator.getPassword(), administrator.getFirstName(), administrator.getLastName(),
			administrator.getEmail()));	
			
			String insertUser = "INSERT INTO Administrator "
			+ "(UserId) "
			+ "VALUES(?);";
	
			Connection connection = null;
			PreparedStatement insertStmt = null;
	
			try {
				connection = connectionManager.getConnection();
				insertStmt = connection.prepareStatement(insertUser);
				insertStmt.setInt(1, u.getUserId());
				insertStmt.executeUpdate();	
	
				administrator.setUserId(u.getUserId());			
				return administrator; 		
				
			} catch (SQLException e) {
				e.printStackTrace();
				throw e;
			} finally {
				if(connection != null) {
					connection.close();
				}
				if(insertStmt != null) {
					insertStmt.close();
				}
			}									
}



public Administrator delete(Administrator administrator) throws SQLException{
	String deleteAdministrator = "DELETE FROM Administrator WHERE UserId=?;";
	Connection connection = null;
	PreparedStatement deleteStmt = null;
	try {
		connection = connectionManager.getConnection();
		deleteStmt = connection.prepareStatement(deleteAdministrator);
		deleteStmt.setInt(1, administrator.getUserId());
		int affectedRows = deleteStmt.executeUpdate();
		if (affectedRows == 0) {
			throw new SQLException("No records available to delete for UserId=" + administrator.getUserId());
		}

		super.delete(administrator);

		return null;
	} catch (SQLException e) {
		e.printStackTrace();
		throw e;
	} finally {
		if(connection != null) {
			connection.close();
		}
		if(deleteStmt != null) {
			deleteStmt.close();
		}
	}
	}	
}
	

