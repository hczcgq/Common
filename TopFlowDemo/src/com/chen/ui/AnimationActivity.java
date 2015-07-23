package com.chen.ui;

import com.chen.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

public class AnimationActivity extends Activity {

    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_animation);
        image = (ImageView) findViewById(R.id.image);
    }

    public void Rotate(View view) {
//        AnimationSet animationSet = new AnimationSet(true);
//        RotateAnimation rotateAnimation = new RotateAnimation(0, 360,
//                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
//                0.5f);
//        rotateAnimation.setDuration(1000);
//        animationSet.addAnimation(rotateAnimation);
//        image.startAnimation(animationSet);
        
        Animation animation=AnimationUtils.loadAnimation(this, R.anim.rotate);
        image.startAnimation(animation);
    }

    public void Scale(View view) {
//        AnimationSet animationSet = new AnimationSet(true);
//        ScaleAnimation scaleAnimation = new ScaleAnimation(0, 0.1f, 0, 0.1f,
//                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
//                0.5f);
//        scaleAnimation.setDuration(1000);
//        animationSet.addAnimation(scaleAnimation);
//        image.startAnimation(animationSet);
        
        Animation animation=AnimationUtils.loadAnimation(this, R.anim.scale);
        image.startAnimation(animation);
    }

    public void Alpha(View view) {
//        AnimationSet animationSet = new AnimationSet(true);
//        AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0);
//        alphaAnimation.setDuration(1000);
//        animationSet.addAnimation(alphaAnimation);
//        image.startAnimation(animationSet);
        
        Animation animation=AnimationUtils.loadAnimation(this, R.anim.alpha);
        image.startAnimation(animation);
    }

    public void Translate(View view) {
//        AnimationSet animationSet = new AnimationSet(true);
//        TranslateAnimation translateAnimation = new TranslateAnimation(
//                Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF,
//                0.5f, Animation.RELATIVE_TO_SELF, 0f,
//                Animation.RELATIVE_TO_SELF, 0.5f);
//        translateAnimation.setDuration(1000);
//        animationSet.addAnimation(translateAnimation);
//        image.startAnimation(animationSet);
        
        Animation animation=AnimationUtils.loadAnimation(this, R.anim.translate);
        image.startAnimation(animation);
    }
}
