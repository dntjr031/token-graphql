package com.lama.graphqlserver.util;

import com.lama.graphqlserver.user.entity.UserView;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthenticationUtil {

  public static UsernamePasswordAuthenticationToken getAuthentication() {
    return (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
  }

  public static UserView getUserDetail() {
    return (UserView)getAuthentication().getPrincipal();
  }

}
