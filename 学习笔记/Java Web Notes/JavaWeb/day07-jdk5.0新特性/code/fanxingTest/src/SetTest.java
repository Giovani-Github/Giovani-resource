import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.junit.Test;


public class SetTest {
	@Test
	public void setTest() {
		Set<String> set = new HashSet<String>();
		set.add("aa");
		set.add("bb");
		set.add("cc");
		
		//使用增强for取出set中的元素
		for (String s1 : set) {
			System.out.println(s1);
		}
		
		System.out.println("=======================");
		
		//使用迭代器取出set的元素
		Iterator<String> it = set.iterator();
		while(it.hasNext()) {
			String s2 = it.next();
			System.out.println(s2);
		}
	}
}
