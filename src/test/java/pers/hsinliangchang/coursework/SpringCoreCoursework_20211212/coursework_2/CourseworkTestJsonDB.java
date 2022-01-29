package pers.hsinliangchang.coursework.SpringCoreCoursework_20211212.coursework_2;

import java.text.SimpleDateFormat;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CourseworkTestJsonDB {
	public static void main(String[] args) throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext2.xml");
		
		JsonDB jsonDB = (JsonDB) ctx.getBean("jsonDB");
		System.out.println(jsonDB.queryAll());
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		//boolean check = jsonDB.add(new Person("John", 0, sdf.parse("2000/1/1")));
		//boolean check = jsonDB.add(new Person("Lucas", 0, sdf.parse("1979/6/4")));
		boolean check = jsonDB.add(new Person("LuLu", 0, sdf.parse("1993/2/14")));
		System.out.println(check);
		System.out.println(jsonDB.queryAll());
		
		((ClassPathXmlApplicationContext)ctx).close();
	}
}