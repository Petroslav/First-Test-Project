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
		if(session.getAttribute("user") != null){
			response.sendRedirect("profile.jsp");			
		}
	%>
	<h1>Hello comrade</h1>
	<form method="post" action="LoginServlet">
		<h3>Log in:</h3>
		<fieldset>
			Username: <input type="text" name="username" required />
			Password: <input type="password" name="pass" required />
			<input type="submit" value="Login" />		
		</fieldset>
		<h2>No account? <a href="reg.html">Register here</a></h2>
	</form>
</body>
</html>