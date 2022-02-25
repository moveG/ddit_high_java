package Study;

/*
 * 제너릭 메서드 <T, R> R method(T t)
 * - 파라미터타입과 리턴타입으로 타입 파라미터를 가지는 메서드
 * - 선언방법 : 리턴타입 앞에 <>기호를 추가하고 타입 파라미터를 기술한 뒤 사용함
 */
class Util {
	public static <K, V> boolean compare11(Pair<K, V> p1, Pair<K, V> p2) {
		//T, V가 무엇인지 모르므로 알려줘야함
		//리턴타입(boolean) 앞에 <K, V>를 추가하고 K, V가 파라미터라는 것을 알려줘야함
		//메서드 사용시점에는 알려줘야함
		boolean key111 = p1.getKey().equals(p2.getKey());
		boolean val111 = p1.getValue().equals(p2.getValue());
		
		return key111 && val111;
		//파라미터 p1, p2를 받아 비교해서 key와 value가 동일하다면 true를 반환함,
		//									     동일하지 않으면 false를 반환함.
	}
}

/*
 * 멀티타입<K, V>을 가지는 제너릭 클래스
 */
class Pair<K, V> {
	private K key;
	private V value;
	//지금은 선언만할 뿐 실제 타입은 아직 미정임
	//사용시 타입이 결정됨
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
	
	//getter, setter를 제외한 메서드 선언
	//key, value 모두 출력하기
	//클래스보다 한정되어 사용됨
	public <K, V> void displayAll(K key12, V val12) {
		System.out.println(key12.toString() + " : " + val12.toString());
	}
	
}

public class S0826_02 {
	
	public static void main(String[] args) {
		Pair<Integer, String> p11 = new Pair<Integer, String>(1, "홍길동");
		Pair<Integer, String> p22 = new Pair<Integer, String>(1, "홍길동");
		Pair<Integer, String> p33 = new Pair<Integer, String>(1, "김길동");
		Pair<Integer, String> p44 = new Pair<Integer, String>(2, "김길동");
		//객체생성시 <K, V>가 각각 Integer와 String이라는 것을 알려줌
//		Pair<K, V> p22 = new Pair<Integer, String>(1, "홍길동");
//		컴파일 에러
//		Pair<?, ?> p22 = new Pair<Integer, String>(1, "홍길동");
//		와일드카드, 무엇인지 모른다른 의미의 타입
//		Pair<?, ?> p22 = new Pair<?, ?>(1, "홍길동");
//		new Pair< , >에는 와일드카드를 사용할 수 없음
//		Pair<?, ?> p22 = new Pair<Integer, Integer>(1, "홍길동")
//		와일드카드를 사용하면 new Pair< , >에 아무 타입이나 전부 사용가능해짐
				
		boolean result = Util.<Integer, String>compare11(p11, p22);
//		boolean result = Util.compare(p11, p22);	//생략가능
		
		if(result) {
			System.out.println("반환결과 : " + result + ", 논리(의미)적으로 동일");
		}else {
			System.out.println("반환결과 : " + result + ", 논리(의미)적으로 다름");
		}
		System.out.println("==========================================");
		
		Pair<String, String> p55 = new Pair<String, String>("001", "홍길동");
		Pair<String, String> p66 = new Pair<String, String>("001", "홍길동");
		Pair<String, String> p77 = new Pair<String, String>("002", "홍길동");
		//객체성성시 <K, V>가 각각 String과 String이라는 것을 알려줌
		boolean result2 = Util.compare11(p55, p77);
		
		if(result2) {
			System.out.println("반환결과 : " + result2 + ", 논리(의미)적으로 동일");
		}else {
			System.out.println("반환결과 : " + result2 + ", 논리(의미)적으로 다름");
		}
		System.out.println("==========================================");
		
		//제너릭 메서드 테스트
		//Pair클래스 p1에서 displayAll메서드를 소환해서 별도의 타입으로 지정해 사용가능
		//p77에서 String, String이지만, displayAll에서는 다르게 지정가능
		p77.<String, Integer>displayAll("신장", 185);
	}
}
