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
import model.PostManager;

@WebServlet("/PpicServlet")
public class PpicServlet extends HttpServlet {
	
	private final static String PIC_DIR = "C:/Users/Luffy/Desktop/TESTPICS/POSTPIC";
	
	public static void returnProfilePic(Post p,  HttpServletResponse response) throws IOException{
		if(p.getPic().length() < 5) return;
		File profilePicFile = new File(p.getPic());
		response.setContentLength((int)profilePicFile.length());
		String contentType = "image/"+profilePicFile.getName().split("[.]")[profilePicFile.getName().split("[.]").length-1];
		response.setContentType(contentType);
		OutputStream out = response.getOutputStream();
		Files.copy(profilePicFile.toPath(), out);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pid = request.getParameter("pic");
		int picrequest = 0;
		if(pid.equals("") || pid == null)return;
		else picrequest = Integer.parseInt(pid);
		Post post = PostManager.getInstance().getPost(picrequest);
		if(!post.getPic().equals("")){
			returnProfilePic(post, response);
		}
		else return;
	}
}
