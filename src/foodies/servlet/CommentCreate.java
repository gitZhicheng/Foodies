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

@WebServlet("/CommentCreate")
public class CommentCreate extends HttpServlet {
	
	protected RecipeDao recipeDao;
	protected UsersDao userDao;
    protected RecipeCommentsDao recipeCommentDao;
    protected CommentsDao commentDao;
	@Override
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
        request.getRequestDispatcher("/recipeCreate.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> messages = new HashMap<String, String>();
        request.setAttribute("messages", messages);
    	String content = request.getParameter("content");

        System.out.println("RecipeName: " + content);
        if (content == null || content.trim().isEmpty()) {
            messages.put("failed", "Invalid content");
        }
        else {
        	Date created = new Date();
        	int userId = Integer.valueOf(request.getParameter("userId"));
        	int recipeId = Integer.valueOf(request.getParameter("recipeId"));
        	Recipes recipe = null;
        	Users user = null;
			try {
	        	user = userDao.getUserById(userId);
	        	recipe = recipeDao.getRecipeById(recipeId);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
        	try {

				Comments comment = new Comments(content,created,user);
				comment = commentDao.create(comment);
				RecipeComments recipeComment = new RecipeComments(comment.getCommentId(), content, created, user, recipe);

				recipeCommentDao.create(recipeComment);
				
        		messages.put("success", "Successfully created recipe: " + comment.getCommentId());
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        request.getRequestDispatcher("findRecipes.jsp").forward(request, response);
	}

}
