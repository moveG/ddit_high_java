package kr.or.ddit.basic;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class T03_AnnotationTest {
	
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		System.out.println("static 변수값 : " + T01_PrintAnnotation.id);
		System.out.println("============================");
		
		//reflection 기능을 이용한 메서드 실행하기
		//선언된 메서드 목록 가져오기
		Method[] declareMethods = T02_Service.class.getDeclaredMethods();
		
		for(Method m : declareMethods) {	//getMethod는 다 가져옴
			System.out.println(m.getName());	//메서드명 출력
			
			//선언된 T01_PrintAnnotation 객체 정보 가져오기
			T01_PrintAnnotation printAnn = m.getDeclaredAnnotation(T01_PrintAnnotation.class);
			
			//count수 만큼 value값 출력하기
			for(int i = 0; i < printAnn.count(); i++) {
				System.out.print(printAnn.value());
			}
			System.out.println();	//줄바꿈 처리
			
			//reflection 기능을 이용한 메서드 실행하기
			//m.invoke(new T02_Service());
			
			//reflection을 이용한 객체 생성
			Class<?> clazz = T02_Service.class;
			T02_Service service = (T02_Service)clazz.newInstance();
			
			m.invoke(service);
		}
	}
}
