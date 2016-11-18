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

@WebServlet("/RecipeCreate")
public class RecipeCreate extends HttpServlet {
	
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
        request.getRequestDispatcher("/recipeCreate.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> messages = new HashMap<String, String>();
        request.setAttribute("messages", messages);
        
        String recipeName = request.getParameter("recipeName");
        System.out.println("RecipeName: " + recipeName);
        if (recipeName == null || recipeName.trim().isEmpty()) {
            messages.put("success", "Invalid RecipeName");
        }
        else {
        	String desc = request.getParameter("desc");
        	String ingredient = request.getParameter("ingredient");
        	String step = request.getParameter("step");
        	int cookingTime = Integer.valueOf(request.getParameter("cookingTime"));
        	Date created = new Date();
        	String image = null;
        	CuisineTypes type = null;
        	int userId = Integer.valueOf(request.getParameter("userId"));
        	System.out.println("RecipeName: " + recipeName + " UserId: " + userId);
        	Experienced exp = null;
			try {
				exp = experiencedDao.getExperiencedById(userId);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
        	try {
            	Recipes recipe = new Recipes(recipeName, desc, image, step, cookingTime, created,
            			type, ingredient, exp);
        		recipe = recipeDao.create(recipe);
        		messages.put("success", "Successfully created recipe: " + recipe.getRecipeId());
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        request.getRequestDispatcher("findRecipes.jsp").forward(request, response);
	}

}
