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
		String insertRecipes = 
				"INSERT INTO Recipes(PostName,Description,Image,Steps,CookingTime,Created,CuisineTypeId,Ingredientid,UserId)" +
						"VALUES(?,?,?,?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertRecipes,
					Statement.RETURN_GENERATED_KEYS);
			
			insertStmt.setString(1, recipe.getPostName());
			insertStmt.setString(2, recipe.getDescription());
			insertStmt.setString(3, recipe.getImage());
			insertStmt.setString(4, recipe.getStep());
			insertStmt.setInt(5, recipe.getCookingTime());
			insertStmt.setTimestamp(6, new Timestamp(recipe.getCreated().getTime()));
			//insertStmt.setInt(7, recipe.getCuisineTypes().getCuisineTypeId());
			insertStmt.setInt(7, 0);
			insertStmt.setString(8, recipe.getIngredients());
			insertStmt.setInt(9, recipe.getUser().getUserId());
			insertStmt.executeUpdate();
			
			resultKey = insertStmt.getGeneratedKeys();
			int recipeId = -1;
			if(resultKey.next()) {
				recipeId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			recipe.setRecipeId(recipeId);
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
	
	public Recipes update(Recipes recipe) throws SQLException {
		String updateRecipe = "UPDATE Recipes SET PostName=?, Description=?, Image=?, Steps=?, CookingTime=?,"
				+ "CuisineTypeId=?,Ingredients=? WHERE RecipeId=? ;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateRecipe);
			updateStmt.setString(1, recipe.getPostName());
			updateStmt.setString(2, recipe.getDescription());
			updateStmt.setString(3, recipe.getImage());
			updateStmt.setString(4, recipe.getStep());
			updateStmt.setInt(5, recipe.getCookingTime());
			updateStmt.setInt(6, 0);
			updateStmt.setString(7, recipe.getIngredients());
			updateStmt.setInt(8, recipe.getRecipeId());
			

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
	public Recipes getRecipeById(int recipeId) throws SQLException{
		String selectRecipes = "SELECT * FROM Recipes WHERE RecipeId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			ExperiencedDao experiencedDao = ExperiencedDao.getInstance();
			CuisineTypesDao cuisineTypesDao = CuisineTypesDao.getInstance();
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRecipes);
			selectStmt.setInt(1, recipeId);
			results = selectStmt.executeQuery();
			
			if(results.next()) {
				int userId = results.getInt("UserId");
				String postName = results.getString("PostName");
				String description = results.getString("Description");
				String image = results.getString("Image");
				String steps = results.getString("Steps");
				int cookTime = results.getInt("CookingTime");
				Date created = results.getDate("Created");
				int cuisineTypeId = results.getInt("CuisineTypeId");
				String ingredients = results.getString("IngredientId");
	
				Experienced user = experiencedDao.getExperiencedById(userId);
				CuisineTypes cuisineType = cuisineTypesDao.getCuisineTypesById(cuisineTypeId);
				Recipes recipe = new Recipes(recipeId, postName, description, image, steps, cookTime, created, cuisineType, ingredients, user);
				
				return recipe;
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
	
	public List<Recipes> getRecipesByUserId(int userId) throws SQLException{
		List<Recipes> recipes = new ArrayList<Recipes>();
		String selectRecipes = "SELECT * FROM Recipes WHERE UserId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			ExperiencedDao experiencedDao = ExperiencedDao.getInstance();
			CuisineTypesDao cuisineTypesDao = CuisineTypesDao.getInstance();
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRecipes);
			selectStmt.setInt(1, userId);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int recipeId = results.getInt("RecipeId");
				String postName = results.getString("PostName");
				String description = results.getString("Description");
				String image = results.getString("Image");
				String steps = results.getString("Steps");
				int cookTime = results.getInt("CookingTime");
				Date created = results.getDate("Created");
				int cuisineTypeId = results.getInt("CuisineTypeId");
				String ingredients = results.getString("IngredientId");
				 
				Experienced user = experiencedDao.getExperiencedById(userId);
				CuisineTypes cuisineType = cuisineTypesDao.getCuisineTypesById(cuisineTypeId);
				Recipes recipe = new Recipes(recipeId, postName, description, image, steps, cookTime, created, cuisineType, ingredients, user);
				recipes.add(recipe);
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
		return recipes;
	}
	
	public List<Recipes> getRecipesByName(String rcpName) throws SQLException{
		List<Recipes> recipes = new ArrayList<Recipes>();
		String selectRecipes = "SELECT * FROM Recipes WHERE PostName like ?" + "LIMIT 10;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			ExperiencedDao experiencedDao = ExperiencedDao.getInstance();
			CuisineTypesDao cuisineTypesDao = CuisineTypesDao.getInstance();
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRecipes);
			selectStmt.setString(1, "%" + rcpName + "%");
			results = selectStmt.executeQuery();
			while(results.next()) {
				int recipeId = results.getInt("RecipeId");
				String postName = results.getString("PostName");
				String description = results.getString("Description");
				String image = results.getString("Image");
				String steps = results.getString("Steps");
				int cookTime = results.getInt("CookingTime");
				Date created = results.getDate("Created");
				int cuisineTypeId = results.getInt("CuisineTypeId");
				String ingredients = results.getString("IngredientId");
				int userId = results.getInt("UserId");
				 
				Experienced user = experiencedDao.getExperiencedById(userId);
				CuisineTypes cuisineType = cuisineTypesDao.getCuisineTypesById(cuisineTypeId);
				Recipes recipe = new Recipes(recipeId, postName, description, image, steps, cookTime, created, cuisineType, ingredients, user);
				recipes.add(recipe);
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
		return recipes;
	}
	
	public List<Recipes> getRecipesByUserName(String userName) throws SQLException{
		ExperiencedDao experiencedDao = ExperiencedDao.getInstance();
		Experienced user = experiencedDao.getExperiencedByUserName(userName);
		int id = user.getUserId();
		return getRecipesByUserId(id);
	}

		
	public List<Recipes> getRecipesByCuisine(CuisineTypes cuisine) throws SQLException{
		List<Recipes> recipes = new ArrayList<Recipes>();
		String selectCreditcards = "SELECT * FROM Recipes WHERE CuisineTypeId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			ExperiencedDao experiencedDao = ExperiencedDao.getInstance();
			CuisineTypesDao cuisineTypesDao = CuisineTypesDao.getInstance();
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCreditcards);
			selectStmt.setInt(1, cuisine.getCuisineTypeId());
			results = selectStmt.executeQuery();
			while(results.next()) {
				int recipeId = results.getInt("RecipeId");
				String postName = results.getString("PostName");
				String description = results.getString("Description");
				String image = results.getString("Image");
				String steps = results.getString("Steps");
				int cookTime = results.getInt("CookingTime");
				Date created = results.getDate("Created");
				String cuisineTypeId = results.getString("CuisineTypeId");
				String ingredients = results.getString("IngredientId");
				int userId = results.getInt("UserId");
				 
				Experienced user = experiencedDao.getExperiencedById(userId);
				CuisineTypes cuisineType = cuisineTypesDao.getCuisineTypesById(Integer.parseInt(cuisineTypeId));
				Recipes recipe = new Recipes(recipeId, postName, description, image, steps, cookTime, created, cuisineType, ingredients, user);
				recipes.add(recipe);
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
		return recipes;
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
