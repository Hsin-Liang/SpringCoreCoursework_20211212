package pers.hsinliangchang.coursework.SpringCoreCoursework_20211212.coursework_1;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CourseworkTest1 {
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext1.xml");
		
		// 回家作業: 請問 mary 的老師有誰 ? 印出 name (請使用Java 8)
		Student mary = ctx.getBean("s2", Student.class);
		System.out.printf("學生 mary:\n%s\n", mary);
		Teacher t1 = ctx.getBean("t1", Teacher.class);
		Teacher t2 = ctx.getBean("t2", Teacher.class);
		Teacher[] teachers = { t1, t2 };
		
		// 從每個老師教授的課程去判斷學生是否有劃課
		// 1. 資料分析
		/*
		Set<Teacher> studentMaryTeachers = Arrays.stream(teachers)
				.filter(t -> t.getClazzs().stream().anyMatch(mary.getClazzs()::contains))
				//.peek(System.out::println)
				.collect(Collectors.toSet());
		*/
		// 2. 考慮到程式碼重用性, 轉換成方法
		Set<Teacher> studentMaryTeachers = getTeachersByStudentClazzs(teachers, mary);
		
		//System.out.println(studentMaryTeachers);
		System.out.printf(
				"學生 mary 的老師: %s\n", 
				studentMaryTeachers.stream()
								   .map(Teacher::getName)
								   .collect(Collectors.toSet())
		);
		
		((ClassPathXmlApplicationContext)ctx).close();
	}
	
	/** 
	 * 從每個老師教授的課程去判斷學生是否有劃課
	 * @param teachers 授課老師集合
	 * @param student 劃課學生
	 * @return Set<Teacher>
	 */
	private static Set<Teacher> getTeachersByStudentClazzs(Teacher[] teachers, Student student) {
		return Arrays.stream(teachers)
					 //.filter(t -> t.getClazzs().stream().anyMatch(c -> student.getClazzs().contains(c)))
					 .filter(t -> t.getClazzs().stream().anyMatch(student.getClazzs()::contains))
					 //.peek(System.out::println)
					 .collect(Collectors.toSet());
	}
}