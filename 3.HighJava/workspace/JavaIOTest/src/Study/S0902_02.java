package Study;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class S0902_02 {
	
	public static void main(String[] args) {
		//File f1 = new File("d:\\D_Other" + File.separator + "Sample.txt");
		//'/'대신 '\'도 사용할 수 있음
		//자바에서는 '\'는 탈출문자이므로 폴더경로로 사용하려면 '\\'방식으로 사용해야함
		//File.separator을 쓰면, 사용OS에 따라 '/'와 '\'를 구분해서 적절하게 넣어줌
		//귀찮으면 그냥 '/'를 넣어주면 된다.
//		File f1 = new File("d:\\EE" + File.separator + "Sample.txt");
		File f2 = new File("d:/EE/Sample.txt");
		File f3 = new File("d:/EE/test.txt");
		
		if(f2.exists()) {
			//exists() : 존재여부를 확인해서 존재하면 true, 존재하지 않으면 false을 반환함
			System.out.println(f2.getAbsolutePath() + "은 존재합니다.");
		}else {
			System.out.println(f2.getAbsolutePath() + "은 없는 파일입니다.");
			
			try {
				if(f2.createNewFile()) {
					System.out.println(f2.getAbsolutePath() + "파일을 새로 만들었습니다.");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		if(f3.exists()) {
			System.out.println(f3.getAbsolutePath() + "은 존재합니다.");
		}else {
			System.out.println(f3.getAbsolutePath() + "은 없는 파일입니다.");
		}
		System.out.println("========================================================");
		
		
		File f4 = new File("d:/EE");
		File[] files = f4.listFiles();
		//listFiles() : 파일의 목록을 File배열 형태로 반환함
		for(File f : files) {
			System.out.print(f.getName() + " =>");
			if(f.isFile()) {
				System.out.println("파일입니다.");
			}else if(f.isDirectory()) {
				System.out.println("디렉토리입니다.");
			}
		}
		System.out.println("========================================================");
		
		String[] strFiles = f4.list();
		//list() : 파일의 목록을 String배열 형태로 반환함
		for(String str : strFiles) {
			System.out.println(str);
		}
		System.out.println("========================================================");
		
		//출력할 디렉토리 정보를 갖는 File객체 생성
		File f5 = new File("d://EE");
		
		displayFileList(f5);
	}
	
	/**
	 * 지정된 디렉토리(폴더)에 포함된 파일과 디렉토리 목록을 보여주는 메서드
	 * @param dir 지정할 디렉토리
	 */
	private static void displayFileList(File dir) {
		System.out.println("[" + dir.getAbsolutePath() + "] 디렉토리의 내용");
		
		//디렉토리 안의 모든 파일목록을 가져온다. 파일의 목록을 File배열 형태로 반환함
		File[] files = dir.listFiles();
		
		//하위 디렉토리 정보를 저장할 ArrayList를 생성(File배열의 첨자 지정)
		List<Integer> subDirList = new ArrayList<Integer>();
		
		//날짜를 출력하기 위한 형식 지정
		//a : 오전 오후 구분
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd a hh:mm");
		
		for(int i = 0; i < files.length; i++) {
			String attr = "";
			//파일의 속성(읽기, 쓰기, 히든, 디렉토리 구분)
			String size = "";
			//파일의 용량
			
			if(files[i].isDirectory()) {
				attr = "<DIR>";
				subDirList.add(i);	//첨자를 List에 추가함
			}else {
				size = files[i].length() + "";
				//파일의 용량을 확인
				attr = files[i].canRead() ? "R" : " ";
				//읽기 권한이 있나 확인
				attr += files[i].canWrite() ? "W" : " ";
				//쓰기 권한이 있나 확인
				attr += files[i].isHidden() ? "H" : " ";
				//숨김파일인지 확인
			}
			System.out.printf("%s %5s %12s %s \n",
				sdf.format(new Date(files[i].lastModified())), attr, size, files[i].getName());
			//lastModified() : 마지막 수정날짜를 반환
			//%s : 문자열
			//%5s : 5글자의 문자열
			//\n : 줄바꿈
			//우측정렬이 기본임
			//%5s 대신에 %-5s를 넣으면 좌측정렬이 됨
		}
		
		int dirCount = subDirList.size();			//폴더 안의 하위폴더의 숫자
		int fileCount = files.length - dirCount;	//폴더 안의 파일의 숫자
		
		System.out.println(fileCount + "개의 파일, " + dirCount + "개의 디렉토리");
		System.out.println();
		
		for(int i = 0; i < subDirList.size(); i++) {
			//하위폴더의 내용들도 출력하기 위해 현재 메서드를 재귀호출하여 처리한다.
			displayFileList(files[subDirList.get(i)]);
			//subDirList배열에 인덱스가 저장되어 있으므로 files[i]형식이 됨
		}
	}
}
