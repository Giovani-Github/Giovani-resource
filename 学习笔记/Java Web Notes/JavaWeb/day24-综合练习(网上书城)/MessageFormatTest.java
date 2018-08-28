import java.text.MessageFormat;
public class MessageFormatTest {
	public static void main(String[] args) {
		/*
			包含了占位符的字符串就是模板
			占位符:{0}、{1}、{2}...
			可变参数，需要指定模版中占位符的值！有几个占位符就提供几个参数
		*/
		String s = MessageFormat.format("{0}或{1}错误！", "用户名", "密码");
		System.out.println(s);
	}
}
