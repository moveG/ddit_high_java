package Study;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class S0824_01 {

	public static void main(String[] args) {
		/*
		 * List, Set, Map은 인터페이스
		 * List의 특징 : 순서(인덱스)가 있고, 중복을 허용함
		 */
		
		//DEFAULT_CAPACITY = 10
		List list1 = new ArrayList();
//		List list1 = new LinkedList();
//		ArrayList대신 LinkedList를 사용해도 오류가 발생하지 않음
		
		//데이터 입력 : add()
		list1.add("aaa");
		list1.add("bbb");
		list1.add(111);
//		원래 형식은 list1.add("new Integer(111)");이지만, 내부에서 자동으로 변환
		list1.add('k');
		list1.add(true);
		list1.add(12.34);
		list1.add(0);
		//데이터의 개수 : size()
		System.out.println("size : " + list1.size());
		System.out.println("list1 : " + list1);
		//데이터 출력 : get()
		System.out.println("1번째 자료 : " + list1.get(0));
		//데이터 끼워넣기 : add(인덱스, 데이터)
		list1.add(0, "zzz");
		System.out.println("1번째 자료 : " + list1.get(0));
		System.out.println("list1 : " + list1);
		//데이터 변경: set()
		String temp = (String)list1.set(0, "yyy");
		System.out.println("temp : " + temp);
		System.out.println("list1 : " + list1);
		//데이터 삭제: remove()
		//0번 인덱스 삭제
		list1.remove(0);
		//데이터 0 삭제
		list1.remove(new Integer(0));
		System.out.println("삭제후 : " + list1);
		//특정값 삭제
		list1.remove("bbb");
		System.out.println("삭제후 : " + list1);
		System.out.println("======================================");
		//제너릭을 지정하는 것이 데이터를 출력할때 용이해짐
		List<String> list2 = new ArrayList<String>();
		list2.add("AAA");
		list2.add("BBB");
		list2.add("CCC");
		list2.add("DDD");
		list2.add("EEE");
		for(int i = 0; i < list2.size(); i++) {
			System.out.println(i + " : " + list2.get(i));
		}
		System.out.println("======================================");
		//contains(비교객체) : 리스트에 비교객체가 존재하면 true반환, 존재하지 않으면 false반환
		System.out.println(list2.contains("DDD"));
		System.out.println(list2.contains("ZZZ"));
		System.out.println("======================================");
		//indexOf(비교객체) : 리스트에서 비교객체를 찾아 그 인덱스값을 반환, 비교객체가 없으면 -1을 반환
		System.out.println("DDD의 인덱스값 : " + list2.indexOf("DDD"));
		System.out.println("ZZZ의 인덱스값 : " + list2.indexOf("ZZZ"));
		System.out.println("======================================");
		//toArray() : 리스트를 배열로 변환, 기본적으로 Object형 배열로 변환
		Object[] strArr = list2.toArray();
		System.out.println("배열의 개수 : " + strArr.length);
		System.out.println("배열 : " + Arrays.toString(strArr));
		//ArrayList 순차삭제, 당겨지므로 뒤에서 부터 삭제하는 것이 바람직함
		for(int i = list2.size() - 1; i >= 0; i--) {
			list2.remove(i);
		}
		System.out.println("삭제 후 크기 : " + list2.size());
	}
}
