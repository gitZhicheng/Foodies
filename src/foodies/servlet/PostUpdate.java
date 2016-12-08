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

import foodies.dal.UsersDao;
import foodies.dal.RecipeDao;
import foodies.dal.PostsDao;
import foodies.model.*;

/**
 * Servlet implementation class RecipeUpdate
 */
@WebServlet("/PostUpdate")
public class PostUpdate extends HttpServlet {
	
	protected RecipeDao recipeDao;
	protected PostsDao postDao;
	protected UsersDao userDao;
	
	@Override
	public void init() throws ServletException {
		recipeDao = RecipeDao.getInstance();
		userDao = UsersDao.getInstance();
		postDao = PostsDao.getInstance();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> messages = new HashMap<String, String>();
        request.setAttribute("messages", messages);
        int recipeId = Integer.valueOf(request.getParameter("recipeId"));
        System.out.println(""+recipeId);
        Recipes recipe = null;
		try {
			recipe = recipeDao.getRecipeById(recipeId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        request.setAttribute("recipe", recipe);
        request.getRequestDispatcher("recipeUpdate.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> messages = new HashMap<String, String>();
        request.setAttribute("messages", messages);
        
        int recipeId = Integer.valueOf(request.getParameter("recipeId"));
        String recipeName = request.getParameter("recipeName");
        if (recipeName == null || recipeName.trim().isEmpty()) {
            messages.put("success", "Invalid RecipeName");
        }
        else {
        	String content = request.getParameter("content");
        	String image = null;
        	String title = request.getParameter("title");
        	
        	Date created = null;

        	Users user = (Users) request.getSession().getAttribute("user");
        	int postId = Integer.valueOf(request.getParameter("postId"));
        	try {
        		Recipes recipe = recipeDao.getRecipeById(recipeId);
				Posts post = new Posts(postId, title, content, image, user, created, recipe);

        		post = postDao.update(post);
        		//messages.put("success", "Successfully updated recipe: " + recipe.getRecipeId());
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        request.getRequestDispatcher("findRecipes.jsp").forward(request, response);
	}

}
