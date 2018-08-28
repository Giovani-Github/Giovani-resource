package cn.itcast.user.domain;

/***
 * 实体类，用来存放数据文件中读取到的数据
 * @author Administrator
 *
 */
public class User {
	private String username;
	private String password;
	private String verifyCode;
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getVerifyCode() {
		return verifyCode;
	}
	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password
				+ ", verifyCode=" + verifyCode + "]";
	}

}
