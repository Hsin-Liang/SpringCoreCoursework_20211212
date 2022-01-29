package pers.hsinliangchang.coursework.SpringCoreCoursework_20211212.coursework_2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.MonthDay;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * 功能:
 * 1. 建立 Person 資料
 * 		→　輸入 name, birth
 * 2. 取得 Person 全部資料
 * 		→　不用輸入參數
 * 3. 根據姓名取得 Person
 * 		→　輸入 name
 * 4. 根據日期取得今天生日的 Person
 * 		→　輸入今天日期
 * 5. 取得某一歲數以上的 Person
 * 		→　輸入 age
 * 6. 根據姓名來修改 Person 的生日
 * 		→　輸入 name, 要修改的birth結果
 * 7. 根據姓名來刪除 Person
 * 		→　輸入 name
 * 
 * @author HsinLiangChang
 */
@Controller
public class PersonController {
	@Autowired
	private PersonService personService;
	
	/**
	 * 建立 Person 資料
	 * @param name 姓名
	 * @param yyyy 生日(年)
	 * @param mm 生日(月)
	 * @param dd 生日(日)
	 */
	public void addPerson(String name, Integer yyyy, Integer mm, Integer dd) {
		// 1. 判斷 name yyyy mm 與 dd 是否有資料
		if(name == null || "".equals(name)) {
			System.out.println("名稱(name)未輸入或為空白");
			return;
		} else if(yyyy == null) {
			System.out.println("未輸入生日(年)");
			return;
		} else if(mm == null) {
			System.out.println("未輸入生日(月)");
			return;
		} else if(dd == null) {
			System.out.println("未輸入生日(日)");
			return;
		}
		
		// 2. 將 yyyy/mm/dd 轉日期格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		try {
			Date birth = sdf.parse(String.format("%d/%d/%d", yyyy, mm, dd));
			addPerson(name, birth);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 建立 Person 資料
	 * @param name 姓名
	 * @param birth 生日
	 * @return boolean 新增成功/失敗
	 */
	public void addPerson(String name, Date birth) {
		// 1. 判斷 name 與 birth 是否有資料
		if(name == null || "".equals(name)) {
			System.out.println("名稱(name)未輸入或為空白");
			return;
		} else if(birth == null) {
			System.out.println("未輸入生日(年/月/日)");
			return;
		}
		
		// 2. 建立 Person 資料
		boolean check = personService.append(name, birth);
		System.out.println("add person : " + check);
	}
	
	/**
	 * 取得 Person 全部資料
	 */
	public void printAllPersons() {
		//System.out.println(personService.findAllPersons());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		// 資料呈現
		List<Person> people = personService.findAllPersons();
		System.out.println("+--------------+---------+--------------+");
		System.out.println("|     name     |   age   |   birthday   |"); // 12, 7, 12
		System.out.println("+--------------+---------+--------------+");
		if(people != null && !people.isEmpty()) {
			for(Person p : people) {
				String birthday = sdf.format(p.getBirth());
				System.out.printf("| %-12s | %7d | %12s |\n", p.getName(), p.getAge(), birthday);
				System.out.println("+--------------+---------+--------------+");
			}
		}
	}
	
	/**
	 * 根據姓名取得 Person
	 * @param name 姓名
	 */
	//public void getPersonByName(String name) {
	public Person getPersonByName(String name) {
		// 1. 判斷 name ?
		if(name == null || "".equals(name)) {
			System.out.println("名稱(name)未輸入或為空白");
			//return;
			return null;
		}
		
		// 2. 根據姓名取得 Person
		Person person = personService.getPerson(name);
		if(person != null) {
			System.out.println(person);
			return person;
		} else {
			System.out.printf("姓名: %s 的資料不存在\n", name);
			return null;
		}
	}
	
	/**
	 * 取得今天生日的 Person
	 */
	public void getBirthPersonByToday() {
		MonthDay today = MonthDay.now();
		getBirthPersonByMonthDay(today);
	}
	
	/**
	 * 取得今天生日的 Person
	 * @param mm 生日(月)
	 * @param dd 生日(日)
	 */
	public void getBirthPersonByDate(Integer mm, Integer dd) {
		if(mm == null || dd == null) {
			System.out.println("生日(月或日)輸入錯誤!");
			return;
		}
		
		MonthDay monthDay = MonthDay.of(mm, dd);
		getBirthPersonByMonthDay(monthDay);
	}
	
	/**
	 * 取得今天生日的 Person
	 * @param monthDay 生日物件
	 */
	public void getBirthPersonByMonthDay(MonthDay monthDay) {
		if(monthDay == null) {
			System.out.println("生日(月或日)輸入錯誤!");
			return;
		}
		
		List<Person> people = personService.getPersonsByBirth(monthDay);
		if(people != null && !people.isEmpty()) {
			System.out.println(people);
		} else {
			System.out.println("本日沒有壽星!");
		}
	}
	
	/**
	 * 取得某一歲數以上的 Person
	 * @param age 欲查詢的年齡
	 */
	public void getPersonByAboveAge(Integer age) {
		if(age == null) {
			System.out.println("未輸入欲查詢年齡!!");
			return;
		} else if(age < 0) {
			System.out.println("輸入不合法的年齡!!");
			return;
		}
		
		List<Person> people = personService.getPersonsByAboveAge(age);
		if(people != null && !people.isEmpty()) {
			System.out.println(people);
		} else {
			System.out.println("未符合查詢之資料!");
		}
	}

	/**
	 * 根據姓名來修改 Person 的生日
	 * @param name 欲修改生日的姓名
	 * @param yyyy 生日(年)
	 * @param mm 生日(月)
	 * @param dd 生日(日)
	 */
	public void updatePersonBirthByName(String name, Integer yyyy, Integer mm, Integer dd) {
		// 1. 判斷 name ?
		if(name == null || "".equals(name)) {
			System.out.println("名稱(name)未輸入或為空白");
			return;
		} else if(yyyy == null) {
			System.out.println("生日(年)未輸入");
			return;
		} else if(mm == null) {
			System.out.println("生日(月)未輸入");
			return;
		} else if(dd == null) {
			System.out.println("生日(日)未輸入");
			return;
		}
		
		// 2. 根據姓名取得 Person
		Person oldPerson = personService.getPerson(name);
		try {
			if(oldPerson != null) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
				Date birth = sdf.parse(String.format("%d/%d/%d", yyyy, mm, dd));
				LocalDate currentLocalDate = LocalDate.now();
				Person newPerson = new Person(oldPerson.getName(), (currentLocalDate.getYear() - yyyy), birth);
				boolean updateCheck = personService.update(oldPerson, newPerson);
				System.out.println("update person : " + updateCheck);
			} else {
				System.out.printf("姓名 %s 資料不存在!!\n", name);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 根據姓名來刪除 Person
	 * @param name 欲刪除資料的姓名
	 */
	public void deletePersonByName(String name) {
		// 1. 判斷 name ?
		if(name == null || "".equals(name)) {
			System.out.println("名稱(name)未輸入或為空白");
			return;
		}
		// 2. 根據姓名取得 Person
		Person person = personService.getPerson(name);
		if(person != null) {
			boolean deleteCheck = personService.remove(person);
			System.out.println("delete person : " + deleteCheck);
		} else {
			System.out.printf("姓名 %s 的資料不存在或已被刪除!\n", name);
		}
	}
}