package Study;

public class S0828_05 {
	
	public static void main(String[] args) throws ClassNotFoundException {
		//클래스 오브젝트 생성하기
		Class<?> clazz = new S0828_02().getClass();
//		Class<?> clazz = new Study.S0828_02().getClass();
//		Class<?> clazz = S0828_02.class;
//		Class<?> clazz = Study.S0828_02.class;
//		Class<?> clazz = Class.forName("Study.S0828_02");
		
		//클래스 정보 가져오기
		System.out.println("심플 클래스명 : " + clazz.getSimpleName());
		System.out.println("클래스명        : " + clazz.getName());
		System.out.println("상위 클래스명 : " + clazz.getSuperclass().getName());
		
		//패키지 정보 가져오기
		Package pkg = clazz.getPackage();
		System.out.println("패키지 정보    : " + pkg.getName());
	}
}
