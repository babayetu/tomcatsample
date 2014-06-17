package login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;

public class CheckLogin
{
	private int mRole = 0;
	private int mAction = 0;
	
	public boolean validLogin(HttpServletRequest request)
	{
		String name=request.getParameter("txtName");
		String pwd=request.getParameter("txtPwd");
		
		if (name == null || name.isEmpty() || pwd ==null || pwd.isEmpty()) {
			return false;
		}
		
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		boolean result = false;
		try {
			con = DriverManager.getConnection("proxool.mysql");
			st =con.createStatement(); 
			String combineSQL= "select * from myuser where name='" + name + "'";
			rs = st.executeQuery(combineSQL);
			if (rs != null) {
				rs.next(); // ask db to transfer a row to this server
				if (Md5Hash.md5(pwd).equals(rs.getString("passwd"))) {
					result = true;
					this.setmRole(rs.getInt("role"));
					this.setmAction(rs.getInt("action"));
				} else {
					result = false;
				}
			} else {
				result = false;
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
		
		return result;
	}

	public int getmRole() {
		return mRole;
	}

	private void setmRole(int mRole) {
		this.mRole = mRole;
	}

	public int getmAction() {
		return mAction;
	}

	private void setmAction(int mAction) {
		this.mAction = mAction;
	}
	
	
}