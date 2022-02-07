package controllers.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controllers.UserDAO;
import models.UserDTO;

public class ModifyUserAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String tel = request.getParameter("tel");
		
		UserDAO dao = UserDAO.getInstance();
		UserDTO user = dao.getId(id);
		
		if(user!=null) {
			if(!pw.equals(user.getPw()))
				user.setPw(pw);
			if(!tel.equals(user.getTel()))
				user.setTel(tel);
			
			dao.updateUser(user);
		}
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		request.getRequestDispatcher("main.jsp").forward(request,response);
		
		
	}

}
