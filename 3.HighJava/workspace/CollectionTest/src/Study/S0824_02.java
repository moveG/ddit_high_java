package Study;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class S0824_02 {
	
	public static void main(String[] args) {
		/*
		 * 정렬과 관련된 interface는 Comparable과 Comparator 두가지가 존재한다.
		 * - 보통 객체 자체에 정렬기능을 넣기 위해서는 Comparable을 구현하고,
		 *   정렬기준을 별도로 구현하고 싶은 경우에는 Comparator을 구현하여 사용하면 된다.
		 *   
		 * - Comparable에서는 compareTo()메서드를 구현해야 하고,
		 *   Comparator에서는 compare()메서드를 구현해야 한다.  
		 */
		
		List<String> list = new ArrayList<String>();
		
		list.add("일지매");
		list.add("홍길동");
		list.add("성춘향");
		list.add("변학도");
		list.add("이순신");
		System.out.println("정렬 전 : " + list);
		
		/*
		 * 정렬은 Collections.sort()메서드를 이용하여 정령한다.
		 * 졍렬은 기본적으로 '오름차순'정렬을 수행한다.
		 * 정렬방식을 변경하려면 정렬방식을 결정하는 개체를 만들어서 Collections.sort()메서드에 인수로 넘겨주면 되다.
		 * 
		 * "Collection"은 인터페이스, "Collections"는 클래스
		 */
		//오름차순으로 정렬하기(정렬기능 삽입, Comparable, defalut가 오름차순)
		
		Collections.sort(list);
		System.out.println("오름차순 정렬 후 : " + list);
		
		//데이터 섞기
		Collections.shuffle(list);
		System.out.println("자료 섞기 후 : " + list);
		
		//정렬방식을 결정하는 객체를 이용하여 정렬하기(Comparator)
		//오름차순으로 정렬하기(정렬기능 변도구현, Comparable)
		Collections.sort(list, new Desc());
		System.out.println("정렬 후 : " + list);
		
		//내림차순으로 정렬하기(정렬기능 변도구현, Comparable)
		Collections.sort(list, new Asc());
		System.out.println("정렬 후 : " + list);
	}
}

/**
 * 정렬방식을 결정하는 class는 Comparator라는 인터페이스를 구현해야 한다.
 */
class Desc implements Comparator<String> {
	/*
	 * compare()메서드의 반환값을 결정하는 방법
	 * => 이 메서드가 양수를 반환하면 두 값의 순서가 바뀐다.(오름차순이 기본임)
	 * 
	 * - 오름차순 정렬일 경우
	 * => 앞의 값이 크면 (양수(+), 같으면 제로(0), 작으면 음수(-)를 반환하도록 한다.
	 * 
	 * - String객체에는 정렬을 위해 Comparable()메서드가 구현되어 있는데, 이 메서드의 반환값은 오름차순에 맞게 반환되도록 구현되어 있다.
	 *   (wrapper클래스와 Date, File클래스에도 구현되어 있다.)
	 */
	@Override
	public int compare(String str1, String str2) {
		return str1.compareTo(str2) * 1;
		//compareTo(): str1이 크면 양수, str2가 크면 음수, 같으면 0을 반환함
	}
}

class Asc implements Comparator<String> {
	@Override
	public int compare(String str1, String str2) {
		return str1.compareTo(str2) * -1;
		//compareTo(): str1이 크면 양수, str2가 크면 음수, 같으면 0을 반환함
		// -1을 곱해줘서 반대로 만듦
	}
}
