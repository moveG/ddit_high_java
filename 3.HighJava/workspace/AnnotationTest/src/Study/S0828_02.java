package Study;

public class S0828_02 {
	@S0828_01
	public void method1() {
		System.out.println("메서드111");
	}
	@S0828_01(value = "*")
	public void method2() {
		System.out.println("메서드222");
	}
	@S0828_01(value = "+", count = 55)
	public void method3() {
		System.out.println("메서드333");
	}
}
