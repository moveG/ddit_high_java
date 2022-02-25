package kr.or.ddit.basic;

/**
 * 제한된 타입파라미터(Bounded Parameter) 예제
 */
public class T03_GenericMethodTest {
	
	public static void main(String[] args) {
		int result1 = Util2.compare(10, 20);
		System.out.println(result1);
		
		int result2 = Util2.compare(3.14, 3);
		System.out.println(result2);
		
//		int result3 = Util2.compare("c", "java");
//		타입에 제한을 걸었기 때문에, Number가 아닌 다른 타입을 사용하면 컴파일 에러가 발생함
//		타입적으로 안전한 코딩이 가능해짐(제너릭 문법의 목적)
//		extends만 사용할 수 있음
		
	}
}

class Util2 {
	public static <T extends Number> int compare(T t1, T t2) {
//		T에 숫자(number)타입만 넣고싶음(제한하고 싶음)
//		<T>에 Number를 extends를 해주면 됨
//		Number와 Number자식들만 사용할 수 있도록 범위가 한정됨
		
		double v1 = t1.doubleValue();
		double v2 = t2.doubleValue();
		
		return Double.compare(v1, v2);
//		.compare() : 앞부분(v1)이 크면 양수, 뒷부분(v2)이 크면 음수, 동일하면 0을 반환함
	}
}
