package com.example.adenirf.movies.mvp;

import com.example.adenirf.movies.pojo.MoviesList;

public interface MoviesContract {
    interface view {
        void showList(MoviesList moviesList);
        void showError(String mensage);
    }

    interface presenter {
        void getListMovies(String page, String typeOfMovies);
    }
}
