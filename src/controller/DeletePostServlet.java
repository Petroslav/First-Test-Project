package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Post;
import model.PostManager;

@WebServlet("/DeletePostServlet")
public class DeletePostServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id;
		if(!request.getParameter("postID").equals("")) {
			id = Integer.parseInt(request.getParameter("postID"));
			Post p = PostManager.getInstance().getPost(id);
			PostManager.getInstance().delPost(p);
		}
		request.getRequestDispatcher("main.jsp").forward(request, response);
	}

}
