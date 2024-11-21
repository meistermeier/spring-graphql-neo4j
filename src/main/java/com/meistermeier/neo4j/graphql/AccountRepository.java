package com.meistermeier.neo4j.graphql;

import org.springframework.data.domain.ScrollPosition;
import org.springframework.data.domain.Sort;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.graphql.data.GraphQlRepository;
import org.springframework.graphql.data.query.QueryByExampleDataFetcher;

/**
 * @author Gerrit Meier
 */
// tag::blog_post[]
@GraphQlRepository // <.>
public interface AccountRepository extends Neo4jRepository<Account, String>
// end::blog_post[]
// tag::sort_order[]
	, QueryByExampleDataFetcher.QueryByExampleBuilderCustomizer<Account>
// end::sort_order[]
// tag::blog_post[]
{
// end::blog_post[]
// tag::sort_order[]

	@Override
	default QueryByExampleDataFetcher.Builder<Account, ?> customize(QueryByExampleDataFetcher.Builder<Account, ?> builder) {
		return builder.sortBy(Sort.by("username"))
				.defaultScrollSubrange(1, (b) -> ScrollPosition.offset());
	}

// end::sort_order[]
// tag::blog_post[]
}
// end::blog_post[]
