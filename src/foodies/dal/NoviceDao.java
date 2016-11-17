package food.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import food.model.Novice;
import food.model.Users;

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
		
//		String insertNovice = "INSERT INTO Novice "
//				+ "() "
//				+ "VALUES();";
//		
//		Connection connection = null;
//		PreparedStatement insertStmt = null;
//		ResultSet resultKey = null;
//		
//		try {
//			connection = connectionManager.getConnection();
//			insertStmt = connection.prepareStatement(insertNovice, Statement.RETURN_GENERATED_KEYS);
//			insertStmt.setString(1, novice.getUserName());
//			insertStmt.executeUpdate();	
//				
//			// Retrieve the auto-generated key and set it, so it can be used by the caller.
//			resultKey = insertStmt.getGeneratedKeys();
//			int noviceId = -1;
//			if(resultKey.next()) {
//				noviceId = resultKey.getInt(1);
//			} else {
//				throw new SQLException("Unable to retrieve auto-generated key.");
//			}
			
			novice.setUserId(u.getUserId());			
			return novice; 
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
