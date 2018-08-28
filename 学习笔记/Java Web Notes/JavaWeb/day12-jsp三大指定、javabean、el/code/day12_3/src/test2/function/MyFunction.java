package test2.function;

/*
 * 自定义函数库要使用到的类
 * 把这个类的方法，部署到WEB-INF/tlds/my.tld中
 * 就可以通过el表达式来使用这个方法了
 * */
public class MyFunction {
	public static String fun(String str) {
		return str;
	}
	
	public static boolean fun2() {
		return true;
	}
	
	public static String fun3(String str) {
		return "固定";
	}
}
