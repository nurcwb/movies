package com.example.adenirf.movies.utils;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.net.Uri;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.net.MalformedURLException;
import java.net.URL;

public class MovieLoadToImageView {

    public static void setImageIntoImageView(String pathWithOutBar, ImageView imageView, boolean animation, boolean animationCameFromLeft) {
        Uri.Builder builder1 = new Uri.Builder();
        builder1.scheme("https").
                authority("image.tmdb.org").
                appendPath("t").
                appendPath("p").
                appendPath("w500").
                appendPath(pathWithOutBar);
        URL url = null;
        try {
            url = new URL(builder1.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        if (null != url) {
            Picasso.get().load(url.toString()).into(imageView);

            if (animation) {

                RotateAnimation anim = new RotateAnimation(0f, 350f, 15f, 15f);
                anim.setInterpolator(new LinearInterpolator());
                anim.setRepeatCount(Animation.ABSOLUTE);
                anim.setDuration(700);


                ObjectAnimator fadeOut = ObjectAnimator.ofFloat(imageView, "alpha", 0f);
                fadeOut.setDuration(700);
                //fadeOut.start();
                ObjectAnimator mover;
                if (animationCameFromLeft)
                    mover = ObjectAnimator.ofFloat(imageView, "translationX", 500f, 0f);
                else
                    mover = ObjectAnimator.ofFloat(imageView, "translationX", -500f, 0f);

                mover.setDuration(700);

                ObjectAnimator fadeIn = ObjectAnimator.ofFloat(imageView, "alpha", 0f, 1f);
                fadeIn.setDuration(700);

                //fadeIn.start();

                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.play(mover).with(fadeIn);

                mover.start();
            }
        }
    }
}
