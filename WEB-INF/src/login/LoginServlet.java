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
	private  static final int LONG_TIME=60*60*24*365;
	private  static final String PASSWORD="testpasswd";

	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException
	{
		CheckLogin checkLogin =new CheckLogin();
		if(checkLogin.validLogin(request))
		{
//    		HttpSession session =request.getSession();
//    		session.setAttribute("username",checkLogin.getmName());
//    		session.setAttribute("role",checkLogin.getmRole());
//    		session.setAttribute("login","true");
    		
    		//role cookie is for other page to determine user permissions
    		StringBuffer sb = new StringBuffer();
    		sb.append(checkLogin.getmName()).append(":")
    		  .append(checkLogin.getmRole()).append(":")
    		  .append(checkLogin.getmMoney());
    		
    		Cookie keys=new Cookie("keys",DESHelper.DoDES(sb.toString(),PASSWORD, 0));
    		
	    	if(request.getParameter("checkbox")!=null)
	    	{
	    		keys.setMaxAge(LONG_TIME);	    		
	    	}
	    	response.addCookie(keys);
	    	
			response.sendRedirect("/tomcatsample/index.jsp");
		}
		else
			response.sendRedirect("/tomcatsample/login.html");
	}	
	
	public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException
	{
		doGet(request,response);
	}

}