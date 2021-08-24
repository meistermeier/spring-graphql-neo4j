package com.meistermeier.springneo4jgraphql;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.boot.test.tester.AutoConfigureGraphQlTester;
import org.springframework.graphql.test.tester.WebGraphQlTester;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.Neo4jContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureGraphQlTester
@Testcontainers
public class SpringNeo4jGraphqlApplicationTests {

    @Container
    private static Neo4jContainer<?> neo4jContainer = new Neo4jContainer<>("neo4j:4.3").withoutAuthentication();
    private final WebGraphQlTester graphQlTester;

    @Autowired
    public SpringNeo4jGraphqlApplicationTests(WebGraphQlTester graphQlTester) {
        this.graphQlTester = graphQlTester;
    }

    @DynamicPropertySource
    static void neo4jProperties(DynamicPropertyRegistry registry) {

        registry.add("spring.neo4j.uri", neo4jContainer::getBoltUrl);
        registry.add("spring.neo4j.authentication.username", () -> "neo4j");
        registry.add("spring.neo4j.authentication.password", () -> null);
        registry.add("tmdb_api", () -> "n/a");
    }

    @Test
    void resultMatchesExpectation() {
        String query = "{" +
                "  movie(title:\"The Matrix\") {" +
                "    title" +
                "  }" +
                "}";

        this.graphQlTester.query(query)
                .execute()
                .path("movie")
                .matchesJson("{\"title\":\"The Matrix\"}");
    }

}
