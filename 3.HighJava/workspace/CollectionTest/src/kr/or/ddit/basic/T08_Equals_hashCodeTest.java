package kr.or.ddit.basic;

import java.util.HashSet;
import java.util.Set;

public class T08_Equals_hashCodeTest {
	/*
	 * 해시함수(hash function)는 임의의 길이의 데이터를 고정된 길이의 데이터로 매핑하는 함수이다.
	 * 해시함수에 의해 얻어지는 값은 해시값, 해시코드, 해시체크섬 또는 간단하게 해시라고 한다.
	 * 
	 * HashSet, HashMap, HashTable과 같은 객체들을 사용할 경우
	 * 객체가 서로 동일한지 비교하기 위해 equals()메서드와 hashCode()메서드를 호출한다.
	 * 그래서 객체가 서로 동일한지에 대한 여부를 결정하려면 두 메서드를 재정의해야 한다.
	 * 
	 * HashSet, HashMap, HashTable에서는 개체가 동일한지에 대한 여부는 데이터를 추가할때 검사한다.
	 * - equals()메서드는 두 객체의 내용(값)이 동일한지 비교하는 메서드이다.
	 * - hashCode()메서드는 객체에 대한 해시코드값을 반환하는 메서드이다.
	 * 
	 * equals()메서드와 hashCode()메서드에 관련된 규칙
	 * - 두 객체가 동일하면 반드시 동일한 hashCode를 가져야 한다.
	 * - 두 객체가 동일하면 equals()메서드를 추출했을 때, true값을 반환해야 한다.
	 *   즉, 객체 a, 객체b가 동일하다면 a.equals(b)와 b.equals(a) 둘 다 true값을 반환해야 한다.
	 * - 두 객체의 hashCode가 동일하다고 해서 두 객체가 반드시 동일한 객체는 아니다.
	 *   하지만 두 객체가 동일하면 반드시 hashCode가 동일해야 한다.
	 * - equals()메서드를 override하면 반드시 hashCode()메서드도 override해야 한다.
	 * - hashCode()메서드는 기본적으로 Heap에 존재하는 각 객체에 대한 메모리주소 매핑 정보를 기반으로하는 정수값을 반환해야 한다.
	 *   그러므로 클래스에서 hashCode()메서드를 override하지 않으면 절대로 두 객체가 동일한 것으로 간주될 수 없다.
	 *   
	 * hashCode()메서드에서 사용하는 '해싱 알고리즘'에서 서로 다른 객체에 대하여 같은 hashCode값을 만들어낼 수 있다.
	 * 그래서 객체가 동일하지 않더라도 hashCode값은 동일할 수 있다.
	 * 
	 * 배열과의 차이
	 * - 해시코드를 통해 매핑된 주소로 접근하기 때문에 검색, 삭제, 등록이 빠르다.
	 * - 순차적으로 저장될 필요가 없다, 해시함수에 따라 중복(충돌)이 발생할 가능성이 존재한다.
	 * - 해시테이블이 존재하기 때문에 메모리 사용이 더 많다.
	 */
	
	public static void main(String[] args) {
		Person p1 = new Person(1, "홍길동");
		Person p2 = new Person(1, "홍길동");
		Person p3 = new Person(1, "이순신");
		
		System.out.println("p1.equals(p2) : " + p1.equals(p2));
		//Object의 equals()는 메모리를 기반으로 판단함
		//동일한 메모리에 존재하는 것만 동일하다고 판단함
		//메모리 주소가 다르기 때문에 동일하지 않다고 판단해 false를 반환함
		//클래스에 equals()를 오버라이딩 해주면, 데이터로 판단해 true를 반환함
		System.out.println("p1 == p2 : " + (p1 == p2));
		
		Set<Person> set = new HashSet<>();
		set.add(p1);
		set.add(p2);
		//제대로 판단하려면 equals와 hashCode 둘 다 판단해야함
		System.out.println("p1, p2 등록 후 데이터");
		for(Person p : set) {
			System.out.println(p.getId() + " : " + p.getName());
		}
		System.out.println("==============================================");
		
		System.out.println("add(p3) 성공 여부 : " + set.add(p3));
		System.out.println("add(p3) 후 데이터");
		for(Person p : set) {
			System.out.println(p.getId() + " : " + p.getName());
		}
		System.out.println("==============================================");
		
		System.out.println("remove(p2) 성공 여부 : " + set.remove(p2));
		System.out.println("remove(p2) 후 데이터");
		for(Person p : set) {
			System.out.println(p.getId() + " : " + p.getName());
		}
		System.out.println("==============================================");
	}
}

class Person {
	private int id;
	private String name;
	
	public Person(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
		//this는 p1 obj는 p2, 주소를 비교함
			return true;
		if (obj == null)
		//p2는 null이 아님
			return false;
		if (getClass() != obj.getClass())
		//this.getClass() != obj.getClass() : this가 생략됨
		//p1과 p2가 같은 클래스인지 비교
			return false;
		Person other = (Person) obj;
		if (id != other.id)
		//p1과 p2의 id를 비교	
			return false;
		if (name == null) {
		//p1의 name이 null인지 확인	
			if (other.name != null)
			//p2의 name이 null인지 확인	
				return false;
		} else if (!name.equals(other.name))
		//p1의 name과 p2의 name을 비교	
			return false;
		return true;
		//p1과 p2를 비교한 결과, 위의 상황에 해당이 안되므로 true를 반환
	}
}
