package kr.or.ddit.reflection;

import kr.or.ddit.basic.T02_Service;

public class T02_ClassMetadataExam {

		public static void main(String[] args) {
			//클래스 오브젝트 생성하기
			Class<?> clazz = T02_Service.class;
			
			System.out.println("심플 클래스명 : " + clazz.getSimpleName());
			System.out.println("클래스명        : " + clazz.getName());
			System.out.println("상위 클래스명 : " + clazz.getSuperclass().getName());
			
			//패키지 정보 가져오기
			Package pkg = clazz.getPackage();
			System.out.println("패키지 정보    : " + pkg.getName());
			
		}
}
