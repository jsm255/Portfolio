package controllers.action;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import models.BlogDTO;

public class DoModifyBlogAction implements Action{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 try {
			    if (-1 < request.getContentType().indexOf("multipart/form-data")) {
		HttpSession session = request.getSession();
		
		String title = request.getParameter("title");
		String countryName = request.getParameter("countryName");
		String range = request.getParameter("range");
		String content = request.getParameter("content");
		String images = request.getParameter("image1");
		System.out.println("Image : " + images);
//		String path = "C:/Users/A/git/journey/journey/src/main/webapp/images";
		
		ServletContext context = request.getSession().getServletContext();
		
		String realPath = context.getRealPath("/blogImages");
		
		System.out.println(realPath);
		
	    int size = 1024 * 1024 * 20; //20MB
		MultipartRequest multiRequest = new MultipartRequest(request,realPath,size,"UTF-8", new DefaultFileRenamePolicy());
		
		Enumeration files = multiRequest.getFileNames();
    	String str = (String)files.nextElement();
    	String filename = multiRequest.getFilesystemName(str);
    	String original_filename = multiRequest.getOriginalFileName(str);
			    }
			    
		   }catch (IOException ie) {
		    ie.printStackTrace();
		   }catch (Exception e) {
		    e.printStackTrace();
		   } 
	}
}
