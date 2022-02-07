package controllers.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controllers.ReviewDAO;
import models.ReviewDTO;

public class WriteReviewAction implements Action{
public void execute (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String content = request.getParameter("content");
		String id = request.getParameter("id");
		String countryName = request.getParameter("countryName");
		int score = Integer.parseInt(request.getParameter("score"));
		String pw = "";
		
		ReviewDTO review = null;
		if(id.equals("Guest")) {
			pw = request.getParameter("pw");
			review = new ReviewDTO(countryName, content, score, pw);
		}
		else {
			int userCode = Integer.parseInt(request.getParameter("userCode"));
			review = new ReviewDTO(countryName, id, content, score, userCode);
		}
		
		
		ReviewDAO rDao = ReviewDAO.getInstance();
		rDao.writeReview(review);
		
		request.getRequestDispatcher("viewCountry.jsp").forward(request, response);
		
	}
}
