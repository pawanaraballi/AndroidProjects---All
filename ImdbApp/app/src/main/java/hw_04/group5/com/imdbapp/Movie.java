package hw_04.group5.com.imdbapp;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by pawan on 2/26/2016.
 */
public class Movie {

    String title, year, imdbID, poster, released, genre, director, actors, plot, imdbRating;

    public Movie() {}

    public Movie(String title, String year, String imdbID, String poster, String released, String genre, String director, String actors, String plot, String imdbRating) {
        this.title = title;
        this.year = year;
        this.imdbID = imdbID;
        this.poster = poster;
        this.released = released;
        this.genre = genre;
        this.director = director;
        this.actors = actors;
        this.plot = plot;
        this.imdbRating = imdbRating;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", year='" + year + '\'' +
                ", imdbID='" + imdbID + '\'' +
                ", poster='" + poster + '\'' +
                ", released='" + released + '\'' +
                ", genre='" + genre + '\'' +
                ", director='" + director + '\'' +
                ", actors='" + actors + '\'' +
                ", plot='" + plot + '\'' +
                ", imdbRating='" + imdbRating + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    public Movie(String title, String year, String imdbID, String poster) {
        this.title = title;
        this.year = year;
        this.imdbID = imdbID;
        this.poster = poster;
    }

    static public Movie getMovieDetails(JSONObject jsonObject) throws JSONException, ParseException {
        Movie movie = new Movie();
        movie.setTitle(jsonObject.getString("Title"));
        movie.setPlot(jsonObject.getString("Plot"));
        movie.setGenre(jsonObject.getString("Genre"));
        movie.setDirector(jsonObject.getString("Director"));
        movie.setActors(jsonObject.getString("Actors"));
        movie.setReleased(jsonObject.getString("Released"));
        movie.setImdbRating(jsonObject.getString("imdbRating"));
        return movie;
    }
}
