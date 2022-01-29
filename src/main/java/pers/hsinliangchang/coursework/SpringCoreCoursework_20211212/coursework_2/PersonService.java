package pers.hsinliangchang.coursework.SpringCoreCoursework_20211212.coursework_2;

import java.time.MonthDay;
import java.util.Date;
import java.util.List;

public interface PersonService {
	boolean append(String name, Date birth);
	boolean append(Person person);
	List<Person> findAllPersons();
	Person getPerson(String name);
	List<Person> getPersonsByBirth(MonthDay birth);
	List<Person> getPersonsByAboveAge(Integer age);
	boolean remove(Person person);
	boolean update(Person oldPerson, Person newPerson);
}