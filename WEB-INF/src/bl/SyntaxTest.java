package bl;

public class SyntaxTest {
	public static void main(String[] args) {
		StringBuffer sb = new StringBuffer();
		sb.append("select * from myuser where name='").append("testnow").append("'");
		System.out.println(sb.toString());
		sb.delete(0, sb.length());
		System.out.println(sb.toString());
		
		java.sql.Date date=new java.sql.Date(new java.util.Date().getTime()); 
		
		sb.append("insert into myorder(name,money,rate_id,order_time) values('")
		   .append("testname").append("',")
		   .append("100,")
		   .append("5,'")
		   .append(date.toString()).append("')")
		   ;
		System.out.println(sb.toString());  
	}
}