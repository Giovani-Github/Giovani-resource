/*

String类适用于描述字符串事物。
那么它就提供了多个方法对字符串进行操作。

常见的操作有哪些？
"abcd"

1，获取。
	1.1 字符串中的包含的字符数，也就是字符串的长度。
		int length():获取长度。
	1.2 根据位置获取位置上某个字符。
		char charAt(int index):
	1.3 根据字符获取该字符在字符串中位置。
		int indexOf(int ch):返回的是ch在字符串中第一次出现的位置。
		int indexOf(int ch, int fromIndex) :从fromIndex指定位置开始，获取ch在字符串中出现的位置。

		int indexOf(String str):返回的是str在字符串中第一次出现的位置。
		int indexOf(String str, int fromIndex) :从fromIndex指定位置开始，获取str在字符串中出现的位置。

		int lastIndexOf(int ch) ：

		
2，判断。
	2.1 字符串中是否包含某一个子串。
		boolean contains(CharSequence str):
		特殊之处：indexOf(String str):可以索引str第一次出现位置，如果返回-1.表示该str不在字符串中存在。
				所以，也可以用于对指定判断是否包含。
				if(str.indexOf("aa")!=-1)

				而且该方法即可以判断，有可以获取出现的位置。

	2.2 字符中是否有内容。
		boolean isEmpty(): 原理就是判断长度是否为0. 
	2.3 字符串是否是以指定内容开头。
		boolean startsWith(str);
	2.4 字符串是否是以指定内容结尾。
		boolean endsWith(str);
	2.5 判断字符串内容是否相同。复写了Object类中的equals方法。
		boolean equals(str);
	2.6 判断内容是否相同，并忽略大小写。
		boolean equalsIgnoreCase();
	
3，转换。
	3.1 将字符数组转成字符串。
		构造函数：String(char[])
				  String(char[],offset,count):将字符数组中的一部分转成字符串。

		静态方法：
				static String copyValueOf(char[]);
				static String copyValueOf(char[] data, int offset, int count) 

				static String valueOf(char[]):

		
	3.2 将字符串转成字符数组。**
		char[] toCharArray():

	3.3 将字节数组转成字符串。
			String(byte[])
			String(byte[],offset,count):将字节数组中的一部分转成字符串。

	3.4 将字符串转成字节数组。
			byte[]  getBytes():
	3.5 将基本数据类型转成字符串。
		static String valueOf(int)
		static String valueOf(double)

		//3+"";//String.valueOf(3);

		特殊：字符串和字节数组在转换过程中，是可以指定编码表的。

4，替换
	String replace(oldchar,newchar);

5，切割
	String[] split(regex);

6，子串。获取字符串中的一部分。
	String substring(begin);
	String substring(begin,end);

7，转换，去除空格，比较。
	7.1 将字符串转成大写或则小写。
		 String toUpperCase();
		 String toLowerCase();

	7.2 将字符串两端的多个空格去除。
		String trim();

	7.3 对两个字符串进行自然顺序的比较。
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
	
	//打印
	public static void print(String str) {
		System.out.println("(" + str + ")");
	}

	//去除两端空格
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
	
	//将指定的字符串的位置反转
	public static String reverseString(String str, int first, int tail) {
		char[] ch = str.toCharArray();//转为字符数组

		//反转
		reverse(ch, first, tail);

		return new String(ch);//反转后的数组转化为字符串
	
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

	//字符串出现的次数（比较低效，会在内存中产生多余字符串，即st的子字符串）
	public static int stringNumber_2(String st, String key) {
		int number = 0;

		while(st.indexOf(key) != -1) {
			st = st.substring(st.indexOf(key)+key.length());
			number++;
		}

		return number;
	}

	//字符串出现次数二(比较高效，内存中不会产生多余字符串)
	public static int stringNumber(String st, String key) {
		int number = 0;
		int index = 0;

		while((index = st.indexOf(key, index)) != -1) {
			index = index + key.length();
			number++;
		}

		return number;
	}

	//寻找最大相同子串
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