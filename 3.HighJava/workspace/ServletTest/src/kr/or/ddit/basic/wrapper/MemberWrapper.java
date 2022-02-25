package kr.or.ddit.basic.wrapper;

public class MemberWrapper implements IMember {
	private IMember member;
	
	public MemberWrapper(IMember member) {
		this.member = member;
	}
	
	@Override
	public String getName() {
		return this.member.getName();
	}

	@Override
	public String getCompany() {
		return this.member.getCompany();
	}
}
