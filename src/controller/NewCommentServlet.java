package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Comment;
import model.CommentManager;
import model.User;
import model.UsersManager;

@WebServlet("/NewCommentServlet")
public class NewCommentServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User u = (User) request.getSession().getAttribute("user");
		int pID;
		if(request.getParameter("postID").equals("")){
			System.out.println("No post ID");
			return;
		}else{
			pID = Integer.parseInt(request.getParameter("postID"));
		}
		String content = request.getParameter("content");
		Comment c = new Comment(u, content, pID);
		CommentManager.getInstance().saveComment(c);
		request.getRequestDispatcher("main.jsp").forward(request, response);
	}

}
