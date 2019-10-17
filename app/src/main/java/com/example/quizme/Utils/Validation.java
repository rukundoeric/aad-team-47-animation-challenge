package com.example.quizme.Utils;

import android.app.Activity;
import android.view.View;

import com.example.quizme.Models.SignupDetail;
import com.example.quizme.R;

public class Validation {
    public static boolean signUpValidation(SignupDetail user, Activity activity) {

        boolean isValidFm = false;
        boolean isValidLm = false;
        boolean isValidEm = false;
        boolean isValidPs = false;
        if (android.util.Patterns.EMAIL_ADDRESS.matcher(user.email).matches()) {
            isValidEm = true;

            activity.findViewById(R.id.email_error).setVisibility(View.GONE);

        } else {
            isValidEm = false;
            activity.findViewById(R.id.email_error).setVisibility(View.VISIBLE);
        }

        if (user.firstName.length() >= 2) {
            isValidFm = true;
            activity.findViewById(R.id.firstname_error).setVisibility(View.GONE);
        } else {
            isValidFm = false;
            activity.findViewById(R.id.firstname_error).setVisibility(View.VISIBLE);
        }

        if (user.lastName.length() >= 2) {
            isValidLm = true;
            activity.findViewById(R.id.lastname_error).setVisibility(View.GONE);
        } else {
            isValidLm = false;
            activity.findViewById(R.id.lastname_error).setVisibility(View.VISIBLE);
        }

        if (user.password.length() >= 6) {
            isValidPs = true;
            activity.findViewById(R.id.password_error).setVisibility(View.GONE);
        } else {
            isValidPs = false;
            activity.findViewById(R.id.password_error).setVisibility(View.VISIBLE);
        }

        if(isValidEm && isValidFm && isValidLm && isValidPs ) { return true; }

        return false;
    }

}
