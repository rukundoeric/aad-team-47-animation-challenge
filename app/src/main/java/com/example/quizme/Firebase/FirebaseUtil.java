package com.example.quizme.Firebase;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class FirebaseUtil {
  public static FirebaseDatabase mFirebaseDatabase;
  public static DatabaseReference mDatabaseReference;
  public static FirebaseStorage mStorage;
  public static StorageReference mStorageRef;
  public String mRef;
  public static FirebaseUtil firebaseUtil;

  private FirebaseUtil(){}

  public static void openFbReference(){
    if(mFirebaseDatabase == null){
      mFirebaseDatabase  = FirebaseDatabase.getInstance();
      mDatabaseReference = mFirebaseDatabase.getReference();
    }
    if (mStorage == null){
      mStorage = FirebaseStorage.getInstance();
      mStorageRef = mStorage.getReference();
    }
  }
}
