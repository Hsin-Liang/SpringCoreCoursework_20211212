package pers.hsinliangchang.coursework.SpringCoreCoursework_20211212.coursework_2;

import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Json 資料庫
 * @author HsinLiangChang
 */
@Component
public class JsonDB {
	@Autowired
	private Gson gson;
	
	/**
	 * Json file 資料庫存放地
	 */
	private static final Path PATH = Paths.get("src/main/java/pers/hsinliangchang/coursework/SpringCoreCoursework_20211212/coursework_2/person.json");
	
	/**
	 * 查詢全部
	 * @return List<Person>
	 * @throws Exception
	 */
	public List<Person> queryAll() throws Exception {
		String jsonStr = Files.readAllLines(PATH)
							  .stream()
							  .collect(Collectors.joining());
		
		Type type = new TypeToken<ArrayList<Person>>(){}.getType();
		List<Person> people = gson.fromJson(jsonStr, type);
		// 請將 age 變為最新年齡
		// 取得現在時間
		Date now = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(now);
		int thisYear = calendar.get(Calendar.YEAR);
		people.stream().forEach(p -> {
			// 取得person出生時間
			Calendar peopleBirth = Calendar.getInstance();
			peopleBirth.setTime(p.getBirth());
			int birthYear = peopleBirth.get(Calendar.YEAR);
			int age = thisYear - birthYear;
			p.setAge(age);
		});
		
		// 老師寫的
		/*
		// 寫法 1.
		Date today = new Date();
		LocalDate todayLocalDate = today.toInstant()
									.atZone(ZoneId.systemDefault())
									.toLocalDate();
		for(Person person : people) {
			LocalDate birthLocalDate = person.getBirth().toInstant()
									.atZone(ZoneId.systemDefault())
									.toLocalDate();
			int age = todayLocalDate.getYear() - birthLocalDate.getYear();
			person.setAge(age);
		}
		
		// 寫法 2.
		Date today = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(today);
		int todayYear = calendar.get(Calendar.YEAR);
		for(Person person : people) {
			calendar.setTime(person.getBirth());
			int birthYear = calendar.get(Calendar.YEAR);
			int age = todayYear - birthYear;
			person.setAge(age);
		}
		*/
		return people;
	}
	
	/**
	 * 新增一筆資料
	 * @param person
	 * @return boolean
	 * @throws Exception
	 */
	public boolean add(Person person) throws Exception {
		List<Person> people = queryAll();
		// 作業 1: 
		// 如果 person 已存在則 return false
		// name, age, birth 皆與目前資料庫的 person 資料相同
		if(people.stream().anyMatch(p -> p.equals(person))) {
			return false;
		}
		
		people.add(person);
		String newJsonString = gson.toJson(people);
		Files.write(PATH, newJsonString.getBytes("UTF-8"));
		return true;
	}
	
	/**
	 * 移除一筆資料
	 * @param person
	 * @return boolean
	 * @throws Exception
	 */
	public boolean remove(Person person) throws Exception {
		List<Person> people = queryAll();
		
		if(people.stream().anyMatch(p -> p.equals(person))) {
			people.remove(person);
			String newJsonString = gson.toJson(people);
			Files.write(PATH, newJsonString.getBytes("UTF-8"));
			return true;
		} else {
			return false;
		}
	}
}