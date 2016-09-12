package hw_04.group5.com.imdbapp;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    final static String MOVIE_KEY = "MOVIE";
    EditText movieName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ImageView image = new ImageView(this);
        image.setImageBitmap(BitmapFactory.decodeFile("imdb_main.jpg"));


        movieName = (EditText) findViewById(R.id.editText_MovieName);
        Button search = (Button) findViewById(R.id.button_Search);



        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String movie = movieName.getText().toString();
                Intent inte = new Intent(MainActivity.this, SearchMovie.class);
                inte.putExtra(MOVIE_KEY, movie);
                Log.d("demoMain", movie);
                startActivity(inte);
            }
        });
    }
}
