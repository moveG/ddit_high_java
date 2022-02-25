package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class T01_ArrayListTest {
	
	public static void main(String[] args) {
		/*
		 * List, Set, Map은 인터페이스
		 * List의 특징 : 순서(인덱스)가 있고, 중복을 허용한다.
		 */
		
		//DEFAULT_CAPACITY = 10
		List list1 = new ArrayList();
//		List list1 = new LinkedList();
//		ArrayList 대신 LinkedList를 사용해도 오류가 발생하지 않음
//		LinkedList list1 = new LinkedList();
//		LinkedList를 하면 list1이 LinkedList에 종속되므로 LinkedList보다는 List를 사용하는 것이 좋음
		
		//add()메서드를 사용하여 데이터를 추가한다.
		list1.add("aaa");
		list1.add("bbb");
		list1.add(111);
//		list1.add(new Integer(111));
//		원래 이 형식으로 해야하지만, 자바 내부에서 자동으로 실행됨
		list1.add('k');
		list1.add(true);
		list1.add(12.34);
		list1.add(0);
		
		//size() => 데이터 개수
		System.out.println("size => " + list1.size());
		System.out.println("list1 => " + list1);
		
		//get()으로 데이터 꺼내오기
		System.out.println("1번째 자료 : " + list1.get(0));
		
		//데이터 끼워넣기도 같다.(0번 인덱스에 "zzz" 끼워넣기)
		list1.add(0, "zzz");
		System.out.println("list1 => " + list1);
		
		//데이터 변경하기(set메서드)
		String temp = (String) list1.set(0, "yyy");
		System.out.println("temp => " + temp);
		System.out.println("list1 => " + list1);
		
		//삭제하기
		//0번 인덱스를 삭제
		list1.remove(0);
		//인덱스가 아닌 값(0)을 삭제
		list1.remove(new Integer(0));
		System.out.println("삭제 후 : " + list1);
		
		//List에서 해당 값("bbb")을 찾아 삭제
		list1.remove("bbb");
		System.out.println("bbb 삭제 후 : " + list1);
		System.out.println("list1 => " + list1);
		System.out.println("======================================");
		//제너릭을 지정하여 선언할 수 있다.
		//제너릭을 지정하지 않으면 값을 넣을 때는 편하지만, 값을 꺼낼 때는 값의 타입을 알기 힘들어 문제가 될 수 있다.
		//제너릭을 지정해서 값을 꺼낼 때 타입에 대한 확인을 생략할 수 있다.
		List<String> list2 = new ArrayList<String>();
		list2.add("AAA");
		list2.add("BBB");
		list2.add("CCC");
		list2.add("DDD");
		list2.add("EEE");
		
		for(int i = 0; i < list2.size(); i++) {
			System.out.println(i + " : " + list2.get(i));
		}
		System.out.println("--------------------------------------");
		
		//contains(비교객체) => 리스트에 '비교객체'가 있으면 true
		//					    리스트에 '비교객체'가 없으면 false
		System.out.println(list2.contains("DDD"));
		System.out.println(list2.contains("ZZZ"));
		System.out.println("--------------------------------------");
		
		//indexOf(비교객체) => 리스트에 '비교객체'를 찾아 '비교객체'가 있는 index값을 반환한다.
		//					  리스트에 '비교객체'가 없으면 -1을 반환한다.
		System.out.println(list2);
		System.out.println("DDD의 index값 : " + list2.indexOf("DDD"));
		System.out.println("ZZZ의 index값 : " + list2.indexOf("ZZZ"));
		System.out.println("--------------------------------------");
		
		//toArray() => 리스트 안의 데이터들을 배열로 변환하여 반환한다.
		//			      기본적으로 Object형 배열로 반환한다.
		Object[] strArr = list2.toArray();
		System.out.println("배열의 개수 : " + strArr.length);
		
		//ArrayList 순차적으로 지우기
		//당겨지지 때문에 전부 삭제가 안됨
		for(int i = 0; i < list2.size(); i++) {
			list2.remove(i);
		}
		//전부 지우려면 뒤에서부터 지워야함
//		System.out.println("삭제 후 크기 => " + list2.size());
//		for(int i = list2.size() - 1; i >= 0; i--) {
//			list2.remove(i);
//		}
		
		//ArrayList : 배열의 특징(순서가 있음, 크기가 정해져 있음)을 가지고 있다, 중간부분을 삭제하면 값이 당겨짐
		//LinkedList : 삭제해도 값이 당겨지지 않음, 중간부분이 자주 변경되고 삭제되는 환경이면 ArrayList보다 LinkedList를 사용하는 것이 효율적임
	}
}
