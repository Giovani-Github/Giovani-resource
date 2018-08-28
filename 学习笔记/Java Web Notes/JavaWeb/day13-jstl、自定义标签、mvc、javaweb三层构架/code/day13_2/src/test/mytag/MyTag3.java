package test.mytag;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class MyTag3 extends SimpleTagSupport {
	@Override
	public void doTag() throws JspException, IOException {
		Writer writer = this.getJspContext().getOut();//获取当前jsp页面的输出流
		writer.write("*****************<br/>");
		this.getJspBody().invoke(writer);//获取标签体jspFragment，把标签体执行的结果写到指定的流中
		writer.write("<br/>*****************");
	}
}
