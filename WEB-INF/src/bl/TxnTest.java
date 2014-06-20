package bl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TxnTest {

	public static void main(String[] args) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection("proxool.mysql");
			con.setAutoCommit(false);
			st =con.createStatement(); 
			StringBuffer sb= new StringBuffer();
			// check user name is in database
			sb.append("select * from myuser where name='").append("karl liu").append("'");
			rs = st.executeQuery(sb.toString());
			sb.delete(0, sb.length());
			if (rs != null) {
				rs.next(); // ask db to transfer a row to this server
				Double balance = rs.getDouble("money");
				if (balance > Double.valueOf("50")) {
					java.sql.Date date=new java.sql.Date(new java.util.Date().getTime());
					sb.append("insert into myorder(name,money,rate_id,order_time) values('")
					   .append("karl liu").append("',")
					   .append("50").append(",")
					   .append("3").append(",'")
					   .append(date.toString()).append("')");
					PreparedStatement pstat =con.prepareStatement(sb.toString());
					pstat.executeUpdate();
					sb.delete(0, sb.length());
					sb.append("update myuser set money=")
					   .append(String.valueOf(balance - Double.valueOf("50"))).append(" where name='")
					   .append("karl liu").append("'");
					pstat =con.prepareStatement(sb.toString());
					pstat.executeUpdate();
					con.commit();
				} else {
					System.out.println(balance + " 余额不足,无法投注。");
				}				
			} else {		    	
		    	System.out.println("karl liu" + " 不是参赛者,无法投注。请联系管理员充值！");
			}			
		} catch (SQLException e) {
			e.printStackTrace();
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
}