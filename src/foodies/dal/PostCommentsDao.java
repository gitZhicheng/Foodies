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


public class PostCommentsDao extends CommentsDao{

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

		String insertPostComment = "INSERT INTO PostComments(CommentId,PostId) VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertPostComment,
					Statement.RETURN_GENERATED_KEYS);
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
	
	public List<PostComments> getCommentsByPostId(int postId) throws SQLException{
		List<PostComments> comments = new ArrayList<PostComments>();
		String selectComments = "SELECT * FROM Comments INNER JOIN PostComments ON"
				+ " Comments.CommentID = PostComments.CommentID WHERE RecipeId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			UsersDao usersDao = UsersDao.getInstance();
			PostsDao postsDao = PostsDao.getInstance();
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectComments);
			selectStmt.setInt(1, postId);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int commentId = results.getInt("CommentId");
				int userId = results.getInt("userId");
				Users user = usersDao.getUserById(userId);

				String content = results.getString("Content");
				Date created = results.getDate("Created");

				Posts post = postsDao.getPostByPostId(postId);
				
				PostComments comment = new PostComments(commentId, content, created, user, post);
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
	
	public PostComments getCommentById(int commentId) throws SQLException{
		String selectComments = "SELECT * FROM Comments INNER JOIN RecipeComments ON"
				+ " Comments.CommentID = RecipeComments.CommentID WHERE RecipeId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		
		try {
			UsersDao usersDao = UsersDao.getInstance();
			PostsDao postsDao = PostsDao.getInstance();
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectComments);
			selectStmt.setInt(1, commentId);
			results = selectStmt.executeQuery();
			
			if(results.next()) {
				int userId = results.getInt("userId");
				Users user = usersDao.getUserById(userId);

				String content = results.getString("Content");
				Date created = results.getDate("Created");
				int postId = results.getInt("RecipeId");

				Posts post = postsDao.getPostByPostId(postId);
				
				PostComments comment = new PostComments(commentId, content, created, user, post);
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
