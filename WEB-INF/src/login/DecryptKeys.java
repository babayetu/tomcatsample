package login;

import javax.servlet.http.HttpServletRequest;

public class DecryptKeys {
	private static final String PASSWORD = "testpassword0101";
	private String mRole = null;
	private String mMoney = null;
	private String mName = null;
	
	public void phraseCookie(HttpServletRequest request) {
		String cookieValue = CookieUtil.getCookieValue(request, "keys");
		if (cookieValue != null) {
			System.out.println("cookieValue:" + cookieValue);
			String decrypt_cookie = DESHelper.DoDES(cookieValue, PASSWORD, 1);
			System.out.println("decrypt_cookie:" + decrypt_cookie);
			
			if (decrypt_cookie == null) {
				return;
			}
			
			if (decrypt_cookie.contains(":")) {
				// Split it.
				String[] parts = decrypt_cookie.split(":");
				this.setmName(parts[0]);
				this.setmRole(parts[1]);
				this.setmMoney(parts[2]);
			} else {
				throw new IllegalArgumentException("Cookie String " + decrypt_cookie
						+ " does not contain :");
			}
		} 
	}

	public String getmRole() {
		return mRole;
	}

	public void setmRole(String mRole) {
		this.mRole = mRole;
	}

	public String getmMoney() {
		return mMoney;
	}

	public void setmMoney(String mMoney) {
		this.mMoney = mMoney;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}
	
	
}