package Study;

import java.util.Arrays;

public class S0826_05 {
	
	public static void main(String[] args) {
		Course<Person> pc = new Course("일반인과정", 5);
		pc.addStd(new Person("일반인1"));
		pc.addStd(new Worker("직장인1"));
		pc.addStd(new Student("학생1"));
		pc.addStd(new HighStudent("고등학생1"));
		
		Course<Worker> wc = new Course("직장인과정", 5);
//		wc.addStd(new Person("일반인2"));
//		타입제한이 Worker라서 Person은 넣을 수 없음
		wc.addStd(new Worker("직장인2"));
		
		Course<Student> sc = new Course("학생과정", 5);
		sc.addStd(new Student("학생3"));
		sc.addStd(new HighStudent("고등학생3"));
//		sc.addStd(new Person("일반인3"));
		
		Course<HighStudent> hsc = new Course("고등학생과정", 5);
		hsc.addStd(new HighStudent("고등학생4"));
//		hsc.addStd(new Student("학생4"));
		System.out.println("================================");
		
		acInfo(pc);
		acInfo(wc);
		acInfo(sc);
		acInfo(hsc);
		System.out.println("================================");
//		scInfo(pc);
//		scInfo(wc);
		scInfo(sc);
		scInfo(hsc);
		System.out.println("================================");
//		wcInfo(pc);
		wcInfo(wc);
//		wcInfo(sc);
//		wcInfo(hsc);
		System.out.println("================================");
//		hcInfo(pc);
//		hcInfo(wc);
//		hcInfo(sc);
		hcInfo(hsc);
		System.out.println("================================");
	}

//일반인강좌 수강정보 조회
	public static void acInfo(Course<?> course) {
		System.out.println("강좌명 : " + course.getName() + ", 수강생 : " + Arrays.toString(course.getStudents()));
	}
//학생강좌 수강정보 조회
	public static void scInfo(Course<? extends Student> course) {
		System.out.println("강좌명 : " + course.getName() + ", 수강생 : " + Arrays.toString(course.getStudents()));
	}
//직장인강좌 수강정보 조회
	public static void wcInfo(Course<? extends Worker> course) {
		System.out.println("강좌명 : " + course.getName() + ", 수강생 : " + Arrays.toString(course.getStudents()));
	}
//고등학생강좌 수강정보 조회	
	public static void hcInfo(Course<? extends HighStudent> course) {
		System.out.println("강좌명 : " + course.getName() + ", 수강생 : " + Arrays.toString(course.getStudents()));
	}
}

//일반인
class Person {
	String name; //이름
	public Person(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "이름 : " + name;
	}
}
//근로자
class Worker extends Person {
	public Worker(String name) {
		super(name);
	}
}
//학생
class Student extends Person {
	public Student(String name) {
		super(name);
	}
}
//고등학생
class HighStudent extends Student {
	public HighStudent(String name) {
		super(name);
	}
}

//강좌 수강
class Course<T> {
	private String name;	//강좌명
	private T[] students;	//수강생 배열(제너릭 배열)
	
	public Course(String name, int capacity) {
		this.name = name;
		students = (T[])(new Object[capacity]);
	}
//강좌명 조회
	public String getName() {
		return name;
	}
//수강생 조회
	public T[] getStudents() {
		return students;
	}
//수강생 등록
	public void addStd(T tt) {
		for(int i = 0; i < students.length; i++) {
			if(students[i] == null) {	//미등록 자리(빈자리)
				students[i] = tt;	//등록
				break;
			}
		}
	}
}
