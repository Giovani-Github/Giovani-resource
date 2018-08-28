import java.math.BigDecimal;
import java.math.BigInteger;


public class BigDecimalAndIntegerTest {
	
	public static void main(String[] args) {
		test1();
		test2();
	}
	/*
	 * 1000的阶乘
	 * long类型存不了那么大的数，会溢出
	 * 所以使用BigInteger来存
	 * */
	public static void test1() {
		BigInteger sum = BigInteger.valueOf(1);//也可以：new BigInteger(1);
		for(int i = 1; i <=1000; i++) {
			BigInteger bi = BigInteger.valueOf(i);
			sum = sum.multiply(bi);//sum = sum * i
		}
		System.out.println(sum);
	}
	
	/*
	 * BigDecimal
	 * 可以用来处理二进制运算导致的误差，如：2.0 - 1.1，会等于：0.8999999999999。相当于十进制中的1/3，二进制中就是1/10
	 * */
	public static void test2() {
		//创建bigDecimal时，必须使用String构造器
		BigDecimal d1 = new BigDecimal("2.0");
		BigDecimal d2 = new BigDecimal("1.1");
		BigDecimal d3 = d1.subtract(d2);//d1 - d2
		
		System.out.println(d3);
	}
}
