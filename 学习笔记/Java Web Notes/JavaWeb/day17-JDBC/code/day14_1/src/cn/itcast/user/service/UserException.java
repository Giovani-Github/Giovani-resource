package cn.itcast.user.service;

/***
 * 自定义异常类
 * 	实现父类的四个方法即可，这些方法内部怎么实现的不用管
 * 	
 * @author Administrator
 *
 */
public class UserException extends Exception {

	public UserException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public UserException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public UserException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
}
