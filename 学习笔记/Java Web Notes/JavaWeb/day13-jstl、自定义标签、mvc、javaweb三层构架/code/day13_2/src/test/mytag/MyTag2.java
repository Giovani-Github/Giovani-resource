package test.mytag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/*
 * 继承SimpleTagSupport类来完成我们的标签处理类
 * SimpleTagSupport:
 * 	它实现了SimpleTag接口，并且帮我们把所有Tomcat传递过来的数据都保存起来了，
 * 	且提供给了get方法来获取这些数据
 * */
public class MyTag2 extends SimpleTagSupport {
	@Override
	public void doTag() throws JspException, IOException {
		this.getJspContext().getOut().print("hello tag 2");
	}
}
