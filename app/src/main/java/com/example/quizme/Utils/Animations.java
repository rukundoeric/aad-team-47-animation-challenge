package com.example.quizme.Utils;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;

public class Animations {
  public static ObjectAnimator translationAnimation (View view, Float from, Float to){
    ObjectAnimator traslateAnimation = ObjectAnimator.ofFloat(view, "translationX", from, to);
    traslateAnimation.setDuration(1000);
    traslateAnimation.setRepeatCount(1);
    traslateAnimation.setRepeatMode(ValueAnimator.REVERSE);
    traslateAnimation.start();
    return traslateAnimation;
  }
}
