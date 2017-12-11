package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import model.UsersManager;
import model.db.UserDAO;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
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
			rd = request.getRequestDispatcher("Success");
			rd.forward(request, response);
			
		}
		else{
			rd = request.getRequestDispatcher("Rip");
			rd.forward(request, response);
		}
	}

}
