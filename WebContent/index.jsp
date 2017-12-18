
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en" >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta charset="UTF-8">
<title>Log in</title>
      <link rel="stylesheet" href="css/style.css">
</head>
<body>	
	<%
		if(session.getAttribute("user") != null){
			response.sendRedirect("profile.jsp");			
		}
	%>
  <div class="wrapper">
	<div class="container">
		<h1>Welcome</h1>
		
		<form action="LoginServlet" method="POST" class="form">
			<input type="text" name="username" placeholder="Username">
			<input type="password" name="pass" placeholder="Password">
			<input type="submit" value="Login">
		</form>
	</div>
	
	<ul class="bg-bubbles">
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
	</ul>
</div>
  <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>

    <script  src="js/index.js"></script>

</body>
</html>