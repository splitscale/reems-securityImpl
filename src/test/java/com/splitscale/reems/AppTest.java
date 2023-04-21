package com.splitscale.reems;

import java.io.FileInputStream;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.ExportedUserRecord;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
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
  public void getListOfUsers() throws FirebaseAuthException {

    System.out.println("Getting user information...");

    ListUsersPage page = auth.listUsers(null);

    for (ExportedUserRecord user : page.iterateAll()) {
      System.out.println("User: " + user.getUid());
    }
  }

  @Test
  public void createToken() throws FirebaseAuthException {
    String uid = "some-uid";
    String customToken = FirebaseAuth.getInstance().createCustomToken(uid);
    // Send token back to client
    System.out.println("token: " + customToken);
  }

  // @Test
  // public void verifyToken() throws FirebaseAuthException {
  //   // idToken comes from the client app (shown above)
  //   FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);
  //   String uid = decodedToken.getUid();
  //   System.out.println("decodedtoken: " + uid);
  // }
}
