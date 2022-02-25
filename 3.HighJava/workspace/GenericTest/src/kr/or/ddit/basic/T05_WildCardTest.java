package kr.or.ddit.basic;

import java.util.Arrays;

public class T05_WildCardTest {
	
	public static void main(String[] args) {
		Course<Person> personCourse = new Course("일반인과정", 5);
		personCourse.add(new Person("일반인1"));
		personCourse.add(new Worker("직장인1"));
		personCourse.add(new Student("학생1"));
		personCourse.add(new HighStudent("고등학생1"));
		
		Course<Worker> workerCourse = new Course("직장인과정", 5);
//		workerCourse.add(new Person("일반인1"));	//타입제한을 Worker로 했기 때문에 일반인(Person)은 올 수 없음
		workerCourse.add(new Worker("직장인1"));
		
		Course<Student> studentCourse = new Course("학생과정", 5);
		studentCourse.add(new Student("학생1"));
		studentCourse.add(new HighStudent("고등학생1"));
//		studentCourse.add(new Person("일반인1"));		//컴파일 에러 발생
		
		Course<HighStudent> highStudentCourse = new Course("고등학생과정", 5);
		highStudentCourse.add(new HighStudent("고등학생1"));
//		highStudentCourse.add(new Student("고등학생1"));	//컴파일 에러 발생
		System.out.println("=============================");
		//모든 강좌 수강정보 조회
		listCourseInfo(personCourse);
		listCourseInfo(workerCourse);
		listCourseInfo(studentCourse);
		listCourseInfo(highStudentCourse);
		System.out.println("=============================");
		//학생 강좌 수강정보 조회
		listStudentCourseInfo(studentCourse);
		listStudentCourseInfo(highStudentCourse);
//		listStudentCourseInfo(workerCourse);
		System.out.println("=============================");
		//직장인 강좌 수강정보 조회
		listWorkerCourseInfo(workerCourse);
		System.out.println("=============================");
		//고등학생 강좌 수강정보 조회(super 사용 - 고등학생강좌 상위(학생강좌, 일반인강좌)도 조회 가능)
		listHighStudentCourseInfo(highStudentCourse);
		listHighStudentCourseInfo(studentCourse);
		listHighStudentCourseInfo(personCourse);
	}
	
	/**
	 * 모든 강좌 수강정보 조회
	 * @param course
	 */
	public static void listCourseInfo(Course<?> course) {
		System.out.println("강좌명 : " + course.getName() + ", 수강생 : " + Arrays.toString(course.getStudents()));
	}
	
	/**
	 * 학생 강좌 수강정보 조회
	 * @param course
	 */
	public static void listStudentCourseInfo(Course<? extends Student> course) {
		System.out.println("강좌명 : " + course.getName() + ", 수강생 : " + Arrays.toString(course.getStudents()));
	}
	
	/**
	 * 직장인 강좌 수강정보 조회
	 * @param course
	 */
	public static void listWorkerCourseInfo(Course<? extends Worker> course) {
		System.out.println("강좌명 : " + course.getName() + ", 수강생 : " + Arrays.toString(course.getStudents()));
	}
	
	/**
	 * 고등학생 강좌 수강정보 조회(super 사용 - 고등학생강좌 상위(학생강좌, 일반인강좌)도 조회 가능)
	 * @param course
	 */
	public static void listHighStudentCourseInfo(Course<? super HighStudent> course) {
		System.out.println("강좌명 : " + course.getName() + ", 수강생 : " + Arrays.toString(course.getStudents()));
	}
}

//일반인
class Person {
	String name;	//이름

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
	private String name;	//과정명
	private T[] students;	//수강생(제너릭 배열)
	
	public Course(String name, int capacity) {
		this.name = name;
		//타입 파라미터로 배열을 생성시 오브젝트 배열을 생성 후, 타입 파라미터 배열로 캐스팅 처리를 해야함
		students = (T[])(new Object[capacity]);
	}
	
	/**
	 * 과정명 조회
	 * @return
	 */
	public String getName() {
		return name;
	}
	/**
	 * 수강생 조회
	 * @return
	 */
	public T[] getStudents() {
		return students;
	}
	
	/**
	 * 수강생 등록
	 * @param t
	 */
	public void add(T t) {
		for(int i = 0; i < students.length; i++) {
			if(students[i] == null) {	//아직 등록되지 않은(빈) 자리
				students[i] = t;	//등록
				break;
			}
		}
	}
}
