package Study;

import kr.or.ddit.basic.T03_PlanetTest.Planet;

public class Homework4 {
	/*
	 * 문제 : 태양계 행성을 나타내는 enum Planet을 이용하여 행성의 표면적을 구하시오.
	 * 		(단, enum 객체 생성시 반지름을 이용하도록 정의하시오.)
	 * 
	 * ex)행성의 반지름
	 * - 수성 : 2439
	 * - 금성 : 6052
	 * - 지구 : 6371
	 * - 화성 : 3390
	 * - 목성 : 69911
	 * - 토성 : 58232
	 * - 천왕성 : 25362
	 * - 해왕성 : 24622
	 */
	public enum Planet {
		수성(2439), 금성(6052), 지구(6371), 화성(3390),
		 목성(69911), 토성(58232), 천왕성(25362), 해왕성(24622);
		private int km;
		Planet(int km) {
			this.km = km;
		}
		public int getKm() {
			return km;
		}
	}
	
	public static void main(String[] args) {
		Planet[] pArr = Planet.values();
		double pi = 3.141592;
		
		for(int i = 0; i < pArr.length; i++) {
			System.out.println(pArr[i].name() + "의 반지름 : "
				+ pArr[i].getKm() + "km");
			System.out.println(pArr[i] + "의 표면적 : "
				+ Math.round((double)pArr[i].getKm() * pArr[i].getKm() * 4 * pi) + "km²");
			System.out.println("===================================");
		}
		
//		for(Planet p : pArr) {
//			System.out.println(p + "의 반지름 : " + p.getKm() + "km");
//			System.out.println(p + "의 표면적 : " + Math.round((double)p.getKm() * p.getKm() * 4 * pi) + "km²");
//			System.out.println("===================================");
//		}
		
//		for(Planet p : pArr) {
//			System.out.println(p + "의 반지름 : "
//				+ String.format("%,d", p.getKm()) + "km");
//			System.out.println(p.name() + "의 표면적 : "
//				+  String.format("%,.0f", (double)p.getKm() * p.getKm() * 4 * pi) + "km²");
//			System.out.println("===================================");
//		}
	}
}
