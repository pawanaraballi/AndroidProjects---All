package hw_04.group5.com.imdbapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;

/**
 * Created by pawan on 2/26/2016.
 */

public class GetMovieAsyncTask extends AsyncTask<String, Void, ArrayList<Movie>> {

    ProgressDialog progressDialog;
    SearchMovie searchMovie;
    final static String CLICKED_DES = "CLICK";

    public GetMovieAsyncTask(SearchMovie searchMovie){this.searchMovie = searchMovie;}
    @Override
    protected ArrayList<Movie> doInBackground(String... params) {
        try {
            URL url = new URL(params[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            int statusCode = connection.getResponseCode();
            if (statusCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line = reader.readLine();
                while (line != null){
                    stringBuilder.append(line);
                    line = reader.readLine();
                }
                Log.d("demo", stringBuilder.toString());
                return MovieUtil.JSONParseMovie.parseMovie(stringBuilder.toString());
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(final ArrayList<Movie> movies) {
        super.onPostExecute(movies);
        if(movies != null) {
            Log.d("demo",movies.toString());
        }
        //dynamic layout for scroll view and linear layout
        ScrollView scrollView = (ScrollView) searchMovie.findViewById(R.id.scrollViewid);
        LinearLayout linearLayout = (LinearLayout) searchMovie.findViewById(R.id.linearlayoutid);
        linearLayout.setOrientation(LinearLayout.VERTICAL);


        for (int i = 0; i < movies.size() ; i++) {
            TextView tv = new TextView(searchMovie.getApplicationContext());
            tv.setTextColor(Color.BLACK);
            tv.setText(movies.get(i).getTitle());
            final int finalI1 = i;
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent inte = new Intent(searchMovie, MovieDetailsActivity.class);
                    inte.putExtra(CLICKED_DES, movies.get(finalI1).getImdbID());
                    Log.d("clicked",movies.get(finalI1).getImdbID());
                    searchMovie.startActivity(inte);
                }
            });
            linearLayout.addView(tv);
        }
        //scrollView.addView(linearLayout);
        progressDialog.dismiss();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        progressDialog = new ProgressDialog(searchMovie);

        progressDialog.setMessage("Loading...");
        progressDialog.show();
        progressDialog.setCancelable(false);
    }
}


