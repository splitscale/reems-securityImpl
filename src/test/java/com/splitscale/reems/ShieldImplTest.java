package com.splitscale.reems;

import com.splitscale.reems.auth.CredentialRequest;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ShieldImplTest {

  private ShieldDriver mockDriver;
  private ShieldImpl shield;

  @Before
  public void setup() {
    mockDriver = mock(ShieldDriver.class);
    shield = new ShieldImpl("http://example.com");
    shield.setDriver(mockDriver);
  }

  @Test
  public void testValidate() {
    String jwtToken = "sampleJwtToken";
    String validationData = "sampleValidationData";
    ValidJwtResponse expectedResponse = new ValidJwtResponse(false);

    // Mock the behavior of the ShieldDriver's validateJwt method
    when(mockDriver.validateJwt(any())).thenReturn(expectedResponse);

    ValidJwtResponse actualResponse = shield.validate(jwtToken, validationData);

    assertEquals(expectedResponse, actualResponse);
  }

  @Test
  public void testInvalidate() {
    String jwtToken = "sampleJwtToken";
    String expectedResponse = "Invalidation successful";

    // Mock the behavior of the ShieldDriver's invalidateJwt method
    when(mockDriver.invalidateJwt(jwtToken)).thenReturn(expectedResponse);

    String actualResponse = shield.invalidate(jwtToken);

    assertEquals(expectedResponse, actualResponse);
  }

  @Test
  public void testRegister() {
    CredentialRequest request = new CredentialRequest(null, null);
    String expectedResponse = "Registration successful";

    // Mock the behavior of the ShieldDriver's register method
    when(mockDriver.register(request)).thenReturn(expectedResponse);

    String actualResponse = shield.register(request);

    assertEquals(expectedResponse, actualResponse);
  }
}
