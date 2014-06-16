package login;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import sun.misc.BASE64Encoder;

public class Md5Hash {

	private Md5Hash() {
	}

	public static String md5(String pwd) {
		if (pwd == null) {
			return null;
		}
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA"); //SHA or MD5
			BASE64Encoder  base = new BASE64Encoder ();
			String pwdAfter = base.encode(md.digest(pwd.getBytes()));
			return pwdAfter;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}		
	}
	
	public static void main(String[] args) {
		String passwd_encrypt1 = md5("hello123");
		System.out.println(passwd_encrypt1);
		String passwd_encrypt2 = md5("let's do it2");
		System.out.println(passwd_encrypt2.equals(passwd_encrypt1));
		String passwd_encrypt3 = md5("let's do it");
		System.out.println(passwd_encrypt3.equals(passwd_encrypt1));
	}
}
