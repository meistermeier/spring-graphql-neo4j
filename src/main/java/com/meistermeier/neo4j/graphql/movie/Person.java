package com.meistermeier.neo4j.graphql.movie;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

@Node
public class Person {

    @Id
    @GeneratedValue
    private final String id;

    private final String name;

    @Property("born")
    private final Integer yearOfBirth;

    public Person(String id, String name, Integer yearOfBirth) {
        this.id = id;
        this.name = name;
        this.yearOfBirth = yearOfBirth;
    }

    public String getName() {
        return name;
    }

    public Integer getYearOfBirth() {
        return yearOfBirth;
    }
}
