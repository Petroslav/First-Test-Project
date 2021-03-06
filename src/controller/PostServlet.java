package controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import model.Post;
import model.PostManager;
import model.User;
import model.UsersManager;
import model.db.PostDAO;


@WebServlet("/PostServlet")
@MultipartConfig
public class PostServlet extends HttpServlet {
	
	private final static String SAVE_DIR = "C:/Users/Luffy/Desktop/TESTPICS/POSTPIC";
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User op = (User) request.getSession().getAttribute("user");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		if(op == null) {
			request.getRequestDispatcher("index.jsp");
		}
		Post p = null;
		p = new Post(title, content, op);
		if(p != null) PostManager.getInstance().savePost(p);
		
		Part filePart = request.getPart("pic");
	    String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
	    InputStream fileContent = filePart.getInputStream();
	    
	    File save = new File(SAVE_DIR);
	    if(!save.exists()){
	    	save.mkdir();
	    }
	    File pics = new File(save, "post-" + p.getId() + "-" + fileName);
	    System.out.println("wtf: " + pics.getAbsolutePath());
	    System.out.println("FileName : " + fileName);
	    Files.copy(fileContent, pics.toPath(), StandardCopyOption.REPLACE_EXISTING);
		p.setPic(pics.getPath());
		PostDAO.getInstance().addPicToDB(p);
		request.getRequestDispatcher("main.jsp").forward(request, response);
		
	}
}
