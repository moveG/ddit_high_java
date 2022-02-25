package Study;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class S0826_01 {
	/*
	 * Map
	 * - Key값과 Value값을 한 쌍으로 관리하는 객체
	 * - Key값은 중복을 허용하지 않고, 순서가 없다.(Set의 특징)
	 * - Value값은 중복을 허용한다.
	 */
	public static void main(String[] args) {
		Map<String, String> map = new HashMap<String, String>();
		
		//자료추가 : put(key값, value값);
		System.out.println("자료추가");
		map.put("name", "홍길동");
		map.put("addr", "대전");
		map.put("tel", "010-111-1111");
		System.out.println("map : " + map);
		System.out.println("==============================================");
		
		//자료읽기 : get(key값);
		System.out.println("자료출력(value값)");
		System.out.println("name : " + map.get("name"));
		System.out.println("addr : " + map.get("addr"));
		System.out.println("tel : " + map.get("tel"));
		System.out.println("==============================================");
		
		//key값들을 읽어와서 자료를 출력하는 방법
		//1. keySet()메서드 이용하기
		//2. keySet()메서드는 Map의 key값들만 읽어와서 Set형으로 반환함
		System.out.println("자료출력(Key값)");
		//방법1
		System.out.println("1. Iterator");
		Set<String> keySet123 = map.keySet();
		Iterator<String> it = keySet123.iterator();
		while(it.hasNext()) {
			String key123 = it.next();
			System.out.println(key123 + " : " + map.get(key123));
		}
		System.out.println("==============================================");
		//방법2 : 향상된 for문
		System.out.println("2. 향상된 for문");
		for(String key123 : keySet123) {
			System.out.println(key123 + " : " + map.get(key123));
		}
		System.out.println("==============================================");
		//방법3 : values()
		System.out.println("3. values()");
		for(String value123 : map.values()) {
			System.out.println(value123);
		}
		System.out.println("==============================================");
		//방법4-1 : entry
		System.out.println("4-1. entry(key + value)");
		Set<Map.Entry<String, String>> mapSet123 = map.entrySet();
		
		Iterator<Map.Entry<String, String>> entryIt123 = mapSet123.iterator();
		while(entryIt123.hasNext()) {
			Map.Entry<String, String> entry123 = entryIt123.next();
			System.out.println("key값 : " + entry123.getKey() + ", value값 : " + entry123.getValue());
			
		}
		System.out.println("==============================================");
		//방법4-2 : 향상된 for문
		System.out.println("4-2. entry(key + value)를 향상된 for문으로 출력");
		for(Map.Entry<String, String> aa : mapSet123) {
			System.out.println(aa);
		}
		System.out.println("==============================================");
	}
}
