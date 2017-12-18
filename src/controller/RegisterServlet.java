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

import model.User;
import model.UsersManager;
import model.db.UserDAO;

@WebServlet("/RegisterServlet")
@MultipartConfig
public class RegisterServlet extends HttpServlet {
	private final static String PIC_DIR = "C:/Users/Luffy/Desktop/TESTPICS/USERPICS";
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("u");
		String p1 = request.getParameter("p1");
		String p2 = request.getParameter("p2");
		String fn = request.getParameter("fn");
		String ln = request.getParameter("ln");

		int age;

		if(request.getParameter("age").equals("") || request.getParameter("age").contains("-")) age = 0;
		else age = Integer.parseInt(request.getParameter("age"));
		
		RequestDispatcher rd = null;
		if(UsersManager.getInstance().validateReg(username, p1, p2)){
			User u = new User(username, p1, fn, ln, age);
			UserDAO.getInstance().saveUser(u);		
			Part filePart = request.getPart("pic");
		    String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
		    InputStream fileContent = filePart.getInputStream();
		    File save = new File(PIC_DIR);
		    if(!save.exists()){
		    	save.mkdir();
		    }
		    File pics = new File(save, u.getUsername() + "-profilepic-" + fileName);
		    System.out.println("wtf: " + pics.getAbsolutePath());
		    System.out.println("FileName : " + fileName);
		    Files.copy(fileContent, pics.toPath(), StandardCopyOption.REPLACE_EXISTING);
		    u.setPic(pics.getPath());
		    UserDAO.getInstance().updateUserInfo(u, u.getFirstName(), u.getLastName(), u.getAge(), u.getPic());
			rd = request.getRequestDispatcher("Success");
			rd.forward(request, response);
			
		}
		else{
			rd = request.getRequestDispatcher("Rip");
			rd.forward(request, response);
		}
	}

}
