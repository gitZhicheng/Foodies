package foodies.dal;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import foodies.model.*;

public class RecipeCommentsDao extends CommentsDao{

	private static RecipeCommentsDao instance = null;
	protected RecipeCommentsDao() {
		super();
	}
	public static RecipeCommentsDao getInstance() {
		if(instance == null) {
			instance = new RecipeCommentsDao();
		}
		return instance;
	}
	
	public RecipeComments create(RecipeComments recipeComment) throws SQLException {
		// Insert into the superclass table first.
		create(new Comments(recipeComment.getCommentId(), recipeComment.getContend(), recipeComment.getCreated(), recipeComment.getUser()));

		String insertFoodCartRestaurant = "INSERT INTO RecipeComments(CommentId,RecipeId) VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertFoodCartRestaurant);
			insertStmt.setInt(1, recipeComment.getCommentId());
			insertStmt.setInt(2, recipeComment.getRecipe().getRecipeId());
			insertStmt.executeUpdate();
			return recipeComment ;
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
	
	public RecipeComments delete(RecipeComments recipeComment) throws SQLException {
		String deleteRecipeComment = "DELETE FROM RecipeComments WHERE CommentId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteRecipeComment);
			deleteStmt.setInt(1, recipeComment.getCommentId());
			int affectedRows = deleteStmt.executeUpdate();
			if (affectedRows == 0) {
				throw new SQLException("No records available to delete for CommentId=" + recipeComment.getCommentId());
			}

			super.delete(recipeComment);

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
	
	
	public List<RecipeComments> getCommentsByUserId(int userId) throws SQLException{
		List<RecipeComments> comments = new ArrayList<RecipeComments>();
		String selectComments = "SELECT * FROM Comments INNER JOIN RecipeComments ON"
				+ " Comments.CommentID = RecipeComments.CommentID WHERE UserId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			UsersDao usersDao = UsersDao.getInstance();
			RecipeDao recipesDao = RecipeDao.getInstance();
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectComments);
			selectStmt.setInt(1, userId);
			results = selectStmt.executeQuery();
			while(results.next()) {
				Users user = usersDao.getUserById(userId);
				int commentId = results.getInt("CommentId");
				int recipeId = results.getInt("RecipeId");
				String content = results.getString("Content");
				Date created = results.getDate("Created");

				Recipes recipe = recipesDao.getRecipeById(recipeId);
				
				RecipeComments comment = new RecipeComments(commentId, content, created, user, recipe);
				comments.add(comment);
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
		return comments;
	}
	
	public List<RecipeComments> getCommentsByRecipeId(int recipeId) throws SQLException{
		List<RecipeComments> comments = new ArrayList<RecipeComments>();
		String selectComments = "SELECT * FROM Comments INNER JOIN RecipeComments ON"
				+ " Comments.CommentID = RecipeComments.CommentID WHERE RecipeId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			UsersDao usersDao = UsersDao.getInstance();
			RecipeDao recipesDao = RecipeDao.getInstance();
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectComments);
			selectStmt.setInt(1, recipeId);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int commentId = results.getInt("CommentId");
				int userId = results.getInt("userId");
				Users user = usersDao.getUserById(userId);

				String content = results.getString("Content");
				Date created = results.getDate("Created");

				Recipes recipe = recipesDao.getRecipeById(recipeId);
				
				RecipeComments comment = new RecipeComments(commentId, content, created, user, recipe);
				comments.add(comment);
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
		return comments;
	}
	
	public RecipeComments getCommentById(int commentId) throws SQLException{
		String selectComments = "SELECT * FROM Comments INNER JOIN RecipeComments ON"
				+ " Comments.CommentID = RecipeComments.CommentID WHERE RecipeId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		
		try {
			UsersDao usersDao = UsersDao.getInstance();
			RecipeDao recipesDao = RecipeDao.getInstance();
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectComments);
			selectStmt.setInt(1, commentId);
			results = selectStmt.executeQuery();
			
			if(results.next()) {
				int userId = results.getInt("userId");
				Users user = usersDao.getUserById(userId);

				String content = results.getString("Content");
				Date created = results.getDate("Created");
				int recipeId = results.getInt("RecipeId");

				Recipes recipe = recipesDao.getRecipeById(recipeId);
				
				RecipeComments comment = new RecipeComments(commentId, content, created, user, recipe);
				return comment;
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
