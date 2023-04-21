package com.splitscale.reems;

import static org.junit.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.ExportedUserRecord;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.auth.ListUsersPage;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.auth.UserRecord.CreateRequest;
import com.google.firebase.auth.UserRecord.UpdateRequest;

public class AppTest {

  FirebaseAuth auth;
  String uid;
  String customToken;

  public String generateRandomPhone() {
    Random rand = new Random();
    String phoneNumber = "+639";

    for (int i = 0; i < 9; i++) {
      int digit = rand.nextInt(10);
      phoneNumber += digit;
    }
    return phoneNumber;
  }

  @Before
  public void setup() throws IOException {
    uid = UUID.randomUUID().toString();

    FileInputStream serviceAccount = new FileInputStream("src/main/resources/serviceAccountKey.json");

    FirebaseOptions options = new FirebaseOptions.Builder()
        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
        .setDatabaseUrl("https://reems-b3fb1-default-rtdb.firebaseio.com")
        .build();

    if (FirebaseApp.getApps().isEmpty()) {
      FirebaseApp.initializeApp(options);
    }
    auth = FirebaseAuth.getInstance();
  }

  @AfterAll
  public void deleteUser() throws FirebaseAuthException {

    auth.deleteUser(uid);
    System.out.println("Successfully deleted user.");
  }

  @Test
  public void createUser() throws FirebaseAuthException {

    CreateRequest request = new CreateRequest()
        .setUid(uid)
        .setEmail("user-" + uid + "@example.com")
        .setPhoneNumber(generateRandomPhone())
        .setPassword("secretPassword")
        .setDisplayName("Steven Dy");

    UserRecord userRecord = auth.createUser(request);
    System.out.println("Successfully created new user: " + userRecord.getUid());
  }

  @Test
  public void retrieveData() throws FirebaseAuthException {
    UserRecord userRecord = auth.getUser(uid);

    System.out.println("Successfully fetched user data: " + userRecord.getUid());
  }

  @Test
  public void updateUser() throws FirebaseAuthException {
    UpdateRequest request = new UpdateRequest(uid)
        .setEmail("user-" + generateRandomPhone() + "@example.com")
        .setPhoneNumber(generateRandomPhone())
        .setPassword("newPassword")
        .setDisplayName("Jane Doe");

    UserRecord userRecord = auth.updateUser(request);
    System.out.println("Successfully updated user: " + userRecord.getUid());
  }

  @Test
  public void retrieveDataAfterUpdate() throws FirebaseAuthException {
    UserRecord userRecord = auth.getUser(uid);

    System.out.println("Successfully fetched user data: " + userRecord.getUid());
  }

  @Test
  public void getListOfAllfUsers() throws FirebaseAuthException {

    System.out.println("Getting user information...");

    ListUsersPage page = auth.listUsers(null);

    for (ExportedUserRecord user : page.iterateAll()) {
      System.out.println("User: " + user.getUid());
    }
  }

  @Test
  public void createCustomToken() throws FirebaseAuthException {
    Map<String, Object> additionalClaims = new HashMap<String, Object>();
    additionalClaims.put("role", "admin");

    customToken = auth.createCustomToken(uid, additionalClaims);

  }

  @Test
  public void verifyIdToken() throws FirebaseAuthException {
    // Verify the ID token first.
    FirebaseToken decoded = FirebaseAuth.getInstance().verifyIdToken(customToken);
    System.out.println(decoded.getClaims().get("role"));
  }
}
