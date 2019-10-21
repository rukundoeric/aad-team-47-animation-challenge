package com.example.quizme.UI.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.example.quizme.R;

import org.w3c.dom.Text;

public class Signin extends AppCompatActivity {
    Button btnLogin;
    TextView textSignUp;
    TextView txtQuizMe;

    Animation anim1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        //Initialize UI
        txtQuizMe = (TextView)findViewById(R.id.fullscreen_content);
        btnLogin = (Button)findViewById(R.id.btnLogin);
        textSignUp = (TextView)findViewById(R.id.textSignUp);

        //Initialize Animations
        anim1 = AnimationUtils.loadAnimation(this, R.anim.anim1);

        //Set Animations
        txtQuizMe.setAnimation(anim1);

       //Implement OnClickListeners
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Signin.this,CourseListActivity.class));

                //TODO: Add Firebase Auth
            }
        });

        textSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Signin.this,SignupActivity.class));
            }
        });
    }
}
