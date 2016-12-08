package foodies.dal;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import foodies.model.*;

public class ReviewsDao {

	protected ConnectionManager connectionManager;

	private static ReviewsDao instance = null;
	protected ReviewsDao() {
		connectionManager = new ConnectionManager();
	}
	public static ReviewsDao getInstance() {
		if(instance == null) {
			instance = new ReviewsDao();
		}
		return instance;
	}

	public Reviews create(Reviews review) throws SQLException {
		String insertReview =
			"INSERT INTO Reviews(Rating,UserId,ReviewId) " +
			"VALUES(?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertReview,
				Statement.RETURN_GENERATED_KEYS);
			insertStmt.setBigDecimal(1, review.getRating());
			insertStmt.setInt(2, review.getUser().getUserId());
			insertStmt.setInt(3, review.getReviewId());
			insertStmt.executeUpdate();

			// Retrieve the auto-generated key and set it, so it can be used by the caller.
			// For more details, see:
			// http://dev.mysql.com/doc/connector-j/en/connector-j-usagenotes-last-insert-id.html
			resultKey = insertStmt.getGeneratedKeys();
			int reviewId = -1;
			if(resultKey.next()) {
				reviewId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			review.setReviewId(reviewId);
			return review;
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

	public Reviews delete(Reviews review) throws SQLException {

		String deleteReview = "DELETE FROM Reviews WHERE ReviewId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteReview);
			deleteStmt.setInt(1, review.getReviewId());
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
	
	public List<Reviews> getReviewsByUserId(int userId) throws SQLException{
		List<Reviews> reviews = new ArrayList<Reviews>();
		String selectReviews = "SELECT * FROM Reviews WHERE UserId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			UsersDao usersDao = UsersDao.getInstance();
			RecipeDao recipesDao = RecipeDao.getInstance();
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReviews);
			selectStmt.setInt(1, userId);
			results = selectStmt.executeQuery();
			while(results.next()) {
				Users user = usersDao.getUserById(userId);
				int reviewId = results.getInt("ReviewId");
				int recipeId = results.getInt("RecipeId");
				Recipes recipe = recipesDao.getRecipeById(recipeId);
				
				BigDecimal rating = results.getBigDecimal("Rating");
				Reviews review = new Reviews(reviewId, rating, user, recipe);
				reviews.add(review);
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
		return reviews;
	}
	
	public Reviews getReviewByReviewId(int reviewId) throws SQLException{
		String selectReviews = "SELECT * FROM Reviews WHERE ReviewId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		
		try {
			UsersDao usersDao = UsersDao.getInstance();
			RecipeDao recipesDao = RecipeDao.getInstance();
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReviews);
			selectStmt.setInt(1, reviewId);
			results = selectStmt.executeQuery();
			
			if(results.next()) {
				int userId = results.getInt("UserId");
				
				Users user = usersDao.getUserById(userId);
				int recipeId = results.getInt("RecipeId");
				Recipes recipe = recipesDao.getRecipeById(recipeId);
				
				BigDecimal rating = results.getBigDecimal("Rating");
				Reviews review = new Reviews(reviewId, rating, user, recipe);
				return review;
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
	
	public double getAvgRatingByRecipeId(int recipeId) throws SQLException {
		String selectAvgRating = "SELECT AVG(Rating) AS AVG_R FROM Reviews WHERE RecipeId=?";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectAvgRating);
			selectStmt.setInt(1, recipeId);
			results = selectStmt.executeQuery();
			
			double avgRating = 0;
			if(results.next()) {
				avgRating = results.getDouble("AVG_R");
			}
			
			return avgRating;
			
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
	
	public int getCntByRecipeId(int recipeId) throws SQLException {
		String selectCnt = "SELECT COUNT(*) AS CNT FROM Reviews WHERE RecipeId=?";
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
