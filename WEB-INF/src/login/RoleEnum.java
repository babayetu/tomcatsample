package login;

public enum RoleEnum {
    ADMIN("admin", 15), GAMBLER("gambler", 7), WATCHER("watcher", 3);

    private String role;
    private int action;
    private String md5String;

    private RoleEnum(String role, int action) {
		this.role = role;
		this.action = action;
		this.md5String = Md5Hash.md5(role);
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getAction() {
		return action;
	}

	public void setAction(int action) {
		this.action = action;
	}

	public String getMd5String() {
		return md5String;
	}

	public void setMd5String(String md5String) {
		this.md5String = md5String;
	}
}