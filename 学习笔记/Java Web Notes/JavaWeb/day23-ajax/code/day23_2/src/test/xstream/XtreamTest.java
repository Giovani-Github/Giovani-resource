package test.xstream;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.thoughtworks.xstream.XStream;

public class XtreamTest {
	public List<Province> getProvince() {
		Province province1 = new Province();
		province1.setName("广东");
		province1.addCity(new City("汕尾", "ShanWei"));
		province1.addCity(new City("汕头", "ShanTou"));
		
		Province province2 = new Province();
		province2.setName("广西");
		province2.addCity(new City("桂林", "GuiLing"));
		province2.addCity(new City("梧州", "WuZhou"));
		
		List<Province> provinceList = new ArrayList<Province>();
		provinceList.add(province1);
		provinceList.add(province2);
		return provinceList;
	}
	
	/*
	 * 把javaBean的list集合转化为字符串形式的xml
	 * */
	@Test
	public void fun1() {
		//得到装载有javabean的集合
		List<Province> provinceList = getProvince();
		
		//创建XStream
		XStream xstream = new XStream();
		//把javabean转换为字符串形式的xml
		String xmlString = xstream.toXML(provinceList);
		System.out.println(xmlString);
	
		/*转换的结果
<list>
  <test.xstream.Province>
    <name>广东</name>
    <cities>
      <test.xstream.City>
        <name>汕尾</name>
        <description>ShanWei</description>
      </test.xstream.City>
      <test.xstream.City>
        <name>汕头</name>
        <description>ShanTou</description>
      </test.xstream.City>
    </cities>
  </test.xstream.Province>
  <test.xstream.Province>
    <name>广西</name>
    <cities>
      <test.xstream.City>
        <name>桂林</name>
        <description>GuiLing</description>
      </test.xstream.City>
      <test.xstream.City>
        <name>梧州</name>
        <description>WuZhou</description>
      </test.xstream.City>
    </cities>
  </test.xstream.Province>
</list>

		 * */
	}
	
	/*
	 * 对上面的结果进行更改, 把类型对应的元素名修改了
	 * <list> --> <china>
	 * <test.xstream.Province> --> <province>
	 * <test.xstream.City> --> <city>
	 * */
	@Test
	public void fun2() {
		//得到装载有javabean的集合
		List<Province> provinceList = getProvince();
		//创建XStream
		XStream xstream = new XStream();
			
		//把类型对应的元素名修改了
		xstream.alias("china", List.class);
		xstream.alias("province", Province.class);
		xstream.alias("city", City.class);
		
		
		//把javabean转换为字符串形式的xml
		String xmlString = xstream.toXML(provinceList);
		System.out.println(xmlString);
		
		
		/*
<china>
  <province>
    <name>广东</name>
    <cities>
      <city>
        <name>汕尾</name>
        <description>ShanWei</description>
      </city>
      <city>
        <name>汕头</name>
        <description>ShanTou</description>
      </city>
    </cities>
  </province>
  <province>
    <name>广西</name>
    <cities>
      <city>
        <name>桂林</name>
        <description>GuiLing</description>
      </city>
      <city>
        <name>梧州</name>
        <description>WuZhou</description>
      </city>
    </cities>
  </province>
</china>*/
	}
	
