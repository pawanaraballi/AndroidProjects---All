package inclass05.group5.com.inclass05;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by pawan on 2/15/2016.
 */
public class GetPhotoIDs extends AsyncTask<String,Void,String> {

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
                //RequestParams.arrayList.add(line);
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
            //Log.d("demo","First element " + RequestParams.arrayList.get(0));

        }else {
            Log.d("demo","Null Data");
        }
    }
}
