package foodies.dal;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
	public List<Posts> getPostsByRecipeId(int recipeId) throws SQLException{
		List<Posts> posts = new ArrayList<Posts>();
		String selectRecipes = "SELECT * FROM Posts WHERE RecipeId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			UsersDao userDao = UsersDao.getInstance();
			RecipeDao recipeDao = RecipeDao.getInstance();
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRecipes);
			selectStmt.setInt(1, recipeId);
			results = selectStmt.executeQuery();
			
			while(results.next()) {
				int userId = results.getInt("UserId");
				int postId = results.getInt("PostId");
				String title = results.getString("Title");
				String content = results.getString("Content");
				String image = results.getString("Image");

				Date created = results.getDate("Created");
	
				Users user = userDao.getUserById(userId);
				Recipes recipe = recipeDao.getRecipeById(recipeId);

				Posts post = new Posts(postId, title, content, image, user, created, recipe);
				
				posts.add(post);
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
		return posts;
	}
	
	public Posts getPostByPostId(int postId) throws SQLException{
		String selectPosts = "SELECT * FROM Posts WHERE PostId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		
		try {
			UsersDao usersDao = UsersDao.getInstance();
			RecipeDao recipesDao = RecipeDao.getInstance();
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectPosts);
			selectStmt.setInt(1, postId);
			results = selectStmt.executeQuery();
			
			if(results.next()) {
				int userId = results.getInt("UserId");
				int recipeId = results.getInt("RecipeId");
				String title = results.getString("Title");
				String content = results.getString("Content");
				String image = results.getString("Image");

				Date created = results.getDate("Created");
	
				Users user = usersDao.getUserById(userId);
				Recipes recipe = recipesDao.getRecipeById(recipeId);

				Posts post = new Posts(postId, title, content, image, user, created, recipe);
				
				return post;
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

	public Posts update(Posts post) throws SQLException {
		String updatePost = "UPDATE Post SET Title=?,Content=?,Image=? WHERE PostId=? ;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updatePost);
			updateStmt.setString(1, post.getTitle());
			updateStmt.setString(2, post.getContent());
			updateStmt.setString(3, post.getImage());
			updateStmt.setInt(4, post.getPostId());
			

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
	
	public int getCntByRecipeId(int recipeId) throws SQLException {
		String selectCnt = "SELECT COUNT(*) AS CNT FROM Posts WHERE RecipeId=?";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCnt);
			selectStmt.setInt(1, recipeId);
			results = selectStmt.executeQuery();
			
			int cnt = 0;
			if(results.next()) {
				cnt = results.getInt("CNT");
			}
			
			return cnt;
			
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
	}
}
