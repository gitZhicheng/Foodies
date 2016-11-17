package food.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import food.model.Experienced;
import food.model.Users;

public class ExperiencedDao extends UsersDao{
	protected ConnectionManager connectionManager;

	private static ExperiencedDao instance = null;
	protected ExperiencedDao() {
		connectionManager = new ConnectionManager();
	}
	public static ExperiencedDao getInstance() {
		if(instance == null) {
			instance = new ExperiencedDao();
		}
		return instance;
	}
	

	public Experienced create(Experienced experienced) throws SQLException {
	Users u=create(new Users(experienced.getUserName(),
			experienced.getPassword(), experienced.getFirstName(), experienced.getLastName(),
			experienced.getEmail()));	
			experienced.setUserId(u.getUserId());			
			return experienced; 			
	}
	
	public Experienced delete(Experienced experienced) throws SQLException{
		String deleteExperienced = "DELETE FROM Experienced WHERE UserId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteExperienced);
			deleteStmt.setInt(1, experienced.getUserId());
			int affectedRows = deleteStmt.executeUpdate();
			if (affectedRows == 0) {
				throw new SQLException("No records available to delete for UserId=" + experienced.getUserId());
			}

			super.delete(experienced);

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
