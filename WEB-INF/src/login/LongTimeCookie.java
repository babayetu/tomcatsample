package login;

import javax.servlet.http.Cookie;

public class LongTimeCookie extends Cookie
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6030011910568303074L;
	
	private  static final int LONG_TIME=60*60*24*365;
	
	public LongTimeCookie(String name,String value)
	{
		super(name,value);
		this.setMaxAge(LONG_TIME);
	}
}