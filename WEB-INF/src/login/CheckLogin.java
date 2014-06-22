package login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;

public class CheckLogin
{
	private String mRole = "watcher";
	private Double mMoney = 0.0;
	private String mName = "";
	
	public boolean validLogin(HttpServletRequest request)
	{
		String name=request.getParameter("username");
		String pwd=request.getParameter("password");
		
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
					this.setmRole(rs.getString("role"));
					this.setmMoney(rs.getDouble("money"));
					this.setmName(rs.getString("name"));
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

	public String getmRole() {
		return mRole;
	}

	private void setmRole(String mRole) {
		this.mRole = mRole;
	}

	public Double getmMoney() {
		return mMoney;
	}

	public void setmMoney(Double mMoney) {
		this.mMoney = mMoney;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	
}