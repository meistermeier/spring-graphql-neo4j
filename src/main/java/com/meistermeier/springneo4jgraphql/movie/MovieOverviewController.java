package com.meistermeier.springneo4jgraphql.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

@Controller
public class MovieOverviewController {

    private final MovieService movieService;

    @Autowired
    public MovieOverviewController(MovieService movieService) {
        this.movieService = movieService;
    }

    @SchemaMapping(typeName = "Movie", field = "overview")
    private Mono<String> overview(Movie movie) {
        return this.movieService.getOverview(movie);
    }

}
