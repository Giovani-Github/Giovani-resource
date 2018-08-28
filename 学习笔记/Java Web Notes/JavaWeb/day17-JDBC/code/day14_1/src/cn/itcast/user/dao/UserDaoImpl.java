package cn.itcast.user.dao;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import cn.itcast.user.domain.User;

/***
 * UserDao的实现类
 * 对xml文件中的数据进行操作
 * @author Administrator
 *
 */
public class UserDaoImpl implements UserDao {
	private String path = "E:/users.xml";
	
	/***
	 * 根据用户名从数据库（xml）中查询用户
	 * 使用dom4j
	 * @param username
	 * @return
	 */
	public User findByUsername(String username) {
		/*
		 * 1.创建解析器，得到Document
		 * 2.通过Document得到用户名为username的Element元素,使用xpath
		 * 	3.如果返回的Element元素为null，表示没有找到指定的用户
		 * 	4.如果不为null，表示找到了指定用户名username的用户，并且把Element的数据封装到user，且返回
		 * */
		
		//1.创建解析器，得到Document
		SAXReader reader = new SAXReader();
		Document doc;
		try {
			doc = reader.read(path);
			
			//2.得到用户名为username的element
			Element ele = (Element) doc.selectSingleNode("//user[@username='"+username + "']");
			
			//3.判断ele是否为null，为空返回null
			if(ele == null) return null;
			
			//4.不为null，把element数据封装成user
			User user = new User();
			String attrUsername = ele.attributeValue("username");//获取该元素的username属性值
			String attrPassword = ele.attributeValue("password");//获取该元素的password属性值
			user.setUsername(attrUsername);//设置user的username
			user.setPassword(attrPassword);//设置user的password
			return user;
			
		} catch (DocumentException e) {
			throw new RuntimeException(e);
		}
	}
	
	/***
	 * 添加用户
	 * @param user
	 */
	public void add(User user) {
		/*
		 * 1.创建解析器，得到Document
		 * 2.通过Document得到root元素！<users>
		 * 3.通过root元素创建一个新的子元素<user>，来保存用户信息(把参数user的数据保存到这个子元素中)
		 * 4.回写document到xml文件中
		 * 
		 * * 子元素：<user username="xxx" password="yyy"/>
		 * */
		
		//1.创建解析器，得到Document
		SAXReader reader = new SAXReader();
		Document doc;
		try {
			doc = reader.read(path);
			
			//2.通过Document得到root元素！<users>
			Element users = doc.getRootElement();
			
			//3.通过root元素创建一个新的子元素user
			Element addUser = users.addElement("user");
			//为子元素user创建username属性，并且把参数user的username数据保存进去
			addUser.addAttribute("username", user.getUsername());
			//为子元素user创建password属性，并且把参数user的password数据保存进去
			addUser.addAttribute("password", user.getPassword());
			
			
			//4.回写document到xml文件中
			
			//创建对xml文件进行格式化的类
			/*
			 * 另一种写法：
			 * 	OutputFormat format = OutputFormat.createPrettyPrint();
			 * 表示：得到漂亮到格式
			 * */
			OutputFormat format = new OutputFormat("\t", true);//表示使用"\t"缩进，并且要换行
			format.setTrimText(true);//清空document原有的换行和缩进
			
			//创建回写xml的类
			try {
				XMLWriter writer = new XMLWriter(
						new OutputStreamWriter(//字符转换流，将要写入流中的字符编码成字节
								new FileOutputStream(path), "UTF-8"), format);
				
				//使用writer把document写入xml中
				writer.write(doc);
				writer.close();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		} catch (DocumentException e) {
			throw new RuntimeException(e);
		}
		
	}
}
