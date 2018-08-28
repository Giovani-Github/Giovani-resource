package junit.test.one;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class JunitTest {
	@Before
	public void Test1() {
		System.out.println("Before......");
	}
	@Test
	public void addTest() {
		JunitDemo jd = new JunitDemo();
		System.out.println(jd.addDemo(2, 3));
	}
	@Test
	public void sysDemo() {
		JunitDemo jd = new JunitDemo();
		jd.sysDemo();
	}
	@After
	public void Test2() {
		System.out.println("After....");
	}
}
