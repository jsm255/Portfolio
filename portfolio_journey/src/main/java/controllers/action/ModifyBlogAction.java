package controllers.action;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import controllers.BlogDAO;
import models.BlogDTO;

public class ModifyBlogAction  implements Action{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String url = "modifyBlog.jsp";

		String str = request.getParameter("code");
		int code = Integer.parseInt(str);
		BlogDAO blogDao = BlogDAO.getInstance();
		
		BlogDTO blogDto = blogDao.getBlog(code);
		
		HttpSession session = request.getSession();
		session.setAttribute("bSession", blogDto);

		request.getRequestDispatcher(url).forward(request, response);
	
	}
}
