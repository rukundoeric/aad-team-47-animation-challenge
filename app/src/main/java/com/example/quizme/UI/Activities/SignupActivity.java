package com.example.quizme.UI.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.quizme.Firebase.FirebaseUtil;
import com.example.quizme.MainActivity;
import com.example.quizme.Models.SignupDetail;
import com.example.quizme.R;
import com.example.quizme.Utils.Helper;
import com.example.quizme.Utils.Validation;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


public class SignupActivity extends AppCompatActivity {
    private static final int PICTURE_REQUEST = 42;
    EditText firstName;
    EditText lastName;
    EditText email;
    EditText password;
    TextView firstNameError;
    Button  btnSignUp;
    ImageView profileImg;
    private DatabaseReference mDatabaseReference;
    private StorageReference mStorageRef;
    private ProgressDialog progressBar;
    private SignupDetail user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseUtil.openFbReference();
        setContentView(R.layout.activity_signup);
        mDatabaseReference = FirebaseUtil.mDatabaseReference;
        mStorageRef = FirebaseUtil.mStorageRef;
        progressBar = new ProgressDialog(this);
        user= new SignupDetail();
        firstName = (EditText) findViewById(R.id.firstName);
        lastName = (EditText) findViewById(R.id.lastName);
        email =(EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.createPass);
        btnSignUp = (Button) findViewById(R.id.btnSign);
        profileImg = (ImageView) findViewById(R.id.defaultImg);
        firstNameError = (TextView) findViewById(R.id.firstname_error);

        profileImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/jpeg");
                intent.putExtra(intent.EXTRA_LOCAL_ONLY,true);
                startActivityForResult(intent.createChooser(intent,"Choose Picture"), PICTURE_REQUEST);
            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreatingUser();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICTURE_REQUEST && resultCode == RESULT_OK){
            Uri imageUri = data.getData();
            final StorageReference ref = mStorageRef.child("pictures").child(imageUri.getLastPathSegment());
            progressBar.setMessage("Uploading Image...");
            progressBar.show();
            Glide.with(SignupActivity.this)
                    .load(imageUri)
                    .apply(new RequestOptions()
                            .optionalFitCenter()
                            .placeholder(R.drawable.avatar_default)
                            .centerCrop())
                    .into(profileImg);
            ref.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            Uri downloadUrl = uri;
                            String fileUrl = downloadUrl.toString();
                            user.imageUrl=fileUrl;
                            progressBar.dismiss();
                        }
                    });
                }
            });
        }

    }


    public void CreatingUser() {
        user.firstName=firstName.getText().toString();
        user.lastName=lastName.getText().toString();
        user.email=email.getText().toString();
        user.password=password.getText().toString();

        if(Validation.signUpValidation(user, this)){
            mDatabaseReference.child("Users").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if(dataSnapshot.hasChild(Helper.subString(user.email))){
                        Toast.makeText(SignupActivity.this,"User already exists",Toast.LENGTH_LONG).show();
                    } else {
                        mDatabaseReference.child("Users").child(Helper.subString(user.email)).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Intent intent = new Intent(SignupActivity.this, CourseListActivity.class);
                                startActivity(intent);

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(SignupActivity.this,e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }

}
