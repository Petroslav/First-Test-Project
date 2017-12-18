package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import model.db.UserDAO;

@WebServlet("/UpdateServlet")
@MultipartConfig
public class UpdateServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fn = request.getParameter("fn");
		String ln = request.getParameter("ln");		
		
		int age;
		
		if(request.getParameter("age").equals("") || request.getParameter("age").contains("-")) {
			age = 0;
		}
		else {
			age = Integer.parseInt(request.getParameter("age"));
		}
		
		User u = (User) request.getSession().getAttribute("user");
		UserDAO.getInstance().updateUserInfo(u, fn, ln, age, u.getPic());		
		request.getRequestDispatcher("profile.jsp").forward(request, response);
	}

}
