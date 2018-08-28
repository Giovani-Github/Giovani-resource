import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.Test;


public class MapTest {
	@Test
	public void mapTest() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("aa", "11");
		map.put("bb", "22");
		map.put("cc", "33");
		
		//ȡ��map�е�key����,��ʹ����ǿfor��ȡÿһ��key���ٸ���key���value
		Set<String> mapKeys = map.keySet();
		for (String key : mapKeys) {
			String value = map.get(key);
			System.out.println(key + ":" + value);
		}
		
		System.out.println("=======================");
		
		//ʹ��map��������ڲ��ӿڣ�entry��ȡmap�е�Ԫ�أ�entry�ӿ���������map��key-value��ϵ�����Ը��������ϵ�ӿڻ�ȡ��ֵ��Ԫ��
		Set<Entry<String, String>> entrySet = map.entrySet();//��ȡmap��entry��ϵ���set����
		
		for (Entry<String, String> entry : entrySet) {//���������ϵ���ϣ���ȡÿһ�Լ�ֵ�ԵĹ�ϵ�࣬ͨ����ϵ�࣬��ȡ��Լ�ֵ�Ե�key��value
			String key = entry.getKey();
			String value = entry.getValue();
			System.out.println(key + ":" + value);
		}
	}
}
