package Study;

import java.util.ArrayList;
import java.util.List;

public class S0826_04 {
	/*
	 * 와일드 카드
	 * 
	 * - 와일드 카드(?)는 제너릭 타입으로 타입적으로 안전한 코드를 작성하기 위해 사용되는
	 *   특별한 종료의 인수
	 * - 변수 선언, 객체 생성, 메서드 정의에 사용됨
	 * - 제너릭 타입 선언시에는 사용안됨
	 * 
	 * - <? extends T> : 와일드카드의 상한 제한, T와 그 자손들만 가능
	 *   ex) <? extends Number> : 상한을 Number로 설정, Number와 그 자손들만 사용가능
	 * - <? super T>   : 와일드카드의 하한 제한, T와 그 조상들만 가능
	 *   ex) <? super Number> : 하한을 Number로 설정, Number와 그 조상들만 사용가능
	 * - <?>           : 모든 타입 가능, <? extends Object>의 개념
	 * 
	 * - 어떤 타입인지 모른다는 의미의 타입
	 * - 제너릭한 타입을 표현하면서 모른다는 의미를 표현하기 위해 와일드카드 개념이 등장함
	 */
	public static void main(String[] args) {
		FruitBox<Fruit> fruitBox = new FruitBox();	//과일상자
		FruitBox<Apple> appleBox = new FruitBox();	//사과상자
		//객체를 생성할 시점에는 타입을 명시해줘야 객체의 선언과 생성이 가능하다.
		fruitBox.add1(new Apple());
		fruitBox.add1(new Grape());
		
		appleBox.add1(new Apple());
		appleBox.add1(new Apple());
//		appleBox.add1(new Grape());	//잘못된 타입이라서 컴파일 에러가 발생함
		
		Juicer.makeJuice(fruitBox);
		Juicer.makeJuice(appleBox);
	}
}

class Juicer {
//	static <T extends Fruit> void makeJuice(FruitBox<T> Box) {
		//제한된타입 파라미터
		//Juicer에 사과상자도 넣기위해 <>안에 fruit대신 T를 넣음
		//반환 타입 앞에 <T>를 넣음
		//<T extends Fruit> : Fruit를 extends한 타입만 넣을 수 있음
	static void makeJuice(FruitBox<? extends Fruit> B) {
		//와일드카드 방식
		//지금은 타입을 모르지만 적어도 Fruit를 extends한 타입이다.
		String fList = "";	//과일목록
		
		int count = 0;
		for(Fruit f : B.getFruitList()) {
			if(count == 0) {
				fList += f;
			}else {
				fList += ", " + f;
			}
			count++;
		}
		System.out.println(fList + " => 쥬스완성!!!");
	}
}

class Fruit {
	private String fname;	//과일이름
	public Fruit(String fname) {
		super();
		this.fname = fname;
	}
	public String getName() {
		return fname;
	}
	public void setName(String fname) {
		this.fname = fname;
	}
	@Override
	public String toString() {
		return "과일 (" + fname + ")";
	}
}
class Apple extends Fruit {
	public Apple() {
		super("사과");
	}
}
class Grape extends Fruit {
	public Grape() {
		super("포도");
	}
}
class FruitBox<T> {
	private List<T> fruitList;
	public FruitBox() {
		fruitList = new ArrayList<>();	//아직 타입이 정해지지 않은 ArrayList
	}
	public List<T> getFruitList() {
		return fruitList;
	}
	public void setFruitList(List<T> fruitList) {
		this.fruitList = fruitList;
	}
	
	public void add1(T fruit) {
		fruitList.add(fruit);
	}
}
