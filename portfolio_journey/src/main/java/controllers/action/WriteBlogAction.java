package controllers.action;

import java.io.File;
import java.io.IOException;
import java.net.http.HttpRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class WriteBlogAction implements Action{
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		String path = session.getServletContext().getRealPath("/blogImages");
		int maxSize = 5 * 1024 * 1024;
		try {
			MultipartRequest multi = null;
			multi = new MultipartRequest(request, path, maxSize, "utf-8", new DefaultFileRenamePolicy());
			
			String fileName = multi.getFilesystemName("image");
			String originalName = multi.getOriginalFileName("image");
			String type = multi.getContentType("image");
			File file = multi.getFile("image");
			
			System.out.println(fileName);
			System.out.println(originalName);
			System.out.println(type);
			System.out.println(file.length());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
