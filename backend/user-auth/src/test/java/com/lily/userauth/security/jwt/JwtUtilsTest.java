package com.lily.userauth.security.jwt;

import static com.lily.userauth.security.jwt.JwtUtils.JWT_TOKEN_VALIDITY;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class JwtUtilsTest {

  public static final String TEST_SECRET_KEY = "testSecretKey";

  private final JwtUtils dut = new JwtUtils(TEST_SECRET_KEY);

  private static JwtBuilder TEST_TOKEN_BUILDER() {
    Date now = new Date();
    Date expiresIn = new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY.toMillis());

    return Jwts.builder()
        .setIssuedAt(now)
        .setExpiration(expiresIn)
        .signWith(SignatureAlgorithm.HS512, TEST_SECRET_KEY);
  }

  @Test
  public void mustThrowWhenSecretKeyIsNull() {
    assertThrows(RuntimeException.class, () -> {
      new JwtUtils(null);
    });
  }

  @Test
  public void mustFalseWhenWrongSignature() {
    String signatureExceptionToken = wrongSignature();

    boolean actual = dut.validateJwtToken(signatureExceptionToken);

    assertFalse(actual);
  }

  @Test
  public void mustFalseWhenTokenExpired() {
    String expiredJwtExceptionToken = expiredToken();

    boolean actual = dut.validateJwtToken(expiredJwtExceptionToken);

    assertFalse(actual);
  }

  @Test
  public void mustFalseWhenTokenUnsupported() {
    String unsupportedJwtExceptionToken = noSignatureToken();

    boolean actual = dut.validateJwtToken(unsupportedJwtExceptionToken);

    assertFalse(actual);
  }

  @Test
  public void mustFalseWhenMalformedToken() {
    String malformedJwtExceptionToken = malformedJwtExceptionToken();

    boolean actual = dut.validateJwtToken(malformedJwtExceptionToken);

    assertFalse(actual);
  }

  @Test
  public void mustTrueWhenValidToken() {
    String validToken = validToken();

    boolean actual = dut.validateJwtToken(validToken);

    assertTrue(actual);
  }

  private String validToken() {
    return TEST_TOKEN_BUILDER().compact();
  }

  private String wrongSignature() {
    return TEST_TOKEN_BUILDER()
        .signWith(SignatureAlgorithm.HS512, "differentSecret")
        .compact();
  }

  private String expiredToken() {
    return TEST_TOKEN_BUILDER()
        .setExpiration(new Date(System.currentTimeMillis() - JWT_TOKEN_VALIDITY.toMillis()))
        .compact();
  }

  private String noSignatureToken() {
    Date now = new Date();
    Date expiresIn = new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY.toMillis());
    return Jwts.builder()
        .setIssuedAt(now)
        .setExpiration(expiresIn)
        .compact();
  }

  private String malformedJwtExceptionToken() {
    return "anyString";
  }

}