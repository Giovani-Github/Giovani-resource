import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.Test;


public class MapTest {
	@Test
	public void mapTest() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("aa", "11");
		map.put("bb", "22");
		map.put("cc", "33");
		
		//取出map中的key集合,再使用增强for获取每一个key，再根据key获得value
		Set<String> mapKeys = map.keySet();
		for (String key : mapKeys) {
			String value = map.get(key);
			System.out.println(key + ":" + value);
		}
		
		System.out.println("=======================");
		
		//使用map类里面的内部接口，entry获取map中的元素，entry接口描述的是map的key-value关系，可以根据这个关系接口获取键值对元素
		Set<Entry<String, String>> entrySet = map.entrySet();//获取map的entry关系类的set集合
		
		for (Entry<String, String> entry : entrySet) {//遍历这个关系集合，获取每一对键值对的关系类，通过关系类，获取这对键值对的key和value
			String key = entry.getKey();
			String value = entry.getValue();
			System.out.println(key + ":" + value);
		}
	}
}
