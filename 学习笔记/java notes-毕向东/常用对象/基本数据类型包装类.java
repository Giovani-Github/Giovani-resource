
/*
�����������Ͷ����װ�ࡣ

byte	Byte
short	short
int		Integer
long	Long
boolean Boolean
float	Float
double	Double
char	Character



�����������Ͷ����װ���������ã�
�������ڻ����������ͺ��ַ�������֮����ת��

������������ת���ַ�����

	������������+""

	������������.toString(������������ֵ);

	�磺 Integer.toString(34);//��34�������"34";


�ַ���ת�ɻ����������͡�

	xxx a = Xxx.parseXxx(String);

	int a = Integer.parseInt("123");

	double b = Double.parseDouble("12.23");

	boolean b = Boolean.parseBoolean("true");

	Integer i = new Integer("123");

	int num = i.intValue();

	


ʮ����ת���������ơ�
	toBinaryString();
	toHexString();
	toOctalString();


��������ת��ʮ���ơ�
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
JDK1.5�汾�Ժ���ֵ������ԡ�

*/

class IntegerDemo1 
{
	public static void main(String[] args) 
	{
		
//		Integer x = new Integer(4);

		Integer x = 4;//�Զ�װ�䡣//new Integer(4)

		x = x/* x.intValue() */ + 2;//x+2:x �����Զ����䡣��ɳ���int���͡���2���мӷ����㡣
					//�ٽ��ͽ���װ�丳��x��

		

		Integer m = 128;
		Integer n = 128;

		sop("m==n:"+(m==n));

		Integer a = 127;
		Integer b = 127;

		sop("a==b:"+(a==b));//���Ϊtrue����Ϊa��bָ����ͬһ��Integer����
						//��Ϊ����ֵ��byte��Χ���ݣ����������ԣ��������ֵ�Ѿ����ڣ��򲻻��ڿ����µĿռ䡣
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
