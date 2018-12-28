package com.example.adenirf.movies.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.adenirf.movies.BuildConfig;
import com.example.adenirf.movies.R;
import com.example.adenirf.movies.adapter.CLickListener;
import com.example.adenirf.movies.adapter.ItemOffSetDecoration;
import com.example.adenirf.movies.adapter.MovieAdapter;
import com.example.adenirf.movies.mvp.MoviesContract;
import com.example.adenirf.movies.mvp.MoviesPresenter;
import com.example.adenirf.movies.pojo.MoviesList;
import com.example.adenirf.movies.pojo.Result;
import com.example.adenirf.movies.utils.ConnectionUtils;

public class MainActivity extends AppCompatActivity implements MoviesContract.view {
    private RecyclerView rvListMovies;

    private CLickListener cLickListener;
    public static final String EXTRA_MOVIE_DETAILS = "movie_details";

    private MovieAdapter adapter;
    private MoviesPresenter presenter;

    private AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        cLickListener = new CLickListener() {
            @Override
            public void OnClickListener(Result result) {
                Intent intent = new Intent(getApplicationContext(), ActivityDetailsMovie.class);
                intent.putExtra(EXTRA_MOVIE_DETAILS, result);
                startActivity(intent);
            }
        };

        if (!ConnectionUtils.isThereInternet(this)) {
            setAlertDialog("Sem internet, conecte e tente novamente.");
        } else {
            rvListMovies = findViewById(R.id.rw_list_movies);

            setTitle("Easy movies");

            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false);
            rvListMovies.setLayoutManager(layoutManager);
            rvListMovies.setHasFixedSize(true);

            ItemOffSetDecoration decoration = new ItemOffSetDecoration(0);
            rvListMovies.addItemDecoration(decoration);

            String parameterValue = BuildConfig.MY_MOVIE_DB_API_KEY;


            presenter = new MoviesPresenter(parameterValue, this, this);

            String pathPopularMovies = "popular";
            String page = "1";

            presenter.getListMovies(page, pathPopularMovies);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String page = "1";
        switch (item.getItemId()) {
            case R.id.popular:
                String pathPopularMovies = "popular";
                presenter.getListMovies(page, pathPopularMovies);
                break;

            case R.id.top_rated:
                String pathLastestMovies = "top_rated";
                presenter.getListMovies(page, pathLastestMovies);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showList(MoviesList moviesList) {
        adapter = new MovieAdapter(moviesList.getResults());
        adapter.setcLickListener(cLickListener);
        rvListMovies.setAdapter(adapter);
    }

    @Override
    public void showError(String mensage) {

    }

    private void setAlertDialog(String mensage) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Ops... algo errado!");
        builder.setMessage(mensage);
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        alertDialog = builder.create();
        alertDialog.show();
    }
}
