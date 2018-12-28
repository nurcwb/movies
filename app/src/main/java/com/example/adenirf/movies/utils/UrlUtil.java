package com.example.adenirf.movies.utils;

import android.net.Uri;

import java.net.MalformedURLException;
import java.net.URL;

public class UrlUtil {

    private static String scheme = "https";
    private static String authority = "api.themoviedb.org";
    private static String path1 = "3";
    private static String path2 = "movie";
    private static String parameterApiKey = "api_key";
    private static String parameterNunberOfPage = "page";
    private static String parameterLanguageKey = "language";
    private static String parameterLanguageValue = "pt-BR";

    public static URL createURL(String page, String typeOfMovies, String parameterApiKeyValue) {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme(scheme)
                .authority(authority)
                .appendPath(path1)
                .appendPath(path2)
                .appendPath(typeOfMovies)
                .appendQueryParameter(parameterApiKey, parameterApiKeyValue)
                .appendQueryParameter(parameterLanguageKey, parameterLanguageValue)
                .appendQueryParameter(parameterNunberOfPage, page);
        try {
            return new URL(builder.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
