package com.lama.graphqlserver.security.token;

import javax.servlet.http.HttpServletRequest;

public interface AuthenticationTokenProvider {

  /***
   * HTTP 요청에서 토큰 취득
   * @param request HTTP 요청
   * @return 토큰
   */
  String parseTokenString(HttpServletRequest request);

  /***
   * 토큰 발급
   * @param userId 유저 No
   * @return 토큰
   */
  AuthenticationToken issue(String userId);

  /***
   * 토큰에서 userId 취득
   * @param token 토큰
   * @return userId
   */
  String getTokenOwnerNo(String token);

  /***
   * 토큰 유효성 검사
   * @param token 토큰
   * @return 유효여부
   */
  boolean validateToken(String token);
}
