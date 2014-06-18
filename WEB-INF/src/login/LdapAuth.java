package login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.servlet.http.HttpServletRequest;

public class LdapAuth {
	private String mRole = "watcher";
	private int mAction = 0;
	
	public String getmRole() {
		return mRole;
	}

	public void setmRole(String mRole) {
		this.mRole = mRole;
	}

	public int getmAction() {
		return mAction;
	}

	public void setmAction(int mAction) {
		this.mAction = mAction;
	}

	public boolean validate(HttpServletRequest request) {
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		
		if (username == null || username.isEmpty() || password ==null || password.isEmpty()) {
			return false;
		}
		
		String principalPrefix = "CORP";
		Hashtable<String,String> env = new Hashtable<String,String>(11);
		DirContext ctx = null;
		boolean passed = true;
		
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, "ldap://SJC-ENTDC-001.corp.ebay.com");
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		env.put(Context.SECURITY_PRINCIPAL, principalPrefix + "\\" + username);
		env.put(Context.SECURITY_CREDENTIALS, password);
		try {
			ctx = new InitialDirContext(env);					
			passed=true;	
			ctx.close();
		} catch (NamingException e) {
			passed=false;			
		}		
		
		if (passed) {
			Connection con = null;
			Statement st = null;
			ResultSet rs = null;
			boolean result = false;
			try {
				con = DriverManager.getConnection("proxool.mysql");
				st =con.createStatement(); 
				String combineSQL= "select * from myuser where name='" + username + "'";
				rs = st.executeQuery(combineSQL);
				if (rs != null) {
					rs.next(); // ask db to transfer a row to this server
					this.setmRole(rs.getString("role"));
					this.setmAction(rs.getInt("action"));	
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
				result = false;
			} finally {
	            try {
	                   if (rs != null) {
	                          rs.close();
	                   }
	                   if (st != null) {
	                          st.close();
	                   }
	                   if (con != null) {
	                          con.close();
	                   }
	            } catch (Exception e) {
	                   e.printStackTrace();
	            }                      
			}
		}
		
		return passed;
	}
}
