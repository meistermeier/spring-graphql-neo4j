package com.meistermeier.springneo4jgraphql.movie;

import graphql.schema.idl.RuntimeWiring;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.boot.RuntimeWiringBuilderCustomizer;
import org.springframework.graphql.data.QuerydslDataFetcher;
import org.springframework.stereotype.Component;

@Component
public class MovieDataWiring implements RuntimeWiringBuilderCustomizer {

    private final MovieRepository repository;

    @Autowired
    public MovieDataWiring(MovieRepository repository) {
        this.repository = repository;
    }

    @Override
    public void customize(RuntimeWiring.Builder builder) {
        builder.type("Query", typeWiring -> typeWiring
                .dataFetcher("movies", QuerydslDataFetcher.builder(repository).many())
                .dataFetcher("movie", QuerydslDataFetcher.builder(repository).single()));
    }
}
