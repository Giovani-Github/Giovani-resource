package enumtest.demo;

public class EnumTest {
	public static void main(String[] args) {
		Test test = Test.A;
	}
}

enum Test {
	A("s") {
		public void test1() {}
		
	}, B("d") {
		public void test1() {}
		
	}, C("c") {
		public void test1() {}
	};
	
	private Test(String s){}
	public abstract void test1();
}
