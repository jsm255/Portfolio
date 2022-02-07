package controllers.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controllers.ReviewDAO;
import models.ReviewDTO;

public class DeleteReviewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int code = Integer.parseInt(request.getParameter("code"));
		ReviewDAO rDao = ReviewDAO.getInstance();
		ReviewDTO review = rDao.getReview(code);		
		
		HttpSession session = request.getSession();
		if(session.getAttribute("log") == null) {	// Guest이면
			if(request.getParameter("additional") != null) {	// 비밀번호를 입력하는 단계를 거쳤으면
				if(review.getPw().equals(request.getParameter("pw"))) {	// 그래서 그게 맞았으면
					rDao.removeReview(review);
					if(session.getAttribute("review") != null) 
						session.removeAttribute("review");
					request.getRequestDispatcher(String.format("viewCountry.jsp?countryName=%s", review.getCountryName())).forward(request, response);
					return;
				}
				else {	// 틀렸으면
					session.setAttribute("review", review);
					request.getRequestDispatcher("deleteConfirm.jsp?error=pw").forward(request, response);
					return;
				}
			}
			else {	// 아 비밀번호 입력하고 오십쇼
				session.setAttribute("review", review);
				request.getRequestDispatcher("deleteConfirm.jsp").forward(request, response);
				return;
			}
		}
		else {	// 회원이면 삭제 확인을 받으러
			if(request.getParameter("additional") != null) {
				if(request.getParameter("confirm").equals("확인")) {
					rDao.removeReview(review);
					request.getRequestDispatcher(String.format("viewCountry.jsp?countryName=%s", review.getCountryName())).forward(request, response);
					return;
				}
				else {
					session.setAttribute("review", review);
					request.getRequestDispatcher("deleteConfirm.jsp?error=confirm").forward(request, response);
					return;
				}
			}
			else {
				session.setAttribute("review", review);
				request.getRequestDispatcher("deleteConfirm.jsp").forward(request, response);
				return;
			}
		}
		
	}

}
