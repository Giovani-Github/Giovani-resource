package test.mytag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/*
 * 自定义带属性的标签处理类
 * */
public class MyTag5 extends SimpleTagSupport{
	private boolean test;//添加一个属性，这个属性必须有且有一个set方法

	/*
	 * 这个方法由tomcat调用，并且在doTag之前被调用
	 * */
	public void setTest(boolean test) {
		this.test = test;
	}
	
	@Override
	public void doTag() throws JspException, IOException {
		if(test) {//如果属性值为true
			this.getJspBody().invoke(null);//把标签体执行的内容输出到页面中
		}
	}
}
