package tomcatsample;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MysqlServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		PrintWriter out = res.getWriter();
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection("proxool.mysql");
			st =con.createStatement(); 
			rs = st.executeQuery( "select * from betuser" );
			if (rs != null) {
				rs.next(); // ask db to transfer a row to this server
				out.println(rs.getString("corp_handle") + rs.getString("deposit_point") + rs.getDate("last_update"));
			} else {
				out.println("Nothing found");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			out.close();
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
}