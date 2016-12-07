package foodies.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import foodies.dal.CuisineTypesDao;
import foodies.dal.RecipeDao;
import foodies.dal.UsersDao;
import foodies.model.CuisineTypes;
import foodies.model.Recipes;

/**
 * Servlet implementation class GetRecipe
 */
@WebServlet("/GetRecipe")
public class GetRecipe extends HttpServlet {

	RecipeDao recipeDao;
	CuisineTypesDao cuisineTypeDao;
	
	@Override
	public void init() throws ServletException {
		recipeDao = RecipeDao.getInstance();
		cuisineTypeDao = CuisineTypesDao.getInstance();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestId = request.getParameter("recipeId");
		int rid = Integer.valueOf(requestId);
		try {
			Recipes recipe = recipeDao.getRecipeById(rid);
			request.setAttribute("recipe", recipe);
			String[] ingrs = recipe.getIngredients().split("\n");
//			int len = ingrs.length;
//			String[] ingrs1 = Arrays.copyOfRange(ingrs, 0, len / 2);
//			String[] ingrs2 = Arrays.copyOfRange(ingrs, len / 2 + 1, len);
//			request.setAttribute("ingrs1", ingrs1);
//			request.setAttribute("ingrs2", ingrs2);
			request.setAttribute("ingrs", ingrs);
			request.setAttribute("steps", recipe.getStep().split("\n"));
			String parentIds = recipe.getCuisineTypes().getParentIds();
			List<String> cuisineNames = parentIds2Names(parentIds);
			request.setAttribute("cuisineNames", cuisineNames);
			
			//request.setAttribute("user", request.getAttribute("user"));
		} catch (SQLException e){
			e.printStackTrace();
		}
		request.getRequestDispatcher("recipe.jsp").forward(request, response);
	}
	
	private List<String> parentIds2Names(String parentIds) throws NumberFormatException, SQLException {
		String[] pids = parentIds.split(",");
		List<String> pidList = new ArrayList<String>();
		for(String pid : pids) {
			CuisineTypes ct = cuisineTypeDao.getCuisineTypesById(Integer.valueOf(pid));
			pidList.add(ct.getCuisineTypeName());
		}
		return pidList;
	}

}
