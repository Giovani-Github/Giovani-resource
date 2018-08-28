package test.domain;

/*
 * 从数据库中查找出来的数据，存放到这个类中
 * */
public class User {
	private String username;
	private String parssword;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getParssword() {
		return parssword;
	}
	public void setParssword(String parssword) {
		this.parssword = parssword;
	}
	@Override
	public String toString() {
		return "User [username=" + username + ", parssword=" + parssword + "]";
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public User(String username, String parssword) {
		this.username = username;
		this.parssword = parssword;
	}
}
