package inclass05.group5.com.inclass05;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;

/**
 * Created by pawan on 2/15/2016.
 */


class GetDictionaryAsyncTask extends AsyncTask<String, Void, String> {

    IData mainActivity;

    public GetDictionaryAsyncTask(IData mainActivity) {
        this.mainActivity = mainActivity;
    }

    LinkedList<String> inputdata = new LinkedList<String>();



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
                    inputdata.add(line);
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
                Log.d("demo","First element " + inputdata.get(0));

            }else {
                Log.d("demo","Null Data");
            }
        }

    static public interface IData{
        public void setupData(String strings);

        public Context getContext();
    }

    }

