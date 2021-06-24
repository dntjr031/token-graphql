package com.lama.graphqlserver.security.token;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class JwtAuthenticationTokenProvider implements AuthenticationTokenProvider {

  @Value("spring.jwt.secret")
  private String SECRET_KEY;

  private static final long EXPIRATION_MS = 1000 * 60 * 60 * 24; // 토큰 만료 시간 지정

  @PostConstruct
  protected void init() {
    SECRET_KEY = Base64.getEncoder().encodeToString(SECRET_KEY.getBytes());
  }

  @Override
  public String parseTokenString(HttpServletRequest request) {
    String bearerToken = request.getHeader("Authorization");
    if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
      return bearerToken.substring(7);
    }
    return null;
  }

  @Override
  public AuthenticationToken issue(String userId) {
    return JwtAuthenticationToken.builder().token(buildToken(userId)).build();
  }

  @Override
  public String getTokenOwnerNo(String token) {
    Claims claims = Jwts.parser()
        .setSigningKey(SECRET_KEY)
        .parseClaimsJws(token)
        .getBody();
    return claims.getSubject();
  }

  @Override
  public boolean validateToken(String token) {
    if (StringUtils.isNotEmpty(token)) {
      try {
        Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
        return true;
      } catch (SignatureException e) {
        log.error("Invalid JWT signature: {}", e.getMessage());
      } catch (MalformedJwtException e) {
        log.error("Invalid JWT token: {}",  e.getMessage());
      } catch (ExpiredJwtException e) {
        log.error("Expired JWT token: {}",  e.getMessage());
      } catch (UnsupportedJwtException e) {
        log.error("Unsupported JWT token: {}",  e.getMessage());
      } catch (IllegalArgumentException e) {
        log.error("JWT claims string is empty.: {}",  e.getMessage());
      }
    }
    return false;
  }

  // JWT 토큰 생성
  private String buildToken(String userId) {
    LocalDateTime now = LocalDateTime.now(); // 현재 시간
    LocalDateTime expiredAt = now.plus(EXPIRATION_MS, ChronoUnit.MILLIS); // 토큰 만료 시간
    return Jwts.builder()
        .setSubject(userId)
        .setIssuedAt(Date.from(now.atZone(ZoneId.systemDefault()).toInstant()))
        .setExpiration(Date.from(expiredAt.atZone(ZoneId.systemDefault()).toInstant()))
        .signWith(SignatureAlgorithm.HS512, SECRET_KEY) // SHA-512 알고리즘과 키를 사용하여 암호화
        .compact();
  }

}
