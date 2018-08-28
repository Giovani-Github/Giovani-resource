/*
StringBuffer���ַ�����������

��һ��������
�ص㣺
1�������ǿɱ仯�ġ�
2�������ֽڲ�������������͡�
3�����ջ�ͨ��toString��������ַ�����



C create U update R read D delete

1���洢��
	StringBuffer append():��ָ��������Ϊ������ӵ��������ݽ�β����
	StringBuffer insert(index,����):���Խ����ݲ��뵽ָ��indexλ�á�


2��ɾ����
	StringBuffer delete(start,end):ɾ���������е����ݣ�����start��������end��
	StringBuffer deleteCharAt(index):ɾ��ָ��λ�õ��ַ���
	
3����ȡ��
	char charAt(int index) 
	int indexOf(String str) 
	int lastIndexOf(String str) 
	int length() 
	String substring(int start, int end) 
 
4���޸ġ�
	StringBuffer replace(start,end,string);
	void setCharAt(int index, char ch) ;


5����ת��
	StringBuffer reverse();
 
6��
	����������ָ�����ݴ洢��ָ���ַ������С�
	void getChars(int srcBegin, int srcEnd, char[] dst, int dstBegin) 

	
JDK1.5 �汾֮�������StringBuilder.

StringBuffer���߳�ͬ����
StringBuilder���̲߳�ͬ����

�Ժ󿪷�������ʹ��StringBuilder

�����������أ�
1�����Ч�ʡ�
2������д��
3����߰�ȫ�ԡ�

*/

public class StringBufferDemo {
	public static void main(String[] args) {
		add();
		del();
		re();
		getCharArrays();
	}

	public static void add() {
		StringBuffer sb = new StringBuffer();

		sb.append("dfdf");//��ӣ��᷵��һ��StringBuffer
		sb.insert(1, "A");//ָ��λ�ò���

		System.out.println(sb.toString());//��StringBuffer��toStringת�����ַ������
	}

	public static void del() {
		StringBuffer sb = new StringBuffer("abcde");

		//sb.delete(1,4);
		sb.deleteCharAt(2);

		System.out.println(sb.toString());
	}

	public static void re() {
		StringBuffer sb = new StringBuffer("abcde");

//		sb.replace(1, 3, "AVI");
		sb.setCharAt(1,'A');
		System.out.println(sb.toString());
	}

	public static void getCharArrays() {
		StringBuffer sb = new StringBuffer("Holle JAVA Holle APIAN");
		char[] chs = new char[sb.length()];
				
		sb.getChars(0, sb.length(), chs, 0);

		for(int i = 0; i < chs.length; i++) {
			System.out.print(chs[i]);
		}
	}
}