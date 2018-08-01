import org.junit.Before;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bean.Person;
import people.ValidatorClass;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;

public class ValidatorTest {
	static Logger logger = LoggerFactory.getLogger(ValidatorTest.class);
	Person p = new Person();

	@Before
	public void setData() {

		p.setName("it");
		p.setAge(23);
		p.setAddress("address");
		Map<String, String> m = new LinkedHashMap<String, String>();
		m.put("mobile", "1234");
		m.put("home", "5678");
		List<Map<String, String>> l = new ArrayList<Map<String, String>>();
		l.add(m);
		p.setTelNumbers(l);

	}

	@Test
	public void validateTest() {
		Set<ConstraintViolation<Person>> violations = ValidatorClass.validate(p);
		if (violations.size() > 0) {
			logger.info("Run test");
			logger.info(violations.toString());
		}
		assertEquals(0, violations.size());
	}

}
