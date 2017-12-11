package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.UsersManager;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setStatus(200);
		RequestDispatcher rd = request.getRequestDispatcher("grill.html");
		rd.forward(request, response);
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String u = request.getParameter("username");
		String p = request.getParameter("pass");
		
		if(UsersManager.getInstance().validateLogin(u, p)){
			RequestDispatcher rd = request.getRequestDispatcher("grill.html");
			rd.forward(request, response);
		}
		else{

			RequestDispatcher rd = request.getRequestDispatcher("Rip");
			rd.forward(request, response);
		}
	}

}