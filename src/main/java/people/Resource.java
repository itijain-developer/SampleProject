package people;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bean.DataIntegrityValidation;
import bean.Person;

@Path("/api")
public class Resource {

	static Logger logger = LoggerFactory.getLogger(Resource.class);

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("getPeople")
	public Response getPeople() {
		Person person = ValidatorClass.getSampleBean();

		Set<ConstraintViolation<Person>> violations = ValidatorClass.validate(person);
		if (violations.size() > 0) {
			logger.info("Running Validations");
			logger.info(violations.toString());

			final Map<String, String> errorResponse = 
					violations
					.stream()
					.collect(Collectors.toMap(o -> o.getPropertyPath().toString(), o -> o.getMessage()));

			return Response
					.status(Response.Status.BAD_REQUEST)
					.entity(new DataIntegrityValidation(errorResponse))
					.build();

		}

		return Response.ok().entity(person).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("people")
	public Response updatePeople(Person request) {

		Person person = new Person();
		person.setName(request.getName());
		person.setAge(request.getAge());
		person.setAddress(request.getAddress());
		person.setTelNumbers(request.getTelNumbers());

		Set<ConstraintViolation<Person>> violations = ValidatorClass.validate(person);
		if (!violations.isEmpty()) {
			final Map<String, String> errorResponse = 
					violations
					.stream()
					.collect(Collectors.toMap(s -> s.getPropertyPath().toString(), s -> s.getMessage()));
			return Response
					.status(Response.Status.BAD_REQUEST)
					.entity(new DataIntegrityValidation(errorResponse))
					.build();
		}

		final Map<String, String> successResponse = new HashMap<String, String>();
		successResponse.put("message", "success");
		return Response.ok(new DataIntegrityValidation(successResponse)).build();
	}

}
