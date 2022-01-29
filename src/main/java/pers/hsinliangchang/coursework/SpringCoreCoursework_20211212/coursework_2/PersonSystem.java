package pers.hsinliangchang.coursework.SpringCoreCoursework_20211212.coursework_2;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PersonSystem {
	private ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext2.xml");
	private PersonController personController = ctx.getBean("personController", PersonController.class);
	
	private boolean stop;
	private Scanner sc;
	
	private void menu() {
		System.out.println("=========================================");
		System.out.println("1. 建立 Person 資料");
		System.out.println("2. 取得 Person 全部資料");
		// 回家作業: 3 4 5 6 7
		System.out.println("3. 根據姓名取得 Person");
		System.out.println("4. 取得今天生日的 Person");
		System.out.println("5. 取得某一歲數以上的 Person");
		System.out.println("6. 根據姓名來修改Person的生日");
		System.out.println("7. 根據姓名來刪除Person");
		System.out.println("0. 離開");
		System.out.println("=========================================");
		System.out.print("請選擇: ");
		sc = new Scanner(System.in);
		int choice = sc.nextInt();
		switch (choice) {
			case 1:					// 建立 Person 資料
				createPerson();
				break;
			case 2:					// 取得 Person 全部資料
				printPersons();
				break;
			case 3:					// 根據姓名取得 Person
				searchPersonByName();
				break;
			case 4:					// 取得今天生日的 Person
				searchTodayBirthPerson();
				break;
			case 5:					// 取得某一歲數以上的 Person
				searchPersonByAboveAge();
				break;
			case 6:					// 根據姓名來修改 Person 的生日
				modifyBirthByName();
				break;
			case 7:					// 根據姓名來刪除 Person
				deletePersonByName();
				break;
			case 0:
				System.out.println("離開系統");
				stop = true;
				break;
		}
	}
	
	/**
	 * 建立 Person 資料
	 */
	private void createPerson() {
		System.out.print("請輸入姓名 生日年 月 日: ");
		// Ex: Jack 1999 4 5
		//Scanner sc = new Scanner(System.in).useDelimiter(",");
		sc = new Scanner(System.in);
		String name = sc.next();
		int yyyy = sc.nextInt();
		int mm = sc.nextInt();
		int dd = sc.nextInt();
		personController.addPerson(name, yyyy, mm, dd);
	}
	
	/**
	 * 取得 Person 全部資料
	 */
	private void printPersons() {
		personController.printAllPersons();
	}
	
	/**
	 * 根據姓名取得 Person
	 */
	private void searchPersonByName() {
		System.out.print("請輸入欲查找的姓名: ");
		// Ex: Jack
		sc = new Scanner(System.in);
		String searchName = sc.next();
		personController.getPersonByName(searchName);
	}
	
	/**
	 * 取得今天生日的 Person
	 */
	private void searchTodayBirthPerson() {
		System.out.print("請選擇查詢方式(1: 根據系統日, 2: 根據輸入日期): ");
		// Ex: 2
		sc = new Scanner(System.in);
		int choice = sc.nextInt();
		switch (choice) {
		case 1:
			personController.getBirthPersonByToday();
			break;
		case 2:
			System.out.print("請輸入生日(月): ");
			// Ex: 04
			sc = new Scanner(System.in);
			int mm = sc.nextInt();
			System.out.print("請輸入生日(日): ");
			// Ex: 23
			int dd = sc.nextInt();
			personController.getBirthPersonByDate(mm, dd);
			break;
		default:
			System.out.println("無此選項!");
			break;
		}
	}
	
	/**
	 * 取得某一歲數以上的 Person
	 */
	private void searchPersonByAboveAge() {
		System.out.print("請輸入欲查詢的年齡: ");
		// Ex: 18
		sc = new Scanner(System.in);
		int age = sc.nextInt();
		personController.getPersonByAboveAge(age);
	}
	
	/**
	 * 根據姓名來修改 Person 的生日
	 */
	private void modifyBirthByName() {
		System.out.print("請輸入欲修改生日的姓名: ");
		// Ex: YoYo
		sc = new Scanner(System.in);
		String name = sc.next();
		Person person = personController.getPersonByName(name);
		if(person != null) {
			System.out.print("請輸入欲修改的生日年 月 日: ");
			// Ex: 1990 06 29
			sc = new Scanner(System.in);
			int yyyy = sc.nextInt();
			int mm = sc.nextInt();
			int dd = sc.nextInt();
			personController.updatePersonBirthByName(name, yyyy, mm, dd);
		}
	}
	
	/**
	 * 根據姓名來刪除 Person
	 */
	private void deletePersonByName() {
		System.out.print("請輸入欲刪除的姓名: ");
		// Ex: YoYo
		sc = new Scanner(System.in);
		String name = sc.next();
		Person person = personController.getPersonByName(name);
		if(person != null) {
			System.out.print("確定要刪除嗎? (0: 取消; 1: 確定刪除): ");
			// Ex: 1
			sc = new Scanner(System.in);
			int delete = sc.nextInt();
			if(delete == 1) {
				personController.deletePersonByName(name);
			} else {
				System.out.println("取消刪除!");
			}			
		}
	}
	
	public void start() {
		while (!stop) {
			menu();
		}
		sc.close();
	}
	
	public static void main(String[] args) {
		new PersonSystem().start();
	}
}