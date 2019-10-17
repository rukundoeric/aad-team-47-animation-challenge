package com.example.quizme.Utils;

import android.app.Activity;
import android.os.Build;
import android.view.View;

public class Helper {
    public static void setFullScreenView(Activity context, int activity) {
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) {
            View v = context.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            View decorView = context.getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
        context.setContentView(activity);
    }

    public static String subString(String string){
        char a = '@';
        int pos = 0;
        StringBuilder str = new StringBuilder(string);
        for (int i= 0; i<str.length();i++){
            if(str.charAt(i) == a){
                pos = i;
            }
        }
        return string.substring(0, pos);
    }
}