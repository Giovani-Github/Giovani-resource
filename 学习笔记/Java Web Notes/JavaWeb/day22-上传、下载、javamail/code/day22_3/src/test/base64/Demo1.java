package test.base64;

import java.io.UnsupportedEncodingException;
import org.junit.Test;
import sun.misc.BASE64Encoder;
import sun.misc.BASE64Decoder;

public class Demo1 {
	@Test
	public void fun1() throws Exception {
		//使用base64对字符串进行加密
		String s = "Username";
		BASE64Encoder encoder = new BASE64Encoder();
		byte[] bytes = s.getBytes("utf-8");//先使用utf-8编码成字节
		s = encoder.encode(bytes);//再把字节进行base64加密
		System.out.println(s);
		
		//使用base64对字符串进行解密
		BASE64Decoder decoder = new BASE64Decoder();
		byte[] b = decoder.decodeBuffer(s);//进行解密，得到原来的字节
		s = new String(b, "utf-8");//再把字节解码成字符
		System.out.println(s);
		
	}
}
