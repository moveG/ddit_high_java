package Study;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class S0828_03 {
	
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		System.out.println("static 변수값 : " + S0828_01.id);
		System.out.println("============================");
		
		//reflection 기능을 이용한 메서드 샐힝
		//선언된 메서드 목록 가져오기
		Method[] dm = S0828_02.class.getDeclaredMethods();	//getMethod는 전부 가져옴
		for(Method m : dm) {
			System.out.println(m.getName());	//메서드명 출력
			
			//선언된 S0828_01 클래스의 객체정보 가져오기
			S0828_01 pAnn = m.getDeclaredAnnotation(S0828_01.class);
			
			//count수 만큼 value값 출력하기
			for(int i = 0; i < pAnn.count(); i++) {
				System.out.print(pAnn.value());
			}
			System.out.println();
			
			//reflection 기능을 이용한 메서드 실행
//			m.invoke(new S0828_02());
			
			//reflection을 이용한 객체 생성
			Class<?> clazz = S0828_02.class;
			S0828_02 s = (S0828_02)clazz.newInstance();
			m.invoke(s);
			System.out.println("============================");
		}
		
	}
}
