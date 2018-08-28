package test.jsonlib;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

import org.junit.Test;

public class JSONArrayTest {
	@Test
	public void W() {
		Person person = new Person("ZhanSan", 14, "男");	
		
		//javabean转换为JSONArray
		JSONArray list = JSONArray.fromObject(person);
		
		//内容转换成json串
		//{"name":"ZhangSan","age":12,"sex":"男"}
		System.out.println(list.toString());
	}
	
	@Test
	public void fun2() {
		Person person1 = new Person("ZhanSan", 14, "男");	
		Person person2 = new Person("LiSi", 16, "女");
		
		JSONArray list = new JSONArray();
		//JSONArray本身就是一个list，往list中添加javabean
		list.add(person1);
		list.add(person2);
		
		//内容转换成json串
		//[{"age":14,"name":"ZhanSan","sex":"男"},{"age":16,"name":"LiSi","sex":"女"}]
		System.out.println(list.toString());
	}
	
	@Test
	public void fun3() {		
		Person person1 = new Person("ZhanSan", 14, "男");	
		Person person2 = new Person("LiSi", 16, "女");
		
		List l = new ArrayList();
		l.add(person1);
		l.add(person2);
		
		//把list转换为JSONArray对象
		JSONArray list = JSONArray.fromObject(l);
		
		//内容转换成json串
		//[{"age":14,"name":"ZhanSan","sex":"男"},{"age":16,"name":"LiSi","sex":"女"}]
		System.out.println(list.toString());
	}
}
