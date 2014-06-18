package login;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5714465944344943983L;

	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException
	{
//		CheckLogin checkLogin =new CheckLogin();
//		if(checkLogin.validLogin(request))
		LdapAuth ldap = new LdapAuth();
		if (ldap.validate(request))
		{
    		HttpSession session =request.getSession();
    		String name=request.getParameter("username");
    		session.setAttribute("userName",name);
    		
    		//role cookie is for other page to determine user permissions
    		
    		//Cookie role=new LongTimeCookie("role",Md5Hash.md5(String.valueOf(checkLogin.getmRole())));
    		Cookie role=new LongTimeCookie("role",Md5Hash.md5(String.valueOf(ldap.getmRole())));
    		response.addCookie(role);
    		
	    	if(request.getParameter("checkbox")!=null)
	    	{
	    		Cookie loginName=new LongTimeCookie("loginName",name);	    		
	    		response.addCookie(loginName);	    		
	    	} else {
	    		Cookie loginName=new Cookie("loginName",name);	    		
	    		response.addCookie(loginName);
	    	}
	    	
			response.sendRedirect("/tomcatsample/html/table_betpal.html");
		}
		else
			response.sendRedirect("/tomcatsample/html/login_betpal.html");
	}	
	
	public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException
	{
		doGet(request,response);
	}

}