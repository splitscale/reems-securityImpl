// package com.splitscale.reems;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.mockito.Mockito.mock;
// import static org.mockito.Mockito.verify;
// import static org.mockito.Mockito.when;

// import com.splitscale.reems.services.ShieldService;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;

// public class ShieldImplTest {

//   private ShieldDriver shieldDriver;
//   private ShieldService shieldService;

//   @BeforeEach
//   public void setUp() {
//     // Create a mock ShieldDriver
//     shieldDriver = mock(ShieldDriver.class);

//     // Create an instance of ShieldImpl with the mock ShieldDriver
//     shieldService = new ShieldImpl("http://example.com");
//     ((ShieldImpl) shieldService).setShieldDriver(shieldDriver);
//   }

//   @Test
//   public void testLogin() {
//     // Set up the mock behavior
//     UserRequest userRequest = new UserRequest("username", "password");
//     LoginResponse expectedResult = new LoginResponse("exampleToken", null);
//     when(shieldDriver.login(userRequest)).thenReturn(expectedResult);

//     // Call the login method
//     Object result = shieldService.login(userRequest);

//     // Verify the interaction and the result
//     verify(shieldDriver).login(userRequest);
//     assertEquals(expectedResult, result);
//   }

//   @Test
//   public void testRegister() {
//     // Set up the mock behavior
//     UserRequest userRequest = new UserRequest("username", "password");

//     // Call the register method
//     String result = shieldService.register(userRequest);

//     // Verify the interaction and the result
//     verify(shieldDriver).register(userRequest);
//     assertEquals("User registration completed.", result);
//   }

//   @Test
//   public void testValidate() {
//     // Set up the mock behavior
//     String jwtToken = "exampleToken";
//     String validationData = "exampleData";
//     ValidateRequest validateRequest = new ValidateRequest(
//       jwtToken,
//       validationData
//     );
//     ValidJwtResponse expectedResult = new ValidJwtResponse(true);
//     when(shieldDriver.validateJwt(validateRequest)).thenReturn(expectedResult);

//     // Call the validate method
//     Object result = shieldService.validate(jwtToken, validationData);

//     // Verify the interaction and the result
//     verify(shieldDriver).validateJwt(validateRequest);
//     assertEquals(expectedResult, result);
//   }

//   @Test
//   public void testInvalidate() {
//     // Set up the mock behavior
//     String jwtToken = "exampleToken";
//     String expectedResult = "Invalidation success";
//     when(shieldDriver.invalidateJwt(jwtToken)).thenReturn(expectedResult);

//     // Call the invalidate method
//     String result = shieldService.invalidate(jwtToken);

//     // Verify the interaction and the result
//     verify(shieldDriver).invalidateJwt(jwtToken);
//     assertEquals(expectedResult, result);
//   }
// }
