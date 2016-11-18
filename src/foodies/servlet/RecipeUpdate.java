package foodies.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import foodies.model.Experienced;
import foodies.dal.ExperiencedDao;
import foodies.dal.RecipeDao;
import foodies.model.*;

/**
 * Servlet implementation class RecipeUpdate
 */
@WebServlet("/RecipeUpdate")
public class RecipeUpdate extends HttpServlet {
	
	protected RecipeDao recipeDao;
	protected ExperiencedDao experiencedDao;
	
	@Override
	public void init() throws ServletException {
		recipeDao = RecipeDao.getInstance();
		experiencedDao = ExperiencedDao.getInstance();
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
        	String desc = request.getParameter("desc");
        	String image = null;
        	String ingredient = request.getParameter("ingredient");
        	String step = request.getParameter("step");
        	int cookingTime = Integer.valueOf(request.getParameter("cookingTime"));
        	Date created = null;
        	CuisineTypes type = null;
        	Experienced exp = null;
        	int userId = Integer.valueOf(request.getParameter("userId"));
        	try {
            	exp = experiencedDao.getExperiencedById(userId);
            	Recipes recipe = new Recipes(recipeId, recipeName, desc, image, step, cookingTime, 
            			created, type, ingredient, exp);
        		recipe = recipeDao.update(recipe);
        		//messages.put("success", "Successfully updated recipe: " + recipe.getRecipeId());
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        request.getRequestDispatcher("findRecipes.jsp").forward(request, response);
	}

}
