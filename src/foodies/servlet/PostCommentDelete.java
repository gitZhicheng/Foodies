package foodies.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import foodies.dal.CommentsDao;
import foodies.dal.PostCommentsDao;
import foodies.dal.PostsDao;
import foodies.dal.UsersDao;
import foodies.model.*;

/**
 * Servlet implementation class PostDelete
 */
@WebServlet("/PostCommentDelete")
public class PostCommentDelete extends HttpServlet {

	protected PostsDao postDao;
	protected PostCommentsDao postCommentsDao;
	protected CommentsDao commentDao;
	protected UsersDao usersDao;
	
	public void init() throws ServletException {
		postDao = PostsDao.getInstance();
		usersDao = UsersDao.getInstance();
		postCommentsDao = PostCommentsDao.getInstance();
		commentDao = CommentsDao.getInstance();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> messages = new HashMap<String, String>();
        request.setAttribute("messages", messages);
        
		int commentId = Integer.valueOf(request.getParameter("commentId"));
		try {
			PostComments postComment = postCommentsDao.getCommentById(commentId);
			postCommentsDao.delete(postComment);
			messages.put("success", "Successfully deleted comment: " + commentId);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
		
		request.getRequestDispatcher("findPosts.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
