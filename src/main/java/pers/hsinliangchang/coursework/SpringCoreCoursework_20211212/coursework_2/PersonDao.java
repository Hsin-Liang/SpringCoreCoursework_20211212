package pers.hsinliangchang.coursework.SpringCoreCoursework_20211212.coursework_2;

import java.util.List;

public interface PersonDao {
	/**
	 * 建立 Person
	 * @param person 欲建立的 Person 資料
	 * @return boolean 新增成功/失敗
	 */
	public boolean create(Person person);
	
	/**
	 * 查詢所有 Person
	 * @return List<Person> 查詢的全部 Person 資料
	 */
	public List<Person> readAll();
	
	/**
	 * 移除 Person
	 * @param person 欲移除的 Person 資料
	 * @return boolean 移除成功/失敗
	 */
	public boolean delete(Person person);
}