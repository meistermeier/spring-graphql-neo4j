package com.meistermeier.neo4j.graphql;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.context.ImportTestcontainers;
import org.springframework.graphql.ExecutionGraphQlService;
import org.springframework.graphql.test.tester.ExecutionGraphQlServiceTester;
import org.springframework.graphql.test.tester.GraphQlTester;

/**
 * This (integration) test class makes use of the new {@link ImportTestcontainers} annotation.
 * The configuration of the container is extracted into the interface {@link Neo4jContainerConfiguration} to be used in other tests.
 *
 * @author Gerrit Meier
 */
// tag::blog_post[]
@SpringBootTest
@ImportTestcontainers(Neo4jContainerConfiguration.class)
public class Neo4jGraphqlApplicationTests {

    private final GraphQlTester graphQlTester;

    @Autowired
    public Neo4jGraphqlApplicationTests(ExecutionGraphQlService graphQlService) {
        this.graphQlTester = ExecutionGraphQlServiceTester.builder(graphQlService).build();
    }

    @Test
    void resultMatchesExpectation() {
        String query = "{" +
                "  account(username:\"meistermeier\") {" +
                "    displayName" +
                "  }" +
                "}";

        this.graphQlTester.document(query)
                .execute()
                .path("account")
                .matchesJson("[{\"displayName\":\"Gerrit Meier\"}]");

    }

}
// end::blog_post[]