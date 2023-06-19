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
public class Account {

	@Id
	private final String id; // <.>
	private final String username;
	@Property("display_name")
	private final String displayName; // <.>
	@Relationship("REGISTERED_ON")
	private final Server server;
	@Relationship("FOLLOWS")
	private List<Account> following;

	// constructor, etc.
// end::blog_post[]

	public Account(String id, String username, String displayName, Server server) {
		this.id = id;
		this.username = username;
		this.displayName = displayName;
		this.server = server;
	}

//	Not working right now:
//	see https://github.com/spring-projects/spring-data-neo4j/issues/2748
//	public Account withFollowing(List<Account> following) {
//		var account = new Account(this.id, this.username, this.displayName, this.server);
//		account.following = following;
//		return account;
//	}

	public Server getServer() {
		return server;
	}

	public String getId() {
		return id;
	}

	public void setFollowing(List<Account> following) {
		this.following = following;
	}
// tag::blog_post[]
}
// end::blog_post[]
