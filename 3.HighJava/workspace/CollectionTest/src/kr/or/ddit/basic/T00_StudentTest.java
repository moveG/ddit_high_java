package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class T00_StudentTest {
	/*
	 * 학번(String), 이름(String), 국어점수(int), 영어점수(int), 수학점수(int), 총점(int), 석차(int)를 멤버로 갖는 Student클래스를 만든다.
	 * - 생성자는 학번, 이름, 국어점수, 영어점수, 수학점수만 매개변수로 받아서 처리한다.
	 * - 이 Student 객체들은 List에 저장하여 관리한다.
	 * - 학번의 정렬기준은 Student클래스 자체에서 제공
	 * - 총점의 정렬기준은 외부클래스에서 제공
	 * 0) 데이터는 직접 입력, 총점이 동일한 사람 구현
	 * 1) 학번 : 오름차순으로 정렬, Comparable
	 * 2) 총점 : 내림차순으로 정렬, Comparator, 동일점수는 학번의 내림차순으로 정렬
	 */
	public static void main(String[] args) {
		List<Student> studentList = new ArrayList<Student>();
		
		studentList.add(new Student("001", "류인성", 100, 100, 100));
		studentList.add(new Student("007", "이미정", 90, 90, 90));
		studentList.add(new Student("002", "석기현", 90, 90, 90));
		studentList.add(new Student("006", "이동근", 90, 90, 90));
		studentList.add(new Student("003", "조명석", 70, 70, 70));
		studentList.add(new Student("005", "이광효", 60, 60, 60));
		studentList.add(new Student("004", "양기욱", 50, 50, 50));
		
		for(int i = 0; i < studentList.size(); i++) {
			studentList.get(i).setSum(studentList.get(i).getKor() + studentList.get(i).getEng() + studentList.get(i).getMath());
		}
		
		for(int i = 0; i < studentList.size(); i++) {
			int rank = 1;
			for(int j = 0; j < studentList.size(); j++) {
				if(studentList.get(i).getSum() < studentList.get(j).getSum()) {
					rank++;
//					studentList.get(i).setRank(++rank);
				}
				studentList.get(i).setRank(rank);
			}
		}
		
		System.out.println("정렬 전");
		for(Student std : studentList) {
			System.out.println(std);
		}
		System.out.println("======================================");
		
		Collections.sort(studentList);
		System.out.println("학번으로 오름차순 정렬 후");
		for(Student std : studentList) {
			System.out.println(std);
		}
		System.out.println("======================================");
		
		Collections.sort(studentList, new Asc1());
		System.out.println("총점으로 내림차순 정렬 후");
		for(Student std : studentList) {
			System.out.println(std);
		}
		System.out.println("======================================");
		
	}
}

class Student implements Comparable<Student> {
	private String no;		//학번
	private String name;	//이름
	private int kor;		//국어점수
	private int eng;		//영어점수
	private int math;		//수학점수
	private int sum;		//합계점수
	private int rank;		//석차
	
	public Student(String no, String name, int kor, int eng, int math) {
		super();
		this.no = no;
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.math = math;
	}

	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getKor() {
		return kor;
	}
	public void setKor(int kor) {
		this.kor = kor;
	}
	public int getEng() {
		return eng;
	}
	public void setEng(int eng) {
		this.eng = eng;
	}
	public int getMath() {
		return math;
	}
	public void setMath(int math) {
		this.math = math;
	}
	public int getSum() {
		return sum;
	}
	public void setSum(int sum) {
		this.sum = sum;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}

	@Override
	public int compareTo(Student std) {
		return getNo().compareTo(std.getNo());
	}
	
	public String toString() {
		return "Student [학번: " + no + ", 이름: " + name + ", 국어: " + kor + ", 영어: " + eng + ", 수학: " + math + 
				   ", 합계: " + sum + ", 석차: " + rank + "]";
	}
}

class Asc1 implements Comparator<Student> {
	@Override
	public int compare(Student std1, Student std2) {
		if(std1.getSum() > std2.getSum()) {
			return -1;
		} else if(std1.getSum() == std2.getSum()) {
			return std1.getNo().compareTo(std2.getNo()) * -1;
		} else {
			return 1;
		}
	}
}
