package foodies.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
	
	
	

}
