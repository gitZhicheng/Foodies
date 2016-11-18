package foodies.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import foodies.model.*;

public class PostsDao {
	protected ConnectionManager connectionManager;

	private static PostsDao instance = null;
	protected PostsDao() {
		connectionManager = new ConnectionManager();
	}
	public static PostsDao getInstance() {
		if(instance == null) {
			instance = new PostsDao();
		}
		return instance;
	}
	
	public Posts create(Posts post) throws SQLException {
		String insertPost =
			"INSERT INTO Posts(Title,Content,Image,UserId,Created,RecipeId) " +
			"VALUES(?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertPost,
				Statement.RETURN_GENERATED_KEYS);
			insertStmt.setString(1, post.getTitle());
			insertStmt.setString(2, post.getContent());
			insertStmt.setString(3, post.getImage());
			insertStmt.setInt(4, post.getUser().getUserId());
			insertStmt.setTimestamp(5, new Timestamp(post.getCreated().getTime()));
			insertStmt.setInt(6, post.getRecipe().getRecipeId());
			insertStmt.executeUpdate();
			
			// Retrieve the auto-generated key and set it, so it can be used by the caller.
			// For more details, see:
			// http://dev.mysql.com/doc/connector-j/en/connector-j-usagenotes-last-insert-id.html
			resultKey = insertStmt.getGeneratedKeys();
			int postId = -1;
			if(resultKey.next()) {
				postId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			post.setPostId(postId);
			return post;
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
			if(resultKey != null) {
				resultKey.close();
			}
		}
				
	}
	
	public Posts delete(Posts post) throws SQLException {
		
		String deletePost = "DELETE FROM Posts WHERE PostId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deletePost);
			deleteStmt.setInt(1, post.getPostId());
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the BlogPosts instance.
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
