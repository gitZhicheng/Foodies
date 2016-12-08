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

import foodies.dal.ReviewsDao;
import foodies.model.*;

/**
 * Servlet implementation class ReviewDelete
 */
@WebServlet("/ReviewDelete")
public class ReviewDelete extends HttpServlet {

	protected ReviewsDao reviewDao;
	
	public void init() throws ServletException {
		reviewDao = ReviewsDao.getInstance();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> messages = new HashMap<String, String>();
        request.setAttribute("messages", messages);
        
		int reviewId = Integer.valueOf(request.getParameter("reviewId"));
		try {
			Reviews review = reviewDao.getReviewByReviewId(reviewId);
			reviewDao.delete(review);
			messages.put("success", "Successfully deleted review: " + reviewId);
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
