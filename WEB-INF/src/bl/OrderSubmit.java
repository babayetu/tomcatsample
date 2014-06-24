package bl;

import java.io.IOException;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OrderSubmit extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -643101517948503479L;

	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException
	{
   		String rate_id = request.getParameter("rate_id");
    	String betuser = request.getParameter("betuser");
    	String betmoney = request.getParameter("betmoney");
    	
    	if (rate_id==null || rate_id.isEmpty() || betuser==null || betuser.isEmpty() || betmoney==null || betmoney.isEmpty()) {
    		return;
    	}
    		
    		// TODO check cookie for username:role, to make sure user is correct person
    		
//check myuser table to get money, judge banlance, and deduce money
//    	System.out.println("rate_id:" + rate_id);
//    	System.out.println("betuser:"+ betuser);
//    	System.out.println("betmoney:" + betmoney);
    	
    	Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		PreparedStatement pstat = null;

		try {
			con = DriverManager.getConnection("proxool.mysql");
			con.setAutoCommit(false);
			st =con.createStatement(); 
			StringBuffer sb= new StringBuffer();
			// check user name is in database
			sb.append("select * from myuser where name='").append(betuser).append("'");
			rs = st.executeQuery(sb.toString());
			sb.delete(0, sb.length());
			if (rs != null && rs.next()) {				
				Double balance = rs.getDouble("money");
				
				//judge whether this match is already bet
				sb.append("select * from myorder o where o.name='").append(betuser).append("'")
				  .append(" and o.rate_id in (select r1.rate_id from myrate r1, myrate r2 where r2.rate_id=")
				  .append(rate_id).append(" and r1.match_id=r2.match_id)");
				rs = st.executeQuery(sb.toString());
				sb.delete(0, sb.length());
				if (rs != null && rs.next()) {
					// user already bet on this match
			    	response.setContentType("text/html;charset=UTF-8");
			    	Writer out = response.getWriter();
			    	out.write(betuser + " 已经投注过本场比赛，请勿反复投注！");
				} else {
					if (balance >= Double.valueOf(betmoney)) {						
						//insert into myorder, make it happen
						java.sql.Date date=new java.sql.Date(new java.util.Date().getTime());
						sb.append("insert into myorder(name,money,rate_id,order_time) values('")
						.append(betuser).append("',")
						.append(betmoney).append(",")
						.append(rate_id).append(",'")
						.append(date.toString()).append("')");
						pstat =con.prepareStatement(sb.toString());
						pstat.executeUpdate();
						sb.delete(0, sb.length());
						sb.append("update myuser set money=")
						.append(String.valueOf(balance - Double.valueOf(betmoney))).append(" where name='")
						.append(betuser).append("'");
						pstat =con.prepareStatement(sb.toString());
						pstat.executeUpdate();
						con.commit();
						response.setContentType("text/html;charset=UTF-8");
			    		Writer out = response.getWriter();
			    		out.write(betuser + " 下注成功！");
					} else {
						response.setContentType("text/html;charset=UTF-8");
						Writer out = response.getWriter();
						out.write(betuser + " 余额不足，仅有" + balance + "点，下注失败！");
					}
				}
			} else {		
				// Not found user in database
		    	response.setContentType("text/html;charset=UTF-8");
		    	Writer out = response.getWriter();
		    	out.write(betuser + " 不是参赛者，请联系管理员！");
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
                   if (pstat != null) {
                	   pstat.close(); 
                   }
                   if (con != null) {
                       con.close();
                   }
            } catch (Exception e) {
                   e.printStackTrace();
            }                      
		}
	}	
	
	public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException
	{
		doGet(request,response);
	}
}