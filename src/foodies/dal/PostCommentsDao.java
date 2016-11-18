package foodies.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import foodies.model.*;


public class PostCommentsDao extends CommentsDao{

	protected ConnectionManager connectionManager;

	private static PostCommentsDao instance = null;
	protected PostCommentsDao() {
		super();
	}
	public static PostCommentsDao getInstance() {
		if(instance == null) {
			instance = new PostCommentsDao();
		}
		return instance;
	}

	public PostComments create(PostComments postComment ) throws SQLException {
		// Insert into the superclass table first.
		create(new Comments(postComment.getCommentId(), postComment.getContend(), postComment.getCreated(), postComment.getUser()));

		String insertFoodCartRestaurant = "INSERT INTO PostComments(CommentId,PostId) VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertFoodCartRestaurant);
			insertStmt.setInt(1, postComment.getCommentId());
			insertStmt.setInt(2, postComment.getPost().getPostId());
			insertStmt.executeUpdate();
			return postComment ;
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

	public PostComments delete(PostComments postComment) throws SQLException {
		String deletePostComment = "DELETE FROM PostComments WHERE CommentId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deletePostComment);
			deleteStmt.setInt(1, postComment.getCommentId());
			int affectedRows = deleteStmt.executeUpdate();
			if (affectedRows == 0) {
				throw new SQLException("No records available to delete for CommentId=" + postComment.getCommentId());
			}

			super.delete(postComment);

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
