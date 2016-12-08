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
		doPost(request, response);
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
        	Users user = (Users)(request.getSession().getAttribute("user"));
        	int userId = Integer.valueOf(user.getUserId());
        	System.out.println(userId);
        	int recipeId = Integer.valueOf(request.getParameter("recipeId"));
        	System.out.println(recipeId);
        	Recipes recipe = null;
			try {
	        	user = userDao.getUserById(userId);
	        	recipe = recipeDao.getRecipeById(recipeId);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
        	try {

				RecipeComments recipeComment = new RecipeComments(content, created, user, recipe);
				recipeCommentDao.create(recipeComment);
				
        		messages.put("success", "Successfully created recipe: " + recipeComment.getCommentId());
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        request.getRequestDispatcher("GetRecipe?recipeId="+request.getParameter("recipeId")).forward(request, response);
	}

}
