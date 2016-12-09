package foodies.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import foodies.dal.*;
import foodies.model.*;

@WebServlet("/PostCommentCreate")
public class PostCommentCreate extends HttpServlet {
	
	protected PostsDao postDao;
	protected PostCommentsDao postCommentsDao;
	protected CommentsDao commentDao;
	protected UsersDao usersDao;
	@Override
	public void init() throws ServletException {
		postDao = PostsDao.getInstance();
		usersDao = UsersDao.getInstance();
		postCommentsDao = PostCommentsDao.getInstance();
		commentDao = CommentsDao.getInstance();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> messages = new HashMap<String, String>();
        request.setAttribute("messages", messages);
    	String content = request.getParameter("content");

        System.out.println("PostName: " + content);
        if (content == null || content.trim().isEmpty()) {
            messages.put("failed", "Invalid content");
        }
        else {
        	Date created = new Date();
        	Users user = (Users)(request.getSession().getAttribute("user"));
        	int userId = Integer.valueOf(user.getUserId());
        	System.out.println(userId);
        	int postId = Integer.valueOf(request.getParameter("postId"));
        	System.out.println(postId);
        	Posts post = null;
			try {
	        	user = usersDao.getUserById(userId);
	        	post = postDao.getPostByPostId(postId);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
        	try {

				PostComments postComment = new PostComments(content, created, user, post);
				postCommentsDao.create(postComment);
				
        		messages.put("success", "Successfully created post: " + postComment.getCommentId());
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        request.getRequestDispatcher("GetPost?postId="+request.getParameter("postId")).forward(request, response);
	}

}
