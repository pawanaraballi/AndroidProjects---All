package videos.pawan.com.httpdemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isConnectedOnline()){
                    Toast.makeText(MainActivity.this,"Internet is available",Toast.LENGTH_LONG).show();
                    //new GetData().execute("http://rss.cnn.com/rss/cnn_topstories.rss");
                    //new GetImage().execute("https://www.google.com/logos/doodles/2016/valentines-day-2016-5699846440747008-5096695956242432-ror.gif");
                    RequestParams requestParams = new RequestParams("GET","http://dev.theappsdr.com/lectures/inclass_photos/index.php");
                    requestParams.addParams("Key1","Value1");
                    requestParams.addParams("Key2","Value2");
                    requestParams.addParams("Key3","Value3");
                    requestParams.addParams("Key4","Value4");
                    new GetDataWithParams().execute(requestParams);
                }
                else {
                    Toast.makeText(MainActivity.this,"Internet is not available",Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    private class GetData extends AsyncTask<String,Void,String>{

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
            }else {
                Log.d("demo","Null Data");
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
                Log.d("Demo",s);
            }else {
                Log.d("demo", "Null Data");
            }
        }
    }



    private boolean isConnectedOnline(){
        ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        //networkInfo.getType()
        if(networkInfo != null && networkInfo.isConnected()){
            return true;
        }
        return false;
    }
}
