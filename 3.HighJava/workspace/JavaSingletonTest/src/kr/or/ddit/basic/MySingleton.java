package kr.or.ddit.basic;

/*
 * Singleton 패턴 : 객체(인스턴스)를 하나만 만들어지도록 제한하는 프로그래밍 기법
 * 
 * - Singleton Class를 구성하는 방법
 * 1. 자기 자신의 class의 참조변수를 멤버변수로 선언한다.
 *    (이 변수는 접근제어자를 private static으로 지정한다.)
 *    
 * 2. 생성자를 private로 지정한다.
 *    (외부에서 생성자에 접근하지 못하게 하기 위해서(외부에서 new 명령을 통해 사용하지 못하게 하기 위해서)
 *     
 * 3. 객체(인스턴스)는 내부에서 생성해서 이 생성된 객체를 반환하는 메서드를 만든다.
 *    (이 메서드의 이름은 보통 getInstance()로 지정한다. 이 메서드는 static으로 지정한다.)
 */
public class MySingleton {
	//자기 자신의 class의 참조값을 지정하는 멤버변수 선언
	private static MySingleton single;	
	
	//생성자를 private로 지정한다.
	private MySingleton() {	//외부에서 new 명령을 통해 사용하지 못하게 하기 위해서
		System.out.println("생성자입니다.");
	}
	
	public static MySingleton getInstance() {	//static 메서드 : 객체 생성 없이 호출, 접근할 수 있는 특징을 가진 메서드
		if(single == null) {
			single = new MySingleton();
		}
		return single;
	}
	
	//나머지 내용들은 이 클래스로 처리할 내용들을 기술한다.
	public void displayText() {
		System.out.println("안녕하세요, 싱글톤 객체입니다.");
	}
}
