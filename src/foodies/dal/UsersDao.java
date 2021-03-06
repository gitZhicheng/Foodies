package foodies.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import foodies.model.Posts;
import foodies.model.Users;



public class UsersDao{
		protected ConnectionManager connectionManager;

		private static UsersDao instance = null;
		protected UsersDao() {
			connectionManager = new ConnectionManager();
		}
		public static UsersDao getInstance() {
			if(instance == null) {
				instance = new UsersDao();
			}
			return instance;
		}



	public Users create(Users user) throws SQLException {

		String insertUser = "INSERT INTO Users "
				+ "(UserName,Password,FirstName,LastName,Email) "
				+ "VALUES(?,?,?,?,?);";

		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;

		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertUser, Statement.RETURN_GENERATED_KEYS);
			insertStmt.setString(1, user.getUserName());
			insertStmt.setString(2, user.getPassword());
			insertStmt.setString(3, user.getFirstName());
			insertStmt.setString(4, user.getLastName());
			insertStmt.setString(5, user.getEmail());
			insertStmt.executeUpdate();


			// Retrieve the auto-generated key and set it, so it can be used by the caller.
			resultKey = insertStmt.getGeneratedKeys();
			int userId = -1;
			if(resultKey.next()) {
				userId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}

			user.setUserId(userId);
			return user;

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



	public Users delete(Users user) throws SQLException {
		String deleteUsers = "DELETE FROM Users WHERE UserId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteUsers);
			deleteStmt.setInt(1, user.getUserId());
			deleteStmt.executeUpdate();

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



	//getuserbyuserId
	public Users getUserById(int userId) throws SQLException {
		String selectUser =
				"SELECT UserId,UserName,Password,Firstname,LastName,Email " +
				"FROM Users " +
				"WHERE UserId=?;";
			Connection connection = null;
			PreparedStatement selectStmt = null;
			ResultSet results = null;
			try {
				connection = connectionManager.getConnection();
				selectStmt = connection.prepareStatement(selectUser);
				selectStmt.setInt(1, userId);
				results = selectStmt.executeQuery();

				if(results.next()) {
					int resultUsernId = results.getInt("UserId");
					String userName = results.getString("UserName");
					String password = results.getString("Password");
					String firstName = results.getString("FirstName");
					String lastName = results.getString("LastName");
					String email = results.getString("Email");
					Users user=new Users(resultUsernId, userName, password, firstName, lastName, email);
					return user;
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

	public Users update(Users user) throws SQLException {
		String updateUser = "UPDATE User SET FirstName=?,LastName=?,Password=?, Email=? WHERE UserId=? ;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateUser);
			updateStmt.setString(1, user.getFirstName());
			updateStmt.setString(2, user.getLastName());
			updateStmt.setString(3, user.getPassword());
			updateStmt.setString(4, user.getEmail());
			updateStmt.setInt(5, user.getUserId());
			

			updateStmt.executeUpdate();

			// Return null so the caller can no longer operate on the Persons instance.
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(updateStmt != null) {
				updateStmt.close();
			}
		}
	}
	
	public Users getUserByUserName(String username) throws SQLException {
		String selectUser =
				"SELECT UserId,UserName,Password,Firstname,LastName,Email " +
				"FROM Users " +
				"WHERE UserName=?;";
			Connection connection = null;
			PreparedStatement selectStmt = null;
			ResultSet results = null;
			try {
				connection = connectionManager.getConnection();
				selectStmt = connection.prepareStatement(selectUser);
				selectStmt.setString(1, username);
				results = selectStmt.executeQuery();

				if(results.next()) {
					int resultUsernId = results.getInt("UserId");
					String userName = results.getString("UserName");
					String password = results.getString("Password");
					String firstName = results.getString("FirstName");
					String lastName = results.getString("LastName");
					String email = results.getString("Email");
					Users user=new Users(resultUsernId, userName, password, firstName, lastName, email);
					return user;
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
