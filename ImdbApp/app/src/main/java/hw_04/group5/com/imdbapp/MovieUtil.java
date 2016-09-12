package hw_04.group5.com.imdbapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by pawan on 2/26/2016.
 */
public class MovieUtil {

    static public class JSONParseMovie{
        ArrayList<Movie> movieList;
        Movie movie;

        static ArrayList<Movie> parseMovie(String in) throws JSONException {
            ArrayList<Movie> movieList = new ArrayList<>();

            JSONObject root = new JSONObject(in);
            JSONArray movieJSONarray = root.getJSONArray("Search");

            String title,year,imdbid,poster;

            Log.d("demo",movieJSONarray.toString());
            Log.d("demo length",movieJSONarray.length()+"");

            for (int i = 0; i < movieJSONarray.length(); i++) {
                JSONObject movieJSONObj = movieJSONarray.getJSONObject(i);
                Log.d("inner demo",movieJSONObj.getString("Title"));
                if (movieJSONObj.getString("Title").length() != 0 && movieJSONObj.getString("Year").length() != 0 &&
                movieJSONObj.getString("imdbID").length() != 0 && movieJSONObj.getString("Poster").length() != 0){
                    title = movieJSONObj.getString("Title");
                    year = movieJSONObj.getString("Year");
                    imdbid = movieJSONObj.getString("imdbID");
                    poster = movieJSONObj.getString("Poster");

                    Movie mv = new Movie(title,year,imdbid,poster);
                    movieList.add(mv);
                }
            }
            return movieList;
        }

    }
}
