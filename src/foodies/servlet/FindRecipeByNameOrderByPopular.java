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

import foodies.dal.ExperiencedDao;
import foodies.dal.RecipeDao;
import foodies.model.Recipes;

@WebServlet("/FindRecipeByNameOrderByPopular")
public class FindRecipeByNameOrderByPopular extends HttpServlet{
	
	protected RecipeDao recipeDao;
	//protected ExperiencedDao experiencedDao;
       
	@Override
	public void init() throws ServletException {
		recipeDao = RecipeDao.getInstance();
		//experiencedDao = ExperiencedDao.getInstance();
	}
	
	 @Override
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    	Map<String, String> messages = new HashMap<String, String>();
	        request.setAttribute("messages", messages);
	        
	        String stringrcpName = request.getParameter("rcpName");
	    	List<Recipes> recipes = new ArrayList<Recipes>();
	        if (stringrcpName == null || stringrcpName.trim().isEmpty()) {
	            messages.put("success", "Invalid Name");
	        }
	        else {

				try {
					recipes = recipeDao.getRecipesByNameOrderByPopular(stringrcpName);
					messages.put("success", "Displaying results for rcpName: " + stringrcpName);
				} catch (SQLException e) {
					e.printStackTrace();
					throw new IOException(e);
		        }
	        }
			request.setAttribute("rcpName", stringrcpName);
			request.setAttribute("recipes", recipes);
			request.getRequestDispatcher("list-view.jsp").forward(request, response);
		}

		@Override
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doGet(request, response);
		}

}
