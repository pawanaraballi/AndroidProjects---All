package inclass05.group5.com.inclass_05;

import android.app.ProgressDialog;
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
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);


    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (s != null){
            Log.d("demo", s);
            final String[] photoID = s.split(";");
            Log.d("demo","PhotoID"+photoID[0]);
            for(String m:photoID)
            {
                String token = "";
                int n = m.indexOf(",");
                if (n>0) {
                     token = m.substring(0, n);
                }
                Log.d("demo","Token :" + n);
                if(token.equals("UNCC"))
                {
                    MainActivity.UNCC.add(m.substring(n + 1, m.length()));
                }
                else if(token.equals("Android"))
                {
                    MainActivity.ANDROID.add(m.substring(n + 1, m.length()));
                }
                else if(token.equals("winter"))
                {
                    MainActivity.WINTER.add(m.substring(n + 1, m.length()));
                }
                else if(token.equals("aurora"))
                {
                    MainActivity.AURORA.add(m.substring(n + 1, m.length()));
                }
                else if(token.equals("wonders"))
                {
                    MainActivity.AURORA.add(m.substring(n + 1, m.length()));
                }
            }
            Log.d("demo",MainActivity.UNCC.size()+"");
            //Log.d("demo","First element " + RequestParams.arrayList.get(0));

        }else {
            Log.d("demo","Null Data");
        }
    }
}
