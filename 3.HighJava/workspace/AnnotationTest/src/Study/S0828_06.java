package Study;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class S0828_06 {
	
	public static void main(String[] args) throws ClassNotFoundException {
		//클래스 객체 생성
		Class<?> klass = new S0828_02().getClass();
//		Class<?> klass = S0828_02.class;
//		Class<?> klass = Class.forName("Study.S0828_02");
		
		//클래스에 선언된 모든 메서드의 메타데이터 정보 가져오기
		Method[] mAddr = klass.getDeclaredMethods();
		
		for(Method m : mAddr) {
			//해당 메서드의 메서드 정보 가져오기
			System.out.println("메서드명 : " + m.getName());
			System.out.println("메서드 리턴타입 : " + m.getReturnType());
			
			//해당 메서드의 접근제어자 정보 가져오기
			int modFlag = m.getModifiers();
			System.out.println("메서드 접근제어자 : " + Modifier.toString(modFlag));
			
			//해당 메서드의 파라미터 정보 가져오기
			Class<?>[] paramArr = m.getParameterTypes();
			System.out.print("메서드 파라미터 타입 : ");
			for(Class<?> clazz : paramArr) {
				System.out.print(clazz.getName() + " ");				
			}
			System.out.println();
			
			//해당 메서드의 annotation 타입 정보 가져오기
			Annotation[] anoArr = m.getAnnotations();
			System.out.print("어노테이션 타입 : ");
			for(Annotation a : anoArr) {
				System.out.print(a.annotationType().getName());
			}
			System.out.println();
			System.out.println("===============================");
		}
		
	}
}
