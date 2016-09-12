package hw_04.group5.com.imdbapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class MoviewebviewActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moviewebview);
        setTitle("Movie WebView");


        // Comment : Get the imdbID from the previous activity and set it on below line.
        String imdbId = null;
        String url = "http://m.imdb.com/title/" + imdbId;
        WebView webView = (WebView) findViewById(R.id.webView);
        webView.loadUrl(url);
    }
}
