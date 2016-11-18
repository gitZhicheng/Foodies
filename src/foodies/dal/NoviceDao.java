package foodies.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import foodies.model.*;

public class NoviceDao extends UsersDao{

	protected ConnectionManager connectionManager;

	private static NoviceDao instance = null;
	protected NoviceDao() {
		connectionManager = new ConnectionManager();
	}
	public static NoviceDao getInstance() {
		if(instance == null) {
			instance = new NoviceDao();
		}
		return instance;
	}
	
	
	public Novice create(Novice novice) throws SQLException {
		Users u=create(new Users(novice.getUserName(),
				novice.getPassword(), novice.getFirstName(), novice.getLastName(),
				novice.getEmail()));	
		
		String insertUser = "INSERT INTO Novice "
				+ "(UserId) "
				+ "VALUES(?);";
		
		Connection connection = null;
		PreparedStatement insertStmt = null;
		
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertUser);
			insertStmt.setInt(1, u.getUserId());
			insertStmt.executeUpdate();	
			
			novice.setUserId(u.getUserId());			
			return novice; 
			
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


	
	public Novice delete(Novice novice) throws SQLException {
		String deleteNovice = "DELETE FROM Novice WHERE UserId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteNovice);
			deleteStmt.setInt(1, novice.getUserId());
			int affectedRows = deleteStmt.executeUpdate();
			if (affectedRows == 0) {
				throw new SQLException("No records available to delete for UserId=" + novice.getUserId());
			}

			super.delete(novice);

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
