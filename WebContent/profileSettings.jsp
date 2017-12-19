<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="model.User" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Settings page</title>
</head>
<body>
	<%
   		response.addHeader("Cache-Control", "no-cache,no-store,private,must-revalidate,max-stale=0,post-check=0,pre-check=0"); 
   		response.addHeader("Pragma", "no-cache"); 
   		response.addDateHeader ("Expires", 0);
  	%>
	<% out.println("<h1>" + ((User)session.getAttribute("user")).getUsername() + "'s profile</h1>" + "<br /> <br />");%>
	<br />
	
	<form action="UpdateServlet" method="POST" >
	<%
	User user = ((User)session.getAttribute("user"));
	out.println("<input type=\"text\" value =\"" + user.getFirstName() + "\" name = \"fn\" /><br />");
	out.println("<input type=\"text\" value =\"" + user.getLastName() + "\" name = \"ln\" /><br />");
	out.println("<input type=\"text\" value =\"" + (user.getAge() == 0 ? "" : user.getAge()) + "\" name = \"age\" /><br />");
	%>
		<input type="submit" value="Save" />
	</form>
	
	<form action="UpdateUserPicServlet" method="POST" enctype="multipart/form-data">
		<input type="file" name="pic" /> <br />
		<input type="submit" value="Save" />
	</form>
	<a href="profile.jsp" class="button">Cancel</a>
</body>
	<style>
		a.button {
		    -webkit-appearance: button;
		    -moz-appearance: button;
		    appearance: button;
		
		    text-decoration: none;
		    color: initial;
		}
	</style>
</html>