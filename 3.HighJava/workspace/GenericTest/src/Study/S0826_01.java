package Study;

public class S0826_01 {
	/*
	 * 제너릭 클래스
	 * - 형식
	 *   class 클래스명<제너릭타입글자> {
	 *   	제너릭타입글자 변수명;	//변수선언에 제너릭을 사용할 경우
	 *   		:
	 *   	제너릭타입글자 메서드명(){	//반환값이 있는 메서드에 사용할 경우
	 *   			:
	 *   		return 값;
	 *   	}
	 *   	:
	 *   }
	 *   
	 * - 제너릭타입글자
	 *   T : Type
	 *   K : Key
	 *   V : Value
	 *   E : Element(자료구조에 들어갈 것을 나타낼 때 사용)
	 *   
	 * - Map<K, V> : K는 key타입을 의미, V는 value타입을 의미
	 * 
	 * - 제너릭은 인터페이스, 클래스, 메서드에 사용할 수 있음
	 * - 인터페이스나 클래스의 이름 뒤에 <>를, 메서드의 반환타입 앞에 <>를 넣는다.
	 */
	public static void main(String[] args) {
		//일반
		Non ng1 = new Non();
		ng1.setVal("가나다라");
		Non ng2 = new Non();
		ng2.setVal(100);
		System.out.println(ng1.getVal() + ", " + ng2.getVal());
		System.out.println("============================");
		
		System.out.println("1. 일반 : Object에서 형변환이 필요함");
		String st1 = (String)ng1.getVal();
		System.out.println(st1);
		int st2 = (int)ng2.getVal();
		System.out.println(st2);
		System.out.println("============================");
		
		//제너릭
		Gen<String> g1 = new Gen<String>();
		g1.setVal("가나다라");
		Gen<Integer> g2 = new Gen<Integer>();
		g2.setVal(100);
		System.out.println(g1.getVal() + ", " + g2.getVal());
		System.out.println("============================");
		System.out.println("2. 제너릭 : 사용시 타입을 알림");
		String st3 = g1.getVal();
		System.out.println(st3);
		int st4 = g2.getVal();
		System.out.println(st4);
		System.out.println("============================");
	}
}

class Non {
	private Object val;

	public Non() {
		this.val = val;
	}
	public Object getVal() {
		return val;
	}
	public void setVal(Object val) {
		this.val = val;
	}
}

class Gen<T> {
//지금은 T가 무엇인지 모르지만 사용할때는 알아야함
//class Gen<T extends Number, M extends String> {
//클래스에서도 제한된 타입 파라미터(Bounded Parameter)를 사용할 수 있음
	private T val;

	public T getVal() {
		return val;
	}
	public void setVal(T val) {
		this.val = val;
	}
}
