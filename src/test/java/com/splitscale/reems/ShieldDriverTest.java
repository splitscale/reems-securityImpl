package com.splitscale.reems;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

@RunWith(MockitoJUnitRunner.class)
public class ShieldDriverTest {

  private static final String BASE_URL = "http://example.com";
  private static final String REGISTRATION_URL = BASE_URL + "/api/v1/register";
  private static final String LOGIN_URL = BASE_URL + "/api/v1/login";
  private static final String INVALIDATE_URL =
    BASE_URL + "/api/v1/inValidateJwt";
  private static final String VALIDATE_URL = BASE_URL + "/api/v1/validateJwt";

  @Mock
  private RestTemplate restTemplate;

  private ShieldDriver shieldDriver;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
    shieldDriver = new ShieldDriver(BASE_URL);
    shieldDriver.restTemplate = restTemplate;
  }

  @Test
  public void testRegister_Successful() {
    // Mock the response entity
    ResponseEntity<String> responseEntity = new ResponseEntity<>(HttpStatus.OK);
    when(
      restTemplate.exchange(
        eq(REGISTRATION_URL),
        eq(HttpMethod.POST),
        any(HttpEntity.class),
        eq(String.class)
      )
    )
      .thenReturn(responseEntity);

    // Create a sample UserRequest
    UserRequest userRequest = new UserRequest();

    // Invoke the register method
    shieldDriver.register(userRequest);

    // Verify that the exchange method was called with the correct arguments
    verify(restTemplate)
      .exchange(
        eq(REGISTRATION_URL),
        eq(HttpMethod.POST),
        any(HttpEntity.class),
        eq(String.class)
      );
  }

  @Test
  public void testRegister_Failure() {
    // Mock the response entity
    ResponseEntity<String> responseEntity = new ResponseEntity<>(
      HttpStatus.INTERNAL_SERVER_ERROR
    );
    when(
      restTemplate.exchange(
        eq(REGISTRATION_URL),
        eq(HttpMethod.POST),
        any(HttpEntity.class),
        eq(String.class)
      )
    )
      .thenReturn(responseEntity);

    // Create a sample UserRequest
    UserRequest userRequest = new UserRequest();

    // Invoke the register method
    shieldDriver.register(userRequest);

    // Verify that the exchange method was called with the correct arguments
    verify(restTemplate)
      .exchange(
        eq(REGISTRATION_URL),
        eq(HttpMethod.POST),
        any(HttpEntity.class),
        eq(String.class)
      );
  }

  public void testLogin_Successful() {
    // Mock the response entity
    LoginResponse loginResponse = new LoginResponse(null, null);
    ResponseEntity<LoginResponse> responseEntity = new ResponseEntity<>(
      loginResponse,
      HttpStatus.OK
    );
    when(
      restTemplate.exchange(
        eq(LOGIN_URL),
        eq(HttpMethod.POST),
        any(HttpEntity.class),
        eq(LoginResponse.class)
      )
    )
      .thenReturn(responseEntity);

    // Create a sample UserRequest
    UserRequest userRequest = new UserRequest();

    // Invoke the login method
    LoginResponse result = shieldDriver.login(userRequest);

    // Verify that the exchange method was called with the correct arguments
    verify(restTemplate)
      .exchange(
        eq(LOGIN_URL),
        eq(HttpMethod.POST),
        any(HttpEntity.class),
        eq(LoginResponse.class)
      );

    // Verify that the login response is returned correctly
    assertEquals(loginResponse, result);
  }

  @Test
  public void testLogin_Failure() {
    // Mock the response entity
    ResponseEntity<LoginResponse> responseEntity = new ResponseEntity<>(
      HttpStatus.INTERNAL_SERVER_ERROR
    );
    when(
      restTemplate.exchange(
        eq(LOGIN_URL),
        eq(HttpMethod.POST),
        any(HttpEntity.class),
        eq(LoginResponse.class)
      )
    )
      .thenReturn(responseEntity);

    // Create a sample UserRequest
    UserRequest userRequest = new UserRequest();

    // Invoke the login method
    LoginResponse result = shieldDriver.login(userRequest);

    // Verify that the exchange method was called with the correct arguments
    verify(restTemplate)
      .exchange(
        eq(LOGIN_URL),
        eq(HttpMethod.POST),
        any(HttpEntity.class),
        eq(LoginResponse.class)
      );

    // Verify that null is returned for failed login
    assertEquals(null, result);
  }

  @Test
  public void testValidateJwt_Successful() {
    // Mock the response entity
    ValidJwtResponse validJwtResponse = new ValidJwtResponse(null, null);
    ResponseEntity<ValidJwtResponse> responseEntity = new ResponseEntity<>(
      validJwtResponse,
      HttpStatus.OK
    );
    when(
      restTemplate.exchange(
        eq(VALIDATE_URL),
        eq(HttpMethod.POST),
        any(HttpEntity.class),
        eq(ValidJwtResponse.class)
      )
    )
      .thenReturn(responseEntity);

    // Create a sample ValidateRequest
    ValidateRequest validateRequest = new ValidateRequest();

    // Invoke the validateJwt method
    ValidJwtResponse result = shieldDriver.validateJwt(validateRequest);

    // Verify that the exchange method was called with the correct arguments
    verify(restTemplate)
      .exchange(
        eq(VALIDATE_URL),
        eq(HttpMethod.POST),
        any(HttpEntity.class),
        eq(ValidJwtResponse.class)
      );

    // Verify that the validation response is returned correctly
    assertEquals(validJwtResponse, result);
  }

  @Test
  public void testValidateJwt_Failure() {
    // Mock the response entity
    ResponseEntity<ValidJwtResponse> responseEntity = new ResponseEntity<>(
      HttpStatus.INTERNAL_SERVER_ERROR
    );
    when(
      restTemplate.exchange(
        eq(VALIDATE_URL),
        eq(HttpMethod.POST),
        any(HttpEntity.class),
        eq(ValidJwtResponse.class)
      )
    )
      .thenReturn(responseEntity);

    // Create a sample ValidateRequest
    ValidateRequest validateRequest = new ValidateRequest();

    // Invoke the validateJwt method
    ValidJwtResponse result = shieldDriver.validateJwt(validateRequest);

    // Verify that the exchange method was called with the correct arguments
    verify(restTemplate)
      .exchange(
        eq(VALIDATE_URL),
        eq(HttpMethod.POST),
        any(HttpEntity.class),
        eq(ValidJwtResponse.class)
      );

    // Verify that null is returned for failed validation
    assertEquals(null, result);
  }

  @Test
  public void testInvalidateJwt_Successful() {
    // Mock the response entity
    ResponseEntity<String> responseEntity = new ResponseEntity<>(HttpStatus.OK);
    when(
      restTemplate.exchange(
        eq(INVALIDATE_URL),
        eq(HttpMethod.POST),
        any(HttpEntity.class),
        eq(String.class)
      )
    )
      .thenReturn(responseEntity);

    // Create a sample JWT token
    String jwtToken = "sample-jwt-token";

    // Invoke the invalidateJwt method
    String result = shieldDriver.invalidateJwt(jwtToken);

    // Verify that the exchange method was called with the correct arguments
    verify(restTemplate)
      .exchange(
        eq(INVALIDATE_URL),
        eq(HttpMethod.POST),
        any(HttpEntity.class),
        eq(String.class)
      );

    // Verify that the response body is returned correctly
    assertEquals(responseEntity.getBody(), result);
  }

  @Test
  public void testInvalidateJwt_Failure() {
    // Mock the response entity
    ResponseEntity<String> responseEntity = new ResponseEntity<>(
      HttpStatus.INTERNAL_SERVER_ERROR
    );
    when(
      restTemplate.exchange(
        eq(INVALIDATE_URL),
        eq(HttpMethod.POST),
        any(HttpEntity.class),
        eq(String.class)
      )
    )
      .thenReturn(responseEntity);

    // Create a sample JWT token
    String jwtToken = "sample-jwt-token";

    // Invoke the invalidateJwt method
    String result = shieldDriver.invalidateJwt(jwtToken);

    // Verify that the exchange method was called with the correct arguments
    verify(restTemplate)
      .exchange(
        eq(INVALIDATE_URL),
        eq(HttpMethod.POST),
        any(HttpEntity.class),
        eq(String.class)
      );

    // Verify that null is returned for failed JWT invalidation
    assertEquals(null, result);
  }
}
