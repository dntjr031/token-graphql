package com.lama.graphqlserver.security;

import com.lama.graphqlserver.security.token.AuthenticationTokenProvider;
import com.lama.graphqlserver.security.token.TokenAuthenticationFilter;
import com.lama.graphqlserver.user.service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@AllArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  private final AuthenticationTokenProvider authenticationTokenProvider;

  private final UserService userService;

  private static final String[] PUBLIC_URI = {
      "/public-api", "/gui", "/graphql"
  };

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        // 개발 편의성을 위해 CSRF 프로텍션을 비활성화
        .csrf().disable()
        // HTTP 기본 인증 비활성화
        .httpBasic().disable()
        // 폼 기반 인증 비활성화
        .formLogin().disable()
        .logout().disable()
        // stateless 한 세션 정책 설정
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        // 리소스 별 허용 범위 설정
        .authorizeRequests()
        .antMatchers(PUBLIC_URI).permitAll()
        .anyRequest().authenticated()
        .and()
        // 인증 오류 발생 시 처리를 위한 핸들러 추가
        .exceptionHandling()
        .authenticationEntryPoint(new JLFAuthenticationEntryPoint())
    ;
    // 토큰 인증 필터 추가
    http.addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
  }

  @Override
  public void configure(WebSecurity web) {
    web.ignoring().antMatchers("/playground");
  }

  @Bean
  public AuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
    daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
    daoAuthenticationProvider.setUserDetailsService(userService);

    return daoAuthenticationProvider;
  }

  @Bean
  public TokenAuthenticationFilter tokenAuthenticationFilter() {
    return new TokenAuthenticationFilter(this.authenticationTokenProvider, this.userService);
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

}
