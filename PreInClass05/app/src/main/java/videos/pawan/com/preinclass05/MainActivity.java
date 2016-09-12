package videos.pawan.com.preinclass05;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity{



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new GetData().execute("http://dev.theappsdr.com/lectures/inclass_photos/index.php");
        RequestParams requestParams = new RequestParams("GET","http://dev.theappsdr.com/lectures/inclass_photos/index.php");
        new GetDataWithParams().execute(requestParams);

        Button btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isConnectedOnline()) {
                    Toast.makeText(MainActivity.this, "Internet is available", Toast.LENGTH_LONG).show();
                    //RequestParams requestParams = new RequestParams("GET","http://dev.theappsdr.com/lectures/inclass_photos/index.php");
                    //new GetDataWithParams().execute(requestParams);
                    //new GetImage().execute("http://dev.theappsdr.com/lectures/inclass_photos/index.php?pid=r6GRtY98sM");


                } else {
                    Toast.makeText(MainActivity.this, "Internet is not available", Toast.LENGTH_LONG).show();
                }
            }
        });

        //new GetTweetAsyncTask(this).execute("http://dev.theappsdr.com/lectures/inclass_photos/index.php");
    }

    private class GetData extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

            BufferedReader bufferedReader = null;

            try {
                URL url = new URL(params[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET"); // connection.setRequestMethod("POST");
                //start connection
                //connection.getInputStream()
                bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line = "";
                while ((line = bufferedReader.readLine()) != null){
                    stringBuilder.append(line + "\n");
                    RequestParams.arrayList.add(line);
                }
                return stringBuilder.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                try {
                    if (bufferedReader != null){
                        bufferedReader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            if (s != null){
                Log.d("Demo",s);
                Log.d("demo","First element " + RequestParams.arrayList.get(0));

            }else {
                Log.d("demo","Null Data");
            }
        }
    }

    /*public void setupData(LinkedList<String> data){
        ListView listView = (ListView) findViewById(R.id.listView);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,android.R.id.text1,data);
        listView.setAdapter(arrayAdapter);
    }

    @Override
    public Context getContext() {
        return null;
    }*/

    private class GetDataWithParams extends AsyncTask<RequestParams,Void,String>{

        @Override
        protected String doInBackground(RequestParams... params) {

            BufferedReader bufferedReader = null;

            try {
                HttpURLConnection connection = params[0].setupConnection();
                bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line = "";
                while ((line = bufferedReader.readLine()) != null){
                    stringBuilder.append(line + "\n");
                    RequestParams.arrayList.add(line);
                }
                return stringBuilder.toString();

            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    if (bufferedReader != null){
                        bufferedReader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            if (s != null){

                Log.d("demo", s);

            }else {
                Log.d("demo", "Null Data");
            }
        }
    }

    private class GetImage extends AsyncTask<String,Void,Bitmap>{

        @Override
        protected Bitmap doInBackground(String... params) {

            BufferedReader bufferedReader = null;
            InputStream inputStream = null;

            try {
                URL url = new URL(params[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET"); // connection.setRequestMethod("POST");
                inputStream = connection.getInputStream();
                Bitmap image = BitmapFactory.decodeStream(inputStream);
                return image;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if(inputStream != null){
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap s) {
            if (s != null){
                ImageView imageView = (ImageView) findViewById(R.id.imageView);
                imageView.setImageBitmap(s);
            }
        }
    }


    private boolean isConnectedOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        //networkInfo.getType()
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        }
        return false;
    }
}