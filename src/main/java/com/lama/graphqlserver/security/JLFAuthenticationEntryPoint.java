package com.lama.graphqlserver.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class JLFAuthenticationEntryPoint implements AuthenticationEntryPoint {

  @Override
  public void commence(HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse, AuthenticationException e)
      throws IOException, ServletException {

    log.error("UnAuthorized!!! message: " + e.getMessage());
    //response 에 넣기
    httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
    httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
    try (OutputStream os = httpServletResponse.getOutputStream()) {
      ObjectMapper objectMapper = new ObjectMapper();
//      objectMapper.writeValue(os, exceptionResponse);
      os.flush();
    }

  }
}
