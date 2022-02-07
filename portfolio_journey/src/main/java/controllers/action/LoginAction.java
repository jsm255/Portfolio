package controllers.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controllers.UserDAO;
import models.UserDTO;

public class LoginAction implements Action{
	public void execute (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();	
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		UserDAO dao = UserDAO.getInstance();
		
		ArrayList<UserDTO> user = dao.getUsers();
		
		boolean check = false;
		
		for(UserDTO u : user) {
			if(id.equals(u.getId()) && pw.equals(u.getPw())) {
				check = true;
			}
		}
		
		
		String url ="";
		
		if(check) {
			//request.setAttribute("log", id);
			session.setAttribute("log", id);
			System.out.println("id: "+session.getAttribute("log"));
			url="main.jsp";
		}
		else {
			session.setAttribute("msg", "아이디, 비밀번호를 다시 확인해주세요");
			url = "login.jsp";
			
		}
		
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	
}
