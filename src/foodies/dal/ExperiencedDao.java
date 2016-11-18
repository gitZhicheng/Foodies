package foodies.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import foodies.model.*;

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
				
			String insertUser = "INSERT INTO Experienced "
					+ "(UserId) "
					+ "VALUES(?);";
			
			Connection connection = null;
			PreparedStatement insertStmt = null;
			
			try {
				connection = connectionManager.getConnection();
				insertStmt = connection.prepareStatement(insertUser);
				insertStmt.setInt(1, u.getUserId());
				insertStmt.executeUpdate();	
				
				experienced.setUserId(u.getUserId());			
				return experienced;
				
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
	
	
	public Experienced getExperiencedById(int userId) throws SQLException {
		String selectExperienced =
				"SELECT Experienced.UserId as UserId,UserName,Password,Firstname,LastName,Email " +
				"FROM Experienced INNER JOIN Users " +
				"ON Experienced.UserId = Users.UserId "	+	
				"WHERE Experienced.UserId=?;";
			Connection connection = null;
			PreparedStatement selectStmt = null;
			ResultSet results = null;
			try {
				connection = connectionManager.getConnection();
				selectStmt = connection.prepareStatement(selectExperienced);
				selectStmt.setInt(1, userId);
				results = selectStmt.executeQuery();
				
				if(results.next()) {
					int resultUsernId = results.getInt("UserId");
					String userName = results.getString("UserName");
					String password = results.getString("Password");
					String firstName = results.getString("FirstName");
					String lastName = results.getString("LastName");
					String email = results.getString("Email");
					Experienced experienced=new Experienced(resultUsernId, userName, password, firstName, lastName, email);
					return experienced;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw e;
			} finally {
				if(connection != null) {
					connection.close();
				}
				if(selectStmt != null) {
					selectStmt.close();
				}
				if(results != null) {
					results.close();
				}
			}
			return null;		
	}
			
	public Experienced getExperiencedByUserName(String userName) throws SQLException {
		String selectExperienced =
				"SELECT Experienced.UserId as UserId,Experienced.UserName as UserName,Password,Firstname,LastName,Email " +
				"FROM Experienced INNER JOIN Users " +
				"ON Experienced.UserId = Users.UserId "	+	
				"WHERE Experienced.UserName=?;";
			Connection connection = null;
			PreparedStatement selectStmt = null;
			ResultSet results = null;
			try {
				connection = connectionManager.getConnection();
				selectStmt = connection.prepareStatement(selectExperienced);
				selectStmt.setString(1, userName);
				results = selectStmt.executeQuery();
				
				if(results.next()) {
					int resultUsernId = results.getInt("UserId");
					String password = results.getString("Password");
					String firstName = results.getString("FirstName");
					String lastName = results.getString("LastName");
					String email = results.getString("Email");
					Experienced experienced=new Experienced(resultUsernId, userName, password, firstName, lastName, email);
					return experienced;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw e;
			} finally {
				if(connection != null) {
					connection.close();
				}
				if(selectStmt != null) {
					selectStmt.close();
				}
				if(results != null) {
					results.close();
				}
			}
			return null;		
	}
	
}
