package com.lama.graphqlserver.security.token;

import com.lama.graphqlserver.user.entity.UserView;
import com.lama.graphqlserver.user.service.UserService;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

public class TokenAuthenticationFilter extends OncePerRequestFilter {

  private final AuthenticationTokenProvider authenticationTokenProvider;

  private final UserService userService;

  @Autowired
  public TokenAuthenticationFilter(AuthenticationTokenProvider authenticationTokenProvider,
      UserService userService) {
    this.authenticationTokenProvider = authenticationTokenProvider;
    this.userService = userService;
  }

  @Override
  protected void doFilterInternal(@NotNull HttpServletRequest request,
      @NotNull HttpServletResponse response, @NotNull FilterChain filterChain)
      throws ServletException, IOException {
    String token = authenticationTokenProvider.parseTokenString(request);

    if (authenticationTokenProvider.validateToken(token)) {
      String userId = authenticationTokenProvider.getTokenOwnerNo(token);

      try {
        UserView member = userService.loadUserByUsername(userId);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
            member,
            member.getPassword(), member.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
      } catch (UsernameNotFoundException e) {
        throw new UsernameNotFoundException(e.getMessage());
      }
    }
    filterChain.doFilter(request, response);
  }

}
