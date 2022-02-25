package Study;

@FunctionalInterface
public interface LambdaTestInterface1 {
	/*
	 * 반환값이 없고 매개변수도 없는 추상메서드 선언
	 */
	public void test();
}

@FunctionalInterface
interface LambdaTestInterface2 {
	/*
	 * 반환값이 없고 매개변수는 있는 추상메서드 선언
	 */
	public void test(int a);
}

@FunctionalInterface
interface LambdaTestInterface3 {
	/*
	 * 반환값이 있고 매개변수도 있는 추상메서드 선언
	 */
	public int test(int a, int b);
//	public int test2(int a, int b);
	//어노테이션으로 함수적 인터페이스라 정의했으므로 추상메서드를 하나 더 추가하면 오류가 발생함
}
