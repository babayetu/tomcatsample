<%
	Cookie cookie = new Cookie("keys",null); 
	cookie.setMaxAge(0);
	response.addCookie(cookie); 	
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>Login Successfully</title>
</head>

<body>

	<p>Logout OK</p>
	<p>&nbsp;</p>
	<p><a href="/tomcatsample/index.jsp">Go Back Home</a></p>
	<p>&nbsp;</p>
</body>
</html>