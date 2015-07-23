package com.chen.ui;

import com.chen.R;
import com.chen.view.FlexibleRatingBar;
import com.chen.view.RatingBar;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class RatingActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_rating);
        RatingBar mRatingBar = (RatingBar) findViewById(R.id.ratingbar);
        mRatingBar
                .setOnRatingChangeListener(new RatingBar.OnRatingChangeListener() {
                    @Override
                    public void onRatingChange(int RatingCount) {
                        Toast.makeText(RatingActivity.this,
                                "the fill star is" + RatingCount,
                                Toast.LENGTH_LONG).show();
                    }
                });

        // mRatingBar.setStar(5);
        // mRatingBar.setmClickable(true);
        // mRatingBar.setStarImageSize(16f);
        // mRatingBar.setStarEmptyDrawable(getResources().getDrawable(R.mipmap.ic_star_empty));
        // mRatingBar.setStarFillDrawable(getResources().getDrawable(R.mipmap.ic_star_fill));
   
        FlexibleRatingBar ratingBar = (FlexibleRatingBar) findViewById(R.id.flexibleRatingBar);
        FlexibleRatingBar ratingBar2 = (FlexibleRatingBar) findViewById(R.id.flexibleRatingBar2);
        FlexibleRatingBar ratingBar3 = (FlexibleRatingBar) findViewById(R.id.flexibleRatingBar3);
        FlexibleRatingBar ratingBar4 = (FlexibleRatingBar) findViewById(R.id.flexibleRatingBar4);
        FlexibleRatingBar ratingBar5 = (FlexibleRatingBar) findViewById(R.id.flexibleRatingBar5);

        ratingBar2.setPolygonVertices(0);
        ratingBar3.setPolygonVertices(4);
        ratingBar3.setStrokeWidth(5);
        ratingBar4.setPolygonVertices(6);
        ratingBar4.setColorFillOn(Color.rgb(111, 63, 182));
        ratingBar4.setColorFillOff(Color.argb(55,145,122,235));
        ratingBar5.setInteriorAngleModifier(1.5F);
        
       
    }
}
