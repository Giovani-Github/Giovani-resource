package test.mytag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.SkipPageException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/*
 * 这个标签，我们在doTag方法中抛出SkipPageException异常，实现：这个标签后面的内容不执行这个案例
 * */
public class MyTag4 extends SimpleTagSupport{
	@Override
	public void doTag() throws JspException, IOException {
		this.getJspContext().getOut().print("后面的内容都不执行");
		throw new SkipPageException();//当tomcat捕捉到这个异常，就会跳过这个标签后面的内容，去处理这个异常
	}
}
