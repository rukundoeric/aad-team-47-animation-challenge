package com.example.quizme.Firebase;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseUtil {
  private static FirebaseDatabase mFirebaseDatabase;
  private static DatabaseReference mDatabaseReference;
  private String mRef;
  private static FirebaseUtil firebaseUtil;

  public static DatabaseReference getFirebaseRef(String ref){
    return FirebaseDatabase.getInstance().getReference().child(ref);
  }
}
