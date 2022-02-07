package controllers.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controllers.ReviewDAO;
import models.ReviewDTO;

public class ModifyReviewAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int code = Integer.parseInt(request.getParameter("code"));
		
		ReviewDAO rDAO = ReviewDAO.getInstance();
		ReviewDTO review = rDAO.getReview(code);
		
		// 오브젝트 타입이라 받을 때 타입캐스팅만 해준다면 클래스도 문제없이 넘겨받을 수 있다!
		HttpSession session = request.getSession();
		session.setAttribute("review", review);
		session.setAttribute("code", code);
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		
		request.getRequestDispatcher("modifyReview.jsp").forward(request, response);
		
	}

}
