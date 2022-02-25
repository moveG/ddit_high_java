package Study;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Homework1 {
	/*
	 * 학번, 이름, 국어점수, 영어점수, 수학점수, 총점, 석차를 멤버로 갖는 Student클래스를 만든다.
	 * 이 Student 객체들은 List에 저장하여 관리한다.
	 * 
	 * - List에 저장된 데이터들을 학번의 오름차순으로 정렬하여 출력
	 * - 총점의 역순으로 정렬하여 출력(총점이 같으면 학번의 내림차순으로 정렬)
	 * - 학번의 정렬기준은 Student클래스 자체에서 제공
	 * - 총점의 정렬기준은 외부클래스에서 제공
	 */
	
	public static void main(String[] args) {
		List<Student> studentList = new ArrayList<Student>();
		
		int kor = 0;
		int eng = 0;
		int math = 0;
		int sum = 0;
		
		studentList.add(new Student(1, "류인성", kor, eng, math));
		studentList.add(new Student(7, "이미정", kor, eng, math));
		studentList.add(new Student(2, "석기현", kor, eng, math));
		studentList.add(new Student(6, "이동근", kor, eng, math));
		studentList.add(new Student(4, "조명석", kor, eng, math));
		studentList.add(new Student(5, "이광효", kor, eng, math));
		studentList.add(new Student(3, "양기욱", kor, eng, math));
		
		for(int i = 0; i < studentList.size(); i++){
			kor = (int)(Math.random() * 101);
			eng = (int)(Math.random() * 101);
			math = (int)(Math.random() * 101);
			
			studentList.get(i).setKor(kor);
			studentList.get(i).setEng(eng);
			studentList.get(i).setMath(math);
		}
		
		for(int i = 0; i < studentList.size(); i++){
			sum = studentList.get(i).getKor()
					+ studentList.get(i).getEng()
					+ studentList.get(i).getMath();
			studentList.get(i).setSum(sum);
		}
		
		for(int i = 0; i < studentList.size(); i++){
			for(int j = 0; j < studentList.size(); j++){
				if(studentList.get(i).getSum() < studentList.get(j).getSum()){
					studentList.get(i).setRank(studentList.get(i).getRank() + 1);
				}
			}
		}
		
		System.out.println("정렬 전");
		for(Student std : studentList){
			System.out.println(std);
		}
		System.out.println("======================================");
		
		Collections.sort(studentList);
		
		System.out.println("정렬 후");
		for(Student std : studentList){
			System.out.println(std);
		}
		System.out.println("======================================");
		
		Collections.sort(studentList, new Desc1());
		
		System.out.println("정렬 후");
		for(Student std : studentList){
			System.out.println(std);
		}
		System.out.println("======================================");
	}
}

class Student implements Comparable<Student>{
	private int no;			//학번
	private String name;	//이름
	private int kor;		//국어
	private int eng;		//영어
	private int math;		//수학
	private int sum;		//합계
	private int rank = 1;	//석차
	
	public Student(int no, String name, int kor, int eng, int math){
		super();
		this.no = no;
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.math = math;
	}
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
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
		return ((Integer)this.getNo()).compareTo(std.getNo());
	}
	@Override
	public String toString(){
		return "Student [학번: " + no + ", 이름: " + name + ",국어: " + kor + ",영어: " + eng + ",수학: " + math + 
					   ",합계: " + sum + ",석차: " + rank + "]";
	}
}

class Desc1 implements Comparator<Student> {
	@Override
	public int compare(Student std1, Student std2){
		if(((Integer)std1.getSum()).compareTo(std2.getSum()) != 0){
			return -((Integer)std1.getSum()).compareTo(std2.getSum());
		}else{
			return -((Integer)std1.getNo()).compareTo(std2.getNo());
		}
	}
}