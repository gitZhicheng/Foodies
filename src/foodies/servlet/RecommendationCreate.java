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

@WebServlet("/RecommendationCreate")
public class RecommendationCreate extends HttpServlet {
	
	protected RecommendationsDao recommendationDao;
	protected UsersDao userDao;
    protected RecipeDao recipeDao;

	@Override
	public void init() throws ServletException {
		recommendationDao = RecommendationsDao.getInstance();
		userDao = UsersDao.getInstance();
		recipeDao = RecipeDao.getInstance();

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> messages = new HashMap<String, String>();
        request.setAttribute("messages", messages);
        request.getRequestDispatcher("/recommendationCreate.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> messages = new HashMap<String, String>();
        request.setAttribute("messages", messages);
        
        String content = request.getParameter("content");
        System.out.println("Recommendation: " + content);
        if (content == null || content.trim().isEmpty()) {
            messages.put("failed", "Invalid Recommendation");
        }
        else {

        	int recipeId = Integer.valueOf(request.getParameter("recipeId"));
        	int toUserId = Integer.valueOf(request.getParameter("toUserId"));
        	Date created = new Date();
        	Users user = (Users)(request.getSession().getAttribute("user"));

        	try {
            	Users toUser = userDao.getUserById(toUserId);
            	Recipes recipe = recipeDao.getRecipeById(recipeId);
            	Recommendations recommendation = new Recommendations(user, toUser, content, created, recipe);
        		recommendation = recommendationDao.create(recommendation);
        		messages.put("success", "Successfully created recommendation: " + recommendation.getRecommendationId());
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        request.getRequestDispatcher("findRecommendations.jsp").forward(request, response);
	}

}
