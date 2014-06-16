package login;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class CookieUtil
{
	public static String getCookieValue(HttpServletRequest request,String cookieName)
	{
		Cookie cookie[]=request.getCookies();
		
		if(cookie!=null)
		{
			for(int i=0;i<cookie.length;i++)
			{
				Cookie temp=cookie[i];
				if(temp.getName().equals(cookieName))
				{
					return temp.getValue();
				}
			}
		}
		return null;
	}	
	
	public static Cookie getCookie(HttpServletRequest request,String cookieName)
	{
		Cookie cookie[]=request.getCookies();
		
		if(cookie!=null)
		{
			for(int i=0;i<cookie.length;i++)
			{
				Cookie temp=cookie[i];
				if(temp.getName().equals(cookieName))
				{
					return temp;
				}
			}
		}
		return null;
	}
}