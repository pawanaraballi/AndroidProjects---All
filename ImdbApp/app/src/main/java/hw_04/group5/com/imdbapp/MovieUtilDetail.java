package hw_04.group5.com.imdbapp;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;

/**
 * Created by Pawan on 2/28/2016.
 */
public class MovieUtilDetail {
    static public class MoviesJSONParser {
        static ArrayList<Movie> parseMovieDetails(String in) throws JSONException, ParseException {
            ArrayList<Movie> movieDetailsList = new ArrayList<>();
            JSONObject jsonObject = new JSONObject(in);
            Movie movie = Movie.getMovieDetails(jsonObject);
            movieDetailsList.add(movie);
            Log.d("MovieList....", movieDetailsList.toString());
            return movieDetailsList;
        }
    }
}


