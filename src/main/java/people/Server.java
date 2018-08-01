package people;

import java.net.URI;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

public class Server {

	private final URI ADDRESS = UriBuilder.fromPath("http://localhost:8000").build();

	public Server() {
		ResourceConfig resourceConfig = new ResourceConfig();
		resourceConfig.packages("people");
		resourceConfig.property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
		GrizzlyHttpServerFactory.createHttpServer(ADDRESS, resourceConfig);
	}

	public static void main(String[] args) {
		Server server = new Server();
	}

}
