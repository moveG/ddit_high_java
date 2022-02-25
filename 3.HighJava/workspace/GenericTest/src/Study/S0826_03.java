package Study;

/*
 * 제한된타입 파라미터(Bounded Parameter) 예제
 */
public class S0826_03 {
	
	public static void main(String[] args) {
		int result1 = Util2.compare22(10, 20);
		System.out.println(result1);
		
		int result2 = Util2.compare22(3.14, 3);
		System.out.println(result2);
		
//		int result3 = Util2.compare22("c", "java");
//		타입에 제한을 걸어서 Number가 아닌 타입은 컴파일 에러가 발생함
//		타입적으로 안전한 코딩이 가능해짐(제너릭 문법의 목적)
//		extends만 사용가능
	}
}

class Util2 {
	public static <T extends Number> int compare22(T t1, T t2) {
		//T에 숫자(Number)타입만 넣고 싶음(제한하고 싶음)
		//<T>에 Number를 extends해주면 제한할 수 있음
		//Number와 Number자식들만 사용이 가능해짐
		double v1 = t1.doubleValue();
		double v2 = t2.doubleValue();
		
		return Double.compare(v1, v2);
		//.compare(): 앞이 크면 양수, 뒤가 크면 음수, 같으면 0을 반환함
	}
}
