package controllers.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controllers.ReviewDAO;
import models.ReviewDTO;

public class ModifyReviewSubmitAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ReviewDAO rDao = ReviewDAO.getInstance();
		
		int code = Integer.parseInt(request.getParameter("code"));
		ReviewDTO before = rDao.getReview(code);
		
		String id = request.getParameter("id");
		int score = Integer.parseInt(request.getParameter("score"));
		String content = request.getParameter("content");
		String countryName = request.getParameter("countryName");
		if(id.equals("Guest")) {
			String beforePw = request.getParameter("beforePw");
			String afterPw = request.getParameter("afterPw");
			
			if(afterPw.compareTo("") == 0) afterPw = beforePw;
			
			if(beforePw.equals(before.getPw())) {
				ReviewDTO review = new ReviewDTO(code, countryName, content, score, afterPw);
				rDao.modifyReview(review);
			}
			else {
				HttpSession session = request.getSession();
				session.setAttribute("code", code);
				session.setAttribute("content", content);
				session.setAttribute("review", rDao.getReview(code));
				request.getRequestDispatcher("modifyReview.jsp?error=pw").forward(request, response);
				return;
//				response.sendRedirect("modifyReview.jsp?error=pw&code");
			}
		}
		else {
			int userCode = Integer.parseInt(request.getParameter("userCode"));
			ReviewDTO review = new ReviewDTO(code, countryName, id, content, score, userCode);
			rDao.modifyReview(review);
		}
		
		HttpSession session = request.getSession();
		if(session.getAttribute("code") != null) session.removeAttribute("code");
		else if(session.getAttribute("content") != null) session.removeAttribute("content");
		else if(session.getAttribute("review") != null) session.removeAttribute("review");
		
		request.getRequestDispatcher(String.format("viewCountry.jsp?countryName=%s", countryName)).forward(request, response);
		return;
	}

}
