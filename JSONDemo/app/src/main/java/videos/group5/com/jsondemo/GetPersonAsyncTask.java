package videos.group5.com.jsondemo;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.xmlpull.v1.XmlPullParserException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by pawan on 2/22/2016.
 */
public class GetPersonAsyncTask extends AsyncTask<String, Void, ArrayList<Person>> {
    @Override
    protected ArrayList<Person> doInBackground(String... params) {
        try {
            URL url = new URL(params[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            int statusCode = connection.getResponseCode();
            if (statusCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line = reader.readLine();
                while (line != null){
                    stringBuilder.append(line);
                    line = reader.readLine();
                }
                Log.d("demo",stringBuilder.toString());
                return PersonUtil.JSONParseperson.parsePerson(stringBuilder.toString());
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<Person> persons) {
        super.onPostExecute(persons);
        if(persons != null) {
            Log.d("demo",persons.toString());
        }
    }
}
