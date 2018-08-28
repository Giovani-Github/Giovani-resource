import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;


public class ListTest {
	@Test
	public void listTest() {
		List<String> list = new ArrayList<String>();
		list.add("aa");
		list.add("bb");
		list.add("cc");
		
		//使用for循环取出list集合里面的元素
		for(int i=0; i<list.size(); i++) {
			String list1 = list.get(i);
			System.out.println(list1);
		}
		
		System.out.println("=======================");
		
		//使用增强for取出listi集合里面的元素
		for (String list2 : list) {
			System.out.println(list2);
		}
		
		System.out.println("=======================");
		
		//使用迭代器出去list集合中的元素
		Iterator<String> it = list.iterator();
		while(it.hasNext()) {
			String list3 = it.next();
			System.out.println(list3);
		}
	}
}
