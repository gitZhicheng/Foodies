package foodies.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import foodies.dal.UsersDao;
import foodies.model.Recipes;
import foodies.model.Users;
import foodies.model.Reviews;
import foodies.dal.*;;

@WebServlet("/FetchUser")
public class FetchUser extends HttpServlet {

	protected UsersDao usersDao;
	protected RecipeDao recipeDao;
	protected ReviewsDao reviewsDao;
       
	@Override
	public void init() throws ServletException {
		usersDao = UsersDao.getInstance();
		recipeDao = RecipeDao.getInstance();
		reviewsDao = ReviewsDao.getInstance();
	}
       
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Map<String, String> messages = new HashMap<String, String>();
        request.setAttribute("messages", messages);
        Users user = (Users)request.getSession().getAttribute("user");

    	List<Recipes> recipes = new ArrayList<Recipes>();
    	List<Reviews> reviews = new ArrayList<Reviews>();


		try {
			int userId = user.getUserId();
			recipes = recipeDao.getRecipesByUserId(userId);
			reviews = reviewsDao.getReviewsByUserId(userId);
			messages.put("success", "Displaying results for userId: " + userId);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
        
        
        
		request.setAttribute("user", user);
		request.setAttribute("reviews", reviews);
		request.setAttribute("recipes", recipes);
		request.getRequestDispatcher("userPage.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
