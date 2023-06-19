package com.meistermeier.neo4j.graphql;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

/**
 * @author Gerrit Meier
 */
// tag::blog_post[]
@Node
public class Server {

	@Id
	private final String uri; // <.>
	private final String title;
	@Property("registrations")
	private final Boolean registrationsAllowed;
	@Property("short_description")
	private final String shortDescription;
	@Relationship("CONNECTED_TO")
	private List<Server> connectedServers;

	// constructor, etc.
// end::blog_post[]

	public Server(String title, String uri, Boolean registrationsAllowed, String shortDescription) {
		this.title = title;
		this.uri = uri;
		this.registrationsAllowed = registrationsAllowed;
		this.shortDescription = shortDescription;
	}

//	Not working right now:
//	see https://github.com/spring-projects/spring-data-neo4j/issues/2748
//	public Server withConnectedServers(List<Server> connectedServers) {
//		var server = new Server(this.title, this.uri, this.registrations, this.shortDescription);
//		server.connectedServers = connectedServers;
//		return server;
//	}


	public String getUri() {
		return uri;
	}

	public void setConnectedServers(List<Server> connectedServers) {
		this.connectedServers = connectedServers;
	}
// tag::blog_post[]
}
// end::blog_post[]
