<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="model.User" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Profile page</title>
</head>
<body>
	<%
   		response.addHeader("Cache-Control", "no-cache,no-store,private,must-revalidate,max-stale=0,post-check=0,pre-check=0"); 
   		response.addHeader("Pragma", "no-cache"); 
   		response.addDateHeader ("Expires", 0);
  	%>
	<% out.println("<h1>" + ((User)session.getAttribute("user")).getUsername() + "'s profile</h1>" + "<br /> <br />");%>
	<br />
	<% 
		User user = ((User)session.getAttribute("user"));
		out.println("<p>" + user.getFirstName() + "<br />");
		out.println(user.getLastName() + "<br />");
		out.println((user.getAge() == 0 ? "" : user.getAge()) + "</p>");
	%>
	<a href="profileSettings.jsp" class="button">Settings</a>
	<a href="main.jsp" class="button">Main</a>
	
	<form action="LogoutServlet" method="POST">
		<input type="submit" value="Logout" />
	</form>
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