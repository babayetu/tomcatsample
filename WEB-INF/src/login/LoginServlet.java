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
		CheckLogin c =new CheckLogin();
		if(c.isLogin(request))
		{
    		HttpSession session =request.getSession();
    		String name=request.getParameter("txtName");
    		session.setAttribute("userName",name);
    		
	    	if(request.getParameter("checkbox")!=null)
	    	{
	    		Cookie coo=new LongTimeCookie("loginName",name);
	    		response.addCookie(coo);		
	    	}
	    	
			response.sendRedirect("/tomcatsample/jsp/loginAccess.jsp");
		}
		else
			response.sendRedirect("/tomcatsample/jsp/loginFail.jsp");
	}	
	
	public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException
	{
		doGet(request,response);
	}

}