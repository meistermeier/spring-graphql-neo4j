package com.meistermeier.neo4j.graphql.movie;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class MovieService {

    public Map<Movie, Integer> getRatings(Iterable<Movie> movies) {
        Map<Movie, Integer> ratings = new HashMap<>();
        for (Movie movie : movies) {
            ratings.put(movie, new Random().nextInt(101));
        }
        return ratings;
    }

}
