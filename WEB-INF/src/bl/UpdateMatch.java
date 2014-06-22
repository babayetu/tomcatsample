package bl;

import java.io.IOException;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateMatch extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -643101517948503479L;

	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException
	{
   		String match_id = request.getParameter("match_id");
    	String hostscore = request.getParameter("hostscore");
    	String guestscore = request.getParameter("guestscore");
    	
    	if (match_id==null || match_id.isEmpty() || hostscore==null || hostscore.isEmpty() 
    			|| guestscore==null || guestscore.isEmpty()) {
    		return;
    	}
    		
    		// TODO check cookie for username:role, to make sure user is correct person
    		
//    	System.out.println("match_id:"+match_id);
//    	System.out.println("hostscore:"+hostscore);
//    	System.out.println("guestscore:"+guestscore);
    	
    	int hostScoreInt = Integer.parseInt(hostscore);
    	int guestScoreInt = Integer.parseInt(guestscore);
    	String match_result = "host_win";
    	if (hostScoreInt > guestScoreInt) {
    		match_result = "host_win";
    	} else if (hostScoreInt < guestScoreInt) {
    		match_result = "guest_win";
    	} else {
    		match_result = "deuce";
    	}
    	
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
			
			sb.append("select o.order_id, r.match_result, o.name, o.money, r.rate, u.money from myorder o, myrate r, myuser u where o.rate_id=r.rate_id ")
			  .append("and r.match_id=")
			  .append(match_id)
			  .append(" and u.name=o.name and order_status='valid'");
			rs = st.executeQuery(sb.toString());
			sb.delete(0, sb.length());
			while (rs != null && rs.next()) {
				String order_id = String.valueOf(rs.getInt("o.order_id"));
				String order_match_result = rs.getString("r.match_result");
				String username = rs.getString("o.name");
				Double betMoney= rs.getDouble("o.money");
				Double userMoney= rs.getDouble("u.money");
				Double rate = rs.getDouble("r.rate");
				//System.out.println("order_id:"+order_id+" order_match_result:"+order_match_result + " username:" +username+" betmoney:"+betmoney + " rate:"+rate);
				if (order_match_result.equals(match_result)) {
					// add money to personal account
					sb.append("update myorder set order_status='bingo' where order_id=").append(order_id);
					pstat = con.prepareStatement(sb.toString());
					pstat.executeUpdate();
					sb.delete(0, sb.length());
					
					userMoney = userMoney + betMoney * rate;
					System.out.println("user money update to "+ userMoney);
					
					//add money to user account
					sb.append("update myuser set money=").append(String.valueOf(userMoney))
					  .append(" where name='").append(username).append("'");
					pstat = con.prepareStatement(sb.toString());
					pstat.executeUpdate();
					sb.delete(0, sb.length());
					
				} else {
					// not money add to personal account, just put order to finished
					sb.append("update myorder set order_status='missed' where order_id=").append(order_id);
					pstat = con.prepareStatement(sb.toString());
					pstat.executeUpdate();
					sb.delete(0, sb.length());
					System.out.println("missed ");
				}
			}
			
			sb.append("update mymatch set host_score=").append(hostscore)
			  .append(",guest_score=").append(guestscore) 
			  .append(",match_status='finished'")
			  .append(" where match_id=").append(match_id);
			pstat = con.prepareStatement(sb.toString());
			pstat.executeUpdate();
			sb.delete(0, sb.length());
			con.commit();
			
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
//                   if (pstat != null) {
//                	   pstat.close(); 
//                   }
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