package foodies.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import foodies.model.Recipes;

public class RecipeDao {
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static RecipeDao instance = null;
	protected RecipeDao() {
		connectionManager = new ConnectionManager();
	}
	public static RecipeDao getInstance() {
		if(instance == null) {
			instance = new RecipeDao();
		}
		return instance;
	}
	
	public Recipes create(Recipes recipe) throws SQLException {
		String insertRecipes = "INSERT INTO Recipes(RecipeName,About) VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertRecipes);
 
			insertStmt.setInt(1, recipe.getRecipeId());
			insertStmt.setString(2, recipe.getPostName());
			insertStmt.setString(3, recipe.getDescription());
			insertStmt.setString(4, recipe.getImage());
			insertStmt.setString(5, recipe.getStep());
			insertStmt.setInt(6, recipe.getCoookingTime());
			insertStmt.setTimestamp(7, new Timestamp(recipe.getCreated().getTime()));
			insertStmt.setInt(8, recipe.getCuisineTypes().getCuisineTypeId());
			insertStmt.setString(9, recipe.getIngredientid());
			insertStmt.setInt(10, recipe.getUser().getUserId());

 
			insertStmt.executeUpdate();
			return recipe;
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
	
	public Recipes delete(Recipes recipe) throws SQLException {
		String deleteRecipe = "DELETE FROM Recipes WHERE RecipeId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteRecipe);
			deleteStmt.setInt(1, recipe.getRecipeId());
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the Persons instance.
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
