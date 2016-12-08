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

import foodies.dal.RecipeDao;
import foodies.model.*;

/**
 * Servlet implementation class RecipeDelete
 */
@WebServlet("/PostCommentDelete")
public class PostCommentDelete extends HttpServlet {

	protected RecipeDao recipeDao;
	
	public void init() throws ServletException {
		recipeDao = RecipeDao.getInstance();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> messages = new HashMap<String, String>();
        request.setAttribute("messages", messages);
        
		int recipeId = Integer.valueOf(request.getParameter("recipeId"));
		try {
			Recipes recipe = recipeDao.getRecipeById(recipeId);
			recipeDao.delete(recipe);
			messages.put("success", "Successfully deleted recipe: " + recipeId);
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
