
package com.lily.userauth.security.jwt;

import com.lily.userauth.security.UserDetailsImpl;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.*;


@Slf4j
@Component
public class JwtUtils {

  public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

  @Value("${app.jwt.secret}")
  private String jwtSecret;

  public String generateJwtToken(Authentication authentication) {
    final UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

    Date now = new Date();
    Date expiresIn = new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000);

    return Jwts.builder()
        .setSubject(userPrincipal.getEmail())
        .setIssuedAt(now)
        .setExpiration(expiresIn)
        .signWith(SignatureAlgorithm.HS512, jwtSecret)
        .compact();
  }

  public String getEmailFromJwtToken(String token) {
    return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
  }

  public boolean validateJwtToken(String authToken) {
    try {
      Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
      return true;
    } catch (SignatureException e) {
      log.error("Invalid JWT signature: {}", e.getMessage());
    } catch (MalformedJwtException e) {
      log.error("Invalid JWT token: {}", e.getMessage());
    } catch (ExpiredJwtException e) {
      log.error("JWT token is expired: {}", e.getMessage());
    } catch (UnsupportedJwtException e) {
      log.error("JWT token is unsupported: {}", e.getMessage());
    } catch (IllegalArgumentException e) {
      log.error("JWT claims string is empty: {}", e.getMessage());
    }
    return false;
  }
}
