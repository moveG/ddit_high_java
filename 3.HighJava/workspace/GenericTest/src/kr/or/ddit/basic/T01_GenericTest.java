package kr.or.ddit.basic;

import java.util.Map;

public class T01_GenericTest {
	/*
	 * 제너릭 클래스 만드는 방법
	 * - 형식 : 
	 * class 클래스명<제너릭타입글자> {
	 * 		제너릭타입글자 변수명;	//변수선언에 제너릭을 사용할 경우
	 * 			:
	 * 		제너릭타입글자 메서드명(){	//반환값이 있는 메서드에 사용할 경우
	 * 				:
	 * 			return 값;
	 * 		}
	 * 		:
	 * }
	 * 
	 * 제너릭타입글자
	 * - T : Type
	 * - K : Key
	 * - V : Value
	 * - E : Element(자료구조에 들어가는 것을 나타낼 때 사용)
	 * 
	 * Map<K, V>	//K: Key타입을 의미, V: Value타입을 의미
	 * 
	 * 제너릭은 인터페이스, 클래스, 메서드 등에 사용할 수 있음
	 * 인터페이스나 클래스이름 다음에 <>, 메서드 반환타입 앞에 <>
	 */
	
	public static void main(String[] args) {
		NonGeneraicClass ng1 = new NonGeneraicClass();
		ng1.setVal("가나다라");
		
		NonGeneraicClass ng2 = new NonGeneraicClass();
		ng2.setVal(100);
		
		System.out.println("1. 일반 : Object에서 형변환이 필요함");
		String rtnNg1 = (String) ng1.getVal();
		System.out.println("문자열 반환값 rtnNg1 : " + rtnNg1);
		//꺼내서 사용할 때는 Object에서 원하는 타입으로 형변환을 해줘야함
		Integer irtnNg1 = (Integer)ng2.getVal();
//		int irtnNg1 = (Integer)ng2.getVal();
		System.out.println("정수형 반환값 irtNg2 : " + irtnNg1);
		//꺼내서 사용할 때는 Object에서 원하는 타입으로 형변환을 해줘야함
		System.out.println("============================");
		
		System.out.println("2. 제너릭 : 사용시 타입을 알려줘야함");
		MyGeneric<String> mg1 = new MyGeneric<String>();
		MyGeneric<Integer> mg2 = new MyGeneric<Integer>();
		//아래서 T를 넣을 수 있었던것 문법에 맞게 입력해서이고, 실제로 사용할 때는 원하는 타입을 입력해야한다.
		//형변환 없이 바로 사용해도 컴파일에러가 발생하지 않는다.
		mg1.setVal("우리나라");
		mg2.setVal(500);
		String rtnNg2 = mg1.getVal();
		Integer irtnNg2 = mg2.getVal();
		System.out.println("제너릭 문자열 반환값 : " + rtnNg2);
		System.out.println("제너릭 정수형 반환값 : " + irtnNg2);
		System.out.println("============================");
	}
}

class NonGeneraicClass {
	private Object val;

	public Object getVal() {
		return val;
	}
	public void setVal(Object val) {
		this.val = val;
	}
}

class MyGeneric<T> {
	//T가 지금은 무엇인지 모르지만, 사용할 때는 타입을 알아야 한다.
//class MyGeneric<T extends Number, M extends String> {
	//클래스에서도 제한된 타입파라미터(Bounded Parameter)를 사용할 수 있다.
	private T val;

	public T getVal() {
		return val;
	}
	public void setVal(T val) {
		this.val = val;
	}	
}
