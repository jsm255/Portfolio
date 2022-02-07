package controllers.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import controllers.ReReviewDAO;
import controllers.ReviewDAO;
import models.ReReviewDTO;
import models.ReviewDTO;

public class WriteReReviewAction implements Action{
	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int reviewCode = Integer.parseInt(request.getParameter("code"));
		
		String id = "Guest";
		if(request.getParameter("id") != null)
			id = request.getParameter("id");
		
		HttpSession session = request.getSession();
		if(request.getParameter("additional") == null) {	// 답글을 작성하러 간다
			ReviewDAO rDao = ReviewDAO.getInstance();
			ReviewDTO review = rDao.getReview(reviewCode);
			
			session.setAttribute("review", review);
			
			request.getRequestDispatcher("writeReReview.jsp").forward(request, response);
		}
		else {	// 답글을 작성하고 왔다면
			String countryName = "";
			if(session.getAttribute("review") != null) {
				if(session.getAttribute("review") instanceof ReviewDTO)
					countryName = ((ReviewDTO)session.getAttribute("review")).getCountryName();
				session.removeAttribute("review");
			}
				
			ReReviewDAO rrDao = ReReviewDAO.getInstance();
			
			ReReviewDTO rrview = null;
			if(id.equals("Guest")) {
				String content = request.getParameter("content");
				int attachCode = Integer.parseInt(request.getParameter("code"));
				String pw = request.getParameter("pw");
				
				rrview = new ReReviewDTO(content, attachCode, pw);
			}
			else {
				String content = request.getParameter("content");
				int attachCode = Integer.parseInt(request.getParameter("code"));
				int userCode = Integer.parseInt(request.getParameter("userCode"));
				
				rrview = new ReReviewDTO(id, content, attachCode, userCode);
			}
			
			rrDao.writeReReview(rrview);
			request.getRequestDispatcher(String.format("viewCountry.jsp?countryName=%s", countryName)).forward(request, response);
			
		}
		
	}
}
