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

@WebServlet("/PostCreate")
public class PostCreate extends HttpServlet {
	
	protected PostsDao postDao;
	protected UsersDao userDao;
    protected RecipeDao recipeDao;
	@Override
	public void init() throws ServletException {
		postDao = PostsDao.getInstance();
		userDao = UsersDao.getInstance();
		recipeDao = RecipeDao.getInstance();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> messages = new HashMap<String, String>();
        request.setAttribute("messages", messages);
        request.getRequestDispatcher("/postCreate.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> messages = new HashMap<String, String>();
        request.setAttribute("messages", messages);
        
        String title = request.getParameter("title");
        System.out.println("PostName: " + title);
        if (title == null || title.trim().isEmpty()) {
            messages.put("success", "Invalid PostName");
        }
        else {
        	String content = request.getParameter("content");
        	int recipeId = Integer.valueOf(request.getParameter("recipeId"));
        	Date created = new Date();
        	String image = null;
        	Users user = (Users)(request.getSession().getAttribute("user"));
        	

        	try {
        		Recipes recipe = recipeDao.getRecipeById(recipeId);
				Posts post = new Posts(title, content, image, user, created, recipe);

        		post = postDao.create(post);
        		messages.put("success", "Successfully created post: " + post.getPostId());
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        request.getRequestDispatcher("findPosts.jsp").forward(request, response);
	}

}
