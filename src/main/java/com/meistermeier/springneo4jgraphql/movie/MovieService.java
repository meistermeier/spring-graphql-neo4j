package com.meistermeier.springneo4jgraphql.movie;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
public class MovieService {

    @Value("${tmdb_api}")
    String apiKey;

    public Mono<String> getOverview(Movie movie) {
        URI uri = URI.create("https://api.themoviedb.org/3/search/movie?api_key=" + apiKey + "&query=" + URLEncoder.encode(movie.getTitle(), StandardCharsets.UTF_8));
        CompletableFuture<String> futureResponse = HttpClient.newHttpClient().sendAsync(HttpRequest.newBuilder().uri(uri).build(), HttpResponse.BodyHandlers.ofString())
                .thenApply(response -> {
                    List<Object> results = (List<Object>) new JacksonJsonParser().parseMap(response.body()).get("results");
                    if (results.isEmpty()) {
                        return "no overview found";
                    }
                    return (String) ((Map<String, Object>) results.get(0)).get("overview");
                });

        return Mono.fromCompletionStage(futureResponse);

    }

}
