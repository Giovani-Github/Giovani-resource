/*
需求：
1、验证用户名密码是否正确
2、修改用户名与密码
3、用户名与密码不得包含特殊字符以及密码不能小于四位字符
*/

/**
用户名和密码的小程序，验证以及修改密码与用户名的功能
@author 李庆旺
@version v1.0
*/
public class UserService {

	//用户名和密码不能有的字符
	private static char[] c = {':', '!', '#', '$', '%', '^', '&', '*', '(', ')', '~',
								'`', '<', '>', '?', '\\', '|', ':', '\'', ';', '-', 
								'=', '+', '/'};
	
	//初始的用户名及密码
	private static String name = null;
	private static String password = null;

	/**
	验证输入的用户名或密码是否包含特殊字符的方法
	@param arr 输入的用户名或密码
	@return 返回boolean类型
	*/
	private static boolean chazhao(char[] arr) {
		for(int i = 0; i < arr.length; i++)
			for(int j = 0; j < c.length; j++)
				if(arr[i] == c[j])
					return true;
				
		return false;
	}
	
	/**
	初始化用户名和密码
	@param name 初始用户名
	@param password 初始密码
	*/
	public UserService(String name, String password) {
		this.name = name;
		this.password = password;
	}

	/**
	验证用户名及密码是否正确的方法
	@param n 用户名
	@param p 密码
	@return 返回boolean类型
	*/
	public static boolean validate(String n, String p) {
		if(n.equals(name) && p.equals(password))
			return true;
		else
			return false;
	
	}
	
	/**
	修改用户名的方法，在初始用户名及密码正确的情况下
	@param n 修改的用户名
	@param b 原本用户名与密码的否正确
	@return 返回boolean类型
	*/
	public static boolean setName(boolean b, String n) {
		if(b == true) {
			//用户输入的密码
			String temp = n;

			//把要修改的密码转换乘字符数组，用于查找里面是否包含特殊字符
			char[] c1 = temp.toCharArray();
			
			//验证用户名里面是否包含特殊字符
			if(chazhao(c1) == false) {
				name = temp;
				return true;
			} else {
				System.out.println("包含特殊字符!!");
				return false;
			}
		}

		return false;
	}

	/**
	修改密码，在初始用户名及密码正确的情况下
	@param p 修改的密码
	@param b 愿用户名与密码是否正确
	@return 返回boolean类型
	*/
	public static boolean setPassword(boolean b, String p) {
		if(b == true) {
			String temp = p;

			//把要修改的密码转换乘字符数组，用于查找里面是否包含特殊字符
			char[] c1 = temp.toCharArray();
			
			//密码长度不能小于四
			if(temp.length() > 4) {
				//验证密码是否包含特殊字符
				if(chazhao(c1) == false) {
					password = temp;
					return true;
				} else {
					System.out.println("包含特殊字符!!");
					return false;
				}

			} else {
				System.out.println("密码不能小于四位!！");
				return false;
			}
		}

		return false;
	}
}