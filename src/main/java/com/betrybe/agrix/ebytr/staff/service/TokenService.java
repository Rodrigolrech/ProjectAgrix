package com.betrybe.agrix.ebytr.staff.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * TokenService.
 */
@Service
public class TokenService {

  private final Algorithm algorithm;

  public TokenService(@Value("minhasenhaaqui") String secret) {
    this.algorithm = Algorithm.HMAC256(secret);
  }

  /**
   * Generate Token.
   *
   * @param username username.
   * @return token.
   */
  public String generateToken(String username) {
    return JWT.create()
        .withSubject(username)
        .withExpiresAt(generateExpiration())
        .sign(algorithm);
  }

  /**
   * Validate Token.
   *
   * @param token token.
   * @return validation.
   */
  public String validateToken(String token) {
    return JWT.require(algorithm)
        .build()
        .verify(token)
        .getSubject();
  }

  private Instant generateExpiration() {
    return Instant.now().plus(2, ChronoUnit.HOURS);
  }

}
