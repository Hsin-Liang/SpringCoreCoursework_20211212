package pers.hsinliangchang.coursework.SpringCoreCoursework_20211212.coursework_2;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PersonDaoImpl implements PersonDao {
	@Autowired
	private JsonDB jsonDB;
	
	/**
	 * 建立 Person
	 * @param person 欲建立的 Person 資料
	 * @return boolean 新增成功/失敗
	 */
	@Override
	public boolean create(Person person) {
		Boolean check = null;
		try {
			check = jsonDB.add(person);
		} catch (Exception e) {
			e.printStackTrace();
			check = false;
		}
		return check;
	}

	/**
	 * 查詢所有 Person
	 * @return List<Person> 查詢的全部 Person 資料
	 */
	@Override
	public List<Person> readAll() {
		List<Person> people = null;
		try {
			people = jsonDB.queryAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return people;
	}

	/**
	 * 移除 Person
	 * @param person 欲移除的 Person 資料
	 * @return boolean 移除成功/失敗
	 */
	@Override
	public boolean delete(Person person) {
		Boolean check = null;
		try {
			check = jsonDB.remove(person);
		} catch (Exception e) {
			e.printStackTrace();
			check = false;
		}
		return check;
	}
}