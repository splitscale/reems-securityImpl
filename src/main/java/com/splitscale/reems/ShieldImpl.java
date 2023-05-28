package com.splitscale.reems;

import com.splitscale.reems.auth.CredentialRequest;
import com.splitscale.reems.services.SecurityService;

public class ShieldImpl implements SecurityService {

  private ShieldDriver driver;

  public ShieldImpl(String baseUrl) {
    this.driver = new ShieldDriver(baseUrl);
  }

  public void setDriver(ShieldDriver driver) {
    this.driver = driver;
  }

  @Override
  public ValidJwtResponse validate(String jwtToken, String validationData) {
    ValidateRequest validateRequest = new ValidateRequest(
      jwtToken,
      validationData
    );
    return driver.validateJwt(validateRequest);
  }

  @Override
  public String invalidate(String jwtToken) {
    return driver.invalidateJwt(jwtToken);
  }

  @Override
  public String register(CredentialRequest request) {
    return driver.register(request);
  }
}
