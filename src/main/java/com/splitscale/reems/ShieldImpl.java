package com.splitscale.reems;

import java.io.IOException;
import java.security.GeneralSecurityException;

import com.splitscale.reems.security.auth.CredentialRequest;
import com.splitscale.reems.security.services.SecurityService;

public class ShieldImpl implements SecurityService {

  private ShieldDriver driver;

  public ShieldImpl(String baseUrl) {
    this.driver = new ShieldDriver(baseUrl);
  }

  public void setDriver(ShieldDriver driver) {
    this.driver = driver;
  }

  @Override
  public void register(CredentialRequest request) throws IOException, IllegalArgumentException {
    driver.register(request);
  }

  @Override
  public void validateJwt(String jwtToken, String userId) throws GeneralSecurityException, IOException {
    ValidateRequest validateRequest = new ValidateRequest(
        jwtToken,
        userId);

    driver.validateJwt(validateRequest);
  }
}
