/*
记录字符出现次数
*/

import java.util.*;

public class Test {
	public static void main(String[] args) {
		String s = getString("dfdfadsfsade3sfs");
		System.out.println(s);
	}

	public static String getString(String st) {
		TreeMap<Character, Integer> tm = new TreeMap<Character, Integer>();
		char[] chs = st.toCharArray();
		int count = 0;

		for(int i=0; i<chs.length; i++) {
			if(!(chs[i] >= 'a' && chs[i] <= 'z' || chs[i] >= 'A' && chs[i] <= 'Z'))
				continue;

			Integer value = tm.get(chs[i]);

			if(value != null)
				count = value;
			count++;
			tm.put(chs[i], count);

			count = 0;
		}
		
		StringBuilder sb = new StringBuilder();

		Set<Map.Entry<Character, Integer>> entrySet = tm.entrySet();

		for(Iterator<Map.Entry<Character, Integer>> it = entrySet.iterator(); it.hasNext();	) {
			Map.Entry<Character, Integer> me = it.next();

			Character key = me.getKey();
			Integer value = me.getValue();

			sb.append(key + "(" + value + ")");
		}

		return sb.toString();
	}
}