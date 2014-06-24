package mail;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class MailSenderServlet extends HttpServlet {
	private Mailer sender;
	private Thread t;

	public void init() throws ServletException {
		sender = new Mailer("PHX-EXMCS-VIP.corp.ebay.com", "true", "corp\\jingjliu", "jingjliu@paypal.com", "Mayflower2k");

		t = new Thread(sender);
		t.start(); // 启动邮件发送线程
	}

	public void destroy() {
		sender.stop(); // 停止邮件发送线程
		try {
			t.join(1000);
			if (t.isAlive()) {
				System.out.println("邮件发送线程未停止。");
			}
		} catch (Exception e) {
		}
	}
}