package people;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bean.Person;

public class ValidatorClass {
	static Logger logger = LoggerFactory.getLogger(ValidatorClass.class);

	public static Set<ConstraintViolation<Person>> validate(Person p) {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<Person>> violations = validator.validate(p);
		return violations;
	}

	public static Person getSampleBean() {

		Person person = new Person();

		person.setName("iti");
		person.setAge(23);
		person.setAddress("address");
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("mobile", "1234");
		map.put("home", "5678");
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		list.add(map);
		person.setTelNumbers(list);

		return person;
	}

}
