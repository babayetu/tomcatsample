<%@ page contentType="text/html; charset=UTF-8" language="java" import="login.*" errorPage="" %>

<%!String name=null;
%>

<% if (CookieUtil.getCookieValue(request, "loginName") != null) 
   {
		name = CookieUtil.getCookieValue(request, "loginName");
		
		session.setAttribute("userName", name);
   }
%>



<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Login Page</title>
</head>

<body>
<% 
	if(name==null)
	{
%>
		Current User: guest
<%
	}
	else
	{
%>
		Current User: <%=name%>
<%  }
%>

<form id="form1" name="form1" method="post" action="/tomcatsample/login.do">
  <table width="336" height="102" border="1">
    <tr>
      <td width="169"><div align="center">name</div></td>
      <td colspan="2"><label>
        <input name="txtName" type="text" id="txtName" />
      </label></td>
    </tr>
    <tr>
      <td><div align="center">pwd</div></td>
      <td colspan="2"><label>
        <input name="txtPwd" type="password" id="txtPwd" />
      </label></td>
    </tr>
    <tr>
      <td><label>
        <input type="checkbox" name="checkbox" value="3" />
        Remember Me</label></td>
      <td width="60"><label></label></td>
      <td width="85"><input type="submit" name="Submit" value="Submit" /></td>
    </tr>
  </table>
</form>
</body>
</html>
