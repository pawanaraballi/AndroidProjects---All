package hw_04.group5.com.imdbapp;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.RatingBar;
import android.widget.TextView;

import org.json.JSONException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;

/**
 * Created by sujit on 2/27/2016.
 */
public class FetchMovieDetailsAsyncTask extends AsyncTask<String, Void, ArrayList<Movie>> {
    MovieDetailsActivity movieDetails;
    ProgressDialog progressDialog;

    public FetchMovieDetailsAsyncTask(MovieDetailsActivity movieDetails) {
        this.movieDetails = movieDetails;
    }

    @Override
    protected ArrayList<Movie> doInBackground(String... params) {
        URL url;
        try {
            url = new URL(params[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            Log.d("demo",url.toString());
            int statusCode = connection.getResponseCode();
            Log.d("demo", "Status code" + String.valueOf(statusCode));
            if(statusCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line = reader.readLine();
                while(line!= null) {
                    stringBuilder.append(line);
                    line = reader.readLine();
                }
                Log.d("Demo..", stringBuilder.toString());
                return MovieUtilDetail.MoviesJSONParser.parseMovieDetails(stringBuilder.toString());
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(movieDetails);
        progressDialog.setMessage("Loading Movie");
        progressDialog.show();
        progressDialog.setCancelable(false);

    }

    @Override
    protected void onPostExecute(ArrayList<Movie> movies) {
        super.onPostExecute(movies);
        TextView movieName = (TextView) movieDetails.findViewById(R.id.textView_movieName);
        TextView releaseDate = (TextView) movieDetails.findViewById(R.id.textView_releaseDate);
        TextView genre = (TextView) movieDetails.findViewById(R.id.textView_genre);
        TextView director = (TextView) movieDetails.findViewById(R.id.textView_director);
        TextView actors = (TextView) movieDetails.findViewById(R.id.textView_actors);
        TextView plot = (TextView) movieDetails.findViewById(R.id.textView_plot);
        RatingBar ratingBar = (RatingBar) movieDetails.findViewById(R.id.ratingBar);

        //Log.d("detail size", String.valueOf(movies.size()));

        movieName.setText(movies.get(0).getTitle());
        releaseDate.setText(movies.get(0).getReleased());
        genre.setText(movies.get(0).getGenre());
        director.setText(movies.get(0).getDirector());
        actors.setText(movies.get(0).getActors());
        plot.setText(movies.get(0).getPlot());
        ratingBar.setNumStars(3);
        progressDialog.dismiss();
    }
}
