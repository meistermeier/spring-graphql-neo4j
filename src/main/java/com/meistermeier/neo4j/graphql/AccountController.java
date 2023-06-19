package com.meistermeier.neo4j.graphql;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.BatchMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

/**
 * @author Gerrit Meier
 */
// tag::body[]
@Controller
public class AccountController {
// end::body[]

	private final AccountRepository repository;

	@Autowired
	public AccountController(AccountRepository repository) {
		this.repository = repository;
	}

	@QueryMapping
	// renamed `account` to `account2`
	// to keep it in the sources
	public List<Account> accounts2() {
		return repository.findAll();
	}

	// renamed `lastMessage` to `lastMessage2`
	// to keep it in the sources
	@SchemaMapping
	public String lastMessage2(Account account) {
		var id = account.getId();
		String serverUri = account.getServer().getUri();

		WebClient webClient = WebClient.builder()
				.baseUrl("https://" + serverUri)
				.build();

		return webClient.get()
				.uri("/api/v1/accounts/{id}/statuses?limit=1", id)
				.exchangeToMono(clientResponse ->
						clientResponse.statusCode().equals(HttpStatus.OK)
						? clientResponse
							.bodyToMono(String.class)
							.map(AccountController::extractData)
						: Mono.just("could not retrieve last status")
				)
				.block();
	}


// tag::federation_batch_mapping[]
	@BatchMapping
	public Flux<String> lastMessage(List<Account> accounts) {
		return Flux.concat(
			accounts.stream().map(account -> {
				var id = account.getId();
				String serverUri = account.getServer().getUri();

				WebClient webClient = WebClient.builder()
						.baseUrl("https://" + serverUri)
						.build();


				return webClient.get()
						.uri("/api/v1/accounts/{id}/statuses?limit=1", id)
						.exchangeToMono(clientResponse ->
								clientResponse.statusCode().equals(HttpStatus.OK)
								? clientResponse
									.bodyToMono(String.class)
									.map(AccountController::extractData)
								: Mono.just("could not retrieve last status")
						);
		}).toList());
	}

// end::federation_batch_mapping[]
	private static String extractData(String response) {
		Map<String, Object>jsonObject = null;
		try {
			jsonObject = (Map<String, Object>) new ObjectMapper().readValue(response, List.class).get(0);
		} catch (JsonProcessingException e) {
			return "an error occured while processing the result";
		}
		return (String) jsonObject.get("content");
	}
// tag::body[]
}
// end::body[]
