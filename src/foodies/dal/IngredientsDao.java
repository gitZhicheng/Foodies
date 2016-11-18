package foodies.dal;

import foodies.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class IngredientsDao {
	
	protected ConnectionManager connectionManager;
	// Single pattern: instantiation is limited to one object.
	private static IngredientsDao instance = null;
	protected IngredientsDao() {
		connectionManager = new ConnectionManager();
	}
	public static IngredientsDao getInstance() {
		if(instance == null) {
			instance = new IngredientsDao();
		}
		return instance;
	}
	
	public Ingredients create(Ingredients ingredient) throws SQLException {
		String insertIngredient = "INSERT INTO Ingredients(IngredientName) VALUES(?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertIngredient,
					Statement.RETURN_GENERATED_KEYS);
			
			insertStmt.setString(1, ingredient.getIngredientName());
			insertStmt.executeUpdate();
			
			resultKey = insertStmt.getGeneratedKeys();
			int ingredientId = -1;
			if(resultKey.next()) {
				ingredientId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			ingredient.setIngredientId(ingredientId);

			return ingredient;
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
	
	public Ingredients delete(Ingredients ingredient) throws SQLException {

		String deleteIngredient = "DELETE FROM Ingredients WHERE IngredientId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteIngredient);
			deleteStmt.setInt(1, ingredient.getIngredientId());
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

}
