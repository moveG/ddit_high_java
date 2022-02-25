package Study;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class S0824_03 {
	
	public static void main(String[] args) {
		/*
		 * Set은 데이터의 순서가 없고, 중복을 허용하지 않는다.
		 * 그래서 이미 존재하는 데이터를 add하면 false를 반환하고, 데이터는 추가되지 않는다.
		 */
		Set hs1 = new HashSet();
		
		//Set에 데이터를 추가할 때도 add()를 이용한다.
		hs1.add("DD");
		hs1.add("AA");
		hs1.add(2);
		hs1.add("CC");
		hs1.add("BB");
		hs1.add(1);	//Integer 객체로 형변환되어 삽입됨, 오토박싱
		hs1.add(3);
		
		System.out.println("Set 데이터 : " + hs1);
		System.out.println("==============================================");
		
		boolean isAdd = hs1.add("FF");
		System.out.println("중복되지 않을 때 : " + isAdd);
		System.out.println("Set 데이터 : " + hs1);
		System.out.println();
		
		isAdd = hs1.add("DD");
		System.out.println("중복될 때 : " + isAdd);
		System.out.println("Set 데이터 : " + hs1);
		System.out.println("==============================================");
		
		/*
		 * Set의 데이터를 수정하려면 수정하는 명령이 따로 없기 때문에 해당자료를
		 * 삭제한 후 새로운 데이터를 추가해줘야 한다.
		 * 
		 * 삭제하는 메서드
		 * 1) clear() => 데이터 전체 삭제
		 * 2) remove(삭제할 자료) => 해당 자료 삭제
		 */
		
		//EX) "FF"를 "EE"로 수정하기
		hs1.remove("FF");
		System.out.println("remove 후 Set 데이터 : " + hs1);
		System.out.println();
		
		hs1.add("EE");
		System.out.println("remove 후 Set 데이터 : " + hs1);
		System.out.println();
		
		//전체자료 삭제
//		hs1.clear();
		System.out.println("==============================================");
		
		/*
		 * Set은 데이터의 순서가 없기 때문에 List처럼 인덱스로 데이터를 하나씩 불러올 수 없다.
		 * 그래서 데이터를 하나씩 얻기 위해서는 Iterator를 이용해야 한다.
		 */ 
		
		//Set의 데이터에서 Iterator객체 가져오기
		Iterator it = hs1.iterator();
		
		//데이터 개수만큼 반복
		//hasNext()메서드 : 포인터 다음 위치에 데이터가 있으면 true, 없으면 false를 반환
		System.out.println("꺼내기");
		while(it.hasNext()) {
			System.out.print(it.next() + " ");
		}
		System.out.println();
		System.out.println("==============================================");
		
		Set<Integer> intRnd = new HashSet<>();
		while(intRnd.size() < 5) {
			int num = (int)(Math.random() * 100 + 1);
			intRnd.add(num);
		}
		System.out.println("만들어진 난수들 : " + intRnd);
		System.out.println("==============================================");
		
		//Collection 유형의 객체들은 서로 다른 자료구조로 쉽게 변경해서 사용할 수 있다.
		//다른 종류의 객체를 생성할 때 생성자에 변경할 데이터를 넣어주면 된다.
		List<Integer> intRndList = new ArrayList<Integer>(intRnd);
		System.out.println("List의 자료 출력...");
		for(Integer num : intRndList) {
			System.out.print(num + " ");
		}	
	}
}
