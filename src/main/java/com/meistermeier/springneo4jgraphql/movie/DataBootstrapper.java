package com.meistermeier.springneo4jgraphql.movie;

import org.neo4j.driver.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

@Component
public class DataBootstrapper implements ApplicationRunner {

    private final Driver driver;

    @Autowired
    public DataBootstrapper(Driver driver) {
        this.driver = driver;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        try (
                var session = driver.session();
                BufferedReader moviesReader = new BufferedReader(
                        new InputStreamReader(DataBootstrapper.class.getResourceAsStream("/cypher/movies.cypher")))) {

            session.run("MATCH (n) detach delete n");

            for (String statement : moviesReader.lines().collect(Collectors.joining(" ")).split(";")) {
                session.run(statement).consume();
            }
        }

    }

}
