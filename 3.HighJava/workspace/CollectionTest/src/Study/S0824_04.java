package Study;

import java.util.SortedSet;
import java.util.TreeSet;

/**
 * TreeSet예제
 */
public class S0824_04 {
	
	public static void main(String[] args) {
		//TreeSet은 자동정렬 기능을 가지고 있다.
		TreeSet<String> ts = new TreeSet<>();
		//TreeSet에만 headSet, tailSet메서드가 존재하기 때문에, Set<String>을 사용할 수 없음
		//Set에는 headSet, tailSet메서드가 존재하지 않음
		//new TreeSet<String>();에서 String을 생략할 수 있음
		
		//영어 대문자를 문자열로 반환하여 TreeSet에 저장
		for(char ch = 'Z'; ch >= 'A'; ch--) {
			String temp = String.valueOf(ch);
			ts.add(temp);
		}
		System.out.println("TreeSet 자료 : " + ts);
		System.out.println("==============================================");
		
		/*
		 * TreeSet에 저장된 자료 중 특정한 자료보다 작은 자료를 찾아서,
		 * SortedSet으로 반환하는 메서드가 존재함
		 * - headSet(기준값) : 기본적으로 '기준값'은 포함시키지 않는다.
		 * - headSet(기준값, 논리값) : 논리값이 true라면 '기준값'을 포함시킨다. 
		 */
		SortedSet<String> ss1 = ts.headSet("K");
		System.out.println("K 이전 자료(기준값 미포함) : " + ss1);					//기준값 미포함
		System.out.println("K 이전 자료(기준값 포함) : " + ts.headSet("K", true));	//기준값 포함
		System.out.println("==============================================");
		
		/*
		 * '기준값'보다 큰 자료를 찾아 SortedSet으로 반환하는 메서드
		 * - tailSet(기준값) : 기본적으로 '기준값'을 포함시킨다.
		 * - tailSet(기준값, 논리값) : 논리값이 false라면 '기준값'을 포함시키지 않는다.
		 */
		SortedSet<String> ss2 = ts.tailSet("K");
		System.out.println("K 이후 자료(기준값 포함) : " + ss2);
		System.out.println("K 이후 자료(기준값 미포함) : " + ts.tailSet("K", false));
		System.out.println("==============================================");
		
		/*
		 * 두 '기준값' 사이의 자료를 반환하는 메서드
		 * subSet(기준값1, 기준값2) : 기준값1 ~ 기준값2 사이의 값을 가져온다.
		 * subSet(기준값1, 논리값1, 기준값2, 논리값2) : 각 기준값 포함여부 설정
		 */
		SortedSet<String> ss3 = ts.subSet("K", "N");
		System.out.println("K(포함)부터 N(미포함)까지 : " + ss3);
		System.out.println("K(포함)부터 N(미포함)까지 : " + ts.subSet("K", "N"));
		System.out.println("K(포함)부터 N(미포함)까지 : " + ts.subSet("K", true, "N", false));
		System.out.println("K(포함)부터 N(포함)까지 : " + ts.subSet("K", true, "N", true));
		System.out.println("K(미포함)부터 N(미포함)까지 : " + ts.subSet("K", false, "N", false));
		System.out.println("K(미포함)부터 N(포함)까지 : " + ts.subSet("K", false, "N", true));
		System.out.println("==============================================");
	}
}
