package Study;

/*
 * Java Reflection
 * 1. 리플렉션은 런타임 시점에 클래스 또는 멤버변수, 메서드, 생성자에 대한 정보를 가져오거나 수정할 수 있고,
 *    새로운 객체를 생성하거나 메서드를 실행할 수 있음
 *    
 * 2. Reflection API는 java.lang.reflect 패키지와 java.lang.Class를 통해 제공됨
 * 
 * 3. java.lang.Class의 주요 메서드
 *    - getName(), getSuperclass(), getInterface(), getModifiers()
 * 
 * 4. java.lang.reflect 패키지의 주요 클래스
 *    - Field, Method, Constructor, Mdofier 등
 * 
 * 5. Reflection API의 기능은 뛰어나지만 약간의 오버헤드가 발생함
 *    (느린 수행속도, 보안 취약성, 권한 문제 등)
 *    그러므로 가급적으로 마지막 수단으로 사용하도록 고려되어야함   
 */
public class S0828_04 {

	public static void main(String[] args) throws ClassNotFoundException {
		//방법1: Class.forName() 사용
		//      Class라는 이름의 클래스의 forName()메서드 사용
		Class<?> klass = Class.forName("Study.S0828_04");
		//아직 클래스의 타입을 모르므로 와일드 카드를 사용함
		//클래스에 대한 정보를 담을 때는 klass 또는 Clazz라는 변수명을 사용함
		
		//방법2: getClass() 사용
		klass = new S0828_04().getClass();
		
		//방법3: .class 사용
		klass = S0828_04.class;
	}
}
