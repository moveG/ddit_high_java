package kr.or.ddit.basic;

public class T02_Service {
	
	@T01_PrintAnnotation
	public void method1() {
		System.out.println("메서드1에서 출력되었습니다.");		
	}
	@T01_PrintAnnotation(value = "%")	//value가 기본이라서 value만 값을 줄 때는 ()안에 %만 넣어도 됨
	public void method2() {
		System.out.println("메서드2에서 출력되었습니다.");		
	}
	@T01_PrintAnnotation(value = "#", count = 25)
	public void method3() {
		System.out.println("메서드3에서 출력되었습니다.");		
	}
}
