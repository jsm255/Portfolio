package controllers.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controllers.BlogDAO;

public class DeleteBlogAction implements Action{
	 @Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 int blogCode = Integer.parseInt(request.getParameter("code"));
		 System.out.println("code : " + blogCode);
		 BlogDAO bDao = BlogDAO.getInstance();
		 bDao.deleteBlog(blogCode);
		 
		 String url = "blogPage.jsp";
		 RequestDispatcher rdp = request.getRequestDispatcher(url);
			rdp.forward(request, response);
	}
}
