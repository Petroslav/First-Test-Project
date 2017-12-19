<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register</title>
      <link href="css/style.css" type="text/css" rel="stylesheet">
</head>
<body>

	<%
   		response.addHeader("Cache-Control", "no-cache,no-store,private,must-revalidate,max-stale=0,post-check=0,pre-check=0"); 
   		response.addHeader("Pragma", "no-cache"); 
   		response.addDateHeader ("Expires", 0);
  	%>	
	<%
		if(session.getAttribute("user") != null){
			response.sendRedirect("profile.jsp");			
		}
	%>
  <div class="wrapper">
	<div class="container">
		<h1 class="headline">Sign Up</h1>
		
		<form action="RegisterServlet" method="POST" class="form">			
			<input type="text" class="input-login" name='u' placeholder="Username" required>
			<input type="text" class="input-login" name='fn' placeholder="First Name">
			<input type="text" class="input-login" name='ln' placeholder="Last Name">
			<input type="text" class="input-login" name='age' placeholder="Age">
			<input type="password" class="input-login" name='p1' placeholder="Password" required>
			<input type="password" class="input-login" name='p2' placeholder="Repeat Password" required>
			<input type="submit" class="btn btn-login" value="Register">
		</form>
	</div>
</div>
  <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>

    <script  src="js/index.js"></script>

</body>
</html>