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

import model.Post;
import model.User;

/**
 * Servlet implementation class PicRequest
 */
@WebServlet("/PicRequest")
public class PicRequest extends HttpServlet {
	private final static String SAVE_DIR = "C:/Users/Luffy/Desktop/TESTPICS/POSTPIC";	
	
	public static void returnProfilePic(Post p,  HttpServletResponse response) throws IOException{

		File profilePicFile = new File(SAVE_DIR, p.getPic());
		response.setContentLength((int)profilePicFile.length());
		String contentType = "image/"+profilePicFile.getName().split("[.]")[profilePicFile.getName().split("[.]").length-1];
		response.setContentType(contentType);
		OutputStream out = response.getOutputStream();
		Files.copy(profilePicFile.toPath(), out);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

}
