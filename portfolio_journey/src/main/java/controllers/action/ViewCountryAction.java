package controllers.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controllers.CountryDAO;
import controllers.XMLParser;
import models.CountryDTO;

public class ViewCountryAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String countryName = request.getParameter("country");
		
		CountryDAO cDao = CountryDAO.getInstance();
		CountryDTO country = cDao.searchCountry(countryName);
		
		// 리턴이 있다면
		if(country != null) 
			request.getRequestDispatcher(String.format("viewCountry.jsp?countryName=%s", countryName)).forward(request, response);
			
		// 없다면
		else {
			Map<String, Object> map = XMLParser.getCountryInfo(countryName);
			
			if(map == null) 
				request.getRequestDispatcher("main.jsp?error=fail").forward(request, response);
			else {
				country = new CountryDTO(String.valueOf(map.get("countryName")), String.valueOf(map.get("info")), String.valueOf(map.get("img")));
				
				cDao.generateCountry(country);
				
				request.getRequestDispatcher(String.format("viewCountry.jsp?countryName=%s", countryName)).forward(request, response);
			}
			
		}
		
	}

}
