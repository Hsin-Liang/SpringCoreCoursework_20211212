package pers.hsinliangchang.coursework.SpringCoreCoursework_20211212.coursework_2;

import java.text.SimpleDateFormat;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CourseworkTestPersonDao {
	public static void main(String[] args) throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext2.xml");
		PersonDao personDao = ctx.getBean("personDaoImpl", PersonDaoImpl.class);
		
		System.out.println(personDao.readAll());
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		boolean check = personDao.create(new Person("Zero", 0, sdf.parse("2000/1/1")));
		System.out.println(check);
		System.out.println(personDao.readAll());
		
		((ClassPathXmlApplicationContext)ctx).close();
	}
}