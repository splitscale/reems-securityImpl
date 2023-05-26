package com.splitscale.reems;

public class LoginResponse {

  String token;
  UserResponse userResponse;

  public LoginResponse(String token, UserResponse userResponse) {
    this.token = token;
    this.userResponse = userResponse;
  }

  public String getToken() {
    return token;
  }

  public UserResponse getUserResponse() {
    return userResponse;
  }
}
