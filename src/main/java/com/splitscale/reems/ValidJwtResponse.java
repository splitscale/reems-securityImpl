package com.splitscale.reems;

import io.jsonwebtoken.Claims;

public class ValidJwtResponse {

  String token;
  Claims claims;

  public ValidJwtResponse(String token, Claims claims) {
    this.token = token;
    this.claims = claims;
  }

  public String getToken() {
    return token;
  }

  public Claims getClaims() {
    return claims;
  }
}
