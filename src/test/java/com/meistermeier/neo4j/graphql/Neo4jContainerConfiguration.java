package com.meistermeier.neo4j.graphql;

import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.Neo4jContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.utility.DockerImageName;

/**
 * Central configuration of the Neo4j testcontainer.
 * The {@link ServiceConnection} takes care that all needed properties are set after the container has started
 * and before the test execution begins.
 *
 * @author Gerrit Meier
 */
public interface Neo4jContainerConfiguration {

    @Container
    @ServiceConnection
    Neo4jContainer<?> neo4jContainer = new Neo4jContainer<>(DockerImageName.parse("neo4j:5"))
            .withRandomPassword()
            .withReuse(true);

}