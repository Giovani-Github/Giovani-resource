package test.mytag;

import java.io.IOException;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.JspTag;
import javax.servlet.jsp.tagext.SimpleTag;

/*
 * 自定义标签类，要求实现SimpleTag接口
 * 在这个标签类中，我们不打算实现父标签和标签体 
 * */
public class MyTag1 implements SimpleTag {
	
	/*
	 * 把tomcat传过来的数据保存起来，也就是三个set方法的参数
	 * */
	private PageContext pageContext;
	private JspFragment jspFragment;
	private JspTag jspTag;

	/*
	 * 每次执行标签时都会调用这个方法
	 * 而且会在其他三个set方法被调用之后才会被tomcat调用
	 * */
	public void doTag() throws JspException, IOException {
		//每次执行这个标签时，都向页面输出一次文字
		pageContext.getOut().print("hello tag");
	}

	/*
	 * 获取父标签 ，我们自己调用
	 * */
	@Override
	public JspTag getParent() {
		return null;
	}

	/*
	 * 设置标签体，tomcat自己调用
	 * 在这里我们不打算设置标签体
	 * */
	@Override
	public void setJspBody(JspFragment fragment) {
		this.jspFragment = fragment;
	}

	/*
	 * tomcat自己调用
	 * 设置jsp上下文对象，它的子类是PageContext
	 * */
	@Override
	public void setJspContext(JspContext jspContext) {
		this.pageContext = (PageContext)jspContext;
	}
	
	/*
	 * tomcat自己调用
	 * 设置父标签，在这里我们不打算设置父标签
	 * */
	public void setParent(JspTag jspTag) {
		this.jspTag = jspTag;
	}
	
}
