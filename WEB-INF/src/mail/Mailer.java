package mail;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

//使用Exchange Server邮件服务器发送邮件的特殊之处在于用户名username必须添加所在域的前缀，比如要使用域domain中的用户demo@sample.com来发送邮件，
//那么登录名要改成domain\demo，而不是普通stmp服务器中的demo@sample.com。

public class Mailer implements Runnable {
	private String host;
	private String auth;
	private String username;
	private String domainUser;
	private String password;

	private boolean flag = true; // 停止线程标记
	private static final long SLEEP_TIME = 5 * 60 * 60 * 1000L; // 5 hours
	// private static final long SLEEP_TIME = 30 * 1000L;

	public boolean send(String[] to, String[] cc, String[] bcc, String subject, String content) throws MessagingException {
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.auth", auth);
		Session s = Session.getInstance(props);
		// s.setDebug(true);

		MimeMessage message = new MimeMessage(s);

		InternetAddress from = new InternetAddress(username);
		message.setFrom(from);
		InternetAddress[] Toaddress = new InternetAddress[to.length];
		for (int i = 0; i < to.length; i++)
			Toaddress[i] = new InternetAddress(to[i]);
		message.setRecipients(Message.RecipientType.TO, Toaddress);

		if (cc != null) {
			InternetAddress[] Ccaddress = new InternetAddress[cc.length];
			for (int i = 0; i < cc.length; i++)
				Ccaddress[i] = new InternetAddress(cc[i]);
			message.setRecipients(Message.RecipientType.CC, Ccaddress);
		}

		if (bcc != null) {
			InternetAddress[] Bccaddress = new InternetAddress[bcc.length];
			for (int i = 0; i < bcc.length; i++)
				Bccaddress[i] = new InternetAddress(bcc[i]);
			message.setRecipients(Message.RecipientType.BCC, Bccaddress);
		}
		message.setSubject(subject);
		message.setSentDate(new Date());

		BodyPart mdp = new MimeBodyPart();
		mdp.setContent(content, "text/html;charset=utf-8");
		Multipart mm = new MimeMultipart();
		mm.addBodyPart(mdp);
		message.setContent(mm);

		message.saveChanges();
		Transport transport = s.getTransport("smtp");
		transport.connect(host, (null == domainUser) ? username : domainUser, password);
		transport.sendMessage(message, message.getAllRecipients());
		transport.close();
		return true;
	}

	public Mailer(String host, String auth, String domainUser, String username, String password) {
		super();
		this.host = host;
		this.auth = auth;
		this.domainUser = domainUser;
		this.username = username;
		this.password = password;
	}

	public void run() {
		long lastTime = new Date().getTime(); // 保存前一次发送邮件的时间
		while (flag) { // 服务器停止时退出线程
			long k = new Date().getTime() - lastTime;
			if (k < -1000) { // 防止系统修改时间
				lastTime = new Date().getTime();
				continue;
			}

			checknameListAndSendMail();
			lastTime = new Date().getTime();

			try {
				Thread.sleep(SLEEP_TIME);
			} catch (Exception e) {
			}
		}
	}

	public void stop() {
		flag = false;
	}

	private void checknameListAndSendMail() {
		boolean result = false;
		HashMap<String, Double> foulUser = new HashMap<String, Double>();
		try {
			Connection con = null;
			Statement st = null;
			ResultSet rs = null;

			con = DriverManager.getConnection("proxool.mysql");
			st = con.createStatement();
			StringBuffer sb = new StringBuffer();
			// get user bet but total money less than 100
			sb.append("select o.name, sum(o.money) as allmoney from myorder o where o.order_status='valid' group by o.name");
			rs = st.executeQuery(sb.toString());
			sb.delete(0, sb.length());
			while (rs != null && rs.next()) {
				Double usermoney = rs.getDouble("allmoney");
				if (usermoney < 100.00) {
					foulUser.put(rs.getString("o.name"), usermoney);
				}
			}
			
			// get user who is bigger than 0.0 and not bet
			sb.append("select u.name, u.money from myuser u where u.money>0.0 and u.name not in (select o.name from myorder o where o.order_status='valid')");
			rs = st.executeQuery(sb.toString());
			sb.delete(0, sb.length());
			while (rs != null && rs.next()) {
				foulUser.put(rs.getString("u.name"), rs.getDouble("u.money"));
			}
			
			Set<Entry<String, Double>> set = foulUser.entrySet();
			Iterator<Entry<String, Double>> itor = set.iterator();
			while (itor!=null && itor.hasNext()) {
				Entry<String, Double> entry = itor.next();
				sb.append("<h3>亲爱的").append(entry.getKey())
				  .append("，今天您的总投注额未达到100点</h3>");
				
				System.out.println("Notification Mails are Sent to " + entry.getKey());
				result = send(new String[] { entry.getKey()+"@ebay.com" }, null, new String[] {"jingjliu@ebay.com"}, "[BetPal]温馨提醒", sb.toString());
				sb.delete(0, sb.length());
			}		

		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}