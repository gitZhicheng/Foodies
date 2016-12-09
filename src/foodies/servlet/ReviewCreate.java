package foodies.servlet;

import java.io.IOException;
import java.math.BigDecimal;
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

@WebServlet("/ReviewCreate")
public class ReviewCreate extends HttpServlet {
	
	protected RecipeDao recipeDao;
	protected ReviewsDao reviewDao;
	protected UsersDao userDao;
       
	@Override
	public void init() throws ServletException {
		recipeDao = RecipeDao.getInstance();
		userDao = UsersDao.getInstance();
		reviewDao = ReviewsDao.getInstance();
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
        	BigDecimal rating = new BigDecimal(request.getParameter("rating"));

        	int recipeId = Integer.valueOf(request.getParameter("recipeId"));
        	Users user = (Users)(request.getSession().getAttribute("user"));
        	try {
        		Recipes recipe = recipeDao.getRecipeById(recipeId);
				Reviews review = new Reviews(rating, user, recipe);

        		review = reviewDao.create(review);
        		messages.put("success", "Successfully created review: " + review.getReviewId());
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }

        
        
        request.getRequestDispatcher("findRecipes.jsp").forward(request, response);
	}

}