	/*
	 * 对上面的结果进行修改，使用为属性
	 * 默认：类的成员，生成的是类对应的元素的子元素。
	 * 我们把它改为：生成的是类对应的元素的属性
	 * <name>广东</name> --> <province name="广东">
	 * <name>广西</name> --> <province name="广西">
	 * */
	@Test
	public void fun3() {
		List<Province> provinceList = getProvince();//得到装载有javabean的集合
		XStream xstream = new XStream();//创建XStream
		xstream.alias("china", List.class);//把类型对应的元素名修改了
		xstream.alias("province", Province.class);
		xstream.alias("city", City.class);
		
		
		//使用为属性
		/*
		 * 把Province类的name成员属性
		 * 生成为province类所对应的元素的属性
		 * */
		xstream.useAttributeFor(Province.class, "name");
		
			
		String xmlString = xstream.toXML(provinceList);//把javabean转换为字符串形式的xml
		System.out.println(xmlString);
	
	/*
<china>
  <province name="广东">
    <cities>
      <city>
        <name>汕尾</name>
        <description>ShanWei</description>
      </city>
      <city>
        <name>汕头</name>
        <description>ShanTou</description>
      </city>
    </cities>
  </province>
  <province name="广西">
    <cities>
      <city>
        <name>桂林</name>
        <description>GuiLing</description>
      </city>
      <city>
        <name>梧州</name>
        <description>WuZhou</description>
      </city>
    </cities>
  </province>
</china>
*/
	}
	
	/*
	 * 对上面的结果进行修改
	 * <cities>元素不生成
	 * 它是一个集合类型，我们希望它的内容生成对应的元素
	 * 但是不希望它本身也生成一个元素
	 * */
	@Test
	public void fun4() {
		List<Province> provinceList = getProvince();//得到装载有javabean的集合
		XStream xstream = new XStream();//创建XStream
		xstream.alias("china", List.class);//把类型对应的元素名修改了
		xstream.alias("province", Province.class);
		xstream.alias("city", City.class);
		xstream.useAttributeFor(Province.class, "name");//使用为属性
		
		/*
		 * 让Province类名为cities的成员不生成对应的元素
		 * 但是cities是一个集合类型，它的内容还会生成对应的元素
		 * */
		xstream.addImplicitCollection(Province.class, "cities");
		
		
		String xmlString = xstream.toXML(provinceList);//把javabean转换为字符串形式的xml
		System.out.println(xmlString);
		
		/*
<china>
  <province name="广东">
    <city>
      <name>汕尾</name>
      <description>ShanWei</description>
    </city>
    <city>
      <name>汕头</name>
      <description>ShanTou</description>
    </city>
  </province>
  <province name="广西">
    <city>
      <name>桂林</name>
      <description>GuiLing</description>
    </city>
    <city>
      <name>梧州</name>
      <description>WuZhou</description>
    </city>
  </province>
</china>
		 * */
	}
	
	/*
	 * 让指定的类成员，不生成元素
	 * <description>ShanWei</description>， 不生成
	 * */
	@Test
	public void fun5() {
		List<Province> provinceList = getProvince();//得到装载有javabean的集合
		XStream xstream = new XStream();//创建XStream
		xstream.alias("china", List.class);//把类型对应的元素名修改了
		xstream.alias("province", Province.class);
		xstream.alias("city", City.class);
		xstream.useAttributeFor(Province.class, "name");//使用为属性
		xstream.addImplicitCollection(Province.class, "cities");//让Province类名为cities的成员不生成对应的元素
		
		
		//让city类的description成员，不生成元素
		xstream.omitField(City.class, "description");
		
		
		String xmlString = xstream.toXML(provinceList);//把javabean转换为字符串形式的xml
		System.out.println(xmlString);
		
	/*
<china>
  <province name="广东">
    <city>
      <name>汕尾</name>
    </city>
    <city>
      <name>汕头</name>
    </city>
  </province>
  <province name="广西">
    <city>
      <name>桂林</name>
    </city>
    <city>
      <name>梧州</name>
    </city>
  </province>
</china>
	 * */
	}
	
	/*
	 * 以上是javabean集合生成的字符类型的xml
	 * 下面是javabean生成的字符类型的xml
	 * */
	@Test
	public void fun6() {
		City city = new City("汕尾", "ShanWei");
		XStream xstream = new XStream();
		
		
		//把city类型对应的元素名改为city
		xstream.alias("city", City.class);
		
		//description成员对应的元素不生成
		xstream.omitField(City.class, "description");
		
		
		String xmlString = xstream.toXML(city);
		System.out.println(xmlString);
		
		
	}
}
