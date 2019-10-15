package com.example.quizme.UI.Activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quizme.R;
import com.example.quizme.Utils.Helper;

public class LoginActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Helper.setFullScreenView(this, R.layout.activity_login);
  }

}
