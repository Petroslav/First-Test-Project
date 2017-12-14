<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<form action = "PostServlet" method = "POST">
		Title:<br />
		<input type = "text" name = "title" />
		<textarea name = "content" rows = "9" maxlength = "5000"/></textarea>
		<input type="submit" value = "Make post" />
	</form>
</body>
</html>