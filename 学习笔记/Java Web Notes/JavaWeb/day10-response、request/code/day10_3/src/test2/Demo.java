package test2;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.junit.Test;

public class Demo {
	@Test
	public void test() throws Exception {
		//url编码
		String name = "张三";
		String name1 = URLEncoder.encode(name, "utf-8");
		System.out.println(name1);
		
		//url解码
		String name2 = URLDecoder.decode(name1, "utf-8");
		System.out.println(name2);
		
	}
}
