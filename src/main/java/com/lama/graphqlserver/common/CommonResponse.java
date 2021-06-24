package com.lama.graphqlserver.common;

import io.leangen.graphql.annotations.GraphQLQuery;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CommonResponse<T> {
  @GraphQLQuery(name = "status")
  private final int status;

  @GraphQLQuery(name = "message")
  private final String message;

  @GraphQLQuery(name = "data")
  private final T data;
}
