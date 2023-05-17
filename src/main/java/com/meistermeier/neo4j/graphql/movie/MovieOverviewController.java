package com.meistermeier.neo4j.graphql.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.BatchMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Controller
public class MovieOverviewController {

    private final MovieService movieService;

    @Autowired
    public MovieOverviewController(MovieService movieService) {
        this.movieService = movieService;
    }

    /**
     * Load ratings in batch instead for every Movie entity one by one.
     *
     * @param movies to get the ratings for
     * @return batch "loaded" ratings
     */
    @BatchMapping
    private Mono<Map<Movie, Integer>> rating(List<Movie> movies) {
        return Mono.just(this.movieService.getRatings(movies));
    }

}
