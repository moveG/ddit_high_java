package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.List;

public class T04_WildCardTest {
	/*
	 * 와일드 카드
	 * 
	 * - 와일드 카드('?')는 제너릭 타입을 이용한 타입적으로 안전한 코드를 위해 사용되는
	 *   특별한 종류의 인수로서 변수선언, 객체생성 및 메서드를 정의할 때 사용된다.
	 *   (제너릭 타입 선언시에는 사용할 수 없다.)
	 *   
	 * - <? extends T> : 와일드 카드의 상한 제한, T와 그 자손들만 가능		ex) <? extends Number> : 상한을 Number로 설정, Number와 그 자손들만 사용할 수 있음
	 * - <? super T>   : 와일드 카드의 하한 제한, T와 그 조상들만 가능		ex) <? super Number> : 하한을 Number로 설정, Number와 그 조상들만 사용할 수 있음
	 * - <?>           : 모든 타입 가능, <? extends Object>와 동일
	 * 
	 * - 어떤 타입인지 모른다는 의미의 하나의 타입임
	 * - 제너릭한 타입을 표현하면서 모른다는 의미를 표현하기 위해 와일드 카드라는 개념이 등장함
	 */
	public static void main(String[] args) {
//		List<?> myList = new ArrayList<String>();	//List가 제너릭 인터페이스라서 와일드카드를 사용해서 타입을 아직 모른다는 표시를 할 수 있음		
		FruitBox<Fruit> fruitBox = new FruitBox();	//과일상자
		FruitBox<Apple> appleBox = new FruitBox();	//사과상자
		
		FruitBox<Integer> integerBox = new FruitBox();
		
		fruitBox.add(new Apple());
		fruitBox.add(new Grape());
		
		appleBox.add(new Apple());
		appleBox.add(new Apple());
//		appleBox.add(new Grape());	//잘못된 타입이라서 컴파일 에러가 발생함
		
		Juicer.makeJuice(fruitBox);	//잘 작동함
		Juicer.makeJuice(appleBox);	//컴파일 에러 발생
		
	}
}

class Juicer {
//	static <T extends Fruit> void makeJuice(FruitBox<T> box) {
		//제한된 타입 파라미터
		//Juicer에 사과상자도 넣기 위해 <>안에 fruit대신 T로 바꿈
		//반환 타입 앞에 <T>를 삽입함
		//<T extends Fruit> : Fruit를 extends한 타입만 넣을 수 있음
	static void makeJuice(FruitBox<? extends Fruit> box) {
		//와일드 카드 방식
		//지금은 타입을 모르지만 적어도 Fruit를 extends한 타입임
		String fruitListStr = "";	//과일목록
		
		int cnt = 0;
		for(Fruit f : box.getFruitList()) {
			if(cnt == 0) {
				fruitListStr += f;
//				fruitListStr += f.toString();
			}else {
				fruitListStr += ", " + f;
			}	//if
			cnt++;
		}	//for
		System.out.println(fruitListStr + " => 주스완성!!!");
	}	//class
}

class Fruit {
	private String name;	//과일이름

	public Fruit(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "과일(" + name + ")";
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
		fruitList = new ArrayList<>();
	}

	public List<T> getFruitList() {
		return fruitList;
	}
	public void setFruitList(List<T> fruitList) {
		this.fruitList = fruitList;
	}

	public void add(T fruit) {
		fruitList.add(fruit);
	}
}
