package cn.itcast.demo3;

@MyAnno1(
	a=100,
	b="hello",
	c=MyEnum1.A,
	d=String.class,
	e=@MyAnno2(aa=200, bb="world"),
	e2=@MyAnno3(),
	f=100,
	s={"zs", "lisi"}
)
public class Demo3 {

}

@interface MyAnno1 {
	int a();
	String b();
	MyEnum1 c();
	Class d();
	MyAnno2 e();
	MyAnno3 e2();
	int[] f();
	String[] s();
}

@interface MyAnno2 {
	int aa();
	String bb();
}

enum MyEnum1 {
	A, B, C
}

@interface MyAnno3 {

}
