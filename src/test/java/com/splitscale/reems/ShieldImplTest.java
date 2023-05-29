package com.splitscale.reems;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

import com.splitscale.reems.security.auth.CredentialRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class ShieldImplTest {

  @Test
  public void testRegister() throws IOException, IllegalArgumentException {
    // Create mock objects
    ShieldDriver mockDriver = mock(ShieldDriver.class);
    CredentialRequest mockRequest = mock(CredentialRequest.class);

    // Create object to test
    ShieldImpl shield = new ShieldImpl("http://example.com");
    shield.setDriver(mockDriver);

    // Call method to test
    shield.register(mockRequest);

    // Verify that the driver's register method was called with the correct argument
    verify(mockDriver).register(mockRequest);
  }

  @Test
  public void testValidateJwt() throws GeneralSecurityException, IOException {
    // Create mock objects
    ShieldDriver mockDriver = mock(ShieldDriver.class);

    // Create object to test
    ShieldImpl shield = new ShieldImpl("http://example.com");
    shield.setDriver(mockDriver);

    // Call method to test
    shield.validateJwt("jwtToken", "userId");

    // Create ArgumentCaptor to capture the argument passed to the validateJwt
    // method
    ArgumentCaptor<ValidateRequest> argument = ArgumentCaptor.forClass(ValidateRequest.class);
    verify(mockDriver).validateJwt(argument.capture());

    // Get the captured argument
    ValidateRequest capturedValidateRequest = argument.getValue();

    // Verify that the captured argument has the correct values
    assertEquals("jwtToken", capturedValidateRequest.getJwtToken());
    assertEquals("userId", capturedValidateRequest.getUserId());
  }

}
