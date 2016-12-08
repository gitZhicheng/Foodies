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
import foodies.dal.RecipeCommentsDao;
import foodies.dal.RecipeDao;
import foodies.dal.UsersDao;
import foodies.model.*;

/**
 * Servlet implementation class RecipeDelete
 */
@WebServlet("/CommentDelete")
public class CommentDelete extends HttpServlet {

	protected RecipeDao recipeDao;
    protected RecipeCommentsDao recipeCommentDao;
    protected CommentsDao commentDao;
    protected UsersDao userDao;
    
	public void init() throws ServletException {
		recipeDao = RecipeDao.getInstance();
		userDao = UsersDao.getInstance();
		recipeCommentDao = RecipeCommentsDao.getInstance();
		commentDao = CommentsDao.getInstance();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> messages = new HashMap<String, String>();
        request.setAttribute("messages", messages);
        
		int commentId = Integer.valueOf(request.getParameter("commentId"));
		try {
			RecipeComments recipeComment = recipeCommentDao.getCommentById(commentId);
			recipeCommentDao.delete(recipeComment);
			messages.put("success", "Successfully deleted comment: " + commentId);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
		
		request.getRequestDispatcher("findRecipes.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
