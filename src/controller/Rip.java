package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Rip
 */
@WebServlet("/Rip")
public class Rip extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String full_page = "<!DOCTYPE html>"
							+ "<html><head><meta charset=\"UTF-8\"><title>New user registration</title>"
							+ "</head><body>"
							+ "<form method=\"post\" action=\"RegisterServlet\">"
							+ "<h3>Register:</h3>"
							+ "<fieldset>"
							+ "Username: <br /><input type=\"text\" name=\"u\" /><br />"
							+ "Password: <br /><input type=\"password\" name=\"p1\" /><br />"
							+ "Repeat password: <br /><input type=\"password\" name=\"p2\" /> <br />"
							+ "First Name: <br /><input type=\"text\" name=\"fn\" /><br />"
							+ "Last Name: <br /><input type=\"text\" name=\"ln\" /><br />"
							+ "Age: <br /><input type=\"text\" name=\"age\" /><br />"
							+ "<h3>Username taken or password doesn't match</h3><br />"
							+ "<input type=\"submit\" value=\"Register\" />"
							+ "</fieldset>"
							+ "<h2><a href=\"index.html\">Home</a></h2>"
							+ "</form></body></html>";
		response.getWriter().append(full_page);
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
