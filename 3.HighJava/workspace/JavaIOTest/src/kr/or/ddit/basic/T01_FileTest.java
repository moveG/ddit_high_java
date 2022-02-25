package kr.or.ddit.basic;

import java.io.File;
import java.io.IOException;

public class T01_FileTest {
	/*
	 * File객체 만들기 연습
	 * 
	 * 1. new File(String 파일또는경로명)
	 *    => 디렉토리와 디렉토리 사이 또는 디렉토리와 파일명 사이의 구분문자는 '\'를 사용하거나 '/'를 사용할 수 있다.
	 * 
	 * 2. new File(File parent, String child)
	 *    => 'parent'디렉토리 안에 있는 'child'파일 또는 디렉토리
	 * 
	 * 3. new File(String parent, String child)
	 * 
	 * 디렉토리(폴더) 만들기 연습
	 * 1. mkdir() : File객체의 경로 중 마지막 위치의 디렉토리를 만든다.
	 *              중간의 경로가 모두 미리 만들어져 있어야 한다.
	 *              만들기를 성공하면 true, 실패하면 false를 반환한다.
	 * 
	 * 2. mkdirs() : 중간에 경로가 없으면 중간의 경로도 새롭게 만든 후, 마지막 위치의 디렉토리를 만들어준다.
	 *               만들기를 성공하면 true, 실패하면 false를 반환한다.
	 */
	public static void main(String[] args) throws IOException {
		//1. new File(String 파일또는경로명)
		File file = new File("d:/D_Other/test.txt");
		System.out.println("파일명 : " + file.getName());
		//getName() : 파일이름 추출
		System.out.println("파일 여부 : " + file.isFile());
		//isFile() : 파일인지 확인해서 true와 false를 반환
		System.out.println("디렉토리(폴더) 여부 : " + file.isDirectory());
		System.out.println("===============================");
		//isDirectory() : 폴더인지 확인해서 true와 false를 반환
		File file2 = new File("d://D_Other");
		System.out.print(file2.getName() + "은 ");
		if(file2.isFile()) {
			System.out.println("파일");
		}else if(file2.isDirectory()) {
			System.out.println("디렉토리(폴더)");
		}
		//d드라이브의 D_Other이 파일인지 폴더인지 확인
		System.out.println("===============================");
		
		//2. new File(File parent, String child)
		File file3 = new File(file2, "test.txt");
		//file2는 디렉토리("d://D_Other")를 가지고 있다.
		//D_Other폴더 아래의 test.txt파일을 file3에 넣음
		System.out.println(file3.getName() + "의 용량 크기 : " + file3.length() + "bytes");
		//파일.length() : 파일의 크기를 bytes타입으로 반환
		
		//3. new File(String parent, String child)
		File file4 = new File("d:/D_Other/test/..", "text.txt");
		System.out.println("절대 경로 : " + file4.getAbsolutePath());
		System.out.println("경로        : " + file4.getPath());
		System.out.println("표준 경로 : " + file4.getCanonicalPath());
		//getAbsolutePath()  : 절대경로를 가져옴
		//getPath()          : 경로를 가져옴
		//getCanonicalPath() : 표준경로를 가져옴
		//'..' : 부모폴더
		// '.' : 현재폴더
		//절대경로 : 나의 위치와는 상관없는 절대적인 위치(경로)
		//상대경로 : 나의 위치를 기준으로하는 상대적인 위치(경로)
		//표준경로 : 절대경로를 가져오지만, '.', '..'같은 경로를 정리해서 표현한 위치(경로)
		System.out.println("===============================");
		
		//1. mkdir()
		// - File객체의 경로 중 마지막 위치의 디렉토리를 만든다.
		// - 중간의 경로가 모두 미리 만들어져 있어야 한다.
		// - 만들기를 성공하면 true, 실패하면 false를 반환한다.
		File file5 = new File("d:/D_Other/연습용");
		if(file5.mkdir()) {
			System.out.println(file5.getName() + " 만들기 성공!");
		}else {
			System.out.println(file5.getName() + " 만들기 실패!");
		}
		System.out.println();
		//중간경로가 모두 존재하므로 폴더 생성에 성공함
		//이미 동일한 이름의 폴더가 존재하면 폴더를 생성하지 않고 false를 반환함
		
		//2. mkdirs()
		// - 중간에 경로가 없으면 중간의 경로도 새롭게 만든 후, 마지막 위치의 디렉토리를 만들어준다.
		// - 만들기를 성공하면 true, 실패하면 false를 반환한다.
		File file6 = new File("d:/D_Other/test/java/src");
//		if(file6.mkdir()) {
		if(file6.mkdirs()) {
			System.out.println(file6.getName() + "만들기 성공!");
		}else {
			System.out.println(file6.getName() + "만들기 실패!");
		}
		//mkdir()을 사용하면 중간경로(test/java/)가 없으므로 폴더 생성에 실패함
		//mkdirs()를 사용하면 중간경로도 만들어서 폴더를 생성할 수 있음
		System.out.println("===============================");
	}
}
