package com.example.adenirf.movies.activities;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.adenirf.movies.R;
import com.example.adenirf.movies.pojo.Result;
import com.example.adenirf.movies.utils.MovieLoadToImageView;

public class ActivityDetailsMovie extends AppCompatActivity {

    private FrameLayout frameLayoutMain;
    private ImageView backdropPath;
    private ImageView miniPoster;
    private TextView tvTitle;
    private TextView tvDate;
    private TextView tvSinopse;
    private RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_movie);

        setTitle("Movie details");

        frameLayoutMain = findViewById(R.id.fl_main);
        miniPoster = findViewById(R.id.iv_mini_poster);
        tvTitle = findViewById(R.id.tv_titile);
        tvDate = findViewById(R.id.tv_date);
        tvSinopse = findViewById(R.id.tv_sinopse);
        ratingBar = findViewById(R.id.rb_rating);



        ratingBar.setStepSize(0.01f);
        ratingBar.setMax(10);
        ratingBar.setNumStars(5);
        ratingBar.setIsIndicator(true);

        backdropPath = findViewById(R.id.backdrop_path);
        backdropPath.setImageAlpha(100);

        Intent intent = getIntent();

        if (intent.hasExtra(MainActivity.EXTRA_MOVIE_DETAILS)) {
            Result result = (Result) intent.getSerializableExtra(MainActivity.EXTRA_MOVIE_DETAILS);

            String pathWithOutBar = (result.getPosterPath().replace("/", ""));

            MovieLoadToImageView.setImageIntoImageView(pathWithOutBar, backdropPath, true, true);
            MovieLoadToImageView.setImageIntoImageView(pathWithOutBar, miniPoster, true, false) ;

            tvTitle.setText(result.getTitle());
            tvTitle.setTypeface(null, Typeface.BOLD);
            tvDate.setText(result.getReleaseDate());
            tvDate.setTypeface(null, Typeface.BOLD);
            tvSinopse.setTypeface(null, Typeface.BOLD);

            tvSinopse.setText(result.getOverview());
            ratingBar.setRating(result.getVoteAverage().floatValue()/2);


            ObjectAnimator fadeIn = ObjectAnimator.ofFloat(frameLayoutMain, "alpha", 0f, 1f);
            fadeIn.setDuration(1500);

            fadeIn.start();

        }


    }
}
