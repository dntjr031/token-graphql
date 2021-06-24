package com.lama.graphqlserver.user.model;

import io.leangen.graphql.annotations.GraphQLQuery;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class UserResponse {

  @GraphQLQuery(name = "userId")
  private String userId;

  @GraphQLQuery(name = "userNm")
  private String userNm;

  @GraphQLQuery(name = "token")
  private String token;

}
