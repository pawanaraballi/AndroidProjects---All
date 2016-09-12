package inclass05.group5.com.inclass05;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;



public class MainActivity extends AppCompatActivity{

    public static final String photoListURL = "http://dev.theappsdr.com/apis/spring_2016/inclass5/URLs.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Button buttongo = (Button) findViewById(R.id.button_go);
        EditText editTextinput = (EditText) findViewById(R.id.editText_searchkeyword);
        ImageButton imageButtonleft = (ImageButton) findViewById(R.id.imageButton_left);
        ImageButton imageButtonright = (ImageButton) findViewById(R.id.imageButton_right);
        ImageView imageViewcontent = (ImageView) findViewById(R.id.imageView_displayimage);

        buttongo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new GetImage().execute("http://dev.theappsdr.com/lectures/inclass_photos/index.php?pid=r6GRtY98sM");
            }
        });


    }


    public boolean isConnected()
    {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnected()){
            return true;
        }
        return false;
    }
}
