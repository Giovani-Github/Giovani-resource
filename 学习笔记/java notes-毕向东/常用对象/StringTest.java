/*

String�������������ַ������
��ô�����ṩ�˶���������ַ������в�����

�����Ĳ�������Щ��
"abcd"

1����ȡ��
	1.1 �ַ����еİ������ַ�����Ҳ�����ַ����ĳ��ȡ�
		int length():��ȡ���ȡ�
	1.2 ����λ�û�ȡλ����ĳ���ַ���
		char charAt(int index):
	1.3 �����ַ���ȡ���ַ����ַ�����λ�á�
		int indexOf(int ch):���ص���ch���ַ����е�һ�γ��ֵ�λ�á�
		int indexOf(int ch, int fromIndex) :��fromIndexָ��λ�ÿ�ʼ����ȡch���ַ����г��ֵ�λ�á�

		int indexOf(String str):���ص���str���ַ����е�һ�γ��ֵ�λ�á�
		int indexOf(String str, int fromIndex) :��fromIndexָ��λ�ÿ�ʼ����ȡstr���ַ����г��ֵ�λ�á�

		int lastIndexOf(int ch) ��

		
2���жϡ�
	2.1 �ַ������Ƿ����ĳһ���Ӵ���
		boolean contains(CharSequence str):
		����֮����indexOf(String str):��������str��һ�γ���λ�ã��������-1.��ʾ��str�����ַ����д��ڡ�
				���ԣ�Ҳ�������ڶ�ָ���ж��Ƿ������
				if(str.indexOf("aa")!=-1)

				���Ҹ÷����������жϣ��п��Ի�ȡ���ֵ�λ�á�

	2.2 �ַ����Ƿ������ݡ�
		boolean isEmpty(): ԭ������жϳ����Ƿ�Ϊ0. 
	2.3 �ַ����Ƿ�����ָ�����ݿ�ͷ��
		boolean startsWith(str);
	2.4 �ַ����Ƿ�����ָ�����ݽ�β��
		boolean endsWith(str);
	2.5 �ж��ַ��������Ƿ���ͬ����д��Object���е�equals������
		boolean equals(str);
	2.6 �ж������Ƿ���ͬ�������Դ�Сд��
		boolean equalsIgnoreCase();
	
3��ת����
	3.1 ���ַ�����ת���ַ�����
		���캯����String(char[])
				  String(char[],offset,count):���ַ������е�һ����ת���ַ�����

		��̬������
				static String copyValueOf(char[]);
				static String copyValueOf(char[] data, int offset, int count) 

				static String valueOf(char[]):

		
	3.2 ���ַ���ת���ַ����顣**
		char[] toCharArray():

	3.3 ���ֽ�����ת���ַ�����
			String(byte[])
			String(byte[],offset,count):���ֽ������е�һ����ת���ַ�����

	3.4 ���ַ���ת���ֽ����顣
			byte[]  getBytes():
	3.5 ��������������ת���ַ�����
		static String valueOf(int)
		static String valueOf(double)

		//3+"";//String.valueOf(3);

		���⣺�ַ������ֽ�������ת�������У��ǿ���ָ�������ġ�

4���滻
	String replace(oldchar,newchar);

5���и�
	String[] split(regex);

6���Ӵ�����ȡ�ַ����е�һ���֡�
	String substring(begin);
	String substring(begin,end);

7��ת����ȥ���ո񣬱Ƚϡ�
	7.1 ���ַ���ת�ɴ�д����Сд��
		 String toUpperCase();
		 String toLowerCase();

	7.2 ���ַ������˵Ķ���ո�ȥ����
		String trim();

	7.3 �������ַ���������Ȼ˳��ıȽϡ�
		int compareTo(string);


*/



public class Test {
	public static void main(String[] args) {
		String s1 = "l";
		String s2 = "abcdefg";
		print(getMaxSubstring(s1, s2));
		/*print(str);
		String s = myTimep(str);
		String s1 = reverseString(s, 0, 3);
		print(s1);
		*/
		
		//System.out.println(stringNumber(str, "a"));
	}
	
	//��ӡ
	public static void print(String str) {
		System.out.println("(" + str + ")");
	}

	//ȥ�����˿ո�
	public static String myTimep(String str) {
		int kai = 0, jie = str.length()-1;

		while(kai <= jie && str.charAt(kai) == ' ') {
			kai++;
		}

		while(kai <= jie && str.charAt(jie) == ' ') {
			jie--;
		}

		return str.substring(kai, jie+1);
	}
	
	//��ָ�����ַ�����λ�÷�ת
	public static String reverseString(String str, int first, int tail) {
		char[] ch = str.toCharArray();//תΪ�ַ�����

		//��ת
		reverse(ch, first, tail);

		return new String(ch);//��ת�������ת��Ϊ�ַ���
	
	}

	public static String reverseString(String str) {
		return reverseString(str, 0, str.length());
	}

	private static void reverse(char[] ch, int i, int z) {
		for(int start = i, end = z-1; start <= end; start++, end--) {
			zhuan(ch, start, end);
		}
	}

	private static void zhuan(char[] ch, int i, int z) {
		char temp = ch[i];
		ch[i] = ch[z];
		ch[z] = temp;
	}

	//�ַ������ֵĴ������Ƚϵ�Ч�������ڴ��в��������ַ�������st�����ַ�����
	public static int stringNumber_2(String st, String key) {
		int number = 0;

		while(st.indexOf(key) != -1) {
			st = st.substring(st.indexOf(key)+key.length());
			number++;
		}

		return number;
	}

	//�ַ������ִ�����(�Ƚϸ�Ч���ڴ��в�����������ַ���)
	public static int stringNumber(String st, String key) {
		int number = 0;
		int index = 0;

		while((index = st.indexOf(key, index)) != -1) {
			index = index + key.length();
			number++;
		}

		return number;
	}

	//Ѱ�������ͬ�Ӵ�
	public static String getMaxSubstring(String s1, String s2) {
		String max, min;
		max = (s1.length() > s2.length()) ? s1 : s2;
		min = (max == s1) ? s2 : s1;
		
		for(int i = 0; i < min.length(); i++) {
			for(int y = 0, z = min.length()-i; z!= min.length()+1; y++, z++) {
				String temp = min.substring(y, z);
				
				if(max.contains(temp)) {
					return temp;
				}
			}
		}

		return "-1";
	}
}