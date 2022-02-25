package kr.or.ddit.basic;

class Util {
	/**
	 * 제너릭 메서드 <T, R> R method(T t)
	 * 
	 * 파라미터 타입과 리턴타입으로 타입 파라미터를 가지는 메서드
	 * 선언방법 : 리턴타입 앞에 <>기호를 추가하고 타입파라미터를 기술한 뒤 사용함
	 * @param p1
	 * @param p2
	 * @return
	 */
	public static <K, V> boolean compare(Pair<K, V> p1, Pair<K, V> p2) {
		//T, V가 무엇인지 모르므로 어떤 것인지 알려줘야함
		//리턴타입(여기서는 boolean) 앞에 <K, V>를 추가하고 K, V가 파라미터라는 것을 알려줘야함
		//메서드를 사용할 시점에는 K와 V가 무엇인지 알려줘야함
		boolean keyCompare = p1.getKey().equals(p2.getKey());
		boolean valueCompare = p1.getValue().equals(p2.getValue());
		return keyCompare && valueCompare;
		//파라미터 p1, p2를 받아서 비교해서 key와 value가 각각 동일하다면 true를 반환함, 그렇지 않으면 false를 반환함
	}
}

/**
 * 멀티타입<K, V>를 가지는 제너릭 클래스
 * @param <K>
 * @param <V>
 */
class Pair<K, V> {
	private K key;
	private V value;
	//지금은 선언만 할뿐 실제 타입은 알 수 없다.
	//사용할 때 어떤 타입인지 알려줘야 그때 타입이 결정된다.
	public Pair(K key, V value) {
		super();
		this.key = key;
		this.value = value;
	}
	
	public K getKey() {
		return key;
	}
	public void setKey(K key) {
		this.key = key;
	}
	public V getValue() {
		return value;
	}
	public void setValue(V value) {
		this.value = value;
	}

	//Getter, Setter 말고 나만의 메서드 선언
	//key와 value 모두 출력하기
	//클래스보다 한정되어 사용함
	public <K, V> void displayAll(K key, V val) {
		System.out.println(key.toString() + " : " + val.toString());
	}
}

public class T02_GenericMethodTest {
	
	public static void main(String[] args) {
		Pair<Integer, String> p1 = new Pair<Integer, String>(1, "홍길동");
		Pair<Integer, String> p2 = new Pair<Integer, String>(1, "홍길동");
		//객체 생성시 <K, V>가 Integer와 String이라는 것을 알려줘야함 
//		Pair<K, V> p2 = new Pair<Integer, String>(1, "홍길동");	//컴파일 에러 발생함
//		Pair<?, ?> p2 = new Pair<Integer, String>(1, "홍길동");	//컴파일 에러 발생하지 않음, 와일드 카드, 무엇인지 모른다는 의미의 하나의 타입
//		Pair<?, ?> p2 = new Pair<?, ?>(1, "홍길동");				//new Pair<, >에는 와일드 카드를 사용할 수 없음
//		Pair<?, ?> p2 = new Pair<Integer, Integer>(1, "홍길동");	//와일드 카드를 사용하면 new Pair<, >에 아무 타입이나 사용할 수 있음
		
		boolean result = Util.<Integer, String>compare(p1, p2);
//		boolean result = Util.compare(p1, p2); 생략이 가능함
		
	
		if(result) {
			System.out.println("반환결과 : " + result + ", 논리(의미)적으로 동일한 객체임");
		}else {
			System.out.println("반환결과 : " + result + ", 논리(의미)적으로 동일한 객체가 아님");
		}
		System.out.println("==========================================");
		
		Pair<String, String> p3 = new Pair<String, String>("001", "홍길동");
		Pair<String, String> p4 = new Pair<String, String>("002", "홍길동");
		//객체 생성시 <K, V>가 String과 String이라는 것을 알려줘야함
		boolean result2 = Util.<String, String>compare(p3, p4);
//		boolean result2 = Util.compare(p3, p4); 생략이 가능함
		
		if(result2) {
			System.out.println("반환결과 : " + result2 + ", 논리(의미)적으로 동일한 객체임");
		}else {
			System.out.println("반환결과 : " + result2 + ", 논리(의미)적으로 동일한 객체가 아님");
		}
		System.out.println("==========================================");
		
		//제너릭 메서드 테스트
		//Pair클래스 p1에서 displayAll메서드를 소환해서 별도의 타입을 지정해서 사용함
		//p1에서는 Integer, String이지만, displayAll에서는 내가 원하는대로 타입을 지정할 수 있음(String, Integer)
		p1.<String, Integer>displayAll("키", 180);
	}
}
