package uuid;

import java.util.UUID;

import org.junit.Test;

public class MyUUID {
	@Test
	public void run() {
		//获取一个uuid：7839b35e-970f-45fa-b242-7f52d6ecbb4d
		UUID uuid = UUID.randomUUID();
		//把uuid转换成字符串
		String string = uuid.toString();
		//使用空字符串替换掉"-"
		string = string.replace("-", "");
		//转换成大写
		string = string.toUpperCase();
		System.out.println(string);
		
		//使用一句代码完成
		System.out.println(run2());
	}
	
	//也可以使用一句代码完成
	public static String run2() {
		return UUID.randomUUID().toString().replace("-", "").toUpperCase();
	}
}
