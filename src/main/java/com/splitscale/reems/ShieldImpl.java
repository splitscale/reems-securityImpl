package com.splitscale.reems;

import com.splitscale.reems.services.ShieldService;

public class ShieldImpl implements ShieldService {

  private ShieldDriver driver;

  public ShieldImpl(String baseUrl) {
    this.driver = new ShieldDriver(baseUrl);
  }

  @Override
  public LoginResponse login(UserRequest request) {
    return driver.login(request);
  }

  @Override
  public void register(UserRequest request) {
    driver.register(request);
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
}
