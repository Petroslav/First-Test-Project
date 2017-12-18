<%@page import="model.Comment"%>
<%@page import="model.CommentManager"%>
<%@page import="model.User"%>
<%@page import="model.PostManager"%>
<%@page import="model.Post"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>PLS GO</title>
</head>
<body>
	<%
   		response.addHeader("Cache-Control", "no-cache,no-store,private,must-revalidate,max-stale=0,post-check=0,pre-check=0"); 
   		response.addHeader("Pragma", "no-cache"); 
   		response.addDateHeader ("Expires", 0);
  	%>
  	
	<h1>POSTS:</h1>
	
	<% if((User) session.getAttribute("user") != null){
		out.print("<a href=\"profile.jsp\">Profile</a>");
		out.print("<a href=\"post.jsp\">New Post</a>");
	}else{
		out.print("<a href=\"index.jsp\">Login</a>");
	}
		
	%>
	<%
		PostManager.getInstance();
		CommentManager.getInstance();
		for(Post p : PostManager.getInstance().getAllPosts().values()){
			request.setAttribute("delPost", p);
			out.print("<table><tr><td><h2>" + p.getTitle() + "</h2></td></tr>");
			out.print("<tr><td><img src=\"PpicServlet?pic=" + p.getId() + "\" alt=\"pic\" height=\"330\" width=\"330\"></td></tr>");
			out.print("<tr><td><h4>" + p.getContent() + "</h4></td></tr>");
			out.print("<tr><td>" + p.getOp().getUsername() + "     " + p.getPostDate() + "</td></tr></table>");
			out.print("<table>");
			if(session.getAttribute("user") != null){
				if(p.getOp().getUsername().equalsIgnoreCase(((User) session.getAttribute("user")).getUsername())){
					out.print("<form action=\"DeletePostServlet\" method=\"POST\">"
							+ "<input type=\"hidden\" name=\"postID\" value=\"" + p.getId() + "\">"
							+ "<input type=\"submit\" value=\"Delete post\"><br />"
							+ "</form>");					
				}
			}
			if(p.getComments().size() > 0){
				out.print("<br /> <h4>Comments: </h4> <br />");
				for(Comment c : p.getComments()){
					if(p.getComments().size() == 0) {
						continue;
					}
					out.print("<tr><td>" + c.getAuthor().getUsername() + "</td> <td>" + c.getContent() + "</td></tr>");
					out.print("<tr><td> Posted on: " + c.getPostDate() + "<br />");
					if(session.getAttribute("user") != null && c.getAuthor().getUsername().equalsIgnoreCase(((User) session.getAttribute("user")).getUsername())){
						out.print("<form action=\"DeleteCommentServlet\" method=\"POST\">"
								+ "<input type=\"hidden\" name=\"commentID\" value=\"" + c.getId() + "\">"
								+ "<input type=\"submit\" value=\"Delete Comment\"></form>");
					}
				}
			}
			if(session.getAttribute("user") != null){
				out.print("<form action = \"NewCommentServlet\" method=\"POST\" >");
				out.print("<input type=\"hidden\" name=\"postID\" value=\"" + p.getId() + "\" <br />");
				out.print("Comment: <br />  <textarea name=\"content\" rows=\"9\" maxlength=\"300\"/></textarea>"
							+ "<br /><input type=\"submit\" value=\"Post Comment\" /></form>");
				out.print("<br /> <br />");
			}
			
		}
	%>
</body>
</html>