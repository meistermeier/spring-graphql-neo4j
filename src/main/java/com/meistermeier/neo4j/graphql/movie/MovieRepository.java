package com.meistermeier.neo4j.graphql.movie;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.graphql.data.GraphQlRepository;

@GraphQlRepository
public interface MovieRepository extends Neo4jRepository<Movie, String>, QuerydslPredicateExecutor<Movie> {
}
