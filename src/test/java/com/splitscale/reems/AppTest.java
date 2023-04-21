package com.splitscale.reems;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.ExportedUserRecord;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.ListUsersPage;

public class AppTest {

  FirebaseAuth auth;

  @Before
  public void setup() throws IOException {
    FileInputStream serviceAccount = new FileInputStream("src/main/resources/serviceAccountKey.json");

    FirebaseOptions options = new FirebaseOptions.Builder()
        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
        .setDatabaseUrl("https://reems-b3fb1-default-rtdb.firebaseio.com")
        .build();

    FirebaseApp.initializeApp(options);

    auth = FirebaseAuth.getInstance();
  }

  @Test
  public void testHelloWorld() throws FirebaseAuthException {

    System.out.println("Getting user information...");

    // Iterate through all users. This will still retrieve users in batches,
    // buffering no more than 1000 users in memory at a time.
    ListUsersPage page = auth.listUsers(null);

    for (ExportedUserRecord user : page.iterateAll()) {
      System.out.println("User: " + user.getUid());
    }

  }
}
