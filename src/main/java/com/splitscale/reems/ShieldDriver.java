package com.splitscale.reems;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.splitscale.reems.security.auth.CredentialRequest;

public class ShieldDriver {

  private String baseUrl;
  private RestTemplate restTemplate;

  public ShieldDriver(String baseUrl) {
    this.baseUrl = baseUrl;
    this.restTemplate = new RestTemplate();
  }

  public String register(CredentialRequest request) throws IOException, IllegalArgumentException {
    String registrationUrl = baseUrl + "/api/v1/register";

    // Create headers with appropriate content type and any other required headers
    HttpHeaders headers = new HttpHeaders();
    headers.set("Content-Type", "application/json");

    // Create an HTTP entity with the user request and headers
    HttpEntity<CredentialRequest> httpEntity = new HttpEntity<>(request, headers);

    // Send an HTTP POST request to the registration endpoint
    ResponseEntity<String> response = restTemplate.exchange(
        registrationUrl,
        HttpMethod.POST,
        httpEntity,
        String.class);

    // Handle the response and perform necessary actions
    if (response.getStatusCode().is2xxSuccessful()) {
      // User registration completed successfully.
    }

    if (response.getStatusCode().is4xxClientError()) {
      throw new IllegalArgumentException("Client error occurred: " + response.getStatusCodeValue());
    }

    if (response.getStatusCode().is5xxServerError()) {
      throw new IOException("Server error occurred: " + response.getStatusCodeValue());
    }

    if (response.getStatusCode().isError()) {
      throw new IOException("Server error occurred: " + response.getStatusCodeValue());
    }

    return response.getBody();
  }

  public ValidJwtResponse validateJwt(ValidateRequest request) throws GeneralSecurityException, IOException {
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
        ValidJwtResponse.class);

    // Handle the response and return the validation response
    if (response.getStatusCode().is2xxSuccessful()) {
      // JWT validation completed successfully.
      return response.getBody();
    }

    if (response.getStatusCode().is4xxClientError()) {
      throw new IllegalArgumentException("Client error occurred: " + response.getStatusCodeValue());
    }

    if (response.getStatusCode().is5xxServerError()) {
      throw new IOException("Server error occurred: " + response.getStatusCodeValue());
    }

    if (response.getStatusCode().isError()) {
      throw new IOException("Server error occurred: " + response.getStatusCodeValue());
    }

    return response.getBody();
  }
}
