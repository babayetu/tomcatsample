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
		t.start(); // �����ʼ������߳�
	}

	public void destroy() {
		sender.stop(); // ֹͣ�ʼ������߳�
		try {
			t.join(1000);
			if (t.isAlive()) {
				System.out.println("�ʼ������߳�δֹͣ��");
			}
		} catch (Exception e) {
		}
	}
}