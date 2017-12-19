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
		if(UsersManager.getInstance().validateReg(username, p1, p2)){
			User u = new User(username, p1, fn, ln, age);
			UserDAO.getInstance().saveUser(u);
			request.getSession().setAttribute("user", UsersManager.getInstance().getUser(u.getUsername()));
			RequestDispatcher rd = request.getRequestDispatcher("main.jsp");
			rd.forward(request, response);
			
		}
		else{
			request.getRequestDispatcher("Rip").forward(request, response);
		}
	}

}
