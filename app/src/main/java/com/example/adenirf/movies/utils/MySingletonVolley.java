package com.example.adenirf.movies.utils;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class MySingletonVolley {
    private static MySingletonVolley mySingletonVolley;
    private RequestQueue requestQueue;
    private static Context context;

    public MySingletonVolley(Context context) {
        this.context = context;
        requestQueue =getRequestQueue();
    }

    public static synchronized MySingletonVolley getInstance(Context context) {
        if (mySingletonVolley == null) {
            mySingletonVolley = new MySingletonVolley(context);
        }
        return mySingletonVolley;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(this.context.getApplicationContext());
        }
        return requestQueue;
    }
}
