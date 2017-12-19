package controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import model.UsersManager;

/**
 * Servlet implementation class UpicServlet
 */
@WebServlet("/UpicServlet")
public class UpicServlet extends HttpServlet {
	
	public static void returnProfilePic(User p,  HttpServletResponse response) throws IOException{
		if(p.getPic().length() < 5) return;
		File profilePicFile = new File(p.getPic());
		response.setContentLength((int)profilePicFile.length());
		String contentType = "image/"+profilePicFile.getName().split("[.]")[profilePicFile.getName().split("[.]").length-1];
		response.setContentType(contentType);
		OutputStream out = response.getOutputStream();
		Files.copy(profilePicFile.toPath(), out);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("user");
		User user = UsersManager.getInstance().getUser(username);
		if(user.getPic() == null) return;
		if(user.getPic() != null || !user.getPic().equals("")){
			returnProfilePic(user, response);
		}
	}
}
