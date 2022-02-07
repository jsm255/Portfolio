package controllers.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controllers.ReReviewDAO;
import models.ReReviewDTO;

public class ModifyReReviewAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int rrCode = Integer.parseInt(request.getParameter("code"));
		String countryName = request.getParameter("countryName");
	
		ReReviewDAO rrDao = ReReviewDAO.getInstance();
		ReReviewDTO rrview = rrDao.getReReview(rrCode);
		
		if(request.getParameter("additional") == null) {	// 수정을 해야하면
			
			HttpSession session = request.getSession();
			session.setAttribute("rrview", rrview);
			session.setAttribute("rrCode", rrCode);
			session.setAttribute("countryName", countryName);
			
			request.getRequestDispatcher("modifyReReview.jsp").forward(request, response);
			return;
		}
		else {	// 했으면
			String content = request.getParameter("content");
			String id = request.getParameter("id");
			
			if(id.equals("Guest")) {
				String beforePw = request.getParameter("beforePw");
				String afterPw = beforePw;
				if(request.getParameter("afterPw").compareTo("") != 0) afterPw = request.getParameter("afterPw");
				if(rrview.getPw().equals(beforePw)) {
					rrDao.modifyReReview(new ReReviewDTO(rrCode, id, content, afterPw));
					request.getRequestDispatcher(String.format("viewCountry.jsp?countryName=%s", countryName)).forward(request, response);
					return;
				}
				else {
					HttpSession session = request.getSession();
					session.setAttribute("rrCode", rrCode);
					session.setAttribute("content", content);
					session.setAttribute("rrview", rrview);
					request.getRequestDispatcher("modifyReReview.jsp?error=pw").forward(request, response);
					return;
				}
			}
			else {
				int userCode = Integer.parseInt(request.getParameter("userCode"));
				rrDao.modifyReReview(new ReReviewDTO(rrCode, id, content, userCode));
				request.getRequestDispatcher(String.format("viewCountry.jsp?countryName=%s", countryName)).forward(request, response);
				return;
			}
		}
	}
}
