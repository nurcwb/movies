package com.example.adenirf.movies.mvp;

import android.content.Context;

import com.example.adenirf.movies.mvp.MoviesRepository.MoviesCallback;
import com.example.adenirf.movies.pojo.MoviesList;

public class MoviesPresenter implements MoviesContract.presenter {

    private final String parameterApiKeyValue;
    private Context context;

    MoviesContract.view view;
    MoviesRepository moviesRepository;

    public MoviesPresenter(String parameterApiKeyValue, Context context, MoviesContract.view view) {
        this.parameterApiKeyValue = parameterApiKeyValue;
        this.context = context;
        this.view = view;
        moviesRepository = new MoviesRepository(parameterApiKeyValue, context);

    }

    @Override
    public void getListMovies(String page, String typeOfMovies) {
        moviesRepository.getMoviesList(page, typeOfMovies, new MoviesCallback() {
            @Override
            public void onMovieLoaded(MoviesList moviesList) {
                view.showList(moviesList);
            }
        });
    }
}
