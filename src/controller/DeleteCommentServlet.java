package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CommentManager;
import model.Comment;

@WebServlet("/DeleteCommentServlet")
public class DeleteCommentServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id;
		if(!request.getParameter("commentID").equals("")) {
			id = Integer.parseInt(request.getParameter("commentID"));
			Comment c = CommentManager.getInstance().getComment(id);
			CommentManager.getInstance().deleteComment(c);
		}
		request.getRequestDispatcher("main.jsp").forward(request, response);
	}

}
