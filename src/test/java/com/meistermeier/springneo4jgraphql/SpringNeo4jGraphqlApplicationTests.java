package com.meistermeier.springneo4jgraphql;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.boot.test.tester.AutoConfigureGraphQlTester;
import org.springframework.graphql.test.tester.GraphQlTester;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureGraphQlTester
@Disabled("The GraphQL Tester bean does not get instantiated :( :shrug:")
public class SpringNeo4jGraphqlApplicationTests {

    @Autowired
    private GraphQlTester graphQlTester;

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
