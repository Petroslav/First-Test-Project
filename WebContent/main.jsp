<%@page import="model.User"%>
<%@page import="model.PostManager"%>
<%@page import="model.Post"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
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
		for(Post p : PostManager.getInstance().getAllPosts().values()){
			out.print("<table><tr><td><h2>" + p.getTitle() + "</h2></td></tr>");
			out.print("<tr><td><h4>" + p.getContent() + "</h4></td></tr>");
			out.print("<tr><td>" + p.getOp().getUsername() + "     " + p.getPostDate() + "</td></tr></table>");
			if(session.getAttribute("user") != null){
				session.setAttribute("delPost", p);
				out.print(p.getOp().getUsername().equalsIgnoreCase(((User) session.getAttribute("user")).getUsername()) ? 
					"<form action=\"DeletePostServlet\" method=\"POST\">"
					+ "<input type=\"hidden\" value=\"delPost\" name=\"postid\" />"
					+ "<input type=\"submit\" value=\"Delete Post\"></form></td></tr></table>" : "</table>");
			}	
			out.print("<br /><br />");
		}
	%>
</body>
</html>