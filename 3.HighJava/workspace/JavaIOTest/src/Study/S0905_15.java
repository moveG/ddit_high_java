package Study;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class S0905_15 {
	
	public static void main(String[] args) {
		//Member 인스턴스 생성
		Member mem1 = new Member("홍길동", 20, "대전");
		Member mem2 = new Member("일지매", 30, "대전");
		Member mem3 = new Member("이몽룡", 40, "강원");
		Member mem4 = new Member("성춘향", 20, "광주");
		
		try {
			//객체를 파일에 저장하기
			//직렬화
			//object단위로 읽고 씀
			
			//출력용 스트림 객체 생성
			ObjectOutputStream oos = new ObjectOutputStream(
					new BufferedOutputStream(	//버퍼기능을 추가
					new FileOutputStream("d:/EE/memObj.bin")));	//bin : 바이너리 파일(알수 없는 파일)이라는 뜻
			
			//쓰기 작업
			oos.writeObject(mem1);	//직렬화 작업이 진행됨
			oos.writeObject(mem2);
			oos.writeObject(mem3);
			oos.writeObject(mem4);
			
			System.out.println("쓰기 작업 완료...");
			oos.close();
			System.out.println("============================");
			
			//저장된 객체를 읽어와 출력하기
			//역직렬화
			
			//입력용 스트림 객체 생성
			ObjectInputStream ois = new ObjectInputStream(
				new BufferedInputStream(
				new FileInputStream("d:/EE/memObj.bin")));
			
			Object obj = null;
			
			//마지막에 다다르면 EOF Exception이 발생함 (파일을 끝까지 다 읽었다는 의미)
			while((obj = ois.readObject()) != null) {	//역직렬화 작업이 진행됨
				//읽어온 데이터를 원래의 객체형으로 변환 후 사용한다.
				Member mem = (Member)obj;
				
				System.out.println("이름 : " + mem.getName());
				System.out.println("나이 : " + mem.getAge());
				System.out.println("주소 : " + mem.getAddr());
				System.out.println("============================");
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			//e.printStackTrace();
			//e.printStackTrace();	//EOF Exception이 발생한 사실을 출력함, 에러메시지가 보기 싫으면 주석처리하면 됨
			System.out.println("출력 끝...");
		}	
	}
}

/**
 * 회원정보 VO
 */
class Member implements Serializable {
	/*
	 * 자바는 Serializable 인터페이스를 구현한 클래스만 직렬화할 수 있도록 제한하고 있다.
	 * 구현하지 않으면 직렬화 작업시 예외가 발생한다.(NonSerializableException)
	 * 
	 * transient : 직렬화되지 않을 멤버변수에 지정한다.
	 *             (static필드도 직렬화가 되지 않는다.)
	 *             직렬화가 되지 않는 멤버변수는 기본값으로 저장된다.
	 *             (참조변수 : null, 숫자형변수 : 0)
	 *             
	 * - 보내고 싶지 않은 정보에 transient를 붙이면, 직렬화에서 제외가 되고 기본값이 간다.
	 */
	private String name;
//	private transient int age;
	private int age;
	private String addr;
	
	public Member(String name, int age, String addr) {
		super();
		this.name = name;
		this.age = age;
		this.addr = addr;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
}