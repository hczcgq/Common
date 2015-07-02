package com.chen.ui;

import com.chen.R;
import com.chen.view.RatingBar;

import android.app.Activity;
import android.os.Bundle;
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
    }
}
