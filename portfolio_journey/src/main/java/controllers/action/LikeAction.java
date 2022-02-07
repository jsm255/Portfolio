package controllers.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controllers.CountryDAO;
import controllers.LikeDAO;
import models.LikeDTO;

public class LikeAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		LikeDAO lDao = LikeDAO.getInstance();
		CountryDAO cDAO = CountryDAO.getInstance();
		String action = request.getParameter("command");
		String countryName = request.getParameter("countryName");
		
		HttpSession session = request.getSession();
		if(session.getAttribute("log") == null) {
			request.getRequestDispatcher("login.jsp?error=needLogin").forward(request, response);
			return;
		}
		else {
			String id = String.valueOf(session.getAttribute("log"));
			if(action.equals("like")) {
				LikeDTO heart = new LikeDTO(String.valueOf(session.getAttribute("log")), countryName);
				if(lDao.findIdCountryName(id, countryName) == -1) { // 이미 처리된 내용이 없으면
					lDao.addLike(heart);
					cDAO.likeCnt(countryName);
					request.getRequestDispatcher(String.format("viewCountry.jsp?countryName=%s&action=like", countryName)).forward(request, response);
					return;
				}
				else {
					request.getRequestDispatcher(String.format("viewCountry.jsp?countryName=%s&action=duplicate", countryName)).forward(request, response);
					return;
				}
			}
			else {
				LikeDTO heart = new LikeDTO(String.valueOf(session.getAttribute("log")), countryName);
				if(lDao.findIdCountryName(id, countryName) == -1) { // 없는데 지우려고 하면
					request.getRequestDispatcher(String.format("viewCountry.jsp?countryName=%s&action=duplicate", countryName)).forward(request, response);
					return;
				}
				else {
					lDao.deleteLike(heart);
					cDAO.delCnt(countryName);
					request.getRequestDispatcher(String.format("viewCountry.jsp?countryName=%s&action=hate", countryName)).forward(request, response);
					return;
				}

			}
		}
	}
}
