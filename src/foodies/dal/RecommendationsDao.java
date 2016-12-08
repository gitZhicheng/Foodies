package foodies.dal;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;

import foodies.model.*;

public class RecommendationsDao {

	protected ConnectionManager connectionManager;
	// Single pattern: instantiation is limited to one object.
	private static RecommendationsDao instance = null;
	protected RecommendationsDao() {
		connectionManager = new ConnectionManager();
	}
	public static RecommendationsDao getInstance() {
		if(instance == null) {
			instance = new RecommendationsDao();
		}
		return instance;
	}

	public Recommendations create(Recommendations recommendation) throws SQLException {
		String insertRecommendation =
			"INSERT INTO Recommendations(From_UserId,To_UserId,Content,Created,RecipeId) " +
			"VALUES(?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertRecommendation,
				Statement.RETURN_GENERATED_KEYS);

			insertStmt.setInt(1, recommendation.getFrom_user().getUserId());
			insertStmt.setInt(2, recommendation.getTo_user().getUserId());
			insertStmt.setString(3, recommendation.getContend());
			insertStmt.setTimestamp(4, new Timestamp(recommendation.getCreated().getTime()));
			insertStmt.setInt(5, recommendation.getRecipe().getRecipeId());
			insertStmt.executeUpdate();

			// Retrieve the auto-generated key and set it, so it can be used by the caller.
			// For more details, see:
			// http://dev.mysql.com/doc/connector-j/en/connector-j-usagenotes-last-insert-id.html
			resultKey = insertStmt.getGeneratedKeys();
			int recommendationId = -1;
			if(resultKey.next()) {
				recommendationId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			recommendation.setRecommendationId(recommendationId);;
			return recommendation;
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

	public Recommendations delete(Recommendations recommendation) throws SQLException {

		String deleteRecommendation = "DELETE FROM Recommendations WHERE RecommendationId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteRecommendation);
			deleteStmt.setInt(1, recommendation.getRecommendationId());
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
	
	public Recommendations getRecommendationByRecommendationId(int recommendationId) throws SQLException{
		String selectRecommendations = "SELECT * FROM Recommendations WHERE RecommendationId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		
		try {
			UsersDao usersDao = UsersDao.getInstance();
			RecipeDao recipesDao = RecipeDao.getInstance();
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRecommendations);
			selectStmt.setInt(1, recommendationId);
			results = selectStmt.executeQuery();
			
			if(results.next()) {
				int fromUserId = results.getInt("From_UserId");
				int toUserId = results.getInt("To_UserId");
				Users fromUser = usersDao.getUserById(fromUserId);
				Users toUser = usersDao.getUserById(toUserId);
				int recipeId = results.getInt("RecipeId");
				Recipes recipe = recipesDao.getRecipeById(recipeId);
				
				String content = results.getString("Content");
				
				Date created = results.getDate("Created");

				Recommendations recommendation = new Recommendations(recommendationId, fromUser, toUser, content, created, recipe);
				return recommendation;
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
