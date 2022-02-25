package kr.or.ddit.basic;

public class T03_PlanetTest {
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
		
		private int leng;
		
		Planet(int leng) {
			this.leng = leng;
		}
		
		public int getInt() {
			return leng;
		}
	}
	
	public static void main(String[] args) {
		Planet[] planetArr = Planet.values();
		double pi = 3.141592;
		
		System.out.println("===============================");
//		for(int i = 0; i < planetArr.length; i++) {
//			System.out.println(planetArr[i].name() + "의 반지름 : " + String.format("%,d", planetArr[i].getInt()) + " km");
//
//			double answer = 4 * pi * planetArr[i].getInt() * planetArr[i].getInt();
//			
//			System.out.println(planetArr[i].name() + "의 표면적 : " + Math.round((double)planetArr[i].getInt() * planetArr[i].getInt() * pi * 4) + " km²");
//			System.out.println(planetArr[i].name() + "의 표면적 : " + String.format("%,.0f", answer)+ " km²");
//			System.out.println("===============================");
//		}
//		for(Planet p : Planet.values()) {
//			System.out.println(p.name() + "의 표면적 : "
//					+ Math.round((double)p.getInt() * p.getInt() * 4 * pi) + " km²");
//		}
//		System.out.println("===============================");
		for(Planet p : Planet.values()) {
			System.out.println(p.name() + "의 반지름 : "
					+ String.format("%,d", p.getInt()) + " km");
			System.out.println(p.name() + "의 표면적 : "
					+  String.format("%,.0f", 4 * pi * p.getInt() * p.getInt()) + " km²");
			System.out.println("===============================");
		}
	}
}
