package hw_04.group5.com.imdbapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class MovieDetailsActivity extends AppCompatActivity {
    ImageView imageView;
    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        setTitle("Movie Details");

        imageView = (ImageView) findViewById(R.id.imageView);
        Button finishButton = (Button) findViewById(R.id.button_finish);
        ImageButton previousButton = (ImageButton) findViewById(R.id.imageButton_previous);
        ImageButton forwardButton = (ImageButton) findViewById(R.id.imageButton_forward);
        TextView movieName = (TextView) findViewById(R.id.textView_movieName);
        TextView releaseDate = (TextView) findViewById(R.id.textView_releaseDate);
        TextView genre = (TextView) findViewById(R.id.textView_genre);
        TextView director = (TextView) findViewById(R.id.textView_director);
        TextView actors = (TextView) findViewById(R.id.textView_actors);
        TextView plot = (TextView) findViewById(R.id.textView_plot);
        RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar);


        // Comment : Get the imdbID and the poster from previous activity and set those in below lines.
        String imdbID = null;
        String poster = null;

        if (getIntent().getExtras() != null){
            imdbID = getIntent().getExtras().getString(GetMovieAsyncTask.CLICKED_DES);
        }

        new FetchMovieDetailsAsyncTask(this).execute("http://www.omdbapi.com/?i=" + imdbID);
        new GetImage().execute(poster);

        //ratingBar.setNumStars();

        final String finalImdbID = imdbID;
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MovieDetailsActivity.this, MoviewebviewActivity.class);
                intent.putExtra("", finalImdbID);
                startActivity(intent);
            }
        });

        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(MovieDetailsActivity.this,SearchMovieActivity.class);
                finish();
                //startActivity(intent);
            }
        });

        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter--;
            }
        });

        forwardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter++;
            }
        });

    }

    //ASYNC task to get images
    class GetImage extends AsyncTask<String, Void, Bitmap> {
        Bitmap image = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            imageView.setImageBitmap(image);
        }

        @Override
        protected Bitmap doInBackground(String... urls) {
            InputStream inputStream = null;
            String urlDisplay = urls[0];
            try {
                inputStream = new java.net.URL(urlDisplay).openStream();
                image = BitmapFactory.decodeStream(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return image;
        }
    }
}
