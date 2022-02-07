package controllers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import controllers.action.Action;
import models.BlogDTO;

/**
 * Servlet implementation class FileServlet
 */
@WebServlet("/writeBlog")
public class WriteBlogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WriteBlogServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		
//		HttpSession session = request.getSession();
		// C:\Users\chox6\git\journey\journey\src\main\webapp\blogImages
		//"C:/Users/A/git/journey/journey/src/main/webapp/blogImages";
		
		ServletContext context = request.getSession().getServletContext();
		
		String realPath = context.getRealPath("/blogImages");
		
		System.out.println(realPath);
//		String path = ".\\blogImages";
		// 포트폴리오 용이라 로컬에 저장해도 괜찮으나
		// 실제 서비스때에는 별도의 이미지서버에 저장을 해야한다는 것을 인지하고 있을 것.
		int maxSize = 20 * 1024 * 1024;
		try {
			MultipartRequest multi = null;
			multi = new MultipartRequest(request, realPath, maxSize, "utf-8", new DefaultFileRenamePolicy());
			
			String fileName = multi.getFilesystemName("image");
			String originalName = multi.getOriginalFileName("image");
			String type = multi.getContentType("image");
			File file = multi.getFile("image");
			
			Enumeration e = multi.getFileNames();
			
			ArrayList<String> images = new ArrayList<>();
			
			while(e.hasMoreElements()) {
				String str = (String)e.nextElement();
				System.out.println(str);
				if(multi.getFilesystemName(str) == null) continue;
				else images.add(multi.getFilesystemName(str));
			}
			
			String title = multi.getParameter("title");
			String content = multi.getParameter("content");
			int score = Integer.parseInt(multi.getParameter("score"));
			String countryName = multi.getParameter("countryName");
			String id = multi.getParameter("id");
			int userCode = Integer.parseInt(multi.getParameter("userCode"));
			
			BlogDTO blog = new BlogDTO(countryName, id, title, content, score, images, userCode);
			
			BlogDAO bDao = BlogDAO.getInstance();
			if(bDao.writeBlog(blog)) {
				HttpSession session = request.getSession();
				if(session.getAttribute("title") != null) {
					session.removeAttribute("title");
					session.removeAttribute("content");
					session.removeAttribute("score");
					session.removeAttribute("countryName");
				}
				request.getRequestDispatcher("blogPage.jsp").forward(request, response);
				return;
			}
			else {
				HttpSession session = request.getSession();
				
				session.setAttribute("title", title);
				session.setAttribute("content", content);
				session.setAttribute("score", score);
				session.setAttribute("countryName", countryName);

				request.getRequestDispatcher("writeBlog.jsp?error=fail").forward(request, response);
				return;
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
