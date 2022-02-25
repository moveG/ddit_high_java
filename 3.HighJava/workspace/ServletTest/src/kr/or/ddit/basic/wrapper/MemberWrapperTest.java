package kr.or.ddit.basic.wrapper;

public class MemberWrapperTest {

	public static void main(String[] args) {
		//기본객체 생성하기(익명 클래스를 이용한 생성방법)
		IMember member = new IMember() {
			@Override
			public String getName() {
				return "석기현";
			}
			
			@Override
			public String getCompany() {
				return "대덕인재개발원";
			}
		};
		System.out.println("안녕하세요. 제 이름은 " + member.getName() + "이고, " 
						+ member.getCompany() + "에 소속되어 있습니다.");
		
		//래퍼 클래스를 이용한 객체 생성하기
		member = new MemberWrapper(member);
		
		System.out.println("안녕하세요. 제 이름은 " + member.getName() + "이고, " 
				+ member.getCompany() + "에 소속되어 있습니다.");
		
		//기본 래퍼 클래스에 기능확장한 래퍼 클래스를 이용한 객체 생성하기
		member = new MyMemberWrapper(member, true);
		
		System.out.println("안녕하세요. 제 이름은 " + member.getName() + "이고, " 
				+ member.getCompany() + "에 소속되어 있습니다.");
	}
}
