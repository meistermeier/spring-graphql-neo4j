package com.meistermeier.springneo4jgraphql.movie;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface MovieRepository extends Neo4jRepository<Movie, String>, QuerydslPredicateExecutor<Movie> {
}
