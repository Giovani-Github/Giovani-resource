package test.jsonlib;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.junit.Test;

public class JSONObjectTest {
	@Test
	public void fun1() {
		JSONObject map = new JSONObject();
		
		//往map中添加内容
		map.put("name", "ZhangSan");
		map.put("age", 12);
		map.put("sex", "男");
		
		//内容转换成json串
		//{"name":"ZhangSan","age":12,"sex":"男"}
		System.out.println(map.toString());
	}
	
	@Test
	public void fun2() {
		/*
		 * 如果有一个现成的map
		 * 可以使用JSONObject.putAll(map);把这个map所有键值对拿过来（这个方法是map的方法，因为JSONObject本身就是一个map）
		 * 然后toString()转换成json串
		 * */
		
		JSONObject map = new JSONObject();
		
		Map map1 = new HashMap();
		map1.put("name", "ZhangSan");
		map1.put("age", 12);
		map1.put("sex", "男");
		
		map.putAll(map1);
		
		
		//内容转换成json串
		//{"name":"ZhangSan","age":12,"sex":"男"}
		System.out.println(map.toString());
	}
	
	@Test
	public void fun3() {
		Person person = new Person("ZhanSan", 14, "男");	
		
		//把javabean转换为JSONObject
		JSONObject map = JSONObject.fromObject(person);
		
		//内容转换成json串
		//{"name":"ZhangSan","age":12,"sex":"男"}
		System.out.println(map.toString());
	}
}
