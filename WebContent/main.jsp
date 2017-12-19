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
<link href="css/style.css" type="text/css" rel="stylesheet" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>PLS GO</title>
</head>
<body>
	<%
   		response.addHeader("Cache-Control", "no-cache,no-store,private,must-revalidate,max-stale=0,post-check=0,pre-check=0"); 
   		response.addHeader("Pragma", "no-cache"); 
   		response.addDateHeader ("Expires", 0);
   		PostManager.getInstance();
   		CommentManager.getInstance();
  	%>
	<div class="header">
		<h1 class="logo">Fake9GAG</h1>
		<% if((User) session.getAttribute("user") != null){
			out.print("<span><a href=\"post.jsp\" class=\"btn-post\">NEW POST</a>"
					+ "<a href=\"profile.jsp\" class=\"header-login\">" + ((User) session.getAttribute("user")).getUsername() + "</a></span>");
		}else{
			out.print("<a href=\"index.jsp\" class=\"header-login\">LOGIN</a>");
		}		
		%>
	</div>
	
  <div class="wrapper">

	<%
	for(Post p : PostManager.getInstance().getAllPosts().values()){
		out.print("<div class=\"post\">");
		out.print("<h1 class=\"post-title\">" + p.getTitle() +  "</h1><hr />");
		out.print("<img src=\"PpicServlet?pic=" + p.getId() + "\" alt=\"" + p.getTitle() + "\" class=\"post-content\">");
		out.print("<div class=\"post-footer\">");
		out.print("<span class=\"post-author comment-date\" title=\"" + p.getPostDate() + "\">" + p.getOp().getUsername() + "</span>");
		if(session.getAttribute("user") != null){
			if(p.getOp().getUsername().equalsIgnoreCase(((User) session.getAttribute("user")).getUsername())){
				out.print("<form action=\"DeletePostServlet\" method=\"POST\">"
						+ "<input type=\"hidden\" name=\"postID\" value=\"" + p.getId() + "\">"
						+ "<input type=\"submit\" class=\"btn btn-del post-button\" value=\"DELETE POST\">"
						+ "</form>");					
			}
		}
		out.print("</div>");
		
		if(session.getAttribute("user") != null){
			out.print("<form action=\"NewCommentServlet\" class=\"comment-form\" method=\"POST\">");
			out.print("<input type=\"hidden\" name=\"postID\" value=\"" + p.getId() + "\" />");
			out.print("<textarea name=\"content\" rows=\"5\" cols=\"50\" class=\"comment-postbox\" ></textarea>");
			out.print("<div class=\"postbox-button-footer\">");
			out.print("<input type=\"submit\" class=\"btn btn-new\" value=\"Post\"></div></form>");
		}
		if(p.getComments().size() > 0){
			out.print("<ul class=\"comments\">");
			for(Comment c : p.getComments()){
				out.print("<li class=\"comment\"><img src=\"UpicServlet?user=" + c.getAuthor().getUsername()  + "\" class=\"comment-author-avatar\" />");
				out.print("<div class=\"comment-contents-wrapper\">"
						+ "<div class=\"comment-header\">"
						+ "<span><span class=\"comment-author\">" + c.getAuthor().getUsername() + "</span>"
						+ "<span class=\"comment-date\">" + c.getPostDate() + "</span></span>");
				if(session.getAttribute("user") != null && c.getAuthor().getUsername().equalsIgnoreCase(((User) session.getAttribute("user")).getUsername())){
					out.print("<form action=\"DeleteCommentServlet\" method=\"POST\">"
							+ "<input type=\"hidden\" name=\"commentID\" value=\"" + c.getId() + "\" />"
							+ "<input type=\"submit\" class=\"btn btn-del\" value=\"Delete\" /></form>");
				}
				out.print("</div><p class=\"comment-content\">" + c.getContent()+ "</p></div></li>");
			}
			out.print("</ul>");
		}
		out.print("</div>");
	}
%>
</div>
</body>
</html>