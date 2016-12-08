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

import foodies.dal.PostsDao;
import foodies.model.*;

/**
 * Servlet implementation class PostDelete
 */
@WebServlet("/PostDelete")
public class PostDelete extends HttpServlet {

	protected PostsDao postDao;
	
	public void init() throws ServletException {
		postDao = PostsDao.getInstance();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> messages = new HashMap<String, String>();
        request.setAttribute("messages", messages);
        
		int postId = Integer.valueOf(request.getParameter("postId"));
		try {
			Posts post = postDao.getPostByPostId(postId);
			postDao.delete(post);
			messages.put("success", "Successfully deleted post: " + postId);
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
