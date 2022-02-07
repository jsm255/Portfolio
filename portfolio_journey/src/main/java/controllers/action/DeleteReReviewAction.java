package controllers.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controllers.ReReviewDAO;
import controllers.ReviewDAO;
import models.ReReviewDTO;
import models.ReviewDTO;

public class DeleteReReviewAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		
		int code = Integer.parseInt(request.getParameter("code"));
		String countryName = request.getParameter("countryName");
		ReReviewDAO rrDao = ReReviewDAO.getInstance();
		ReReviewDTO rrview = rrDao.getReReview(code);	
		
		HttpSession session = request.getSession();
		if(session.getAttribute("log") == null) {	// Guest이면
			if(request.getParameter("additional") != null) {	// 비밀번호를 입력하는 단계를 거쳤으면
				if(rrview.getPw().equals(request.getParameter("pw"))) {	// 그래서 그게 맞았으면
					rrDao.removeReReview(rrview);

					if(session.getAttribute("rrview") != null) 
						session.removeAttribute("rrview");
					request.getRequestDispatcher(String.format("viewCountry.jsp?countryName=%s", countryName)).forward(request, response);
					return;
				}
				else {	// 틀렸으면
					session.setAttribute("rrview", rrview);
					request.getRequestDispatcher(String.format("deleteConfirm.jsp?countryName=%s&error=pw", countryName)).forward(request, response);
					return;
				}
			}
			else {	// 아 비밀번호 입력하고 오십쇼
				session.setAttribute("rrview", rrview);
				request.getRequestDispatcher(String.format("deleteConfirm.jsp?countryName=%s", countryName)).forward(request, response);
				return;
			}
		}
		else {	// 회원이면 삭제 프리패스
			if(request.getParameter("additional") != null) {
				if(request.getParameter("confirm").equals("확인")) {
					rrDao.removeReReview(rrview);
					request.getRequestDispatcher(String.format("viewCountry.jsp?countryName=%s", countryName)).forward(request, response);
					return;
				}
				else {
					session.setAttribute("rrview", rrview);
					request.getRequestDispatcher(String.format("deleteConfirm.jsp?countryName=%s&error=confirm", countryName)).forward(request, response);
				}
			}
			else {
				session.setAttribute("rrview", rrview);
				request.getRequestDispatcher(String.format("deleteConfirm.jsp?countryName=%s", countryName)).forward(request, response);
				return;
			}
		}
		
		
	}
}
