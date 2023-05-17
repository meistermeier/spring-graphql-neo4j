package com.meistermeier.neo4j.graphql.movie;

import com.querydsl.core.annotations.QueryEntity;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

@QueryEntity
@Node
public class Movie {

    @Id
    @GeneratedValue
    private final String id;

    private final String title;

    @Property("tagline")
    private final String description;

    @Relationship(type = "ACTED_IN", direction = Relationship.Direction.INCOMING)
    private final List<Person> actors;

    @Relationship(type = "DIRECTED", direction = Relationship.Direction.INCOMING)
    private final List<Person> directors;

    public Movie(String id, String title, String description, List<Person> actors, List<Person> directors) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.actors = actors;
        this.directors = directors;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<Person> getActors() {
        return actors;
    }

    public List<Person> getDirectors() {
        return directors;
    }

}
