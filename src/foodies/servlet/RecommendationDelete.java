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

import foodies.dal.RecommendationsDao;
import foodies.model.*;

/**
 * Servlet implementation class RecommendationDelete
 */
@WebServlet("/RecommendationDelete")
public class RecommendationDelete extends HttpServlet {

	protected RecommendationsDao recommendationDao;
	
	public void init() throws ServletException {
		recommendationDao = RecommendationsDao.getInstance();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> messages = new HashMap<String, String>();
        request.setAttribute("messages", messages);
        
		int recommendationId = Integer.valueOf(request.getParameter("recommendationId"));
		try {
			Recommendations recommendation = recommendationDao.getRecommendationByRecommendationId(recommendationId);
			recommendationDao.delete(recommendation);
			messages.put("success", "Successfully deleted recommendation: " + recommendationId);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
		
		request.getRequestDispatcher("userPage.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
