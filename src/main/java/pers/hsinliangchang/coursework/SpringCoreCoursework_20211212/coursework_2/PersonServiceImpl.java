package pers.hsinliangchang.coursework.SpringCoreCoursework_20211212.coursework_2;

import java.time.MonthDay;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {
	@Autowired
	private PersonDao personDao;
	
	@Override
	public boolean append(String name, Date birth) {
		Person person = new Person();
		person.setName(name);
		person.setBirth(birth);
		return append(person);
	}

	@Override
	public boolean append(Person person) {
		return personDao.create(person);
	}

	@Override
	public List<Person> findAllPersons() {
		return personDao.readAll();
	}

	@Override
	public Person getPerson(String name) {
		if(name == null || name.equals("")) {
			return null;
		}
		List<Person> people = findAllPersons();
		if(people != null && !people.isEmpty()) {
			Optional<Person> personOpt = people.stream()
					.filter(p -> name.equals(p.getName()))
					.findFirst();
			Person person = (personOpt.isPresent() ? personOpt.get() : null);			
			return person;
		}
		return null;
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<Person> getPersonsByBirth(MonthDay birth) {
		if(birth == null) {
			return null;
		}
		List<Person> people = findAllPersons();	
		if(people != null && !people.isEmpty()) {
			//MonthDay currentMonthDay = MonthDay.of(birth.getYear() + 1990, birth.getMonth() + 1);
			return people.stream()
					.filter(p -> MonthDay.of(p.getBirth().getMonth()+1, p.getBirth().getDate()).equals(birth))
					.collect(Collectors.toList());			
		}
		return null;
	}
	
	@Override
	public List<Person> getPersonsByAboveAge(Integer age) {
		if(age == null || age < 0) {
			return null;
		}
		
		List<Person> people = findAllPersons();
		if(people != null && !people.isEmpty()) {
			return people.stream()
					.filter(p -> p.getAge() >= age)
					.collect(Collectors.toList());
		}
		return null;
	}

	@Override
	public boolean remove(Person person) {
		return personDao.delete(person);
	}

	@Override
	public boolean update(Person oldPerson, Person newPerson) {
		if(oldPerson == null || newPerson == null) {
			return false;
		}
		boolean removeCheck = remove(oldPerson);
		if(removeCheck) {
			boolean createCheck = append(newPerson);
			if(createCheck) {
				return true;
			}
		}
		
		return false;
	}
}