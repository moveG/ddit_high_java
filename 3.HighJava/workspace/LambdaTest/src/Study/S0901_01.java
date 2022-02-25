package Study;

public class S0901_01 {
	/*
	 * 람다식
	 * - 익명함수를 생성하기 위한 식
	 * - 자바에서는 '매개변수를 가진 코드블럭' => 런타임시 익명구현객체로 생성된다.
	 * 
	 * 형식) 인터페이스명 객체변수명 = 람다식;
	 * 
	 * 람다식의 형식) (매개변수들...) -> {처리할 코드들; ...}
	 * 
	 * - 람다식으로 변환할 수 있는 인터페이스는 추상메서드가 1개인 인터페이스만 가능하다.
	 * - 이런 인터페이스를 '함수적 인터페이스'라고 한다.
	 * - 이 함수적 인터페이스를 만들 때는 @FunctionalInterface(어노테이션)로 지정한다.
	 * 
	 * - 람다식을 사용하면 소스가 짧아짐(불필요한 부분을 생략할 수 있음, 가독성이 좋아짐(고민해야할 소스의 수가 적어짐))
	 */
	public static void main(String[] args) {
		//람다식을 사용하지 않는 경우
		Thread th1 = new Thread(new Runnable() {
			//Runnable : 함수적 인터페이스(람다식으로 변환 가능)
			public void run() {
				for(int i = 1; i <= 10; i++) {
					System.out.println(i);
				}
			}
		});
		th1.start();		
				
		//람다식을 사용하는 경우
		Thread th2 = new Thread(
			() -> {
				for(int i = 1; i <= 10; i++) {
					System.out.println("람다쥐썬더 - " + i);
				}
			}
		);
		th2.start();		
		//람다식은 실행 전에 컴파일러가 람다식에서 위의 익명블록 형태로 되돌린다음 실행시킴
		//람다식은 개발자의 가독성을 위한 형식임		
	}
}
