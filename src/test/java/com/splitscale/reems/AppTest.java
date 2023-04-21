package com.splitscale.reems;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.ExportedUserRecord;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.ListUsersPage;

public class AppTest {

  @BeforeAll
  public void setup() {
    FirebaseApp.initializeApp();
  }

  @Test
  public void testHelloWorld() throws FirebaseAuthException {
    // Start listing users from the beginning, 1000 at a time.
    ListUsersPage page = FirebaseAuth.getInstance().listUsers(null);
    while (page != null) {
      for (ExportedUserRecord user : page.getValues()) {
        System.out.println("User: " + user.getUid());
      }
      page = page.getNextPage();
    }
  }
}
