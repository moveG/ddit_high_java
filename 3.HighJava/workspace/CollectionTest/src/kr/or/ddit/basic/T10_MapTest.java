package kr.or.ddit.basic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class T10_MapTest {
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
		
		//자료수정 : 데이터를 저장할때 key값이 동일하면 나중에 입력한 값이 저장됨
		//         put(수정할key값, 새로운value값);
		System.out.println("자료수정");
		map.put("addr", "서울");
		System.out.println("map : " + map);
		System.out.println("==============================================");
		
		//자료삭제 : remove(삭제할key값);
		System.out.println("자료삭제");
		map.remove("name");
		System.out.println("map : " + map);
		System.out.println("==============================================");
		
		//자료읽기 : get(key값);
		System.out.println("자료출력(value값)");
		System.out.println("addr : " + map.get("addr"));
		System.out.println("==============================================");
		
		//key값들을 읽어와서 자료를 출력하는 방법
		//1. keySet()메서드 이용하기
		//2. keySet()메서드는 Map의 key값들만 읽어와서 Set형으로 반환함
		System.out.println("자료출력(key값)");
		//방법1
		Set<String> keySet = map.keySet();	//keySet: key를 set형식으로 넘겨줌
		
		System.out.println("1. Iterator를 이용하는 방법");
		Iterator<String> it = keySet.iterator();
		while(it.hasNext()) {
			String key = it.next();
			System.out.println(key + " : " + map.get(key));
		}
		System.out.println("==============================================");
		
		//방법2 : Set형의 데이터를 "향상된 for문"으로 처리하기
		System.out.println("2. 향상된 for문을 이용하는 방법");
		for(String key : keySet) {
			System.out.println(key + " : " + map.get(key));
		}
		System.out.println("==============================================");
		
		//방법3 : value값만 읽어와서 출력하기 - values()메서드 이용
		System.out.println("3. value()메서드를 이용하는 방법");
		for(String value : map.values()) {
			System.out.println(value);
		}
		System.out.println("==============================================");
		
		//방법4 : Map관련 클래스에는 Map.Entry타입의 내부 class가 만들어져 있다.
		//       이 내부 클래스는 key와 value라는 멤버변수로 구성되어 있다.
		//       Map에서 이 Map.Entry타입의 객체들을 Set형식으로 저장하여 관리한다.
		//       Map.Entry타입의 객체 모두 가져오기
		//엔트리 : Key + Value
		System.out.println("4.");
		Set<Map.Entry<String, String>> mapSet = map.entrySet();
		//entrySet: entry를 set형식으로 넘겨줌
		//Map.Entry<String, String>타입의 Set
		
		//가져온 Entry객체들을 순서대로 처리하기 위해 Iterator객체 이용
		Iterator<Map.Entry<String, String>> entryIt = mapSet.iterator();
		while(entryIt.hasNext()) {
			Map.Entry<String, String> entry = entryIt.next();
			System.out.println("key값 : " + entry.getKey() + ", value값 : " + entry.getValue());
		}
		System.out.println("==============================================");
		
		//향상된 for문으로 출력해보기
		System.out.println("5.");
		for(Map.Entry<String, String> et : mapSet) {
			System.out.println(et);
		}
		System.out.println("==============================================");
	}
}
