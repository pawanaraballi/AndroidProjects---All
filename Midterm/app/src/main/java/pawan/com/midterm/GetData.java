package pawan.com.midterm;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Pawan on 3/21/2016.
 */
public class GetData extends AsyncTask<String, Void, ArrayList<Venue>> {
    IData iData;
    ProgressDialog progressDialog;

    public GetData(IData iData) {
        this.iData = iData;
    }

    @Override
    protected ArrayList<Venue> doInBackground(String... params) {
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
                Log.d("jsonStringBuilder", stringBuilder.toString());
                return VenueUtil.JSONParseVenue.parseVenue(stringBuilder.toString());
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
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(iData.getContext());
        progressDialog.setMessage("Loading Data...");
        progressDialog.show();
        progressDialog.setCancelable(false);
    }

    @Override
    protected void onPostExecute(ArrayList<Venue> venues) {
        super.onPostExecute(venues);
        iData.setupData(venues);
        progressDialog.dismiss();
    }

    static public interface IData{
        public void setupData(ArrayList<Venue> strings);

        public Context getContext();
    }
}
