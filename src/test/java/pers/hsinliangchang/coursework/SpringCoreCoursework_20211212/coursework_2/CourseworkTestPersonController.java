package pers.hsinliangchang.coursework.SpringCoreCoursework_20211212.coursework_2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CourseworkTestPersonController {
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext2.xml");
		PersonController personController = ctx.getBean("personController", PersonController.class);
		//personController.printAllPersons();

		//personController.addPerson("Bob", 2013, 12, 26);
		//personController.printAllPersons();
		
		personController.getPersonByName("Bob");
		personController.getPersonByName("Tom");
		
		((ClassPathXmlApplicationContext) ctx).close();
	}
}