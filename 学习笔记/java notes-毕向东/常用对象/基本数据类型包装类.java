
/*
基本数据类型对象包装类。

byte	Byte
short	short
int		Integer
long	Long
boolean Boolean
float	Float
double	Double
char	Character



基本数据类型对象包装类的最常见作用，
就是用于基本数据类型和字符串类型之间做转换

基本数据类型转成字符串。

	基本数据类型+""

	基本数据类型.toString(基本数据类型值);

	如： Integer.toString(34);//将34整数变成"34";


字符串转成基本数据类型。

	xxx a = Xxx.parseXxx(String);

	int a = Integer.parseInt("123");

	double b = Double.parseDouble("12.23");

	boolean b = Boolean.parseBoolean("true");

	Integer i = new Integer("123");

	int num = i.intValue();

	


十进制转成其他进制。
	toBinaryString();
	toHexString();
	toOctalString();


其他进制转成十进制。
	parseInt(string,radix);
*/

public class Test {
	public static void main(String[] args) {
		double d = 12;
		int i = Integer.parseInt("80", 16);
		System.out.println(i);
	}
}



/*
JDK1.5版本以后出现的新特性。

*/

class IntegerDemo1 
{
	public static void main(String[] args) 
	{
		
//		Integer x = new Integer(4);

		Integer x = 4;//自动装箱。//new Integer(4)

		x = x/* x.intValue() */ + 2;//x+2:x 进行自动拆箱。变成成了int类型。和2进行加法运算。
					//再将和进行装箱赋给x。

		

		Integer m = 128;
		Integer n = 128;

		sop("m==n:"+(m==n));

		Integer a = 127;
		Integer b = 127;

		sop("a==b:"+(a==b));//结果为true。因为a和b指向了同一个Integer对象。
						//因为当数值在byte范围内容，对于新特性，如果该数值已经存在，则不会在开辟新的空间。
	}

	public static void method()
	{
		Integer x = new Integer("123");

		Integer y = new Integer(123);

		sop("x==y:"+(x==y));
		sop("x.equals(y):"+x.equals(y));
	}

	public static void sop(String str)
	{
		System.out.println(str);
	}
	
}
