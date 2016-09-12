package hw_04.group5.com.imdbapp;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SearchMovie extends AppCompatActivity {

    ProgressDialog progressDialog;
    String movieSearched,url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_movie);

        if (getIntent().getExtras() != null){
            movieSearched = getIntent().getExtras().getString(MainActivity.MOVIE_KEY);
        }

        url = "http://www.omdbapi.com/?type=movie&s="+movieSearched;

        new GetMovieAsyncTask(this).execute(url);
    }
}
