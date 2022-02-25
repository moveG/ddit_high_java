package kr.or.ddit.reflection;

/*
 * Java Reflection
 * 
 * 1. 리플렉션은 런타임 시점에 클래스 또는 멤버변수, 메서드, 생성자에 대한 정보를 가져오거나 수정할 수 있고,
 *    새로운 객체를 생성하거나, 메서드를 실행할 수 있다.
 * 
 * 2. Reflection API는 java.lang.reflect 패키지와 java.lang.Class를 통해 제공된다.
 * 
 * 3. java.lang.Class의 주요 메서드
 *    - getName(), getSuperclass(), getInterface(), getModifiers()
 * 
 * 4. java.lang.reflect 패키지의 주요 클래스
 *    - Field, Method, Constructor, Modifier 등
 * 
 * 5. Reflection API의 기능은 뛰어나지만 약간의 오버헤드가 발생한다.
 *    (느린 수행속도, 보안 취약성, 권한문제 등)
 *    그러므로 가급적 마지막 수단으로 사용하도록 고려되어야 한다.
 */
public class T01_ClassObjectCreationExam {
	
	public static void main(String[] args) throws ClassNotFoundException {
		//첫번째 방법 : Class.forName() 사용
		//Class라는 이름의 클래스가 존재함, 그 밑의 forName()이라는 메서드를 이용함
		Class<?> klass = Class.forName("kr.or.ddit.reflection.T01_ClassObjectCreationExam");
		//아직 어떤 타입의 클래스인지 모르므로 와일드카드를 사용함
		//클래스에 대한 정보를 담을 때는 klass 또는 clazz라는 변수명을 많이 사용함
		
		//두번째 방법 : getClass() 사용
		klass = new T01_ClassObjectCreationExam().getClass();
		//getClass() : 해당 객체의 클래스 정보를 전달하는 메서드
		
		//세번째 방법: .class 사용
		klass = T01_ClassObjectCreationExam.class;
	}
}
