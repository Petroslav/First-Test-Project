package controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import model.User;
import model.UsersManager;
import model.db.UserDAO;

/**
 * Servlet implementation class UpdateUserPicServlet
 */
@WebServlet("/UpdateUserPicServlet")
@MultipartConfig
public class UpdateUserPicServlet extends HttpServlet {
	private final static String PIC_DIR = "C:/Users/Luffy/Desktop/TESTPICS/USERPICS";
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = UsersManager.getInstance().getUser(((User)request.getSession().getAttribute("user")).getUsername());
		Part filePart = request.getPart("pic");
	    String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
	    InputStream fileContent = filePart.getInputStream();
	    
	    File save = new File(PIC_DIR);
	    if(!save.exists()){
	    	save.mkdir();
	    }
	    File pics = new File(save, user.getUsername() + "-profilepic-" + fileName);
	    System.out.println("wtf: " + pics.getAbsolutePath());
	    System.out.println("FileName : " + fileName);
	    Files.copy(fileContent, pics.toPath(), StandardCopyOption.REPLACE_EXISTING);
	    user.setPic(pics.getPath());
	    UsersManager.getInstance().updateUser(user, user.getFirstName(), user.getLastName(), user.getAge(), user.getPic());
	    request.getRequestDispatcher("profile.jsp").forward(request, response);
	}

}
