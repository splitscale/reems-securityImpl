package com.splitscale.reems;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class ShieldDriver {

  private String baseUrl;
  RestTemplate restTemplate;

  public ShieldDriver(String baseUrl) {
    this.baseUrl = baseUrl;
    this.restTemplate = new RestTemplate();
  }

  // UserRequest copy from shield
  public void register(UserRequest request) {
    String registrationUrl = baseUrl + "/api/v1/register";

    // Create headers with appropriate content type and any other required headers
    HttpHeaders headers = new HttpHeaders();
    headers.set("Content-Type", "application/json");

    // Create an HTTP entity with the user request and headers
    HttpEntity<UserRequest> httpEntity = new HttpEntity<>(request, headers);

    // Send an HTTP POST request to the registration endpoint
    ResponseEntity<String> response = restTemplate.exchange(
      registrationUrl,
      HttpMethod.POST,
      httpEntity,
      String.class
    );

    // Handle the response and perform necessary actions
    if (response.getStatusCode().is2xxSuccessful()) {
      System.out.println("User registration completed successfully.");
    } else {
      System.out.println(
        "User registration failed. Response status: " +
        response.getStatusCodeValue()
      );
    }
  }

  public LoginResponse login(UserRequest request) {
    String loginUrl = baseUrl + "/api/v1/login";

    // Create headers with appropriate content type and any other required headers
    HttpHeaders headers = new HttpHeaders();
    headers.set("Content-Type", "application/json");

    // Create an HTTP entity with the user request and headers
    HttpEntity<UserRequest> httpEntity = new HttpEntity<>(request, headers);

    // Send an HTTP POST request to the login endpoint
    ResponseEntity<LoginResponse> response = restTemplate.exchange(
      loginUrl,
      HttpMethod.POST,
      httpEntity,
      LoginResponse.class
    );

    // Handle the response and return the login response
    if (response.getStatusCode().is2xxSuccessful()) {
      System.out.println("User login completed successfully.");
      return response.getBody();
    } else {
      System.out.println(
        "User login failed. Response status: " + response.getStatusCodeValue()
      );
      return null;
    }
  }

  public String invalidateJwt(String jwtToken) {
    String invalidateUrl = baseUrl + "/api/v1/inValidateJwt";

    // Create headers with appropriate content type and any other required headers
    HttpHeaders headers = new HttpHeaders();
    headers.set("Content-Type", "application/json");

    // Create an HTTP entity with the JWT token and headers
    HttpEntity<String> httpEntity = new HttpEntity<>(jwtToken, headers);

    // Send an HTTP POST request to the invalidateJwt endpoint
    ResponseEntity<String> response = restTemplate.exchange(
      invalidateUrl,
      HttpMethod.POST,
      httpEntity,
      String.class
    );

    // Handle the response and return the result
    if (response.getStatusCode().is2xxSuccessful()) {
      System.out.println("JWT invalidation completed successfully.");
      return response.getBody();
    } else {
      System.out.println(
        "JWT invalidation failed. Response status: " +
        response.getStatusCodeValue()
      );
      return null;
    }
  }

  public ValidJwtResponse validateJwt(ValidateRequest request) {
    String validateUrl = baseUrl + "/api/v1/validateJwt";

    // Create headers with appropriate content type and any other required headers
    HttpHeaders headers = new HttpHeaders();
    headers.set("Content-Type", "application/json");

    // Create an HTTP entity with the validate request and headers
    HttpEntity<ValidateRequest> httpEntity = new HttpEntity<>(request, headers);

    // Send an HTTP POST request to the validateJwt endpoint
    ResponseEntity<ValidJwtResponse> response = restTemplate.exchange(
      validateUrl,
      HttpMethod.POST,
      httpEntity,
      ValidJwtResponse.class
    );

    // Handle the response and return the validation response
    if (response.getStatusCode().is2xxSuccessful()) {
      System.out.println("JWT validation completed successfully.");
      return response.getBody();
    } else {
      System.out.println(
        "JWT validation failed. Response status: " +
        response.getStatusCodeValue()
      );
      return null;
    }
  }
}
