import java.util.*;

public class Test {
	public static void main(String[] arge) {
		HashMap<String, String> hm = new HashMap<String, String>();
		
		hm.put("01", "li1");
		hm.put("02", "li2");
		hm.put("03", "li3");

		Set<Map.Entry<String, String>> hs = hm.entrySet();

		for	(Iterator<Map.Entry<String, String>> it = hs.iterator(); it.hasNext(); ) {
			Map.Entry<String, String> s = it.next();
			System.out.println(s.getKey() + ":" + s.getValue());
		}
	}

	public static void KeySet() {
		HashMap<String, String> hm = new HashMap<String, String>();
		
		hm.put("01", "li1");
		hm.put("02", "li2");
		hm.put("03", "li3");

		Set<String> hs = hm.keySet();

		for	(Iterator<String> it = hs.iterator(); it.hasNext(); ) {
			String s = it.next();

			System.out.println(hm.get(s));
		}
	}
}