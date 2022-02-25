package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class T04_ListSortTest {
	
	public static void main(String[] args) {
		List<Member> memList = new ArrayList<Member>();
		
		memList.add(new Member(1, "홍길동", "010-1111-1111"));
		memList.add(new Member(5, "변학도", "010-2222-1111"));
		memList.add(new Member(4, "성춘향", "010-3333-1111"));
		memList.add(new Member(3, "이순신", "010-4444-1111"));
		memList.add(new Member(6, "강감찬", "010-5555-1111"));
		memList.add(new Member(2, "일지매", "010-6666-1111"));
		
		System.out.println("정렬 전");
		for(Member mem : memList){
			System.out.println(mem);
		}
		System.out.println("==============================================");
		
		Collections.sort(memList);	//Comparable 정렬하기
		
		System.out.println("이름으로 오름차순 정렬 후 - Comparable 정렬");
		for(Member mem : memList){
			System.out.println(mem);
		}
		System.out.println("==============================================");
		
		Collections.sort(memList, new SortNumAsc());	//Comparator 정렬하기
		
		System.out.println("번호로 내림차순 정렬 후 - Comparator 정렬");
		for(Member mem : memList){
			System.out.println(mem);
		}
		System.out.println("==============================================");
		
		Collections.sort(memList, new SortNumDesc());	//Comparator 정렬하기
		
		System.out.println("번호로 오름차순 정렬 후 - Comparator 정렬");
		for(Member mem : memList){
			System.out.println(mem);
		}
		System.out.println("==============================================");
	}
}

/**
 * 회원정보를 저장할 클래스 만들기
 * (회원이름을 기준으로 오름차순 정렬이 될 수 있는 클래스 만들기)
 */
class Member implements Comparable<Member> {
	private int num; 		//번호
	private String name;	//이름
	private String tel;		//전화번호

	public Member(int num, String name, String tel) {
		super();
		this.num = num;
		this.name = name;
		this.tel = tel;
	}

	public int getNum() {
		return num;
	}


	public void setNum(int num) {
		this.num = num;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getTel() {
		return tel;
	}


	public void setTel(String tel) {
		this.tel = tel;
	}

	@Override
	public int compareTo(Member mem) {
		return getName().compareTo(mem.getName());
		//return this.getName().compareTo(mem.getName());
		//this.이 생략되어 있음
		//나와 비교하는 것이라서 파라미터가 한 개만 있어도 비교할 수 있음
		//기본적으로 오름차순으로 정렬됨
	}
	
	@Override
	public String toString() {
		return "Member [num=" + num + ", name=" + name + ", tel=" + tel + "]";
	}
}

/**
 * 정렬 기준의 외부선언을 위해서는 Comparator인터페이스를 구현한다.
 * (Member객체의 번호(num)를 내림차순으로 정렬하기)
 */
class SortNumAsc implements Comparator<Member> {
	@Override
	public int compare(Member mem1, Member mem2) {
//		if(mem1.getNum() > mem2.getNum()) {
//			return -1;
//		} else if(mem1.getNum() == mem2.getNum()) {
//			return 0;
//		} else {
//			return 1;
//		}
		
		//Wrapper클래스에서 제공하는 메서드를 이용하는 방법1
//		return Integer.compare(mem1.getNum(), mem2.getNum()) * -1;
		
		//Wrapper클래스에서 제공하는 메서드를 이용하는 방법2-1 : 오름차순
//		return new Integer(mem1.getNum()).compareTo(mem2.getNum());

		//Wrapper클래스에서 제공하는 메서드를 이용하는 방법2-2 : 내림차순
		return new Integer(mem1.getNum()).compareTo(mem2.getNum()) * -1;
		
	}
}

class SortNumDesc implements Comparator<Member> {
	@Override
	public int compare(Member mem1, Member mem2) {
		if(mem1.getNum() > mem2.getNum()) {
			return 1;
		} else if(mem1.getNum() == mem2.getNum()) {
			return 0;
		} else {
			return -1;
		}
	}
}
