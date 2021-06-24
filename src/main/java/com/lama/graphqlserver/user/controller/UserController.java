package com.lama.graphqlserver.user.controller;

import com.lama.graphqlserver.security.token.AuthenticationToken;
import com.lama.graphqlserver.security.token.AuthenticationTokenProvider;
import com.lama.graphqlserver.user.entity.UserView;
import com.lama.graphqlserver.common.CommonResponse;
import com.lama.graphqlserver.user.model.UserResponse;
import com.lama.graphqlserver.user.service.UserService;
import com.lama.graphqlserver.util.BeanUtils;
import com.lama.graphqlserver.util.ResponseUtil;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;

@Controller
@GraphQLApi
@AllArgsConstructor
public class UserController {

  private final UserService service;
  private final AuthenticationTokenProvider tokenProvider;

  @PreAuthorize("isFullyAuthenticated()")
  @GraphQLMutation(name = "login")
  public CommonResponse<UserResponse> login(String userId, String password) {
    UserView user = service.loadUserByUsername(userId);
    if (BeanUtils.getBean(BCryptPasswordEncoder.class).matches(password, user.getPassword())) {
      AuthenticationToken token = tokenProvider.issue(userId);
      return ResponseUtil.success(UserResponse.builder().userNm(user.getUserNm()).userId(user.getUserId()).token(token.getToken()).build());
    }
    return ResponseUtil.fail(null, "비밀번호가 일치하지 않습니다.");
  }

//  @PreAuthorize("isAuthenticated()")
//  @GraphQLQuery(name = "user")
//  public CommonResponse<String> getUser() {
//    String id = AuthenticationUtil.getUserDetail().getUserId();
//    return new CommonResponse<>(id);
//  }

}
