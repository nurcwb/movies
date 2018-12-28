package com.example.adenirf.movies.mvp;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.adenirf.movies.pojo.MoviesList;
import com.example.adenirf.movies.utils.MySingletonVolley;
import com.example.adenirf.movies.utils.UrlUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.MalformedURLException;
import java.net.URL;

public class MoviesRepository {
    private String parameterApiKeyValue;

    private RequestQueue requestQueue;
    private URL url = null;

    public MoviesRepository(String parameterApiKeyValue, Context context) {
        this.parameterApiKeyValue = parameterApiKeyValue;
        requestQueue = MySingletonVolley.getInstance(context).getRequestQueue();
    }

    public void getMoviesList(String page, String typeOfMovies, final MoviesCallback moviesCallback) {
        url = UrlUtil.createURL(page, typeOfMovies, parameterApiKeyValue);
        StringRequest stringRequest;
        if (url != null) {
            stringRequest = setStringRequest(moviesCallback, url);
            requestQueue.add(stringRequest);
        }
    }



    @NonNull
    private StringRequest setStringRequest(final MoviesCallback moviesCallback, URL url) {
        return new StringRequest(
                Request.Method.GET,
                url.toString(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        GsonBuilder gsonBuilder = new GsonBuilder();
                        gsonBuilder.setDateFormat("M/d/yy hh:mm a");
                        Gson gson = new Gson();
                        MoviesList moviesList = gson.fromJson(
                                response,
                                MoviesList.class);
                        moviesCallback.onMovieLoaded(moviesList);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("volley", error.toString());
                    }
                });
    }

    public interface MoviesCallback {
        void onMovieLoaded(MoviesList moviesList);
    }
}
