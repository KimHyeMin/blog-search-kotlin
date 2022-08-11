package com.lily.userauth.security;

import java.util.Arrays;
import java.util.List;


public class AccessiblePath {

  public static final String MAIN = "/";
  public static final String LOGIN = "/api/v1/user/login";
  public static final String REGISTER = "/api/v1/user/register";
  public static final String H2_CONSOLE = "/h2-console/**";
  public static final String SWAGGER_UI = "/swagger-ui/**";

  public static final List<String> SKIP_FILTER_URLS =  Arrays.asList(LOGIN, REGISTER, H2_CONSOLE, SWAGGER_UI);

}
