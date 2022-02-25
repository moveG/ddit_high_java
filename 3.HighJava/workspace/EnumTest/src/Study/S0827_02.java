package Study;

public class S0827_02 {
	/*
	 * 가변형 인수 : 메서드의 매개변수의 개수가 실행될 때마다 다르면 사용한다.
	 * 
	 *  - 가변형 인수는 메서드 안에서 배열로 처리된다.
	 *  - 가변형 인수는 한가지 자료형만 사용할 수 있다.
	 *  
	 *  - 파라미터가 몇개가 들어올지 모를 때 사용함
	 */
	
	//매개변수로 받은 정수들의 합계를 구하는 메서드
	
	//방법 1 : 배열, 가변형 인수가 없었을 때는 배열로 값을 받아서 처리함
	public int sumArr(int[] data) {
		int sum = 0;
		for(int i = 0; i < data.length; i++) {
			sum += data[i];
		}
		return sum;
	}
	
	//방법2 : 가변형 인수
	public int sumArg(int...data) {
		int sum = 0;
		for(int i = 0; i < data.length; i++) {
			sum += data[i];
		}
		return sum;
	}
	
	//가변형 인수와 일반형 인수가 동시에 사용되면 가변형 인수를 뒤에 배치함
	public String sumArg2(String name, int...data) {
		int sum = 0;
		for(int i = 0; i < data.length; i++) {
			sum += data[i];
		}
		return name + "씨 점수 : " + sum;
	}
	
	public static void main(String[] args) {
		S0827_02 at = new S0827_02();
		
		int[] data = {100, 200, 300};
		
		System.out.println(at.sumArr(data));
		System.out.println(at.sumArr(new int[] {100, 200, 300}));
		
		System.out.println(at.sumArg(data));
		System.out.println(at.sumArg(new int[] {100, 200, 300}));
		System.out.println(at.sumArg(100, 200, 300));
		
		System.out.println(at.sumArg2("홍길동", 100, 200, 300));
		System.out.println(at.sumArg2("홍길동", new int[] {100, 200, 300}));
	}
}
